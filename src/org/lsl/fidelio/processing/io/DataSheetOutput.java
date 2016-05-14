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
 */

package org.lsl.fidelio.processing.io;

import java.io.*;
public class DataSheetOutput {

    private static OutputStream outputStream;
    private static BufferedWriter bufferedWriter;

    public DataSheetOutput(File file){
        try {
            outputStream = new FileOutputStream(file);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write(int[][] data) throws IOException {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                String output = String.valueOf(data[i][j]);
                if (j + 1 == data[i].length){
                    output = output + "\n";
                }else {
                    output = output + ",";
                }
                bufferedWriter.write(output);
            }
        }
    }

    public static void close() throws IOException {
        outputStream.close();
        bufferedWriter.close();
    }

}
