/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import view.model.StatusType;

/**
 *
 * @author Admin
 */
public class SanPhamPanel extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public SanPhamPanel() {
        initComponents();
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelBorder1 = new view.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new view.component.Table();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sản phẩm");

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Status"
            }
        ));
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private view.PanelBorder panelBorder1;
    private view.component.Table table;
    // End of variables declaration//GEN-END:variables
}
