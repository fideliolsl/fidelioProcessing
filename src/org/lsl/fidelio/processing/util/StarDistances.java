package org.lsl.fidelio.processing.util;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StarDistances extends JPanel {
    private JTextField txtDegreeDistance1_2;
    private JTextField txtMinuteDistance1_2;
    private JTextField txtSecondDistance1_2;

    private JTextField txtDegreeDistance2_3;
    private JTextField txtMinuteDistance2_3;
    private JTextField txtSecondDistance2_3;

    private JTextField txtDegreeDistance1_3;
    private JTextField txtMinuteDistance1_3;
    private JTextField txtSecondDistance1_3;

    public StarDistances() {
        createContents();
    }

    private void createContents() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panelDistance1_2 = new JPanel();
        add(panelDistance1_2);
        panelDistance1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JLabel lblDistance1_2 = new JLabel("Distance 1 - 2:");
            panelDistance1_2.add(lblDistance1_2);

            txtDegreeDistance1_2 = new JTextField();
            txtDegreeDistance1_2.setText("0");
            panelDistance1_2.add(txtDegreeDistance1_2);
            txtDegreeDistance1_2.setColumns(4);

            JLabel lblDegreeDistance1_2 = new JLabel("°");
            panelDistance1_2.add(lblDegreeDistance1_2);

            txtMinuteDistance1_2 = new JTextField();
            txtMinuteDistance1_2.setText("0");
            txtMinuteDistance1_2.setColumns(4);
            panelDistance1_2.add(txtMinuteDistance1_2);

            JLabel lblAzM = new JLabel("'");
            panelDistance1_2.add(lblAzM);

            txtSecondDistance1_2 = new JTextField();
            txtSecondDistance1_2.setText("0");
            panelDistance1_2.add(txtSecondDistance1_2);
            txtSecondDistance1_2.setColumns(4);

            JLabel lblAzS = new JLabel("\"");
            panelDistance1_2.add(lblAzS);

            JLabel lblAzValue = new JLabel("= %n");
            panelDistance1_2.add(lblAzValue);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JLabel label = new JLabel("Distance 2 - 3:");
            panel.add(label);

            this.txtDegreeDistance2_3 = new JTextField();
            this.txtDegreeDistance2_3.setText("0");
            this.txtDegreeDistance2_3.setColumns(4);
            panel.add(this.txtDegreeDistance2_3);

            JLabel label_1 = new JLabel("°");
            panel.add(label_1);

            this.txtMinuteDistance2_3 = new JTextField();
            this.txtMinuteDistance2_3.setText("0");
            this.txtMinuteDistance2_3.setColumns(4);
            panel.add(this.txtMinuteDistance2_3);

            JLabel label_2 = new JLabel("'");
            panel.add(label_2);

            this.txtSecondDistance2_3 = new JTextField();
            this.txtSecondDistance2_3.setText("0");
            this.txtSecondDistance2_3.setColumns(4);
            panel.add(this.txtSecondDistance2_3);

            JLabel label_3 = new JLabel("\"");
            panel.add(label_3);

            JLabel label_4 = new JLabel("= %n");
            panel.add(label_4);

        JPanel panel_1 = new JPanel();
        add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JLabel label_5 = new JLabel("Distance 1 - 3:");
            panel_1.add(label_5);

            this.txtDegreeDistance1_3 = new JTextField();
            this.txtDegreeDistance1_3.setText("0");
            this.txtDegreeDistance1_3.setColumns(4);
            panel_1.add(this.txtDegreeDistance1_3);

            JLabel label_6 = new JLabel("°");
            panel_1.add(label_6);

            this.txtMinuteDistance1_3 = new JTextField();
            this.txtMinuteDistance1_3.setText("0");
            this.txtMinuteDistance1_3.setColumns(4);
            panel_1.add(this.txtMinuteDistance1_3);

            JLabel label_7 = new JLabel("'");
            panel_1.add(label_7);

            this.txtSecondDistance1_3 = new JTextField();
            this.txtSecondDistance1_3.setText("0");
            this.txtSecondDistance1_3.setColumns(4);
            panel_1.add(this.txtSecondDistance1_3);

            JLabel label_8 = new JLabel("\"");
            panel_1.add(label_8);

            JLabel label_9 = new JLabel("= %n");
            panel_1.add(label_9);
    }

}
