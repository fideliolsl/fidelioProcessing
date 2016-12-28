package com.fidelio.processing.objects;

/**
 * Created by jonasdrotleff on 28.12.16.
 */
public class Distance {
    private Star star1;
    private String star2;
    private double distanceSky;
    private double distanceImg;

    public Distance(Star star1, String star2, double distanceSky, double distanceImg) {
        this.star1 = star1;
        this.star2 = star2;
        this.distanceSky = distanceSky;
        this.distanceImg = distanceImg;
    }

    public Distance() {
    }

    public Star getStar1() {
        return star1;
    }

    public void setStar1(Star star1) {
        this.star1 = star1;
    }

    public String getStar2() {
        return star2;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public double getDistanceSky() {
        return distanceSky;
    }

    public void setDistanceSky(double distanceSky) {
        this.distanceSky = distanceSky;
    }

    public double getDistanceImg() {
        return distanceImg;
    }

    public void setDistanceImg(double distanceImg) {
        this.distanceImg = distanceImg;
    }
}
