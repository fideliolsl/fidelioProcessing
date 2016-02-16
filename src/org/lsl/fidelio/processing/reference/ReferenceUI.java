package org.lsl.fidelio.processing.reference;

import org.lsl.fidelio.processing.util.StarPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class ReferenceUI extends JFrame implements ActionListener {

    private JPanel contentPane;
    private StarPanel star1;
    private StarPanel star2;
    private StarPanel star3;
    private JButton btnAbort;
    private JButton btnNext;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ReferenceUI frame = new ReferenceUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ReferenceUI() {
        createContents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbort) {
            dispose();
        } else if (e.getSource() == btnNext) {
            // TODO: calculate necessary value.
        }
    }

    private void createContents() {

        setMinimumSize(new Dimension(720, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 499, 473);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new MatteBorder(1, 0, 0, 0, SystemColor.windowBorder));
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.setLayout(new BorderLayout(0, 0));

        JPanel rightControlPanel = new JPanel();
        rightControlPanel.setBorder(null);
        controlPanel.add(rightControlPanel, BorderLayout.EAST);

        btnAbort = new JButton("Abort");
        btnAbort.addActionListener(this);
        rightControlPanel.add(btnAbort);

        btnNext = new JButton("Next");
        rightControlPanel.add(btnNext);

        JPanel previewPanel = new JPanel();
        contentPane.add(previewPanel, BorderLayout.CENTER);
        previewPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.EAST);

        JPanel starControlPanel = new JPanel();
        starControlPanel.setBorder(new EmptyBorder(0, 0, 0, scrollPane.getVerticalScrollBar().getPreferredSize().width));
        starControlPanel.setLayout(new BoxLayout(starControlPanel, BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(starControlPanel);

        star1 = new StarPanel(1);
        starControlPanel.add(star1);

        star2 = new StarPanel(2);
        starControlPanel.add(star2);

        star3 = new StarPanel(3);
        starControlPanel.add(star3);
    }

    public static void warningDialog(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }
}
