package com.fidelio.processing.objects;

public class Star {

    public String id;

    public double[] positionOnImage = new double[2];
    // x,y

    public String getid() {
        return id;
    }

    public double[] positionOnSky = new double[2];
    // az, height

    Object[][] distances = new Object[2][];
    // id of second star, distance (double)

    public Star(String id, double[] positionOnImage, double[] positionOnSky, Object[][] distances) {
        this.id = id;
        this.positionOnImage = positionOnImage;
        this.positionOnSky = positionOnSky;
        this.distances = distances;
    }

    public void setid(String id) {
        this.id = id;
    }

    public double[] getPositionOnImage() {
        return positionOnImage;
    }

    public void setPositionOnImage(double[] positionOnImage) {
        this.positionOnImage = positionOnImage;
    }

    public double[] getPositionOnSky() {
        return positionOnSky;
    }

    public void setPositionOnSky(double[] positionOnSky) {
        this.positionOnSky = positionOnSky;
    }

    public Object[][] getDistances() {
        return distances;
    }

    public void setDistances(Object[][] distances) {
        this.distances = distances;
    }

}
