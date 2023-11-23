/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.model.StatusType;

/**
 *
 * @author Admin
 */
public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setRowHeight(40);
        setGridColor(new Color(230, 230, 230));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                if (column == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }

        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column != 4) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    comp.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if (isSelected) {
                        comp.setForeground(new Color(15, 89, 140));
                    } else {
                        comp.setForeground(new Color(102, 102, 102));
                    }
                    return comp;
                } else {
                    StatusType type = (StatusType) value;
                    CellStatus cell = new CellStatus(type);
                    return cell;
                }
            }

        });
    }

    public void addRow(Object[] row){
        DefaultTableModel dtm = (DefaultTableModel) getModel();
        dtm.addRow(row);
    }
}
