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
import org.lsl.fidelio.processing.util.ui.ImagePanel;
import org.lsl.fidelio.processing.util.ui.FileImagePreview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by Jonas Drotleff on 12.12.2015.
 */
public class UserInterfaceReference implements ActionListener {

    private JPanel contentPane;

    private JButton mOpenRefImage;
    private JButton mClearRefImg;
    private JPanel mRefImg;

    private JPanel mSettingsPanel;

    private JPanel mStarContainer1;
    private JButton mChangePosition1;
    private JTextField mAz1;
    private JTextField mAlt1;
    private JLabel mCoordinates1;

    private JPanel mStarContainer2;
    private JButton mChangePosition2;
    private JTextField mAz2;
    private JTextField mAlt2;
    private JLabel mCoordinates2;

    private JPanel mStarContainer3;
    private JButton mChangePosition3;
    private JTextField mAz3;
    private JTextField mAlt3;
    private JLabel mCoordinates3;
    private JTextField mStar1d2;
    private JTextField mStar1d3;
    private JTextField mStar2d3;
    private JButton mButtonNext;
    private JButton button1_1;
    private JButton button1_2;
    private JButton button2_1;
    private JButton button2_2;
    private JButton button3_1;
    private JButton button3_2;
    private JButton button7;

    private ImagePanel img;

    private static JFileChooser mFileChooserRefImg = new JFileChooser();
    private static JFrame frame;

    private static boolean selectStar = false;
    private static boolean imgLoaded = false;
    private JPanel mStar;

    private int[] starArray = new int[6];

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        frame = new JFrame("UserInterfaceReference");
        frame.setContentPane(new UserInterfaceReference().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(650, 360));

        // setup file chooser
        mFileChooserRefImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
        mFileChooserRefImg.addChoosableFileFilter(new ImageFilter());
        mFileChooserRefImg.setAcceptAllFileFilterUsed(false);
        mFileChooserRefImg.setAccessory(new FileImagePreview(mFileChooserRefImg));

    }

    public UserInterfaceReference() {
        mOpenRefImage.addActionListener(this);
        mChangePosition1.addActionListener(this);
        mChangePosition2.addActionListener(this);
        mChangePosition3.addActionListener(this);
        mButtonNext.addActionListener(this);
        mRefImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectStar) {
                    changeCoordinates(e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mOpenRefImage) {
            openFileDialog(mFileChooserRefImg);
        }
        if (e.getSource() == mChangePosition1 || e.getSource() == mChangePosition2 || e.getSource() == mChangePosition3) {
            if (imgLoaded) {
                JButton obj = (JButton) e.getSource();
                mStar = (JPanel) obj.getParent();
                selectStar = true;
                frame.setCursor(Frame.CROSSHAIR_CURSOR);
            }
        }
        if (e.getSource() == mButtonNext) {
            if (isComplete()) {
                CalculateZenithNorth czn = new CalculateZenithNorth(
                        starArray,
                        Integer.parseInt(mStar1d2.getText()),
                        Integer.parseInt(mStar1d3.getText()),
                        Integer.parseInt(mStar2d3.getText()),
                        Integer.parseInt(mAlt1.getText()),
                        Integer.parseInt(mAz1.getText()),
                        Integer.parseInt(mAlt2.getText()),
                        Integer.parseInt(mAz2.getText()),
                        Integer.parseInt(mAlt3.getText()),
                        Integer.parseInt(mAz3.getText()));
            } else {
                warningDialog("Fill in all text fields!");
            }
        }
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
                if (jFileChooser == mFileChooserRefImg) {
                    System.out.println("Applying Image");
                    mRefImg.removeAll();
                    img = new ImagePanel(file);
                    img.setVisible(true);

                    mRefImg.add(img);
                    mRefImg.repaint();
                    mRefImg.revalidate();
                    imgLoaded = true;
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

    private boolean isComplete() {
        boolean coordinatesSet = true;
        for (int i = 0; i < starArray.length; i++) {
            if (starArray[i] == 0) {
                coordinatesSet = false;
            } else if (coordinatesSet) {
                coordinatesSet = true;
            }
        }
        return Utils.isNumeric(mStar1d2.getText()) &&
                Utils.isNumeric(mStar1d3.getText()) &&
                Utils.isNumeric(mStar2d3.getText()) &&

                Utils.isNumeric(mAz1.getText()) &&
                Utils.isNumeric(mAlt1.getText()) &&

                Utils.isNumeric(mAz2.getText()) &&
                Utils.isNumeric(mAlt2.getText()) &&

                Utils.isNumeric(mAz3.getText()) &&
                Utils.isNumeric(mAlt3.getText()) &&
                coordinatesSet;
    }

    public void changeCoordinates(int x, int y) {
        if (img.getWidth() < x || img.getHeight() < y) {
            if (mStar == mStarContainer1) {
                mCoordinates1.setText("Error: Out of bounds");
            }
            if (mStar == mStarContainer2) {
                mCoordinates2.setText("Error: Out of bounds");
            }
            if (mStar == mStarContainer3) {
                mCoordinates3.setText("Error: Out of bounds");
            }

        } else {
            if (mStar == mStarContainer1) {
                mCoordinates1.setText("X: " + x + " Y: " + y);
                starArray[0] = x;
                starArray[1] = y;
                img.setPosition(x, y, ImagePanel.STAR_1);
                if (!img.isVisible(ImagePanel.STAR_1)) {
                    img.setVisible(true, ImagePanel.STAR_1);
                }
            }
            if (mStar == mStarContainer2) {
                mCoordinates2.setText("X: " + x + " Y: " + y);
                starArray[2] = x;
                starArray[3] = y;
                img.setPosition(x, y, ImagePanel.STAR_2);
                if (!img.isVisible(ImagePanel.STAR_2)) {
                    img.setVisible(true, ImagePanel.STAR_2);
                }
            }
            if (mStar == mStarContainer3) {
                mCoordinates3.setText("X: " + x + " Y: " + y);
                starArray[4] = x;
                starArray[5] = y;
                img.setPosition(x, y, ImagePanel.STAR_3);
                if (!img.isVisible(ImagePanel.STAR_3)) {
                    img.setVisible(true, ImagePanel.STAR_3);
                }
            }
            frame.setCursor(Frame.DEFAULT_CURSOR);
            selectStar = false;
            mRefImg.repaint();
        }
    }
}
