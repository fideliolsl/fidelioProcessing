package org.lsl.fidelio.processing.util;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Jonas Drotleff on 15.12.2015.
 */
public class PointerStar extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        setBounds(0, 0, 20, 20);
    }


    public void setPosition(int x, int y) {
        setBounds(x, y, 20, 20);
    }

}
