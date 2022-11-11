/**
 * @author AbednegoSteven - 1972009
 */
package com.abednego.entity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BangunDatarTableModel extends AbstractTableModel {
    private List<BangunDatar> listBangunDatar;
    private final String[] COLUMNS = {"Tipe","Panjang","Lebar","Keliling","Luas"};

    public BangunDatarTableModel(List<BangunDatar> listBangunDatar) {
        this.listBangunDatar = listBangunDatar;
    }

    @Override
    public int getRowCount() {
        return listBangunDatar.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex){
            case 0 -> listBangunDatar.get(rowIndex).toString();
            case 1 -> listBangunDatar.get(rowIndex).getPanjang();
            case 2 -> listBangunDatar.get(rowIndex).getLebar();
            case 3 -> listBangunDatar.get(rowIndex).getKeliling();
            case 4 -> listBangunDatar.get(rowIndex).getLuas();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    public Class<?> getColumnClass(int columnIndex){
        if (getValueAt(0,columnIndex)!=null){
            return getValueAt(0,columnIndex).getClass();
        }
        return Object.class;
    }
}
