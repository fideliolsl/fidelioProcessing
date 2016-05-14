/*
 * Copyright 2015-2016 Jonas Drotleff for FIDELIO Project - Life Science Lab Heidelberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @see <a href="http://life-science-lab.org">life-science-lab.org</a>
 */

package org.lsl.fidelio.processing.reference;

import org.lsl.fidelio.processing.Main;
import org.lsl.fidelio.processing.util.*;
import org.lsl.fidelio.processing.util.ui.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ReferenceUI extends JFrame implements ActionListener, DocumentListener {

    private JPanel contentPane;
    private StarPanel star1Panel;
    private StarPanel star2Panel;
    private StarPanel star3Panel;
    private static StarPanel[] starPanels = new StarPanel[3];
    private static StarDistances starDistancesPanel;
    private JButton btnAbort;
    private JButton btnNext;
    private JMenuItem menuItemFileOpen;
    private JMenuItem menuItemFileSave;
    public static ImagePanel previewPanel;
    private JScrollPane scrollPanePreview;

    private static boolean imgLoaded = false;
    private static boolean[] selectStar = {false, false, false};
    private static boolean loadLast = false;

    // Old method
    private double pixelPerDegreeRatio = 0;

    private static JFileChooser mFileChooserRefImg = new JFileChooser();

    public ReferenceUI(boolean loadLastFile) {
        super("FIDELIO Analysis - Reference Image");
        this.loadLast = loadLastFile;
        createContents();
    }

    private void createContents() {
        setMinimumSize(new Dimension(720, 480));
        setPreferredSize(new Dimension(1120, 575));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 499, 473);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for (int i = 0; i < starPanels.length; i++) {
                    starPanels[i].saveValues();
                }
                Main.saveExit();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new MatteBorder(1, 0, 0, 0, SystemColor.windowBorder));
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.setLayout(new BorderLayout(0, 0));

        JPanel rightControlPanel = new JPanel();
        rightControlPanel.setBorder(null);
        controlPanel.add(rightControlPanel, BorderLayout.EAST);

        btnAbort = new JButton("Abort");
        btnAbort.addActionListener(this);
        rightControlPanel.add(btnAbort);

        btnNext = new JButton("Next");
        btnNext.addActionListener(this);
        rightControlPanel.add(btnNext);

        scrollPanePreview = new JScrollPane();
        scrollPanePreview.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanePreview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPanePreview, BorderLayout.CENTER);

        if (loadLast) {
            String file = Utils.getProperty(Utils.KEY_LASTFILE);
            if (file != null && !file.isEmpty()) {
                setUpImagePreview(new File(file));
            }
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.EAST);

        JPanel starControlPanel = new JPanel();
        starControlPanel.setBorder(new EmptyBorder(0, 0, 0, scrollPane.getVerticalScrollBar().getPreferredSize().width));
        starControlPanel.setLayout(new BoxLayout(starControlPanel, BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(starControlPanel);

        star1Panel = new StarPanel(0, loadLast);
        starControlPanel.add(star1Panel);

        star2Panel = new StarPanel(1, loadLast);
        starControlPanel.add(star2Panel);

        star3Panel = new StarPanel(2, loadLast);
        starControlPanel.add(star3Panel);

        starPanels[0] = star1Panel;
        starPanels[1] = star2Panel;
        starPanels[2] = star3Panel;

        starDistancesPanel = new StarDistances(loadLast);
        starControlPanel.add(starDistancesPanel);

        createMenu();
        createFileDialog();
        initListeners();

        pack();
        setVisible(true);

    }

    private void createFileDialog() {
        mFileChooserRefImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
        mFileChooserRefImg.addChoosableFileFilter(new ImageFilter());
        mFileChooserRefImg.setAcceptAllFileFilterUsed(false);
        mFileChooserRefImg.setAccessory(new FileImagePreview(mFileChooserRefImg));

    }

    private void createMenu() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        // make the menu appear in the Mac OS X system bar

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuItemFileOpen = new JMenuItem("Open file...");
        menuItemFileOpen.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItemFileOpen.addActionListener(this);

        menuItemFileSave = new JMenuItem("Save");
        menuItemFileSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItemFileSave.addActionListener(this);

        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileSave);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    private void initListeners() {
        if (imgLoaded) {
            previewPanel.addMouseListener(mouseAdapter);
        }
    }

    private void openFileDialog(JFileChooser jFileChooser) {
        int returnVal = jFileChooser.showOpenDialog(new JPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (!file.exists()) {
                System.out.println("File does not exist");
                Utils.warningDialog("Sorry. Something went wrong, try again.");
            } else {
                if (jFileChooser == mFileChooserRefImg) {
                    System.out.println("Applying Image");
                    setUpImagePreview(file);
                }
            }
        } else {
            // User aborted action
            System.out.println("Aborted");
        }
    }

    private void setUpImagePreview(File file) {
        System.out.println("loaded image: " + file);
        previewPanel = new ImagePanel(file);
        scrollPanePreview.setViewportView(previewPanel);
        if (!imgLoaded) {
            imgLoaded = true;
            initListeners();
        }
        Utils.setProperty(Utils.KEY_LASTFILE, file.toString());
    }

    public static void changeCoordinates(int index, boolean change) {
        if (imgLoaded) {
            if (change) {
                selectStar = new boolean[3];
                selectStar[index] = true;
                starPanels[0].changeBtnState(false);
                starPanels[1].changeBtnState(false);
                starPanels[2].changeBtnState(false);

                starPanels[index].changeBtnState(true);
                previewPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                selectStar = new boolean[3];
                starPanels[0].changeBtnState(true);
                starPanels[1].changeBtnState(true);
                starPanels[2].changeBtnState(true);
                previewPanel.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            Utils.warningDialog("Open image first (File > Open)");
        }
    }

    public static void updateCoordinates(int x, int y) {
        int starIndex = Utils.getIndexOfValue(selectStar, true);
        if (x > previewPanel.getImageWidth() || y > previewPanel.getImageHeight()) {
            selectStar = new boolean[3];
            starPanels[0].changeBtnState(true);
            starPanels[1].changeBtnState(true);
            starPanels[2].changeBtnState(true);
            Utils.warningDialog("Error: Out of bounds!");
            previewPanel.setCursor(Cursor.getDefaultCursor());

        } else {
            previewPanel.setPosition(x, y, starIndex);
            starPanels[starIndex].setCoordinates(x, y);
            if (!previewPanel.isVisible(starIndex)) {
                previewPanel.setVisible(true, starIndex);
            }
            Utils.setProperty(Utils.KEY_POSITIONS[starIndex], String.format("%1$s,%2$s", x, y));
        }
    }

    private void getZenit() {
        /*if (imgLoaded &&
                starPanels[0].isNumeric() &&
                starPanels[1].isNumeric() &&
                starPanels[2].isNumeric() &&
                starDistancesPanel.isNumeric() &&
                Utils.getIndexOfValue(previewPanel.visibility, false) == 3) {

            double[] distances = {starDistancesPanel.getAbsoluteDistance01(),
                    starDistancesPanel.getAbsoluteDistance02(),
                    starDistancesPanel.getAbsoluteDistance12()};

            double[] az = {starPanels[0].getAbsoluteAz(),
                    starPanels[1].getAbsoluteAz(),
                    starPanels[2].getAbsoluteAz()};

            //pixelPerDegreeRatio = CalculateZenitNorth.pixelPerDegreeeRatio(previewPanel.position, distances);

            double[][][] z = CalculateZenitNorth.getZenit(previewPanel.position, distances, az);

            previewPanel.setPosition((int)z[0][0][0], (int)z[0][0][1], 3);
            previewPanel.setPosition((int)z[0][1][0], (int)z[0][1][1], 4);

            previewPanel.setPosition((int)z[1][0][0], (int)z[0][0][1], 5);
            previewPanel.setPosition((int)z[1][1][0], (int)z[0][1][1], 6);

            previewPanel.setPosition((int)z[2][0][0], (int)z[0][0][1], 7);
            previewPanel.setPosition((int)z[2][1][0], (int)z[0][1][1], 8);

            for (int i = 3; i < 9; i++) {
            previewPanel.setVisible(true, i);
            }

            setZenit(pixelPerDegreeRatio);
        } else {
            Utils.warningDialog("Fill in every parameter!");
        }*/

        double[] distances = {starDistancesPanel.getAbsoluteDistance01(),
                starDistancesPanel.getAbsoluteDistance02(),
                starDistancesPanel.getAbsoluteDistance12()};

        double[] az = {starPanels[0].getAbsoluteAz(),
                starPanels[1].getAbsoluteAz(),
                starPanels[2].getAbsoluteAz()};

        double[][] z = CalculateZenitNorth.getZenit(previewPanel.position, distances, az);

        previewPanel.setPosition((int)z[0][0], (int)z[0][1], 3);
        previewPanel.setPosition((int)z[1][0], (int)z[1][1], 4);

        previewPanel.setVisible(true, 3);
        previewPanel.setVisible(true, 4);

    }

    private void setZenit(double ratio) {

        /*double[] radius = {
                (90 -starPanels[0].getAbsoluteHeight()) * ratio,
                (90 -starPanels[1].getAbsoluteHeight()) * ratio,
                (90 -starPanels[2].getAbsoluteHeight()) * ratio
        };
        previewPanel.setRadius(radius);
        previewPanel.setVisible(true, 4);
        VideoUI vid = new VideoUI();
        vid.setVisible(true);
        dispose();*/
    }

    // Listeners

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (selectStar[0] || selectStar[1] || selectStar[2]) {
                updateCoordinates(e.getX(), e.getY());
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbort) {
            for (int i = 0; i < starPanels.length; i++) {
                starPanels[i].saveValues();
            }
            Main.saveExit();
        } else if (e.getSource() == menuItemFileOpen) {
            openFileDialog(mFileChooserRefImg);

        } else if (e.getSource() == menuItemFileSave) {
            for (int i = 0; i < starPanels.length; i++) {
                starPanels[i].saveValues();
            }
            starDistancesPanel.saveValues();

        } else if (e.getSource() == btnNext) {
            getZenit();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
