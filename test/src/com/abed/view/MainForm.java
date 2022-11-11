package com.abed.view;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JTextField txtFirstName;
    private JTextField textField2;
    private JTextField textField3;
    private JButton openButton;
    private JButton secondButtonButton;
    private JPanel rootPanel;

    public MainForm() {
        openButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(rootPanel,"Hallowbang","Information",
                    JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(rootPanel,"Error aweu","Error",
                    JOptionPane.ERROR_MESSAGE);
            int result = JOptionPane.showConfirmDialog(rootPanel,"Are you sure want to continue?");
            if(result==JOptionPane.YES_OPTION){
                String message = JOptionPane.showInputDialog(rootPanel,"Input message");
                JOptionPane.showMessageDialog(rootPanel,message,"Information Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        secondButtonButton.addActionListener(e -> {
            String firstName =  txtFirstName.getText();
            String lastName = textField2.getText();
            int age = Integer.parseInt(textField3.getText());
            JOptionPane.showMessageDialog(rootPanel,firstName+" "+lastName+" "+age,
                    "Information",JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

