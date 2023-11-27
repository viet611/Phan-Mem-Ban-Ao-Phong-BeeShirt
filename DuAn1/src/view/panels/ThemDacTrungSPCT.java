/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

package view.panels;

import domainmodels.ChatLieu;
import domainmodels.HoaTiet;
import domainmodels.KichThuoc;
import domainmodels.KieuDang;
import domainmodels.MauSac;
import domainmodels.MuaPhuHop;
import domainmodels.MucDichSuDung;
import domainmodels.SanPham;
import domainmodels.ThuongHieu;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import services.impl.ChatLieuImpl;
import services.impl.HoaTietImpl;
import services.impl.KichThuongImpl;
import services.impl.KieuDangImpl;
import services.impl.MDSDImpl;
import services.impl.MauSacImpl;
import services.impl.MuaPhuHopImpl;
import services.impl.SanPhamImpl;
import services.impl.ThuongHieuImpl;
import ultilities.MsgBox;

/**
 *
 * @author Admin
 */
public class ThemDacTrungSPCT extends javax.swing.JDialog {
    SanPhamImpl spService = new SanPhamImpl();
    ThuongHieuImpl thService = new ThuongHieuImpl();
    MauSacImpl msService = new MauSacImpl();
    KichThuongImpl ktService = new KichThuongImpl();
    ChatLieuImpl clService = new ChatLieuImpl();
    KieuDangImpl kdService = new KieuDangImpl();
    MuaPhuHopImpl mphService = new MuaPhuHopImpl();
    MDSDImpl msdsService = new MDSDImpl();
    HoaTietImpl htService = new HoaTietImpl();
    ThemSPCT createDialogForm;
    
    private int check;
    /** Creates new form ThemDacTrungSPCT */
    public ThemDacTrungSPCT() {
        //super(parent, modal);
        initComponents();
        LoadData();
        
        
    }
    public void setText(int text){
        int checkForm=text;
        
    }
    
    public void setCreateDialogForm(ThemSPCT createDialogForm) {
        this.createDialogForm = createDialogForm;
    }
    
