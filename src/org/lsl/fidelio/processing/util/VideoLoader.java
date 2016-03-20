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

package org.lsl.fidelio.processing.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jonas on 19.3.16.
 */

public class VideoLoader {

    private int totalFrames; //data.length/(width*height)
    private static final int width = 720;
    private static final int height = 480;

    public int getTotalFrames() {
        return totalFrames;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    BufferedImage[] video;

    private static byte[] loadByteArray(String name) throws IOException {
        File file = new File(name);
        int length = (int) file.length();

        FileInputStream in = new FileInputStream(file);
        byte data[] = new byte[length];
        in.read(data, 0, length);
        in.close();

        return data;
    }

    private static short readUSByte(byte[] data, int offset) {
        return (short) (data[offset] & 0xff);
    }

    private static BufferedImage bytearrToBWImg(byte[] data, int width,
                                               int height) {

        int x = 0, y = height - 1;

        int l = 0;

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0; i < width * height; i++) {
            l = readUSByte(data, i);

            image.setRGB(x, y, (l << 16) | (l << 8) | l);

            x++;
            if (x == width) {
                x = 0;
                y--;
            }
        }

        return image;
    }

    private static byte[] splitByteArray(byte[] array, int i1, int i2) {
        byte[] tmp = new byte[i2 - i1];

        System.arraycopy(array, i1, tmp, 0, i2 - i1);

        return tmp;
    }

    public VideoLoader(String file) throws IOException {
        byte[] data = loadByteArray(file);
        totalFrames = data.length / (width * height);

        video = new BufferedImage[totalFrames];

        for (int i = 0; i < video.length; i++) {
            video[i] = bytearrToBWImg(
                    splitByteArray(data, i * width * height, (i + 1) * width
                            * height), width, height);
        }
    }

    public BufferedImage[] getImageArray() {
        return video;
    }



}
