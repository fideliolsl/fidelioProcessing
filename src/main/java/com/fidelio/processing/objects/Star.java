package com.fidelio.processing.objects;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Star {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSITION_IMG = "position-image";
    private static final String KEY_POSITION_IMG_X = "x";
    private static final String KEY_POSITION_IMG_Y = "y";
    private static final String KEY_POSITION_SKY = "position-sky";
    private static final String KEY_POSITION_SKY_AZ = "az";
    private static final String KEY_POSITION_SKY_HEIGHT = "height";
    private static final String KEY_DISTANCES = "distances";
    private static final String KEY_DISTANCE = "distance";

    public String id;

    public String name;

    public double[] positionOnImage = new double[2];
    // x,y

    public String getid() {
        return id;
    }

    public double[] positionOnSky = new double[2];
    // az, height

    Object[][] distances = new Object[2][];
    // id of second star, distance (double)


    public Star(String id, String name, double[] positionOnImage, double[] positionOnSky, Object[][] distances) {
        this.id = id;
        this.name = name;
        this.positionOnImage = positionOnImage;
        this.positionOnSky = positionOnSky;
        this.distances = distances;
    }

    public Star() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void loadElementDOM(Element element) {
        this.id = element.getAttribute(KEY_ID);
        this.name = element.getElementsByTagName(KEY_NAME).item(0).getTextContent();

        Element positionImg = (Element) element.getElementsByTagName(KEY_POSITION_IMG).item(0);
        this.positionOnImage[0] = Double.valueOf(positionImg.getElementsByTagName(KEY_POSITION_IMG_X).item(0).getTextContent());
        this.positionOnImage[1] = Double.valueOf(positionImg.getElementsByTagName(KEY_POSITION_IMG_Y).item(0).getTextContent());

        Element positionSky = (Element) element.getElementsByTagName(KEY_POSITION_SKY).item(0);
        this.positionOnSky[0] = Double.valueOf(positionSky.getElementsByTagName(KEY_POSITION_SKY_AZ).item(0).getTextContent());
        this.positionOnSky[1] = Double.valueOf(positionSky.getElementsByTagName(KEY_POSITION_SKY_HEIGHT).item(0).getTextContent());

        Element distances = (Element) element.getElementsByTagName(KEY_DISTANCES).item(0);
        NodeList distanceNodeList = distances.getElementsByTagName(KEY_DISTANCE);
        for (int i = 0; i < distanceNodeList.getLength(); i++) {
            Element distance = (Element) distanceNodeList.item(i);
            // TODO add distances
        }
    }

    public Element getElementDOM() {
        return null;
    }

}
