package org.lsl.fidelio.processing.util;

import org.lsl.fidelio.processing.reference.UserInterfaceReference;

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
    private BufferedImage image;

    public static float imgHeight;
    public static float imgWidth;

    private int xz = 0;
    private int yz = 0;
    private boolean VisibleZ = false;

    private int x1 = 0;
    private int y1 = 0;
    private boolean Visible1 = false;

    private int x2 = 0;
    private int y2 = 0;
    private boolean Visible2 = false;

    private int x3 = 0;
    private int y3 = 0;
    private boolean Visible3 = false;

    double RotationRadian = 0;
    Font test = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    private Color color = Color.CYAN;

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

        if (image != null
                && image.getHeight() != this.getHeight()
                && image.getWidth() != this.getWidth()) {

            imgHeight = image.getHeight();
            imgWidth = image.getWidth();
            float imgRatioHW = imgHeight / imgWidth;

            float panelHeight = this.getHeight();
            float panelWidth = this.getWidth();
            float panelRatioHW = panelHeight / panelWidth;

            if (panelRatioHW >= imgRatioHW) {
                if (panelWidth >= imgWidth) {
                    //dont andjust image (max size reached)
                } else {
                    // Using width as baseline
                    imgWidth = panelWidth;
                    imgHeight = imgRatioHW * imgWidth;
                }
            } else {
                if (panelHeight >= imgHeight) {
                    //dont andjust image (max size reached)
                } else {
                    // Using height as baseline
                    imgHeight = panelHeight;
                    imgWidth = imgRatioHW * imgHeight;
                }
            }
            g.setFont(test);
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(0);
            g.drawImage(image, 0, 0, (int) imgWidth, (int) imgHeight,
                    this);
            if (VisibleZ) {
                paintZenith(g);
            }
            if (Visible1) {
                paintStar1(g);
            }
            if (Visible2) {
                paintStar2(g);
            }
            if (Visible3) {
                paintStar3(g);
            }
        }
    }

    private void paintStar1(Graphics g) {
        g.setColor(color);
        g.drawOval(x1 - 10, y1 - 10, 20, 20);
        g.drawLine(x1, y1 + 10, x1, y1 - 10);
        g.drawLine(x1 - 10, y1, x1 + 10, y1);
        g.drawString("1", x1 + 15, y1 + 15);
    }

    private void paintStar2(Graphics g) {
        g.setColor(color);
        g.drawOval(x2 - 10, y2 - 10, 20, 20);
        g.drawLine(x2, y2 + 10, x2, y2 - 10);
        g.drawLine(x2 - 10, y2, x2 + 10, y2);
        g.drawString("2", x2 + 15, y2 + 15);
    }

    private void paintStar3(Graphics g) {
        g.setColor(color);
        g.drawOval(x3 - 10, y3 - 10, 20, 20);
        g.drawLine(x3, y3 + 10, x3, y3 - 10);
        g.drawLine(x3 - 10, y3, x3 + 10, y3);
        g.drawString("3", x3 + 15, y3 + 15);
    }

    private void paintZenith(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(RotationRadian);
        g.setColor(color);
        g.drawOval(xz - 10, yz - 10, 20, 20);
        g.drawLine(xz, yz + 10, xz, yz);
        g.drawString("z", xz + 15, yz + 15);
    }

    public void setVisible1(boolean visible) {
        Visible1 = visible;
        repaint();
    }

    public void setVisible2(boolean visible) {
        Visible2 = visible;
        repaint();
    }

    public void setVisible3(boolean visible) {
        Visible3 = visible;
        repaint();
    }

    public void setVisibleZ(boolean visible) {
        VisibleZ = visible;
        repaint();
    }

    public boolean isVisibleZ() {
        return VisibleZ;
    }

    public boolean isVisible1() {
        return Visible1;
    }

    public boolean isVisible2() {
        return Visible2;
    }

    public boolean isVisible3() {
        return Visible3;
    }

    public void setPosition1(int x, int y) {
        x1 = x;
        y1 = y;
        repaint();
    }

    public void setPosition2(int x, int y) {
        x2 = x;
        y2 = y;
        repaint();
    }

    public void setPosition3(int x, int y) {
        x3 = x;
        y3 = y;
        repaint();
    }

    public void setPositionZ(int x, int y) {
        xz = x;
        yz = y;
        repaint();
    }

    public void rotate(double degree) {
        RotationRadian = Math.toRadians(degree);
        repaint();
    }

}
