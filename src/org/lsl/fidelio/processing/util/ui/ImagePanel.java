package org.lsl.fidelio.processing.util.ui;

import org.lsl.fidelio.processing.reference.UserInterfaceReference;
import org.lsl.fidelio.processing.util.ui.pointers.Star;
import org.lsl.fidelio.processing.util.ui.pointers.StarDistancesPointer;
import org.lsl.fidelio.processing.util.ui.pointers.Zenith;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Jonas Drotleff on 15.12.2015.
 */
public class ImagePanel extends JPanel {

    public static int STAR_1 = 0;
    public static int STAR_2 = 1;
    public static int STAR_3 = 2;
    public static int ZENITH = 3;

    int[][] position = {
            {0, 0},          // Star 1
            {0, 0},          // Star 2
            {0, 0},          // Star 3
            {0, 0}           // Zenith
    };
    boolean[] visibility = {
            false, false, false, false
            //      Star1, Star2, Star3, Zenith
    };

    double RotationRadian = 0;
    BufferedImage image;
    Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    Color color = Color.CYAN;

    public ImagePanel(File file) {
        try {
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            UserInterfaceReference.warningDialog("Cannot load image");
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            g.setFont(font);
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(0);
            g.drawImage(image, 0, 0, (int) image.getWidth(), (int) image.getHeight(),
                    this);
            if (visibility[3]) {
                new Zenith(g, color, "z", 0, position[3]);
            }
            if (visibility[0]) {
                new Star(g, color, "0", position[0]);
            }
            if (visibility[1]) {
                new Star(g, color, "1", position[1]);
            }
            if (visibility[2]) {
                new Star(g, color, "2", position[2]);

            }
            if(visibility[0] && visibility[1]){
                new StarDistancesPointer(g, color, "0 - 1", position[0], position[1]);
            }
            if(visibility[0] && visibility[2]){
                new StarDistancesPointer(g, color, "0 - 2", position[0], position[2]);
            }
            if(visibility[1] && visibility[2]){
                new StarDistancesPointer(g, color, "1 - 2", position[1], position[2]);
            }
        }
    }

    public int getImageHeight(){
        return image.getHeight();
    }

    public int getImageWidth(){
        return image.getWidth();
    }

    public void setVisible(boolean visible, int index) {
        visibility[index] = visible;
        repaint();
    }

    public boolean isVisible(int index) {
        return visibility[index];
    }

    public void setPosition(int x, int y, int index) {
        int[] i = {x, y};
        position[index]=i;
        repaint();
    }

    public void rotate(double degree) {
        RotationRadian = Math.toRadians(degree);
        repaint();
    }

}
