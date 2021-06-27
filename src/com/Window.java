package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicReference;

public class Window extends JFrame {
    JButton button;
    String string;
    JPanel panel;
    JPanel southPanel;
    JPanel eastPanel;
    public Window() throws HeadlessException {
        panel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();
        Image img = new ImageIcon("src\\assets\\images.jpg").getImage();
        setIconImage(img);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(panel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setSize(500,300);
        JTextArea txtArea = new JTextArea(10,30);
        JScrollPane scrollPane = new JScrollPane(txtArea);

        button = new JButton("Run");
        button.addActionListener(e -> {
            if (e.getSource()==button){
                try {
                    FileWriter fw = new FileWriter("src\\files\\file.txt",false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    string = txtArea.getText();
                    bw.write(string);
                    bw.close();
                    Main.read_compute(new File("src\\files\\file.txt"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    Main.intValues.clear();
                    Main.floatValues.clear();
                }
                JOptionPane.showMessageDialog(null,"code compiled");
                System.out.println("-------------------------------------------------------------------");
            }
        });
        string = txtArea.getText();
        scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
        JCheckBox boldCheckbox = new JCheckBox("Bold",false);
        JCheckBox italicCheckbox = new JCheckBox("Italic",false);
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton small = new JRadioButton("small");
        buttonGroup.add(small);
        JRadioButton medium = new JRadioButton("medium");
        buttonGroup.add(medium);
        JRadioButton large = new JRadioButton("large");
        buttonGroup.add(large);
        ActionListener checkBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode =0;
                if (boldCheckbox.isSelected())
                    mode += Font.BOLD;
                if (italicCheckbox.isSelected())
                    mode += Font.ITALIC;
                txtArea.setFont(new Font(Font.SANS_SERIF,mode,14));
            }
        };
        boldCheckbox.addActionListener(checkBoxListener);
        italicCheckbox.addActionListener(checkBoxListener);
        eastPanel.add(boldCheckbox);
        eastPanel.add(italicCheckbox);
        eastPanel.add(small);
        eastPanel.add(medium);
        eastPanel.add(large);
        ActionListener sizeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (small.isSelected())
                    txtArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
                else if (medium.isSelected())
                    txtArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,13));
                else if (large.isSelected())
                    txtArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,17));
            }
        };
        small.addActionListener(sizeListener);
        medium.addActionListener(sizeListener);
        large.addActionListener(sizeListener);
        southPanel.add(button);

        panel.add(southPanel,BorderLayout.SOUTH);
        panel.add(eastPanel,BorderLayout.EAST);
        add(panel);
    }
}
