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

package org.lsl.fidelio.processing.util.ui;

import org.lsl.fidelio.processing.util.Utils;
import org.lsl.fidelio.processing.util.VideoLoader;
import org.lsl.fidelio.processing.util.ui.pointers.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoPanel extends JPanel implements ActionListener {

    int[][] coordinates;
    int frameCount = 0;

    boolean isRunning = false;

    BufferedImage[] video;
    Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    Color color = Color.CYAN;

    final int width = 720;
    final int height = 480;

    JButton n;
    JButton p;
    JButton btnStart;
    JButton btnStop;

    Timer playBack = new Timer(1000 / 25, this);

    public VideoPanel(File file, JButton next, JButton previous, JButton start, JButton stop) {
        try {
            VideoLoader videoLoader = new VideoLoader(file.toString());
            video = videoLoader.getImageArray();
            coordinates = new int[video.length][2];
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Utils.warningDialog("Cannot load image");
        }
        n = next;
        p = previous;
        btnStart = start;
        btnStop = stop;
        n.addActionListener(this);
        p.addActionListener(this);
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (video[frameCount] != null) {
            BufferedImage img = new BufferedImage(width, height,
                    BufferedImage.TYPE_BYTE_GRAY);
            if(frameCount < -1){
                int x = 0, y = height - 1;

                for (int i = 0; i < width * height; i++) {

                    int c1 = video[frameCount].getRGB(x, y);
                    int c2 = video[frameCount +1].getRGB(x,y);

                    int c3 = c1-c2;
                    if(c3 < 0){
                        c3 = 0;
                    }

                    img.setRGB(x,y, c3);

                    x++;
                    if (x == width) {
                        x = 0;
                        y--;
                    }
                }
            }else{
                img = video[frameCount];
            }
            setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
            g.setFont(font);
            g.setColor(color);
            g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(),
                    this);
            g.drawString(String.format("Frame %d / %d (+1)", frameCount, video.length-1), 20, 20);
            //new Star(g, color, String.valueOf(frameCount), coordinates[frameCount]);
        }
    }

    int getImageHeight() {
        return video[1].getHeight();
    }

    int getImageWidth() {
        return video[1].getWidth();
    }

    void setPosition(int x, int y, int index) {
        int[] i = {x, y};
        coordinates[index] = i;
        repaint();
    }

    int[] getPosition() {
        int[] i = {0, 0};
        //TODO subtract images, get meteor position (pixel)!
        return i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(n)) {
            if (frameCount < video.length - 1) {
                frameCount++;
                repaint();
            }else{
                frameCount=0;
            }
        } else if (e.getSource().equals(p)) {
            if (frameCount > 0) {
                frameCount--;
                repaint();
            }else{
                frameCount=video.length-1;
            }
        } else if (e.getSource().equals(btnStart)) {
            playBack.start();
            System.out.println("start");
        } else if (e.getSource().equals(btnStop)) {
            playBack.stop();
            System.out.println("stop");
        } else if (e.getSource().equals(playBack)) {
            frameCount = (frameCount < video.length - 1) ? frameCount+1 : 0;
            System.out.println(frameCount);
            repaint();
        }
    }
}
