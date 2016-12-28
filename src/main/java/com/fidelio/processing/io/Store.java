package com.fidelio.processing.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sun.security.krb5.internal.PAData;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas drotleff on 27.12.16.
 */
public class Store {


    private static final String FILE = // ~/.fidelio/store.xml
            System.getProperty("user.home")
            + File.separatorChar
            + ".fidelio"
            + File.separatorChar
            + "store.xml";
    private File file;
    private List<State> states = new ArrayList<State>();
    private Document doc;

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
        clean();
        createNew(file);
    }

    public void clean() {

    }

    public void read() {
        if (file.exists()) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(file);

                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("saved-states");

                for (int i = 0; i < nList.getLength(); i++) {
                    State state = new State();
                    state.loadElementDOM((Element) nList.item(i));
                    states.add(state);
                }

            } catch (Exception e) {
                // TODO error reading file
                e.printStackTrace();
            }
        } else {
            // TODO file does not exist
            return;
        }
    }

    public File createNew(File file) {
        if (file.exists()) {
            // TODO ask to override file
        } else {
            if (!new File(file.getParent()).exists()) {
                try {
                    new File(file.getParent()).mkdir();
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO could not create directory
                }
            }

            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                doc = docBuilder.newDocument();
                Element states = doc.createElement("saved-states");
                doc.appendChild(states);

                for (int i = 0; i < this.states.size(); i++) {
                    Element state = this.states.get(i).getElementDOM();
                    states.appendChild(state);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(this.file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public File createNew() {
        return createNew(new File(FILE));
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
