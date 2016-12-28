package com.fidelio.processing.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonasdrotleff on 27.12.16.
 */
public class Store {

    private File file;
    private List<State> states = new ArrayList<State>();

    public Store(File file) {
        this.file = file;
    }

    public Store() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void write() {

    }

    public void read() {

    }

    /* TODO:
     * - addState
     * - removeStateById
     * - getStateById
     * - setStateById
     * - getState (index)
     * - setState (index)
     */
}
