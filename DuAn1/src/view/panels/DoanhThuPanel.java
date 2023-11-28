/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import com.raven.datechooser.DateChooser;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import services.ThongKeService;
import services.impl.HoaDonServiceImpl;
import services.impl.SanPhamImpl;
import services.impl.ThongKeImpl;

/**
 *
 * @author Admin
 */
public class DoanhThuPanel extends javax.swing.JPanel {
    private DateChooser date = new DateChooser();
    ThongKeImpl tkService = new ThongKeImpl();
    HoaDonServiceImpl hdServiceImpl = new HoaDonServiceImpl();
    SanPhamImpl spService = new SanPhamImpl();
    /**
     * Creates new form Form_1
     */
    public DoanhThuPanel() {
        initComponents();
        date.setTextField(txt_DateDay);
        date.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        LocalDate dt =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        txt_DateDay.setText(dt.format(formatter));
        
        jLabel2.setSize(250, 145);
        jLabel4.setSize(250, 145);
        jLabel6.setSize(250, 145);
        
        if(rdo_ngay.isSelected()){
            LoadDataThongKe();
            txt_Month.setEnabled(false);
            txt_Year.setEnabled(false);
        }
        String relativePath = "src\\view\\icon";
        String currentWorkingDirectory = System.getProperty("user.dir");
        Path fullPath1 = Paths.get(currentWorkingDirectory, relativePath);
        String fullPath = fullPath1.toString() +"\\" + "dt2.jpg";
        String path2 = fullPath1.toString() +"\\" + "dt4.jpg";
        String path3 = fullPath1.toString() +"\\" + "dt1.jpg";
        String path1= fullPath;
        jLabel2.setIcon(ResizeImage(String.valueOf(path1)));
        jLabel4.setIcon(ResizeImage(String.valueOf(path3)));
        jLabel6.setIcon(ResizeImage(String.valueOf(path2)));
        
        LoadDefaultDate();
        LoadDefaultMoth();
        LoadDefaultYear();
        LoadTOPSP();
    }
    
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    private void LoadDefaultDate(){
        String date = txt_DateDay.getText();
        LocalDate currentDate = LocalDate.now();
        // Chuyển đổi ngày thành chuỗi với định dạng mong muốn (ở đây là "dd/MM/yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(formatter);
        String TestDate ="24-11-2023";
        System.out.println("dateString "+dateString);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getALLHDCT1(dateString);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            String stringValue = String.valueOf(tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel3.setText("Tổng DT "+dateString+": "+decimalFormat.format(tong)+" VND");
            //jLabel3.setText("Tổng DT "+dateString+": "+stringValue+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LoadDefaultMoth(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String stringValue = String.valueOf(currentMonth);
        System.out.println("stringValue "+stringValue);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getHDByMonth(stringValue);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel5.setText("Tổng DT Tháng"+stringValue+": "+decimalFormat.format(tong)+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadDefaultYear(){
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String stringValue = String.valueOf(currentYear);
        System.out.println("stringValue "+stringValue);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getHDByYear(stringValue);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel7.setText("Tổng DT Năm"+stringValue+": "+decimalFormat.format(tong)+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadTOPSP(){
        DefaultTableModel tb = (DefaultTableModel) table2.getModel();
        tb.setRowCount(0);
        int checkID = 0;
        int STT = 1;
        List<HoaDonChiTiet> hdct = tkService.getSumSoLuong();
        for (HoaDonChiTiet x : hdct) {
            //System.out.println("Test getIDSP: " + x.getSanPhamChiTiet().getId());
//            int sumSoLuong = tkService.getSumSoLuong(x.getSanPhamChiTiet().getId());
//            System.out.println("Test Số lượng with IDSP: " + sumSoLuong);
            
            if (x.getSanPhamChiTiet().getId() == checkID) {

            } else {
                String TenSP = spService.getNameID(x.getSanPhamChiTiet().getId_SanPham());
                int sumSoLuong = x.getSoLuong();
                //System.out.println("Test Số lượng with IDSP: " + sumSoLuong);
                tb.addRow(new Object[]{
                    STT, TenSP, sumSoLuong,x.getSanPhamChiTiet().getGia()
                });
                STT++;
            }
            checkID = x.getSanPhamChiTiet().getId();
        }
    }

    private void LoadDataThongKe(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        //List<HoaDonChiTiet> hdct=tkService.getALLHDCT();
        String date = txt_DateDay.getText();
        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateNow = dateFormat.parse(date);
//            java.sql.Date sqlDate = new java.sql.Date(dateNow.getTime());
//            System.out.println("formattedDate: "+sqlDate);
            List<HoaDon> hdct = tkService.getALLHDCT1(date);
            int STT = 1;
            for (HoaDon x : hdct) {
//            int id_hoaDon= x.getHoaDon().getId();
//            System.out.println("id_hD: "+id_hoaDon);
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void LoadThongKeMonth(){
        String Month = txt_Month.getText();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        try {
            List<HoaDon> hdct = tkService.getHDByMonth(Month);
            int STT = 1;
            for (HoaDon x : hdct) {
//            int id_hoaDon= x.getHoaDon().getId();
//            System.out.println("id_hD: "+id_hoaDon);
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadThongKeYear(){
        String Year = txt_Year.getText();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        try {
            List<HoaDon> hdct = tkService.getHDByYear(Year);
            int STT = 1;
            for (HoaDon x : hdct) {
//            int id_hoaDon= x.getHoaDon().getId();
//            System.out.println("id_hD: "+id_hoaDon);
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txt_DateDay = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new view.component.Table();
        rdo_ngay = new javax.swing.JRadioButton();
        rdo_thang = new javax.swing.JRadioButton();
        txt_Month = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        rdo_nam = new javax.swing.JRadioButton();
        txt_Year = new javax.swing.JTextField();
        lbl_Tong = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new view.component.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thống Kê Doanh Thu");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Ngày Tạo", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        buttonGroup1.add(rdo_ngay);
        rdo_ngay.setSelected(true);
        rdo_ngay.setText("Thống Kê Theo ngày");
        rdo_ngay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_ngayItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdo_thang);
        rdo_thang.setText("Thống Kê Theo Tháng");
        rdo_thang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_thangItemStateChanged(evt);
            }
        });

        jButton1.setText("Thống Kê");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_nam);
        rdo_nam.setText("Thống Kê Theo Năm");
        rdo_nam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_namItemStateChanged(evt);
            }
        });
        rdo_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_namActionPerformed(evt);
            }
        });

        lbl_Tong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Tong.setText("Tổng Doanh Thu:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Sản Phẩm", "Số Lượng", "Giá"
            }
        ));
        jScrollPane2.setViewportView(table2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(rdo_ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_DateDay, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdo_thang)
                                            .addComponent(txt_Month, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_Year)
                                            .addComponent(rdo_nam)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Tong, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_ngay)
                    .addComponent(rdo_nam)
                    .addComponent(rdo_thang)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_DateDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lbl_Tong)
                .addGap(58, 58, 58))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(txt_DateDay.getText());
        if(rdo_ngay.isSelected()){
            LoadDataThongKe();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            //float floatValue = Float.parseFloat(Tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            //String formattedNumber = decimalFormat.format(Tong);
            lbl_Tong.setText("Tổng Doanh Thu: "+decimalFormat.format(Tong)+" VND");
        }
        
        if(rdo_thang.isSelected()){
            LoadThongKeMonth();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            //float floatValue = Float.parseFloat(Tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            //String formattedNumber = decimalFormat.format(Tong);
            lbl_Tong.setText("Tổng Doanh Thu: "+decimalFormat.format(Tong)+" VND");
            jLabel5.setText("Tổng Doanh Thu "+txt_Month.getText()+":"+decimalFormat.format(Tong)+" VND");
        }
        
        if(rdo_nam.isSelected()){
            LoadThongKeYear();
           double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            lbl_Tong.setText("Tổng Doanh Thu: "+decimalFormat.format(Tong)+" VND"); 
            jLabel7.setText("Tổng Doanh Thu "+txt_Year.getText()+":"+decimalFormat.format(Tong)+" VND");
        }
        
        System.out.println("width: "+jLabel2.getWidth() +" +"+jLabel4.getWidth() +"+"+jLabel6.getWidth() );
        System.out.println("Height: "+jLabel2.getHeight()+" +"+jLabel4.getHeight()+"+"+jLabel6.getHeight());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdo_thangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_thangItemStateChanged
        // TODO add your handling code here:
        txt_DateDay.setText("");
        txt_Year.setText("");
        txt_DateDay.setEnabled(false);
        txt_Month.setEnabled(true);
        txt_Year.setEnabled(false);
    }//GEN-LAST:event_rdo_thangItemStateChanged

    private void rdo_namItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_namItemStateChanged
        // TODO add your handling code here:
        txt_DateDay.setText("");
        txt_Month.setText("");
        txt_DateDay.setEnabled(false);
        txt_Month.setEnabled(false);
        txt_Year.setEnabled(true);
        
    }//GEN-LAST:event_rdo_namItemStateChanged

    private void rdo_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_namActionPerformed

    private void rdo_ngayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_ngayItemStateChanged
        // TODO add your handling code here:
        LocalDate dt =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        txt_DateDay.setText(dt.format(formatter));
        //txt_DateDay.setText("");
        txt_Month.setText("");
        txt_DateDay.setEnabled(true);
        txt_Month.setEnabled(false);
        txt_Year.setEnabled(false);
    }//GEN-LAST:event_rdo_ngayItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Tong;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_ngay;
    private javax.swing.JRadioButton rdo_thang;
    private view.component.Table table1;
    private view.component.Table table2;
    private javax.swing.JTextField txt_DateDay;
    private javax.swing.JTextField txt_Month;
    private javax.swing.JTextField txt_Year;
    // End of variables declaration//GEN-END:variables
}
