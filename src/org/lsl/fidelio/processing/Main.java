package org.lsl.fidelio.processing;

import org.lsl.fidelio.processing.reference.ReferenceUI;
import org.lsl.fidelio.processing.util.Utils;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        char[] lastEdit = null;
        boolean loadLast = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.propertyFile));
            String s = bufferedReader.readLine();
            System.out.println(s);
            lastEdit = new char[s.length()-1];
            s.getChars(1, s.length(), lastEdit,0);
        }catch (Exception e){
            System.err.println(e);
        }String file = Utils.getProperty(Utils.KEY_LASTFILE);
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


}
