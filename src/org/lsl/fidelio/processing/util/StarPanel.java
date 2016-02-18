package org.lsl.fidelio.processing.util;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

import org.lsl.fidelio.processing.reference.ReferenceUI;

import javax.swing.JSeparator;
import java.awt.FlowLayout;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class StarPanel extends JPanel implements DocumentListener {

    JTextField txtStarX;
    JTextField txtStarY;
    JButton btnEdit;
    JPanel panelAzimuth;
    JTextField txtAzimuthDegree;
    JLabel lblAzD;
    JTextField txtAzimuthMinute;
    JLabel lblAzM;
    JTextField txtAzimuthSecond;
    JLabel lblAzS;
    JSeparator separator;
    JPanel panelHeight;
    JTextField txtHeightDegree;
    JLabel lblHeightD;
    JTextField txtHeightMinute;
    JLabel lblHeightM;
    JTextField txtHeightSecond;
    JLabel lblHeightS;
    JLabel lblHeight;
    JLabel lblAz;
    JSeparator separator_1;
    JLabel lblAzValue;
    JLabel lblHeightValue;

    String value = "=";

    double calculatedHeight = 0;
    double calculatedAzimuth = 0;

    String colorErrorHEX = "0xad8c43";
    String colorNormalHEX = "0x000000";

    boolean isNumeric = false;

    /**
     * Create the panel.
     */

    @Override
    public void insertUpdate(DocumentEvent e) {
        getAbsoluteValuesHelper();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        getAbsoluteValuesHelper();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        getAbsoluteValuesHelper();
    }

    private void getAbsoluteValuesHelper(){
        getAbsoluteAz();
        getAbsoluteHeight();
    }

    public StarPanel(int index) {
        setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), String.format("Star %d", index), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblX = new JLabel("X:");
        GridBagConstraints gbc_lblX = new GridBagConstraints();
        gbc_lblX.insets = new Insets(0, 0, 5, 5);
        gbc_lblX.anchor = GridBagConstraints.WEST;
        gbc_lblX.gridx = 0;
        gbc_lblX.gridy = 0;
        add(lblX, gbc_lblX);

        txtStarX = new JTextField();
        lblX.setLabelFor(txtStarX);
        GridBagConstraints gbc_txtStarX = new GridBagConstraints();
        gbc_txtStarX.insets = new Insets(0, 0, 5, 5);
        gbc_txtStarX.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtStarX.gridx = 1;
        gbc_txtStarX.gridy = 0;
        add(txtStarX, gbc_txtStarX);
        txtStarX.setColumns(10);

        btnEdit = new JButton("Edit");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.gridheight = 2;
        gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
        gbc_btnEdit.fill = GridBagConstraints.BOTH;
        gbc_btnEdit.gridx = 2;
        gbc_btnEdit.gridy = 0;
        add(btnEdit, gbc_btnEdit);

        JLabel lblY = new JLabel("Y:");
        GridBagConstraints gbc_lblY = new GridBagConstraints();
        gbc_lblY.anchor = GridBagConstraints.WEST;
        gbc_lblY.insets = new Insets(0, 0, 5, 5);
        gbc_lblY.gridx = 0;
        gbc_lblY.gridy = 1;
        add(lblY, gbc_lblY);

        txtStarY = new JTextField();
        GridBagConstraints gbc_txtStarY = new GridBagConstraints();
        gbc_txtStarY.insets = new Insets(0, 0, 5, 5);
        gbc_txtStarY.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtStarY.gridx = 1;
        gbc_txtStarY.gridy = 1;
        add(txtStarY, gbc_txtStarY);
        txtStarY.setColumns(10);

        separator = new JSeparator();
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.gridwidth = 3;
        gbc_separator.anchor = GridBagConstraints.NORTH;
        gbc_separator.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator.insets = new Insets(0, 0, 5, 0);
        gbc_separator.gridx = 0;
        gbc_separator.gridy = 2;
        add(separator, gbc_separator);

        panelAzimuth = new JPanel();
        GridBagConstraints gbc_panelAzimuth = new GridBagConstraints();
        gbc_panelAzimuth.gridwidth = 3;
        gbc_panelAzimuth.insets = new Insets(0, 0, 5, 0);
        gbc_panelAzimuth.anchor = GridBagConstraints.NORTHWEST;
        gbc_panelAzimuth.gridx = 0;
        gbc_panelAzimuth.gridy = 3;
        add(panelAzimuth, gbc_panelAzimuth);
        panelAzimuth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblAz = new JLabel("Azimuth:");
        panelAzimuth.add(lblAz);

        txtAzimuthDegree = new JTextField();
        txtAzimuthDegree.setText("0");
        panelAzimuth.add(txtAzimuthDegree);
        txtAzimuthDegree.setColumns(4);

        lblAzD = new JLabel("°");
        panelAzimuth.add(lblAzD);

        txtAzimuthMinute = new JTextField();
        txtAzimuthMinute.setText("0");
        txtAzimuthMinute.setColumns(4);
        panelAzimuth.add(txtAzimuthMinute);

        lblAzM = new JLabel("'");
        panelAzimuth.add(lblAzM);

        txtAzimuthSecond = new JTextField();
        txtAzimuthSecond.setText("0");
        panelAzimuth.add(txtAzimuthSecond);
        txtAzimuthSecond.setColumns(4);

        lblAzS = new JLabel("\"");
        panelAzimuth.add(lblAzS);

        this.lblAzValue = new JLabel(value + " ?");
        this.panelAzimuth.add(this.lblAzValue);

        panelHeight = new JPanel();
        GridBagConstraints gbc_panelHeight = new GridBagConstraints();
        gbc_panelHeight.insets = new Insets(0, 0, 5, 0);
        gbc_panelHeight.gridwidth = 3;
        gbc_panelHeight.anchor = GridBagConstraints.NORTHWEST;
        gbc_panelHeight.gridx = 0;
        gbc_panelHeight.gridy = 4;
        add(panelHeight, gbc_panelHeight);
        panelHeight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblHeight = new JLabel("Height:");
        panelHeight.add(lblHeight);

        txtHeightDegree = new JTextField();
        txtHeightDegree.setText("0");
        txtHeightDegree.setColumns(4);
        panelHeight.add(txtHeightDegree);

        lblHeightD = new JLabel("°");
        panelHeight.add(lblHeightD);

        txtHeightMinute = new JTextField();
        txtHeightMinute.setText("0");
        txtHeightMinute.setColumns(4);
        panelHeight.add(txtHeightMinute);

        lblHeightM = new JLabel("'");
        panelHeight.add(lblHeightM);

        txtHeightSecond = new JTextField();
        txtHeightSecond.setText("0");
        txtHeightSecond.setColumns(4);
        panelHeight.add(txtHeightSecond);

        lblHeightS = new JLabel("\"");
        panelHeight.add(lblHeightS);

        this.lblHeightValue = new JLabel(value + " ?");
        this.panelHeight.add(this.lblHeightValue);

        separator_1 = new JSeparator();
        GridBagConstraints gbc_separator_1 = new GridBagConstraints();
        gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator_1.gridwidth = 3;
        gbc_separator_1.gridx = 0;
        gbc_separator_1.gridy = 5;
        add(separator_1, gbc_separator_1);

        txtAzimuthDegree.getDocument().addDocumentListener(this);
        txtAzimuthMinute.getDocument().addDocumentListener(this);
        txtAzimuthSecond.getDocument().addDocumentListener(this);

        txtHeightDegree.getDocument().addDocumentListener(this);
        txtHeightMinute.getDocument().addDocumentListener(this);
        txtHeightSecond.getDocument().addDocumentListener(this);
    }

    double getAbsoluteAz() {
        calculatedAzimuth = 0;
        try {
            calculatedAzimuth = Double.parseDouble(txtAzimuthDegree.getText())
                    + (Double.parseDouble(txtAzimuthMinute.getText()) / 60
                    + (Double.parseDouble(txtAzimuthSecond.getText()) / 60) / 60);
            lblAz.setForeground(Color.decode(colorNormalHEX));
            isNumeric = true;
        } catch (Exception e) {
            //System.err.println(e);
            lblAz.setForeground(Color.decode(colorErrorHEX));
            isNumeric = false;
        }
        lblAzValue.setText(Utils.numberFormatHelper(value, calculatedAzimuth));
        return calculatedAzimuth;
    }

    double getAbsoluteHeight() {
        calculatedHeight = 0;
        try {
            calculatedHeight = Double.parseDouble(txtHeightDegree.getText())
                    + (Double.parseDouble(txtHeightMinute.getText()) / 60
                    + (Double.parseDouble(txtHeightSecond.getText()) / 60) / 60);
            lblHeight.setForeground(Color.decode(colorNormalHEX));
        } catch (NumberFormatException e) {
            // System.err.println(e.getMessage());
            lblHeight.setForeground(Color.decode(colorErrorHEX));
            isNumeric = false;

        }
        lblHeightValue.setText(Utils.numberFormatHelper(value, calculatedHeight));
        return calculatedHeight;
    }

}