    private void LoadDataKT(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<KichThuoc> listKT = ktService.getALL();
        int count=1;
        for(KichThuoc x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataKD(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<KieuDang> listKT = kdService.getAll();
        int count=1;
        for(KieuDang x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataMS(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<MauSac> listKT = msService.getALL();
        int count=1;
        for(MauSac x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataCL(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<ChatLieu> listKT = clService.getALL();
        int count=1;
        for(ChatLieu x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataMPH(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<MuaPhuHop> listKT = mphService.getALL();
        int count=1;
        for(MuaPhuHop x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataTH(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<ThuongHieu> listKT = thService.getALL();
        int count=1;
        for(ThuongHieu x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataHT(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<HoaTiet> listKT = htService.getAll();
        int count=1;
        for(HoaTiet x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataMDSD(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<MucDichSuDung> listKT = msdsService.getAll();
        int count=1;
        for(MucDichSuDung x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    
    private void LoadDataSP(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<SanPham> listSP = spService.getAll();
        int count = 1;
        for (SanPham x : listSP) {
            int trangThai=x.getTrang_thai();
            int idSP= x.getId();
            //int getCount = spctService.getCountSP(idSP);
            //System.out.println("Tesst trangThai: "+trangThai);
            boolean trang_Thai=true;
            if(trangThai ==0){
                trang_Thai=true;
            }else{
                trang_Thai =false;
            }
            tb.addRow(new Object[]{
                count,
                x.getMa(),
                x.getTen(),
                trang_Thai
            });
            count++;
        }
        
    }
    
    public void LoadData(){
        if(rdo_KT.isSelected()){
            LoadDataKT();
        }
        if(rdo_CL.isSelected()){
            LoadDataCL();
        }
        if(rdo_HT.isSelected()){
            LoadDataHT();
        }
        if(rdo_KD.isSelected()){
            LoadDataKD();
        }
        if(rdo_MDSD.isSelected()){
            LoadDataMDSD();
        }
        if(rdo_MPH.isSelected()){
            LoadDataMPH();
        }
        if(rdo_MS.isSelected()){
            LoadDataMS();
        }
        if(rdo_TH.isSelected()){
            LoadDataTH();
        }
        if(rdo_SP.isSelected()){
            LoadDataSP();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new view.component.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_Ten = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        rdo_KT = new javax.swing.JRadioButton();
        rdo_CL = new javax.swing.JRadioButton();
        rdo_KD = new javax.swing.JRadioButton();
        rdo_TH = new javax.swing.JRadioButton();
        rdo_MS = new javax.swing.JRadioButton();
        rdo_HT = new javax.swing.JRadioButton();
        rdo_MPH = new javax.swing.JRadioButton();
        rdo_MDSD = new javax.swing.JRadioButton();
        rdo_SP = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma", "Ten", "Trang Thai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Them Dac Trung San Pham Chi Tiet");

        jLabel2.setText("Tên");

        jButton1.setText("Thêm Đặc Trưng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_KT);
        rdo_KT.setText("Kích Thước");
        rdo_KT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_KTItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_CL);
        rdo_CL.setText("Chất Liệu");
        rdo_CL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_CLItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_KD);
        rdo_KD.setText("Kiểu Dáng");
        rdo_KD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_KDItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_TH);
        rdo_TH.setText("Thương Hiệu ");
        rdo_TH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_THItemStateChanged(evt);
            }
        });
        rdo_TH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_THActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_MS);
        rdo_MS.setText("Màu Sắc");
        rdo_MS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MSItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_HT);
        rdo_HT.setText("Hoạ Tiết");
        rdo_HT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_HTItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_MPH);
        rdo_MPH.setText("Mùa Phù Hợp");
        rdo_MPH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MPHItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_MDSD);
        rdo_MDSD.setText("Mục Đích Sử Dụng");
        rdo_MDSD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MDSDItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_SP);
        rdo_SP.setSelected(true);
        rdo_SP.setText("Sản Phẩm");
        rdo_SP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_SPItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_TH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_MS)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_HT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_SP)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_KT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdo_CL)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdo_MDSD)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rdo_KD)
                                                .addGap(49, 49, 49)
                                                .addComponent(rdo_MPH)))))))
                        .addGap(0, 221, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_KD)
                    .addComponent(rdo_MPH)
                    .addComponent(rdo_SP)
                    .addComponent(rdo_KT)
                    .addComponent(rdo_CL))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_TH)
                    .addComponent(rdo_MS)
                    .addComponent(rdo_HT)
                    .addComponent(rdo_MDSD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_THActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_THActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_THActionPerformed

    private void rdo_SPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_SPItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataSP();
        }
    }//GEN-LAST:event_rdo_SPItemStateChanged

    private void rdo_KTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_KTItemStateChanged
        // TODO add your handling code here:
        LoadDataKT();
    }//GEN-LAST:event_rdo_KTItemStateChanged

    private void rdo_CLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_CLItemStateChanged
        // TODO add your handling code here:
        LoadDataCL();
    }//GEN-LAST:event_rdo_CLItemStateChanged

    private void rdo_KDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_KDItemStateChanged
        // TODO add your handling code here:
        LoadDataKD();
    }//GEN-LAST:event_rdo_KDItemStateChanged

    private void rdo_MPHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MPHItemStateChanged
        // TODO add your handling code here:
        LoadDataMPH();
    }//GEN-LAST:event_rdo_MPHItemStateChanged

    private void rdo_THItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_THItemStateChanged
        // TODO add your handling code here:
        LoadDataTH();
    }//GEN-LAST:event_rdo_THItemStateChanged

    private void rdo_MSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MSItemStateChanged
        // TODO add your handling code here:
        LoadDataMS();
    }//GEN-LAST:event_rdo_MSItemStateChanged

    private void rdo_HTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_HTItemStateChanged
        // TODO add your handling code here:
        LoadDataHT();
    }//GEN-LAST:event_rdo_HTItemStateChanged

    private void rdo_MDSDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MDSDItemStateChanged
        // TODO add your handling code here:
        LoadDataMDSD();
    }//GEN-LAST:event_rdo_MDSDItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txt_Ten.getText()==""){
            MsgBox.alert(this, "Tên Không Để Trống!");
            return;
        }
        if(MsgBox.confirm(this, "Bạn Có Muốn Thêm không?")){
            if(rdo_SP.isSelected()){
                try {
                    SanPham sp = new SanPham();
                    String prefix = "SP";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    int trangThai=0;
                    sp.setTrang_thai(trangThai);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);
                    spService.InsertSP(sp);
                    LoadDataSP();
                    MsgBox.alert(this, "Thêm Sản Phẩm Thành Công!");
                    txt_Ten.setText("");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(rdo_KT.isSelected()){
                try {
                    KichThuoc sp = new KichThuoc();
                    String prefix = "KT";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    ktService.InsertKT(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataKT();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_CL.isSelected()){
                try {
                    ChatLieu sp = new ChatLieu();
                    String prefix = "CL";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    clService.InsertCL(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataCL();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_KD.isSelected()){
                try {
                    KieuDang sp = new KieuDang();
                    String prefix = "KD";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    kdService.InsertSP(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataKD();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_MPH.isSelected()){
                try {
                    MuaPhuHop sp = new MuaPhuHop();
                    String prefix = "MPH";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    mphService.InsertMPH(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataMPH();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_TH.isSelected()){
                try {
                    ThuongHieu sp = new ThuongHieu();
                    String prefix = "TH";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    thService.InsertTH(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataTH();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_MS.isSelected()){
                try {
                    MauSac sp = new MauSac();
                    String prefix = "MS";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    msService.InsertMS(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataMS();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_HT.isSelected()){
                try {
                    HoaTiet sp = new HoaTiet();
                    String prefix = "HT";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    htService.InsertHT(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataHT();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            if(rdo_MDSD.isSelected()){
                try {
                    MucDichSuDung sp = new MucDichSuDung();
                    String prefix = "MDSD";
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
                    String maSP = prefix + String.format("%02d", randomNumber);
                    //System.out.println(maSP);
                    sp.setMa(maSP);
                    String tenSP = txt_Ten.getText();
                    sp.setTen(tenSP);
                    String tenNT = null;
                    sp.setTao_boi(tenNT);
                    String tenNS = null;
                    sp.setSua_boi(tenNS);
                    boolean daXoa = true;
                    sp.setDa_xoa(daXoa);

                    int trangThai = 0;
                    sp.setTrang_thai(trangThai);
                    msdsService.InsertMDSD(sp);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataMDSD();
                    txt_Ten.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
        createDialogForm.LoadCombobox();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemDacTrungSPCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemDacTrungSPCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemDacTrungSPCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemDacTrungSPCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemDacTrungSPCT dialog = new ThemDacTrungSPCT();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_CL;
    private javax.swing.JRadioButton rdo_HT;
    private javax.swing.JRadioButton rdo_KD;
    private javax.swing.JRadioButton rdo_KT;
    private javax.swing.JRadioButton rdo_MDSD;
    private javax.swing.JRadioButton rdo_MPH;
    private javax.swing.JRadioButton rdo_MS;
    private javax.swing.JRadioButton rdo_SP;
    private javax.swing.JRadioButton rdo_TH;
    private view.component.Table table1;
    private javax.swing.JTextField txt_Ten;
    // End of variables declaration//GEN-END:variables

}
