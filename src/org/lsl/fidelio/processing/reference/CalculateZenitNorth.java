package org.lsl.fidelio.processing.reference;

/**
 * Created by Jonas Drotleff on 28.01.2016.
 */
public class CalculateZenitNorth {

    double[] distancesPixel = new double[3];

    public CalculateZenitNorth(int[][] coordinates, double[] distances) {

        distancesPixel[0] = Math.sqrt(
                Math.pow(coordinates[0][0] - coordinates[1][0], 2) +
                        Math.pow(coordinates[0][1] - coordinates[1][1], 2)
        );

        distancesPixel[1] = Math.sqrt(
                Math.pow(coordinates[0][0] - coordinates[2][0], 2) +
                        Math.pow(coordinates[0][1] - coordinates[2][1], 2)
        );

        distancesPixel[2] = Math.sqrt(
                Math.pow(coordinates[1][0] - coordinates[2][0], 2) +
                        Math.pow(coordinates[1][1] - coordinates[2][1], 2)
        );

        double pixelperheight = ((distancesPixel[0] / distances[0]) + (distancesPixel[1] / distances[1]) + (distancesPixel[2] / distances[2])) / 3;

        System.out.println("Pixel per Height°: " + pixelperheight);

        ReferenceUI.setCircles(pixelperheight);

    }
}
