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

import org.lsl.fidelio.processing.util.ImageFilter;
import org.lsl.fidelio.processing.util.ui.FileImagePreview;
import org.lsl.fidelio.processing.util.ui.StarDistances;
import org.lsl.fidelio.processing.util.ui.StarPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ReferenceUI extends JFrame implements ActionListener, DocumentListener {

    private JPanel contentPane;
    private StarPanel star1;
    private StarPanel star2;
    private StarPanel star3;
    private StarDistances starDistances;
    private JButton btnAbort;
    private JButton btnNext;
    private JMenuItem menuItemFileOpen;

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
                    frame.setVisible(true);
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

        JScrollPane scrollPanePreview = new JScrollPane();
        contentPane.add(scrollPanePreview, BorderLayout.CENTER);

        JPanel previewPanel = new JPanel();
        scrollPanePreview.setViewportView(previewPanel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.EAST);

        JPanel starControlPanel = new JPanel();
        starControlPanel.setBorder(new EmptyBorder(0, 0, 0, scrollPane.getVerticalScrollBar().getPreferredSize().width));
        starControlPanel.setLayout(new BoxLayout(starControlPanel, BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(starControlPanel);

        star1 = new StarPanel(1);
        starControlPanel.add(star1);

        star2 = new StarPanel(2);
        starControlPanel.add(star2);

        star3 = new StarPanel(3);
        starControlPanel.add(star3);

        starDistances = new StarDistances();
        starControlPanel.add(starDistances);

        createMenu();
        createFileDialog();

    }

    private void createFileDialog(){
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

    private void openFileDialog(JFileChooser jFileChooser) {
        System.out.println("Open Dialog");
        int returnVal = jFileChooser.showOpenDialog(new JPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (!file.exists()) {
                System.out.println("File does not exist");
                JOptionPane.showMessageDialog(new JFrame(), "Sorry. Something went wrong, try again.");
            } else {
                /*if (jFileChooser == mFileChooserRefImg) {
                    System.out.println("Applying Image");
                    mRefImg.removeAll();
                    img = new ImagePanel(file);
                    img.setVisible(true);

                    mRefImg.add(img);
                    mRefImg.repaint();
                    mRefImg.revalidate();
                    imgLoaded = true;
                }*/
            }
        } else {
            // User aborted action
            System.out.println("Aborted");
        }
    }

    public static void warningDialog(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
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
