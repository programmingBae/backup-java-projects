/**
 * @author AbednegoSteven - 1972009
 */
package com.abednego.entity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KalkulatorBangunDatar {
    private JComboBox comboBox1;
    private JTextField panjangTxt;
    private JTextField lebarTxt;
    private JButton tambahButton;
    private JTable table1;
    private JSplitPane rootPanel;
    private BangunDatarTableModel bangunDatarTableModel;
    private List<BangunDatar> bangunDatarList;

    public KalkulatorBangunDatar(){
        bangunDatarList = new ArrayList<>();
        bangunDatarTableModel = new BangunDatarTableModel(bangunDatarList);
        table1.setModel(bangunDatarTableModel);
        table1.setAutoCreateRowSorter(true);


        tambahButton.addActionListener(e -> {
            if (panjangTxt.getText().isEmpty() || lebarTxt.getText().isEmpty()  ){
                JOptionPane.showMessageDialog(rootPanel,"Please fill in all field",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }else {
                try{
                    double panjang = Double.parseDouble(panjangTxt.getText());
                    double lebar = Double.parseDouble(lebarTxt.getText());
                    String tipe = (String) comboBox1.getSelectedItem();
                    if (tipe == "Persegi"){
                        Persegi persegi = new Persegi();
                        persegi.setLebar(lebar);
                        persegi.setPanjang(panjang);
                        bangunDatarList.add(persegi);
                        bangunDatarTableModel.fireTableDataChanged();
                        clearAndReset();
                    } else {
                        Elips elips = new Elips();
                        elips.setLebar(lebar);
                        elips.setPanjang(panjang);
                        bangunDatarList.add(elips);
                        bangunDatarTableModel.fireTableDataChanged();
                        clearAndReset();
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(rootPanel, "Please only input decimal for Panjang and Lebar",
                          "Error",JOptionPane.ERROR_MESSAGE );
                }
            }
        });
    }
    private void clearAndReset(){
        panjangTxt.setText("");
        lebarTxt.setText("");
        table1.clearSelection();

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator Bangun Datar");
        frame.setContentPane(new KalkulatorBangunDatar().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
