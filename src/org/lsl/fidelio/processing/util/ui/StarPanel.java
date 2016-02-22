package org.lsl.fidelio.processing.util.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.lsl.fidelio.processing.reference.ReferenceUI;
import org.lsl.fidelio.processing.util.Utils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.StringTokenizer;

public class StarPanel extends JPanel implements ActionListener, DocumentListener {

    JTextField txtStarX;
    JTextField txtStarY;
    JToggleButton btnEdit;
    JPanel panelAzimut;
    JTextField txtAzimutDegree;
    JLabel lblAzD;
    JTextField txtAzimutMinute;
    JLabel lblAzM;
    JTextField txtAzimutSecond;
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

    JTextField[][] jTextFields = new JTextField[2][3];

    String value = "=";

    double calculatedHeight = 0;
    double calculatedAzimut = 0;

    String colorErrorHEX = "0xad8c43";
    String colorNormalHEX = "0x000000";

    boolean isNumeric = false;
    public boolean isNumeric() {
        return isNumeric;
    }

    int panelIndex = 0;
    boolean loadLast = false;

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

    private void getAbsoluteValuesHelper() {
        getAbsoluteAz();
        getAbsoluteHeight();
    }

    public StarPanel(int index, boolean loadLastFile) {
        this.loadLast = loadLastFile;
        panelIndex = index;
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

        btnEdit = new JToggleButton("Edit");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.gridheight = 2;
        gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
        gbc_btnEdit.fill = GridBagConstraints.BOTH;
        gbc_btnEdit.gridx = 2;
        gbc_btnEdit.gridy = 0;
        btnEdit.addActionListener(this);
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

        panelAzimut = new JPanel();
        GridBagConstraints gbc_panelAzimut = new GridBagConstraints();
        gbc_panelAzimut.gridwidth = 3;
        gbc_panelAzimut.insets = new Insets(0, 0, 5, 0);
        gbc_panelAzimut.anchor = GridBagConstraints.NORTHWEST;
        gbc_panelAzimut.gridx = 0;
        gbc_panelAzimut.gridy = 3;
        add(panelAzimut, gbc_panelAzimut);
        panelAzimut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblAz = new JLabel("Azimut:");
        panelAzimut.add(lblAz);

        txtAzimutDegree = new JTextField();
        txtAzimutDegree.setText("0");
        panelAzimut.add(txtAzimutDegree);
        txtAzimutDegree.setColumns(4);

        lblAzD = new JLabel("°");
        panelAzimut.add(lblAzD);

        txtAzimutMinute = new JTextField();
        txtAzimutMinute.setText("0");
        txtAzimutMinute.setColumns(4);
        panelAzimut.add(txtAzimutMinute);

        lblAzM = new JLabel("'");
        panelAzimut.add(lblAzM);

        txtAzimutSecond = new JTextField();
        txtAzimutSecond.setText("0");
        panelAzimut.add(txtAzimutSecond);
        txtAzimutSecond.setColumns(4);

        lblAzS = new JLabel("\"");
        panelAzimut.add(lblAzS);

        this.lblAzValue = new JLabel(value + " ?");
        this.panelAzimut.add(this.lblAzValue);

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

        jTextFields[0][0] = txtAzimutDegree;
        jTextFields[0][1] = txtAzimutMinute;
        jTextFields[0][2] = txtAzimutSecond;

        jTextFields[1][0] = txtHeightDegree;
        jTextFields[1][1] = txtHeightMinute;
        jTextFields[1][2] = txtHeightSecond;

        if (loadLast) {
            loadLastParams();
        }

        txtAzimutDegree.getDocument().addDocumentListener(this);
        txtAzimutMinute.getDocument().addDocumentListener(this);
        txtAzimutSecond.getDocument().addDocumentListener(this);

        txtHeightDegree.getDocument().addDocumentListener(this);
        txtHeightMinute.getDocument().addDocumentListener(this);
        txtHeightSecond.getDocument().addDocumentListener(this);
    }

    void loadLastParams() {
        String position = Utils.getProperty(Utils.KEY_POSITIONS[panelIndex]);
        if (position != null && !position.isEmpty()) {
            StringTokenizer stringTokenizer = new StringTokenizer(position, ",");
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            txtStarX.setText(String.valueOf(x));
            txtStarY.setText(String.valueOf(y));
            ReferenceUI.previewPanel.setPosition(x, y, panelIndex);
            if (!ReferenceUI.previewPanel.isVisible(panelIndex)) {
                ReferenceUI.previewPanel.setVisible(true, panelIndex);
            }
        }
        for (int i = 0; i < 2; i++) {
            String params = Utils.getProperty(Utils.KEY_STARPARAMS[panelIndex][i]);
            if (params != null && !params.isEmpty()) {
                StringTokenizer stringTokenizer = new StringTokenizer(params, ",");
                for (int j = 0; j < 3; j++) {
                    jTextFields[i][j].setText(stringTokenizer.nextToken());
                }
            }
        }
        getAbsoluteValuesHelper();
    }

    public void setCoordinates(int x, int y) {
        txtStarX.setText(String.valueOf(x));
        txtStarY.setText(String.valueOf(y));
    }

    double getAbsoluteAz() {
        calculatedAzimut = 0;
        try {
            calculatedAzimut = Double.parseDouble(txtAzimutDegree.getText())
                    + (Double.parseDouble(txtAzimutMinute.getText()) / 60
                    + (Double.parseDouble(txtAzimutSecond.getText()) / 60) / 60);
            lblAz.setForeground(Color.decode(colorNormalHEX));
            isNumeric = true;
            saveValues();
        } catch (Exception e) {
            //System.err.println(e);
            lblAz.setForeground(Color.decode(colorErrorHEX));
            isNumeric = false;
        }
        lblAzValue.setText(Utils.numberFormatHelper(value, calculatedAzimut));
        return calculatedAzimut;
    }

    public double getAbsoluteHeight() {
        calculatedHeight = 0;
        try {
            calculatedHeight = Double.parseDouble(txtHeightDegree.getText())
                    + (Double.parseDouble(txtHeightMinute.getText()) / 60
                    + (Double.parseDouble(txtHeightSecond.getText()) / 60) / 60);
            lblHeight.setForeground(Color.decode(colorNormalHEX));
            saveValues();
        } catch (NumberFormatException e) {
            // System.err.println(e.getMessage());
            lblHeight.setForeground(Color.decode(colorErrorHEX));
            isNumeric = false;

        }
        lblHeightValue.setText(Utils.numberFormatHelper(value, calculatedHeight));
        return calculatedHeight;
    }

    public void saveValues() {
        try {
            Utils.setPropery(Utils.KEY_STARPARAMS[panelIndex][0],
                    jTextFields[0][0].getText() +
                            "," + jTextFields[0][1].getText() +
                            "," + jTextFields[0][2].getText());

            Utils.setPropery(Utils.KEY_STARPARAMS[panelIndex][1],
                    jTextFields[1][0].getText() + "," + jTextFields[1][1].getText() + "," + jTextFields[1][2].getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeBtnState(boolean state){
        btnEdit.setEnabled(state);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReferenceUI.changeCoordinates(panelIndex, btnEdit.isSelected());
    }
}