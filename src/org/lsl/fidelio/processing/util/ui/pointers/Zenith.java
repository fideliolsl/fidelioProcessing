package org.lsl.fidelio.processing.util.ui.pointers;

import java.awt.*;

/**
 * Created by jonas on 18.02.16.
 */
public class Zenith {
    public Zenith(Graphics g, Color color, String description, int rotation, int[] position) {
        int x = position[0];
        int y = position[1];
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(rotation);
        g.setColor(color);
        g.drawOval(x - 10, y - 10, 20, 20);
        g.drawLine(x, y + 10, x, y);
        g.drawString(description, x + 15, y + 15);
    }
}
