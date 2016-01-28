package org.lsl.fidelio.processing.reference;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class AddNewStar extends JPanel {
    private JTextField txtAzimos;
    private JTextField textField;

    /**
     * Create the panel.
     */
    public AddNewStar() {
        setBorder(new TitledBorder(null, "Star", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{-41, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblXY = new JLabel("X:? Y: ?");
        GridBagConstraints gbc_lblXY = new GridBagConstraints();
        gbc_lblXY.insets = new Insets(0, 0, 5, 5);
        gbc_lblXY.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblXY.gridx = 0;
        gbc_lblXY.gridy = 0;
        add(lblXY, gbc_lblXY);

        JButton btnNewButton = new JButton("x");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        add(btnNewButton, gbc_btnNewButton);

        JButton btnChange = new JButton("Change");
        GridBagConstraints gbc_btnChange = new GridBagConstraints();
        gbc_btnChange.gridwidth = 2;
        gbc_btnChange.insets = new Insets(0, 0, 5, 5);
        gbc_btnChange.anchor = GridBagConstraints.WEST;
        gbc_btnChange.gridx = 0;
        gbc_btnChange.gridy = 1;
        add(btnChange, gbc_btnChange);

        txtAzimos = new JTextField();
        txtAzimos.setText("Azimos");
        GridBagConstraints gbc_txtAzimos = new GridBagConstraints();
        gbc_txtAzimos.anchor = GridBagConstraints.WEST;
        gbc_txtAzimos.insets = new Insets(0, 0, 5, 0);
        gbc_txtAzimos.gridwidth = 2;
        gbc_txtAzimos.gridx = 0;
        gbc_txtAzimos.gridy = 2;
        add(txtAzimos, gbc_txtAzimos);
        txtAzimos.setColumns(10);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.anchor = GridBagConstraints.WEST;
        gbc_textField.gridwidth = 2;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 3;
        add(textField, gbc_textField);
        textField.setColumns(10);

    }

}
