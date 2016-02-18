package org.lsl.fidelio.processing.util.ui.pointers;

import java.awt.*;

/**
 * Created by jonas on 18.02.16.
 */
public class Star {
    public Star(Graphics g, Color color, String description, int[] position) {
        g.setColor(color);
        int x = position[0];
        int y = position[1];
        g.drawOval(x - 10, y - 10, 20, 20);
        g.drawLine(x, y + 10, x, y - 10);
        g.drawLine(x - 10, y, x + 10, y);
        g.drawString(description, x + 15, y + 15);

    }
}
