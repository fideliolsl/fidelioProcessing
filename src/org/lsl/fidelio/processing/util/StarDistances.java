package org.lsl.fidelio.processing.util;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StarDistances extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    public StarDistances() {
        createContents();
    }

    private void createContents() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelAzimut = new JPanel();
        add(panelAzimut);
        panelAzimut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblAz = new JLabel("Azimut:");
        panelAzimut.add(lblAz);

        JTextField txtAzimutDegree = new JTextField();
        txtAzimutDegree.setText("0");
        panelAzimut.add(txtAzimutDegree);
        txtAzimutDegree.setColumns(4);

        JLabel lblAzD = new JLabel("°");
        panelAzimut.add(lblAzD);

        JTextField txtAzimutMinute = new JTextField();
        txtAzimutMinute.setText("0");
        txtAzimutMinute.setColumns(4);
        panelAzimut.add(txtAzimutMinute);

        JLabel lblAzM = new JLabel("'");
        panelAzimut.add(lblAzM);

        JTextField txtAzimutSecond = new JTextField();
        txtAzimutSecond.setText("0");
        panelAzimut.add(txtAzimutSecond);
        txtAzimutSecond.setColumns(4);

        JLabel lblAzS = new JLabel("\"");
        panelAzimut.add(lblAzS);

        JLabel lblAzValue = new JLabel("= %n");
        panelAzimut.add(lblAzValue);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel label = new JLabel("Azimut:");
        panel.add(label);

        this.textField = new JTextField();
        this.textField.setText("0");
        this.textField.setColumns(4);
        panel.add(this.textField);

        JLabel label_1 = new JLabel("°");
        panel.add(label_1);

        this.textField_1 = new JTextField();
        this.textField_1.setText("0");
        this.textField_1.setColumns(4);
        panel.add(this.textField_1);

        JLabel label_2 = new JLabel("'");
        panel.add(label_2);

        this.textField_2 = new JTextField();
        this.textField_2.setText("0");
        this.textField_2.setColumns(4);
        panel.add(this.textField_2);

        JLabel label_3 = new JLabel("\"");
        panel.add(label_3);

        JLabel label_4 = new JLabel("= %n");
        panel.add(label_4);

        JPanel panel_1 = new JPanel();
        add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel label_5 = new JLabel("Azimut:");
        panel_1.add(label_5);

        this.textField_3 = new JTextField();
        this.textField_3.setText("0");
        this.textField_3.setColumns(4);
        panel_1.add(this.textField_3);

        JLabel label_6 = new JLabel("°");
        panel_1.add(label_6);

        this.textField_4 = new JTextField();
        this.textField_4.setText("0");
        this.textField_4.setColumns(4);
        panel_1.add(this.textField_4);

        JLabel label_7 = new JLabel("'");
        panel_1.add(label_7);

        this.textField_5 = new JTextField();
        this.textField_5.setText("0");
        this.textField_5.setColumns(4);
        panel_1.add(this.textField_5);

        JLabel label_8 = new JLabel("\"");
        panel_1.add(label_8);

        JLabel label_9 = new JLabel("= %n");
        panel_1.add(label_9);
    }

}
