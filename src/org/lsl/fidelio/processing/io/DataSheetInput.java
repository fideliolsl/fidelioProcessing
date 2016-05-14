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

package org.lsl.fidelio.processing.io;

import org.lsl.fidelio.processing.util.ui.FileImagePreview;

import javax.swing.*;
import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by jonasdrotleff on 14/05/16.
 */
public class DataSheetInput {

    private static JFileChooser mFileChooser = new JFileChooser();

    /**
     * @author Jonas Drotleff
     *
     * Model:
     * azHeidelberg, hHeidelberg, deltaHeidelberg, azDarmstadt, hDarmstadt, deltaDarmstadt
     * ...
     */

    public DataSheetInput(){
        mFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        mFileChooser.setAccessory(new FileImagePreview(mFileChooser));
    }

    private static void openFileDialog() {
        int returnVal = mFileChooser.showOpenDialog(new JPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = mFileChooser.getSelectedFile();
            if (!file.exists()) {
                System.out.println("File does not exist");
            } else {
                if (mFileChooser == mFileChooser) {
                    System.out.println(read(file)[0]);
                }
            }
        } else {
            // User aborted action
            System.out.println("Aborted");
        }
    }

    public static int[][] read(File file){
        try {
            InputStream streamLineNumber = new FileInputStream(file);
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(streamLineNumber));
            lineNumberReader.skip(Long.MAX_VALUE);
            int lines = lineNumberReader.getLineNumber();

            InputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringTokenizer stringTokenizer;

            int[][] value = new int[lines][6];

            for (int i = 0; i < lines; i++) {

                String line = bufferedReader.readLine();
                // line = azHeidelberg, hHeidelberg, deltaHeidelberg, azDarmstadt, hDarmstadt, deltaDarmstadt

                for (int j = 0; j < value.length; j++) {
                    stringTokenizer = new StringTokenizer(line, ",");
                    value[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            streamLineNumber.close();
            lineNumberReader.close();
            inputStream.close();
            bufferedReader.close();
            return value;
        }catch (Exception e){
            e.printStackTrace();
            return new int[1][6];
        }
    }

}
