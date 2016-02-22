package org.lsl.fidelio.processing;

import org.lsl.fidelio.processing.reference.ReferenceUI;
import org.lsl.fidelio.processing.util.Utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Utils.loadProperties();
        char[] lastEdit = null;
        boolean loadLast = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.propertyFile));
            String s = bufferedReader.readLine();
            lastEdit = new char[s.length() - 1];
            s.getChars(1, s.length(), lastEdit, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String file = Utils.getProperty(Utils.KEY_LASTFILE);
        if (file != null && !file.isEmpty()) {
            int n = JOptionPane.showConfirmDialog(
                    new JFrame(),
                    "Open last reference image?\n" +
                            "Date: " +
                            String.copyValueOf(lastEdit),
                    "Open last file?",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                loadLast = true;
            }
        }
        ReferenceUI ref = new ReferenceUI(loadLast);
    }

    public static void saveExit() {
        int n = JOptionPane.showConfirmDialog(
                new JFrame(),
                "Save open files?",
                "Save?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            System.exit(0);
        } else {
            try {
                Files.delete(Utils.propertyFile.toPath());
                Files.delete(Utils.projectDirectory);
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
