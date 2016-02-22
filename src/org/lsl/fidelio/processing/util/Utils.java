package org.lsl.fidelio.processing.util;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * ---
 *
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

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import org.lsl.fidelio.processing.reference.ReferenceUI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.swing.*;

public class Utils {

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String png = "png";
    public final static String avi = "avi";
    public final static String mov = "mov";

    private static Properties prop = new Properties();
    private static OutputStream outputStream = null;
    private static InputStream inputStream = null;

    public static Path projectDirectory = Paths.get(System.getProperty("user.home") + File.separator + ".fidelio");
    public static File propertyFile = new File(projectDirectory + File.separator + "config.properties");

    public static String KEY_LASTFILE = "last_file";
    public static String[] KEY_POSITIONS = {"star0xy", "star1xy", "star2xy"};
    public static String[][] KEY_STARPARAMS = {
            {"az0", "Height0"},
            {"az1", "height1"},
            {"az2", "height2"}
    };
    public static String[] KEY_DISTANCES = {"distance01", "distance02", "distance12"};

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static public String numberFormatHelper(String string, double d) {
        if (d == (int) d) {
            return String.format(string + " %d", (int) d);
        } else {
            return String.format(string + " %s", new DecimalFormat("#.##").format(d));
        }
    }

    public static void loadProperties() {
        if (Files.notExists(projectDirectory)) {
            projectDirectory.toFile().mkdir();
        }
        if (Files.notExists(propertyFile.toPath())) {
            try {
                outputStream = new FileOutputStream(propertyFile.toString());
                prop.setProperty(KEY_LASTFILE, "");
                prop.store(outputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
                warningDialog("Error while creating property file.");
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void setPropery(String key, String value) {
        try {
            outputStream = new FileOutputStream(propertyFile.toString());
            prop.setProperty(key, value);
            prop.store(outputStream, null);
        } catch (Exception e) {
            e.printStackTrace();
            warningDialog("Error while creating property file.");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        String s = null;
        try {
            inputStream = new FileInputStream(propertyFile);
            prop.load(inputStream);
            s = prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }

    public static void warningDialog(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public static int getIndexOfValue(boolean[] array, boolean value) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }
}
