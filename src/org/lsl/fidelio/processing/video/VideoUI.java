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

package org.lsl.fidelio.processing.video;

import org.lsl.fidelio.processing.Main;
import org.lsl.fidelio.processing.util.Utils;
import org.lsl.fidelio.processing.util.ui.VideoPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Created by jonas on 17.3.16.
 */
public class VideoUI extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btnAbort;
    private JButton btnNext;
    private JScrollPane previewPanel;
    private VideoPanel videoPreview;
    private JButton btnNextFrame;
    private JButton btnPreviousFrame;
    private JButton btnPlayVideo;
    private JButton btnPauseVideo;

    private static JFileChooser mFileChooserRefImg = new JFileChooser();

    public static void main(String[] args) {
        VideoUI videoUI = new VideoUI();
        videoUI.setVisible(true);
    }

    public VideoUI() {
        super("Video");
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

        previewPanel = new JScrollPane();
        previewPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        previewPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(previewPanel, BorderLayout.CENTER);

        JPanel cntrlPanelLeft = new JPanel();
        cntrlPanelLeft.setLayout(new BorderLayout(0,0));
        contentPane.add(cntrlPanelLeft, BorderLayout.WEST);

        JPanel cntrlPanelRight = new JPanel();
        cntrlPanelRight.setLayout(new BorderLayout(0,0));
        contentPane.add(cntrlPanelRight, BorderLayout.EAST);

        btnNextFrame = new JButton("Next");
        cntrlPanelRight.add(btnNextFrame, BorderLayout.NORTH);

        btnPreviousFrame = new JButton("Previous");
        cntrlPanelRight.add(btnPreviousFrame, BorderLayout.SOUTH);

        btnPlayVideo = new JButton("Play");
        cntrlPanelLeft.add(btnPlayVideo, BorderLayout.NORTH);

        btnPauseVideo = new JButton("Pause");
        cntrlPanelLeft.add(btnPauseVideo, BorderLayout.SOUTH);

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
        videoPreview = new VideoPanel(file, btnNextFrame, btnPreviousFrame, btnPlayVideo, btnPauseVideo);
        previewPanel.setViewportView(videoPreview);
        Utils.setProperty(Utils.KEY_LASTFILE, file.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openFileDialog(mFileChooserRefImg);
    }
}
