package org.lsl.fidelio.processing.reference;

/**
 * Created by Jonas Drotleff on 28.01.2016.
 */
public class CalculateZenitNorth {

    private static double[] distancesPixel = new double[3];

    public static double pixelPerDegreeeRatio(int[][] coordinates, double[] distances) {
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

        return pixelperheight;
    }

    public static double getZenit(int[][] coordinates, double[] distancesZenit, double[] distancesStar, double[] azStar) {

        // coordinates = {{Xa, Ya},{Xb, Yb},{Xc, Yc}}
        // distancesZenit = {a,b,c} (distances to zenith in degree)
        // distancesStar = {ab, ac, bc}
        // azStar = {azA, azB, azC}

        double a = distancesZenit[0];
        double b = distancesZenit[1];
        double c = distancesZenit[2];

        int xA = coordinates[0][0];
        int yA = coordinates[0][1];

        int xB = coordinates[1][0];
        int yB = coordinates[1][1];

        int xC = coordinates[2][0];
        int yC = coordinates[2][1];

        int dXAB = xB - xA;
        int dYAB = yB - yA;

        double AB = distancesStar[0];
        double AC = distancesStar[1];
        double BC = distancesStar[2];

        double azA = azStar[0];
        double azB = azStar[1];
        double azC = azStar[2];

        double[][][] zenit = new double[3][2][2];
        // zenit = {
        // {{xZAB1, yZAB1},{xZAB2, yZAB2}},
        // {{xZAC1, yZAC1},{xZAC2, yZAC2}},
        // {{xZBC1, yZBC1},{xZBC2, yZBC2}}
        // }

        zenit[0][0][0] = xA + a * Math.cos(Math.acos(dXAB / AB) + Math.acos( (1-)));

        return zenit[0][0][0];
    }
}
