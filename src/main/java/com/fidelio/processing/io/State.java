package com.fidelio.processing.io;

import com.fidelio.processing.objects.Star;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jonasdrotleff on 27.12.16.
 */
public class State {

    private String id;
    private String name;
    private Date date;
    private File file;
    private List<Star> stars = new ArrayList<Star>();

    public State(String id, String name, Date date, File file, List<Star> stars) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.file = file;
        this.stars = stars;
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

    /* TODO:
     * - addStar
     * - removeStarById
     * - getStarById
     * - setStarById
     * - getStar (index)
     * - setStar (index)
     */
}
