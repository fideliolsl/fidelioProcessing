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

package org.lsl.fidelio.processing;

import org.jcodec.api.FrameGrab;
import org.lsl.fidelio.processing.util.ImageFilter;
import org.lsl.fidelio.processing.util.Utils;
import org.lsl.fidelio.processing.util.VideoFilter;
import org.lsl.fidelio.processing.util.ui.FileImagePreview;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

public class VideoUI {

    private static JFileChooser mFileChooserRefImg = new JFileChooser();

    public static void main(String[] args) {
        VideoUI videoUI = new VideoUI();
    }

    public VideoUI() {
//        createFileDialog();
//        openFileDialog(mFileChooserRefImg);
        loadVideo(new File("/Users/jonas/Dropbox/Fidelio/Ergebnisse/1.jpg"));
    }

    private void createFileDialog() {
        mFileChooserRefImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
        mFileChooserRefImg.addChoosableFileFilter(new ImageFilter());
        mFileChooserRefImg.setAcceptAllFileFilterUsed(false);
        mFileChooserRefImg.setAccessory(new FileImagePreview(mFileChooserRefImg));

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
                    loadVideo(file);
                }
            }
        } else {
            // User aborted action
            System.out.println("Aborted");
        }
    }

    public void loadVideo(File file) {
        try {
            int globalAbsolut = -1;
            BufferedImage image = ImageIO.read(file);
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 8; j < image.getWidth() - 40; j++) {
                    int clr = image.getRGB(j, i);
                    int absolute = (clr & 0x00ff0000) >> 16;
                    if(globalAbsolut == -1){
                        globalAbsolut = absolute;
                    }else{
                        globalAbsolut = (globalAbsolut + absolute) / 2;
                    }
                    if(absolute >= 70){
                        System.out.println(String.format("X: %1$d Y: %2$d", j,i));
                    }
                }
            }
            System.out.println(globalAbsolut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
