package com;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

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
        setSize(510,350);
        setResizable(false);
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
        eastPanel.add(boldCheckbox);
        eastPanel.add(italicCheckbox);
        JComboBox<String> sizeBox = new JComboBox<>();
        JComboBox<String> fontBox = new JComboBox<>();
        sizeBox.addItem("Font Size");
        sizeBox.addItem("10");
        sizeBox.addItem("11");
        sizeBox.addItem("12");
        sizeBox.addItem("13");
        sizeBox.addItem("14");
        sizeBox.addItem("15");
        sizeBox.addItem("16");
        sizeBox.addItem("17");
        sizeBox.addItem("18");
        ActionListener fontSizeListener = e -> {
            String font = (String) fontBox.getSelectedItem();
            if (sizeBox.getSelectedIndex()==0)
                txtArea.setFont(new Font(font,Font.PLAIN,10));
            else if (sizeBox.getSelectedIndex()==1)
                txtArea.setFont(new Font(font,Font.PLAIN,11));
            else if (sizeBox.getSelectedIndex()==2)
                txtArea.setFont(new Font(font,Font.PLAIN,12));
            else if (sizeBox.getSelectedIndex()==3)
                txtArea.setFont(new Font(font,Font.PLAIN,13));
            else if (sizeBox.getSelectedIndex()==4)
                txtArea.setFont(new Font(font,Font.PLAIN,14));
            else if (sizeBox.getSelectedIndex()==5)
                txtArea.setFont(new Font(font,Font.PLAIN,15));
            else if (sizeBox.getSelectedIndex()==6)
                txtArea.setFont(new Font(font,Font.PLAIN,16));
            else if (sizeBox.getSelectedIndex()==7)
                txtArea.setFont(new Font(font,Font.PLAIN,17));
            else if (sizeBox.getSelectedIndex()==8)
                txtArea.setFont(new Font(font,Font.PLAIN,18));
        };
        sizeBox.addActionListener(fontSizeListener);

        fontBox.addItem("Set Font");
        fontBox.addItem(Font.SANS_SERIF);
        fontBox.addItem(Font.DIALOG);
        fontBox.addItem(Font.MONOSPACED);
        fontBox.addItem("Times new roman");
        ActionListener fontListener = e -> {
            int fontSize = sizeBox.getSelectedIndex()+10;
            txtArea.setFont(new Font(Objects.requireNonNull(fontBox.getSelectedItem()).toString(),Font.PLAIN,fontSize));
        };
        ActionListener checkBoxListener = e -> {
            String font = (String) fontBox.getSelectedItem();
            int fontSize = sizeBox.getSelectedIndex()+10;
            int mode =0;
            if (boldCheckbox.isSelected())
                mode += Font.BOLD;
            if (italicCheckbox.isSelected())
                mode += Font.ITALIC;
            txtArea.setFont(new Font(font,mode,fontSize));
        };
        boldCheckbox.addActionListener(checkBoxListener);
        italicCheckbox.addActionListener(checkBoxListener);
        fontBox.addActionListener(fontListener);

        eastPanel.add(fontBox);
        eastPanel.add(sizeBox);
        Border border = BorderFactory.createTitledBorder("settings");
        Border runBorder = BorderFactory.createRaisedBevelBorder();
        Border panelBorder = BorderFactory.createBevelBorder(15,Color.CYAN,Color.blue,Color.black,Color.ORANGE);
        eastPanel.setBorder(border);
        southPanel.setBorder(runBorder);
        panel.setBorder(panelBorder);
        southPanel.add(button);
        panel.add(southPanel,BorderLayout.SOUTH);
        panel.add(eastPanel,BorderLayout.EAST);
        panel.setBackground(Color.PINK.darker());
        eastPanel.setBackground(Color.LIGHT_GRAY);
        add(panel);
    }
}
