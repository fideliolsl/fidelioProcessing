package org.lsl.fidelio.processing.util.ui;

import org.lsl.fidelio.processing.util.Utils;

import java.awt.*;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StarDistances extends JPanel implements DocumentListener {

    JLabel lbl01Description;
    JLabel lbl01Value;
    JTextField txtDegreeDistance0_1;
    JTextField txtMinuteDistance0_1;
    JTextField txtSecondDistance0_1;

    JLabel lbl12Description;
    JLabel lbl12Value;
    JTextField txtDegreeDistance1_2;
    JTextField txtMinuteDistance1_2;
    JTextField txtSecondDistance1_2;

    JLabel lbl02Description;
    JLabel lbl02Value;
    JTextField txtDegreeDistance0_2;
    JTextField txtMinuteDistance0_2;
    JTextField txtSecondDistance0_2;

    JTextField[][] jTextFields = {
            {txtDegreeDistance0_1, txtMinuteDistance0_1, txtSecondDistance0_1},
            {txtDegreeDistance0_2, txtMinuteDistance0_2, txtSecondDistance0_2},
            {txtDegreeDistance1_2, txtMinuteDistance1_2, txtSecondDistance1_2},
    };

    String value = "=";

    double calculatedDistance12 = 0;
    double calculatedDistance23 = 0;
    double calculatedDistance13 = 0;

    String colorErrorHEX = "0xad8c43";
    String colorNormalHEX = "0x000000";

    boolean loadLast = false;

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
        getAbsoluteDistance01();
        getAbsoluteDistance12();
        getAbsoluteDistance02();
    }

    public StarDistances(boolean loadLastFile) {
        this.loadLast = loadLastFile;
        createContents();
    }

    private void createContents() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelDistance1_2 = new JPanel();
        add(panelDistance1_2);
        panelDistance1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl01Description = new JLabel("Distance 1 - 2:");
        panelDistance1_2.add(lbl01Description);

        txtDegreeDistance0_1 = new JTextField();
        txtDegreeDistance0_1.setText("0");
        txtDegreeDistance0_1.setColumns(4);
        txtDegreeDistance0_1.getDocument().addDocumentListener(this);
        panelDistance1_2.add(txtDegreeDistance0_1);

        JLabel lbl2 = new JLabel("°");
        panelDistance1_2.add(lbl2);

        txtMinuteDistance0_1 = new JTextField();
        txtMinuteDistance0_1.setText("0");
        txtMinuteDistance0_1.setColumns(4);
        txtMinuteDistance0_1.getDocument().addDocumentListener(this);
        panelDistance1_2.add(txtMinuteDistance0_1);

        JLabel lbl3 = new JLabel("'");
        panelDistance1_2.add(lbl3);

        txtSecondDistance0_1 = new JTextField();
        txtSecondDistance0_1.setText("0");
        txtSecondDistance0_1.getDocument().addDocumentListener(this);
        txtSecondDistance0_1.setColumns(4);
        panelDistance1_2.add(txtSecondDistance0_1);

        JLabel lbl4 = new JLabel("\"");
        panelDistance1_2.add(lbl4);

        lbl01Value = new JLabel(value + " ?");
        panelDistance1_2.add(lbl01Value);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl12Description = new JLabel("Distance 2 - 3:");
        panel.add(lbl12Description);

        txtDegreeDistance1_2 = new JTextField();
        txtDegreeDistance1_2.setText("0");
        txtDegreeDistance1_2.setColumns(4);
        txtDegreeDistance1_2.getDocument().addDocumentListener(this);
        panel.add(txtDegreeDistance1_2);

        JLabel lbl5 = new JLabel("°");
        panel.add(lbl5);

        txtMinuteDistance1_2 = new JTextField();
        txtMinuteDistance1_2.setText("0");
        txtMinuteDistance1_2.setColumns(4);
        txtMinuteDistance1_2.getDocument().addDocumentListener(this);
        panel.add(txtMinuteDistance1_2);

        JLabel lbl6 = new JLabel("'");
        panel.add(lbl6);

        txtSecondDistance1_2 = new JTextField();
        txtSecondDistance1_2.setText("0");
        txtSecondDistance1_2.setColumns(4);
        txtSecondDistance1_2.getDocument().addDocumentListener(this);
        panel.add(txtSecondDistance1_2);

        JLabel lbl7 = new JLabel("\"");
        panel.add(lbl7);

        lbl12Value = new JLabel(value + " ?");
        panel.add(lbl12Value);

        JPanel panel_1 = new JPanel();
        add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lbl02Description = new JLabel("Distance 1 - 3:");
        panel_1.add(lbl02Description);

        txtDegreeDistance0_2 = new JTextField();
        txtDegreeDistance0_2.setText("0");
        txtDegreeDistance0_2.setColumns(4);
        txtDegreeDistance0_2.getDocument().addDocumentListener(this);
        panel_1.add(txtDegreeDistance0_2);

        JLabel lbl8 = new JLabel("°");
        panel_1.add(lbl8);

        txtMinuteDistance0_2 = new JTextField();
        txtMinuteDistance0_2.setText("0");
        txtMinuteDistance0_2.setColumns(4);
        txtMinuteDistance0_2.getDocument().addDocumentListener(this);
        panel_1.add(txtMinuteDistance0_2);

        JLabel lbl9 = new JLabel("'");
        panel_1.add(lbl9);

        txtSecondDistance0_2 = new JTextField();
        txtSecondDistance0_2.setText("0");
        txtSecondDistance0_2.setColumns(4);
        txtSecondDistance0_2.getDocument().addDocumentListener(this);
        panel_1.add(txtSecondDistance0_2);

        JLabel lbl10 = new JLabel("\"");
        panel_1.add(lbl10);

        lbl02Value = new JLabel(value + " ?");
        panel_1.add(lbl02Value);

        jTextFields[0][0] = txtDegreeDistance0_1;
        jTextFields[0][1] = txtMinuteDistance0_1;
        jTextFields[0][2] = txtSecondDistance0_1;

        jTextFields[1][0] = txtDegreeDistance0_2;
        jTextFields[1][1] = txtMinuteDistance0_2;
        jTextFields[1][2] = txtDegreeDistance0_2;

        jTextFields[2][0] = txtDegreeDistance1_2;
        jTextFields[2][1] = txtMinuteDistance1_2;
        jTextFields[2][2] = txtSecondDistance1_2;

        if (loadLast) {
            loadLastParams();
        }
    }

    void loadLastParams() {
        for (int i = 0; i < 3; i++) {
            String distance = Utils.getProperty(Utils.KEY_DISTANCES[i]);
            if (distance != null && !distance.isEmpty()) {
                StringTokenizer stringTokenizer = new StringTokenizer(distance, ",");
                for (int j = 0; j < 3; j++) {
                    jTextFields[i][j].setText(stringTokenizer.nextToken());
                }
            }
        }
        loadLast = false;
    }

    public double getAbsoluteDistance01() {
        calculatedDistance12 = 0;
        try {
            calculatedDistance12 = Double.parseDouble(txtDegreeDistance0_1.getText())
                    + (Double.parseDouble(txtMinuteDistance0_1.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance0_1.getText()) / 60) / 60);
            lbl01Description.setForeground(Color.decode(colorNormalHEX));
        } catch (Exception e) {
            lbl01Description.setForeground(Color.decode(colorErrorHEX));
        }
        lbl01Value.setText(Utils.numberFormatHelper(value, calculatedDistance12));
        return calculatedDistance12;
    }

    public double getAbsoluteDistance12() {
        calculatedDistance23 = 0;
        try {
            calculatedDistance23 = Double.parseDouble(txtDegreeDistance1_2.getText())
                    + (Double.parseDouble(txtMinuteDistance1_2.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance1_2.getText()) / 60) / 60);
            lbl12Description.setForeground(Color.decode(colorNormalHEX));
        } catch (Exception e) {
            lbl12Description.setForeground(Color.decode(colorErrorHEX));
        }
        lbl12Value.setText(Utils.numberFormatHelper(value, calculatedDistance23));
        return calculatedDistance23;
    }

    public double getAbsoluteDistance02() {
        calculatedDistance13 = 0;
        try {
            calculatedDistance13 = Double.parseDouble(txtDegreeDistance0_2.getText())
                    + (Double.parseDouble(txtMinuteDistance0_2.getText()) / 60
                    + (Double.parseDouble(txtSecondDistance0_2.getText()) / 60) / 60);
            lbl02Description.setForeground(Color.decode(colorNormalHEX));
        } catch (Exception e) {
            lbl02Description.setForeground(Color.decode(colorErrorHEX));
        }
        lbl02Value.setText(Utils.numberFormatHelper(value, calculatedDistance13));
        return calculatedDistance13;
    }

    public void saveValues() {
        for (int i = 0; i < jTextFields.length; i++) {
            Utils.setProperty(Utils.KEY_DISTANCES[i],
                    jTextFields[i][0].getText() +
                            "," +
                            jTextFields[i][1].getText() +
                            "," +
                            jTextFields[i][2].getText());
        }
    }

}
