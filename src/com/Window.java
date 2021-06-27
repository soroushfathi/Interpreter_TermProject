package com;

import javax.swing.*;
import java.awt.*;
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
    public Window() throws HeadlessException {
        panel = new JPanel();
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
        panel.add(button);
        scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
//        panel.add(txtArea);
        add(panel);
    }
}
