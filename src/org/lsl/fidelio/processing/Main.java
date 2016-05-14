package org.lsl.fidelio.processing;

import org.lsl.fidelio.processing.reference.ReferenceUI;
import org.lsl.fidelio.processing.util.Utils;

import java.io.*;
import java.nio.file.Files;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean loadLast = Utils.initProperties();
        char[] lastEdit = null;
        if (loadLast) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.propertyFile));
                String s = bufferedReader.readLine();
                lastEdit = new char[s.length() - 1];
                s.getChars(1, s.length(), lastEdit, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String file = Utils.getProperty(Utils.KEY_LASTFILE);
            loadLast = file != null && !file.isEmpty();
            // if last image does not exist
            if (loadLast) {
                int n = JOptionPane.showConfirmDialog(
                        new JFrame(),
                        "Open last reference image?\n Date:" + String.copyValueOf(lastEdit),
                        "Open last file?",
                        JOptionPane.YES_NO_OPTION);
                if (n != 0) {
                    loadLast = false;
                }
            }
        }
        ReferenceUI ref = new ReferenceUI(loadLast);
    }

    public static void saveExit() {
        System.exit(0);
    }


}
