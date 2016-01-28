package org.lsl.fidelio.processing.reference;

import org.lsl.fidelio.processing.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

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
    private JTextField mAzimos2;
    private JTextField mAlt2;
    private JLabel mCoordinates2;

    private JPanel mStarContainer3;
    private JButton mChangePosition3;
    private JTextField mAzimos3;
    private JTextField mAlt3;
    private JLabel mCoordinates3;

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
        mFileChooserRefImg.setAccessory(new ImagePreview(mFileChooserRefImg));

    }

    public UserInterfaceReference() {
        mOpenRefImage.addActionListener(this);
        mChangePosition1.addActionListener(this);
        mChangePosition2.addActionListener(this);
        mChangePosition3.addActionListener(this);
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

    public void changeCoordinates(int x, int y) {
        if (ImagePanel.imgWidth < x || ImagePanel.imgHeight < y) {
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
                img.setPosition1(x, y);
                if (!img.isVisible1()) {
                    img.setVisible1(true);
                }
            }
            if (mStar == mStarContainer2) {
                mCoordinates2.setText("X: " + x + " Y: " + y);
                starArray[2] = x;
                starArray[3] = y;
                img.setPosition2(x, y);
                if (!img.isVisible2()) {
                    img.setVisible2(true);
                }
            }
            if (mStar == mStarContainer3) {
                mCoordinates3.setText("X: " + x + " Y: " + y);
                starArray[4] = x;
                starArray[5] = y;
                img.setPosition3(x, y);
                if (!img.isVisible3()) {
                    img.setVisible3(true);
                }
            }
            frame.setCursor(Frame.DEFAULT_CURSOR);
            selectStar = false;
            mRefImg.repaint();
        }
    }
}
