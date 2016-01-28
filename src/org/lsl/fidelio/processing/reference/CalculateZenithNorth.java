package org.lsl.fidelio.processing.reference;

/**
 * Created by Jonas Drotleff on 28.01.2016.
 */
public class CalculateZenithNorth {
    private int x1;
    private int y1;

    private int x2;
    private int y2;

    private int x3;
    private int y3;

    private double distance12pixel;
    private double distance13pixel;
    private double distance23pixel;

    private double pixelperheight;

    public CalculateZenithNorth(int[] coordinates, int distance12, int distance13, int distance23, int alt1, int az1, int alt2, int az2, int alt3, int al3) {
        x1 = coordinates[0];
        y1 = coordinates[1];

        x2 = coordinates[2];
        y2 = coordinates[3];

        x3 = coordinates[4];
        y3 = coordinates[5];

        // distance12pixel
        if (x1 > x2) {
            if (y1 > y2) {
                distance12pixel = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            } else {
                distance12pixel = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y2 - y1, 2));
            }
        } else {
            if (y1 > y2) {
                distance12pixel = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y1 - y2, 2));
            } else {
                distance12pixel = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            }
        }

        // distance13pixel
        if (x1 > x3) {
            if (y1 > y3) {
                distance13pixel = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
            } else {
                distance13pixel = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y3 - y1, 2));
            }
        } else {
            if (y1 > y3) {
                distance13pixel = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y1 - y3, 2));
            } else {
                distance13pixel = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
            }
        }

        // distance23pixel
        if (x2 > x3) {
            if (y2 > y3) {
                distance23pixel = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
            } else {
                distance23pixel = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y3 - y2, 2));
            }
        } else {
            if (y2 > y3) {
                distance23pixel = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y2 - y3, 2));
            } else {
                distance23pixel = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
            }
        }

        pixelperheight = ((distance12pixel / distance12) + (distance13pixel / distance13) + (distance23pixel / distance23)) / 3;

        System.out.println("Pixel per Height°: " + pixelperheight);

    }
}
