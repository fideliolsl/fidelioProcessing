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

    public static double[][] getZenit(int[][] coordinates, double[] distancesZenit, double[] azStar) {

        double a = 90-distancesZenit[0];
        double b = 90-distancesZenit[1];

        int xA = coordinates[0][0];
        int yA = coordinates[0][1];

        int xB = coordinates[1][0];
        int yB = coordinates[1][1];

        int dXAB = xB - xA;
        int dYAB = yB - yA;

        double AB = Math.sqrt(Math.pow(coordinates[0][0] - coordinates[1][0], 2) +
                Math.pow(coordinates[0][1] - coordinates[1][1], 2));

        double azA = Math.toRadians(azStar[0]);
        double azB = Math.toRadians(azStar[1]);


        double[][] zenit = new double[2][2];

        double i = phi(dYAB, dXAB, AB, a, b, azA, azB);
        double in = phin(dYAB, dXAB, AB, a, b, azA, azB);

        System.out.println(i);
        System.out.println(in);

        //AB
        zenit[0][0] = xA + (AB/Math.sqrt(K(b/a, Math.abs(azB-azA))))* Math.cos(i); // 410
        zenit[0][1] = yA + (AB/Math.sqrt(K(b/a, Math.abs(azB-azA))))* Math.sin(i); // 199

        zenit[1][0] = xA + (AB/Math.sqrt(K(b/a, Math.abs(azB-azA))))* Math.cos(in);
        zenit[1][1] = yA + (AB/Math.sqrt(K(b/a, Math.abs(azB-azA))))* Math.sin(in);

        return zenit;
    }

    public static double phi(double dy, double dx, double d, double aw, double bw, double alphaA, double alphaB){
        double g = bw/aw;
        double dalpha = Math.abs(alphaB-alphaA);
        return  signum(dy) *
                Math.acos(dx/d) +
                Math.acos((1-g*Math.cos(dalpha))/Math.sqrt(K(g, dalpha)));
    }
    public static double phin(double dy, double dx, double d, double aw, double bw, double alphaA, double alphaB){
        double g = bw/aw;
        double dalpha = Math.abs(alphaB-alphaA);
        return  signum(dy) *
                Math.acos(dx/d) -
                Math.acos((1-g*Math.cos(dalpha))/Math.sqrt(K(g, dalpha)));
    }

    public static double signum(double f) {
        return (f >= 0) ? 1 : -1;
    }

    public static double K(double g, double da){
        System.out.println(Math.pow(g, 2) - 2 * g * Math.cos(da) + 1);
        return Math.pow(g, 2) - 2 * g * Math.cos(da) + 1;
    }

}
