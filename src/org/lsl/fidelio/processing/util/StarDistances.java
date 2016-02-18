package org.lsl.fidelio.processing.util;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.lsl.fidelio.processing.reference.ReferenceUI;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StarDistances extends JPanel implements DocumentListener {

    JLabel lbl12Description;
    JLabel lbl12Value;
    JTextField txtDegreeDistance1_2;
    JTextField txtMinuteDistance1_2;
    JTextField txtSecondDistance1_2;

    JLabel lbl23Description;
    JLabel lbl23Value;
    JTextField txtDegreeDistance2_3;
    JTextField txtMinuteDistance2_3;
    JTextField txtSecondDistance2_3;

    JLabel lbl13Description;
    JLabel lbl13Value;
    JTextField txtDegreeDistance1_3;
    JTextField txtMinuteDistance1_3;
    JTextField txtSecondDistance1_3;

    String value = "=";

    double calculatedDistance12 = 0;
    double calculatedDistance23 = 0;
    double calculatedDistance13 = 0;

    String colorErrorHEX = "0xad8c43";
    String colorNormalHEX = "0x000000";

    boolean isNumeric = false;

    @Override
    public void insertUpdate(DocumentEvent e) {
        getAbsoluteDistanceHelper();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        getAbsoluteDistanceHelper();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        getAbsoluteDistanceHelper();
    }

    private void getAbsoluteDistanceHelper() {
        getAbsoluteDistance12();
        getAbsoluteDistance23();
        getAbsoluteDistance13();
    }

    public StarDistances() {
        createContents();
    }

    private void createContents() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelDistance1_2 = new JPanel();
        add(panelDistance1_2);
        panelDistance1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl12Description = new JLabel("Distance 1 - 2:");
        panelDistance1_2.add(lbl12Description);

        txtDegreeDistance1_2 = new JTextField();
        txtDegreeDistance1_2.setText("0");
        txtDegreeDistance1_2.setColumns(4);
        txtDegreeDistance1_2.getDocument().addDocumentListener(this);
        panelDistance1_2.add(txtDegreeDistance1_2);

        JLabel lbl2 = new JLabel("°");
        panelDistance1_2.add(lbl2);

        txtMinuteDistance1_2 = new JTextField();
        txtMinuteDistance1_2.setText("0");
        txtMinuteDistance1_2.setColumns(4);
        txtMinuteDistance1_2.getDocument().addDocumentListener(this);
        panelDistance1_2.add(txtMinuteDistance1_2);

        JLabel lbl3 = new JLabel("'");
        panelDistance1_2.add(lbl3);

        txtSecondDistance1_2 = new JTextField();
        txtSecondDistance1_2.setText("0");
        txtSecondDistance1_2.getDocument().addDocumentListener(this);
        txtSecondDistance1_2.setColumns(4);
        panelDistance1_2.add(txtSecondDistance1_2);

        JLabel lbl4 = new JLabel("\"");
        panelDistance1_2.add(lbl4);

        lbl12Value = new JLabel(value + " ?");
        panelDistance1_2.add(lbl12Value);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl23Description = new JLabel("Distance 2 - 3:");
        panel.add(lbl23Description);

        txtDegreeDistance2_3 = new JTextField();
        txtDegreeDistance2_3.setText("0");
        txtDegreeDistance2_3.setColumns(4);
        txtDegreeDistance2_3.getDocument().addDocumentListener(this);
        panel.add(txtDegreeDistance2_3);

        JLabel lbl5 = new JLabel("°");
        panel.add(lbl5);

        txtMinuteDistance2_3 = new JTextField();
        txtMinuteDistance2_3.setText("0");
        txtMinuteDistance2_3.setColumns(4);
        txtMinuteDistance2_3.getDocument().addDocumentListener(this);
        panel.add(txtMinuteDistance2_3);

        JLabel lbl6 = new JLabel("'");
        panel.add(lbl6);

        txtSecondDistance2_3 = new JTextField();
        txtSecondDistance2_3.setText("0");
        txtSecondDistance2_3.setColumns(4);
        txtSecondDistance2_3.getDocument().addDocumentListener(this);
        panel.add(txtSecondDistance2_3);

        JLabel lbl7 = new JLabel("\"");
        panel.add(lbl7);

        lbl23Value = new JLabel(value + " ?");
        panel.add(lbl23Value);

        JPanel panel_1 = new JPanel();
        add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl13Description = new JLabel("Distance 1 - 3:");
        panel_1.add(lbl13Description);

        txtDegreeDistance1_3 = new JTextField();
        txtDegreeDistance1_3.setText("0");
        txtDegreeDistance1_3.setColumns(4);
        txtDegreeDistance1_3.getDocument().addDocumentListener(this);
        panel_1.add(txtDegreeDistance1_3);

        JLabel lbl8 = new JLabel("°");
        panel_1.add(lbl8);

        txtMinuteDistance1_3 = new JTextField();
        txtMinuteDistance1_3.setText("0");
        txtMinuteDistance1_3.setColumns(4);
        txtMinuteDistance1_3.getDocument().addDocumentListener(this);
        panel_1.add(txtMinuteDistance1_3);

        JLabel lbl9 = new JLabel("'");
        panel_1.add(lbl9);

        txtSecondDistance1_3 = new JTextField();
        txtSecondDistance1_3.setText("0");
        txtSecondDistance1_3.setColumns(4);
        txtSecondDistance1_3.getDocument().addDocumentListener(this);
        panel_1.add(txtSecondDistance1_3);

        JLabel lbl10 = new JLabel("\"");
        panel_1.add(lbl10);

        lbl13Value = new JLabel(value + " ?");
        panel_1.add(lbl13Value);
    }

    double getAbsoluteDistance12() {
        calculatedDistance12 = 0;
        try {
            calculatedDistance12 = Double.parseDouble(txtDegreeDistance1_2.getText())
                    + (Double.parseDouble(txtMinuteDistance1_2.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance1_2.getText()) / 60) / 60);
            lbl12Description.setForeground(Color.decode(colorNormalHEX));
            isNumeric = true;
        } catch (Exception e) {
            //System.err.println(e);
            lbl12Description.setForeground(Color.decode(colorErrorHEX));
            isNumeric = false;

        }
        lbl12Value.setText(Utils.numberFormatHelper(value, calculatedDistance12));
        return calculatedDistance12;
    }

    double getAbsoluteDistance23() {
        calculatedDistance23 = 0;
        try {
            calculatedDistance23 = Double.parseDouble(txtDegreeDistance1_2.getText())
                    + (Double.parseDouble(txtMinuteDistance1_2.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance1_2.getText()) / 60) / 60);
            lbl23Description.setForeground(Color.decode(colorNormalHEX));
        } catch (Exception e) {
            //System.err.println(e);
            isNumeric = false;
            lbl23Description.setForeground(Color.decode(colorErrorHEX));
        }
        lbl23Value.setText(Utils.numberFormatHelper(value, calculatedDistance23));
        return calculatedDistance23;
    }

    double getAbsoluteDistance13() {
        calculatedDistance13 = 0;
        try {
            calculatedDistance13 = Double.parseDouble(txtDegreeDistance1_2.getText())
                    + (Double.parseDouble(txtMinuteDistance1_2.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance1_2.getText()) / 60) / 60);
            lbl13Description.setForeground(Color.decode(colorNormalHEX));
        } catch (Exception e) {
            //System.err.println(e);
            isNumeric = false;
            lbl13Description.setForeground(Color.decode(colorErrorHEX));
        }
        lbl13Value.setText(Utils.numberFormatHelper(value, calculatedDistance13));
        return calculatedDistance13;
    }

}
