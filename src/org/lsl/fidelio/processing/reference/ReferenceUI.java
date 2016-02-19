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

import org.lsl.fidelio.processing.util.*;
import org.lsl.fidelio.processing.util.ui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.rmi.CORBA.Util;
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
    private StarDistances starDistancesPanel;
    private JButton btnAbort;
    private JButton btnNext;
    private JMenuItem menuItemFileOpen;
    private static ImagePanel previewPanel;
    private JScrollPane scrollPanePreview;

    private static boolean imgLoaded = false;
    private static boolean[] selectStar = {false, false, false};

    private static JFileChooser mFileChooserRefImg = new JFileChooser();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ReferenceUI frame = new ReferenceUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ReferenceUI() {
        super("FIDELIO Analysis - Reference Image");
        createContents();
        Utils.loadProperties();
    }

    private void createContents() {

        setMinimumSize(new Dimension(720, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 499, 473);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

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
        rightControlPanel.add(btnNext);

        scrollPanePreview = new JScrollPane();
        scrollPanePreview.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanePreview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPanePreview, BorderLayout.CENTER);

        String file = Utils.getProperty(Utils.KEY_LASTFILE);
        if (file != null && !file.isEmpty()) {
            System.out.println("loaded image: " + file);
            previewPanel = new ImagePanel(new File(file));
            scrollPanePreview.setViewportView(previewPanel);
            imgLoaded = true;
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.EAST);

        JPanel starControlPanel = new JPanel();
        starControlPanel.setBorder(new EmptyBorder(0, 0, 0, scrollPane.getVerticalScrollBar().getPreferredSize().width));
        starControlPanel.setLayout(new BoxLayout(starControlPanel, BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(starControlPanel);

        star1Panel = new StarPanel(0);
        starControlPanel.add(star1Panel);

        star2Panel = new StarPanel(1);
        starControlPanel.add(star2Panel);

        star3Panel = new StarPanel(2);
        starControlPanel.add(star3Panel);

        starPanels[0] = star1Panel;
        starPanels[1] = star2Panel;
        starPanels[2] = star3Panel;

        starDistancesPanel = new StarDistances();
        starControlPanel.add(starDistancesPanel);

        createMenu();
        createFileDialog();
        initListeners();

//        pack();
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
        menuFile.add(menuItemFileOpen);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    private void initListeners() {
        previewPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectStar[0] || selectStar[1] || selectStar[2]) {
                    updateCoordinates(e.getX(), e.getY());
                }
            }
        });
    }

    private void openFileDialog(JFileChooser jFileChooser) {
        int returnVal = jFileChooser.showOpenDialog(new JPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (!file.exists()) {
                System.out.println("File does not exist");
                JOptionPane.showMessageDialog(new JFrame(), "Sorry. Something went wrong, try again.");
            } else {
                if (jFileChooser == mFileChooserRefImg) {
                    System.out.println("Applying Image");
                    scrollPanePreview.removeAll();
                    previewPanel = new ImagePanel(file);
                    scrollPanePreview.setViewportView(previewPanel);
                    scrollPanePreview.repaint();
                    scrollPanePreview.revalidate();
                    imgLoaded = true;
                    Utils.setPropery(Utils.KEY_LASTFILE, file.toString());
                }
            }
        } else {
            // User aborted action
            System.out.println("Aborted");
        }
    }

    public static void warningDialog(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public static void changeCoordinates(int index) {
        if (imgLoaded) {
            selectStar = new boolean[3];
            selectStar[index] = true;
            previewPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            warningDialog("Open image first (File > Open)");
        }
    }

    public static void updateCoordinates(int x, int y) {
        int starIndex = Utils.getIndexOfValue(selectStar, true);
        if (x > previewPanel.getImageWidth() || y > previewPanel.getImageHeight()) {
            selectStar = new boolean[3];
            warningDialog("Error: Out of bounds!");
            previewPanel.setCursor(Cursor.getDefaultCursor());
        }else{
            previewPanel.setPosition(x, y, starIndex);
            starPanels[starIndex].setCoordinates(x, y);
            if(!previewPanel.isVisible(starIndex)){
                previewPanel.setVisible(true, starIndex);
            }
        }
    }

    // Listeners
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbort) {
            dispose();
        } else if (e.getSource() == menuItemFileOpen) {
            openFileDialog(mFileChooserRefImg);

        } else if (e.getSource() == btnNext) {
            // TODO: calculate necessary value.
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
