package com.fidelio.processing.io;

import com.fidelio.processing.objects.Distance;
import com.fidelio.processing.objects.Star;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jonasdrotleff on 27.12.16.
 */
public class State {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_FILE = "file";
    private static final String KEY_STARS = "stars";
    private static final String KEY_DISTANCES = "distances";

    private String id;
    private String name;
    private Date date;
    private File file;
    private List<Star> stars = new ArrayList<Star>();
    private List<Distance> distances = new ArrayList<Distance>();

    public State(String id, String name, Date date, File file, List<Star> stars, List<Distance> distances) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.file = file;
        this.stars = stars;
        this.distances = distances;
    }

    public State() {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public Element getElementDOM() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element state = doc.createElement("state");
        state.setAttribute(KEY_ID, id);
        doc.appendChild(state);

        Element name = doc.createElement(KEY_NAME);
        name.setTextContent(this.name);
        state.appendChild(name);

        Element date = doc.createElement(KEY_DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("GMT");
        date.setTextContent(dateFormat.format(this.date));
        state.appendChild(date);

        Element file = doc.createElement(KEY_FILE);
        file.setTextContent(this.file.toString());
        state.appendChild(file);

        Element stars = doc.createElement(KEY_STARS);
        state.appendChild(stars);

        for (int i = 0; i < this.stars.size(); i++) {
            state.appendChild(this.stars.get(i).getElementDOM());
        }

        Element distances = doc.createElement(KEY_DISTANCES);
        state.appendChild(distances);

        for (int i = 0; i < this.distances.size(); i++) {

        }

        return state;
    }

    public void loadElementDOM(Element element) {
        this.id = element.getAttribute(KEY_ID);
        this.name = element.getElementsByTagName(KEY_NAME).item(0).getTextContent();

        SimpleDateFormat format = new SimpleDateFormat("GMT");
        try {
            this.date = format.parse(element.getElementsByTagName(KEY_DATE).item(0).getTextContent());
        } catch (Exception e) {
            // TODO error while parsing date
            e.printStackTrace();
        }
        this.file = new File(element.getElementsByTagName(KEY_FILE).item(0).getTextContent());

        NodeList nodeList = element.getElementsByTagName(KEY_STARS).item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Star star = new Star();
            star.loadElementDOM((Element) nodeList.item(i));
            stars.add(star);
        }
    }

    /* TODO:
     * - addStar
     * - removeStarById
     * - getStarById
     * - setStarById
     * - getStar (index)
     * - setStar (index)
     */
}
