package com;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Window extends JFrame {
    JButton button;
    String string;
    JPanel panel;
    JPanel southPanel;
    JPanel eastPanel;
    JTextArea txtArea;
    public Window() throws HeadlessException {
        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("file");
        menuBar.add(fileMenu);
        Action loadAction = new AbstractAction("load file",new ImageIcon("src\\assets\\load.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("enter the path to load file");
                try {
                    String[] lines = Files.readAllLines(Path.of(path)).toArray(new String[0]);
                    StringBuilder line = new StringBuilder();
                    for (String str:lines){
                        line.append(str);
                        line.append("\r\n");
                    }
                    txtArea.setText(line.toString());
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        };
        Action saveAction = new AbstractAction("save file",new ImageIcon("src\\assets\\save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("enter the path to save the file");
                try {
                    FileWriter fr = new FileWriter(path);
                    BufferedWriter br = new BufferedWriter(fr);
                    String lines = txtArea.getText();
                    br.write(lines);
                    br.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        Action saveRunAction = new AbstractAction("save compiled",new ImageIcon("src\\assets\\save run.jpg")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fw = new FileWriter("src\\files\\file.txt",false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    string = txtArea.getText();
                    bw.write(string);
                    bw.close();
                    Main.outputPath = JOptionPane.showInputDialog("enter the path ","ex : C:\\\\folderName\\\\fileName.txt");
                    Main.read_compute(new File("src\\files\\file.txt"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    Main.intValues.clear();
                    Main.floatValues.clear();
                }
                JOptionPane.showMessageDialog(null,"Code Compiled");
                System.out.println("-------------------------------------------------------------------");
            }
        };
        Action clearAction = new AbstractAction("clear",new ImageIcon("src\\assets\\clear.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtArea.setText("");
            }
        };
        fileMenu.add(loadAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveRunAction);
        fileMenu.add(clearAction);

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
        setSize(515,350);
        setResizable(false);
        txtArea = new JTextArea(10,30);
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
                    Main.outputPath = JOptionPane.showInputDialog("enter the path ","ex : C:\\\\folderName\\\\fileName.txt");
                    Main.read_compute(new File("src\\files\\file.txt"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    Main.intValues.clear();
                    Main.floatValues.clear();
                }
                JOptionPane.showMessageDialog(null,"Code Compiled");
                System.out.println("-------------------------------------------------------------------");
            }
        });
//        string = txtArea.getText();
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
            if (sizeBox.getSelectedIndex()==1)
                txtArea.setFont(new Font(font,Font.PLAIN,10));
            else if (sizeBox.getSelectedIndex()==2)
                txtArea.setFont(new Font(font,Font.PLAIN,11));
            else if (sizeBox.getSelectedIndex()==3)
                txtArea.setFont(new Font(font,Font.PLAIN,12));
            else if (sizeBox.getSelectedIndex()==4)
                txtArea.setFont(new Font(font,Font.PLAIN,13));
            else if (sizeBox.getSelectedIndex()==5)
                txtArea.setFont(new Font(font,Font.PLAIN,14));
            else if (sizeBox.getSelectedIndex()==6)
                txtArea.setFont(new Font(font,Font.PLAIN,15));
            else if (sizeBox.getSelectedIndex()==7)
                txtArea.setFont(new Font(font,Font.PLAIN,16));
            else if (sizeBox.getSelectedIndex()==8)
                txtArea.setFont(new Font(font,Font.PLAIN,17));
            else if (sizeBox.getSelectedIndex()==9)
                txtArea.setFont(new Font(font,Font.PLAIN,18));
        };
        sizeBox.addActionListener(fontSizeListener);

        fontBox.addItem("Set Font");
        fontBox.addItem(Font.SANS_SERIF);
        fontBox.addItem(Font.DIALOG);
        fontBox.addItem(Font.MONOSPACED);
        fontBox.addItem("Times new roman");
        ActionListener fontListener = e -> {
            int fontSize = sizeBox.getSelectedIndex()+9;
            txtArea.setFont(new Font(Objects.requireNonNull(fontBox.getSelectedItem()).toString(),Font.PLAIN,fontSize));
        };
        ActionListener checkBoxListener = e -> {
            String font = (String) fontBox.getSelectedItem();
            int fontSize = sizeBox.getSelectedIndex()+9;
            int mode = 0;
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
