/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.panels;

import domainmodels.SanPhamCT;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import services.impl.ChatLieuImpl;
import services.impl.HinhAnhImpl;
import services.impl.HoaTietImpl;
import services.impl.KichThuongImpl;
import services.impl.KieuDangImpl;
import services.impl.MDSDImpl;
import services.impl.MauSacImpl;
import services.impl.MuaPhuHopImpl;
import services.impl.SanPhamCTImpl;
import services.impl.SanPhamImpl;
import services.impl.ThuongHieuImpl;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamCT extends javax.swing.JDialog {
SanPhamImpl spService = new SanPhamImpl();
    ThuongHieuImpl thService = new ThuongHieuImpl();
    MauSacImpl msService = new MauSacImpl();
    KichThuongImpl ktService = new KichThuongImpl();
    ChatLieuImpl clService = new ChatLieuImpl();
    KieuDangImpl kdService = new KieuDangImpl();
    MuaPhuHopImpl mphService = new MuaPhuHopImpl();
    MDSDImpl msdsService = new MDSDImpl();
    HoaTietImpl htService = new HoaTietImpl();
    HinhAnhImpl haService = new HinhAnhImpl();
    
    SanPhamCTImpl spctService = new SanPhamCTImpl();
    
    private int id_SPCT;
    /**
     * Creates new form ChiTietSanPhamCT
     */
    public ChiTietSanPhamCT() {
        //super(parent, modal); java.awt.Frame parent, boolean modal
        initComponents();
        //lbl_QRCode.setSize(200, 230);
        lbl_HinhAnh.setSize(194,113);
    }
    
    private void LoadData(){
        List<SanPhamCT> listSPCT = spctService.getALLSPCTByID(id_SPCT);
        for(SanPhamCT x: listSPCT){
            //id_sanPham
            int id_sanPham = x.getId_SanPham();
            String tenSP = spService.getNameID(id_sanPham);
            //System.out.println("name SP" +MaSP);
            //cbo_TenSP.setSelectedItem(tenSP);
            lbl_TenSp.setText(tenSP);
            
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS=msService.getNameByID(id_mauSac);
            //cbo_MauSac.setSelectedItem(nameMS);
            txt_mauSac.setText(nameMS);
            
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            //cbo_ThuongHieu.setSelectedItem(nameTH);
            txt_thuongHieu.setText(nameTH);
            
            //id kich thuoc
            int id_kichThuoc =x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            //cbo_KichThuoc.setSelectedItem(nameKT);
            txt_kichThuoc.setText(nameKT);
            
            //id_KieuDang;
            int id_kieuDang=x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            //cbo_KieuDang.setSelectedItem(nameKD);
            txt_kieuDang.setText(nameKD);
            
            //id_MuaPhuHop;
            int id_muaPhuHop =x.getId_MuaPhuHop();
            String nameMPH= mphService.getNameByID(id_muaPhuHop);
            //cbo_MPH.setSelectedItem(nameMPH);
            txt_MPH.setText(nameMPH);
            
            //id_ChatLieu;
            int id_chatLieu=x.getId_ChatLieu();
            String nameCL =clService.getNameByID(id_chatLieu);
            //cbo_ChatLieu.setSelectedItem(nameCL);
            txt_chatLieu.setText(nameCL);
            
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            //cbo_HoaTiet.setSelectedItem(nameHT);
            txt_HoaTiet.setText(nameHT);
            
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
            String duongDanHA = haService.getNameByID(id_hinhAnh);
            lbl_HinhAnh.setIcon(ResizeImage(String.valueOf(duongDanHA)));
            
            //so_Luong;
            int so_Luong = x.getSo_Luong();
            String so_Luong_String = Integer.toString(so_Luong);
            txt_soLuong.setText(so_Luong_String);
            //gia;
            float gia=x.getGia();
            String gia_String = Float.toString(gia);
            txt_Gia.setText(gia_String);
            
            //hình ảnh QR
            String qrCode = x.getQrCode();
            if(qrCode==null){
                lbl_QRCode.setText("Không có mã QR");
            }else{
                lbl_QRCode.setIcon(ResizeImageQR(String.valueOf(qrCode)));
            }
            
            
        }
    }
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lbl_HinhAnh.getWidth(), lbl_HinhAnh.getHeight(), Image.SCALE_SMOOTH);
//        int wwifth = lbl_HinhAnh.getWidth();
//        int height = lbl_HinhAnh.getHeight();
//        System.out.println("wwidth height "+wwifth +"="+height);
        //Image newImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    public ImageIcon ResizeImageQR(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lbl_QRCode.getWidth(), lbl_QRCode.getHeight(), Image.SCALE_SMOOTH);
//        int wwifth = lbl_HinhAnh.getWidth();
//        int height = lbl_HinhAnh.getHeight();
//        System.out.println("wwidth height "+wwifth +"="+height);
        //Image newImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }

    public Integer setEditIDData(int ma){
        id_SPCT=ma;
        LoadData();
        return ma;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_TenSp = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Gia = new javax.swing.JTextField();
        txt_soLuong = new javax.swing.JTextField();
        lbl_QRCode = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_HinhAnh = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_mauSac = new javax.swing.JTextField();
        txt_kieuDang = new javax.swing.JTextField();
        txt_kichThuoc = new javax.swing.JTextField();
        txt_thuongHieu = new javax.swing.JTextField();
        txt_HoaTiet = new javax.swing.JTextField();
        txt_MPH = new javax.swing.JTextField();
        txt_chatLieu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl_TenSp.setText("Tên Sản Phấm");

        jLabel2.setText("Kích Thước");

        jLabel3.setText("Màu Sắc");

        jLabel4.setText("Chất Liệu");

        jLabel5.setText("Thương Hiệu");

        jLabel6.setText("Kiểu Dáng");

        jLabel7.setText("Mùa Phù Hơp");

        jLabel8.setText("Hoạ Tiết");

        jLabel9.setText("Giá");

        jLabel10.setText("Số Lượng");

        txt_Gia.setEnabled(false);

        jLabel12.setText("hình Ảnh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setText("Thông Tin Chi Tiết");

        txt_mauSac.setEnabled(false);

        txt_kieuDang.setEnabled(false);

        txt_kichThuoc.setEnabled(false);

        txt_thuongHieu.setEnabled(false);

        txt_HoaTiet.setEnabled(false);

        txt_MPH.setToolTipText("");
        txt_MPH.setEnabled(false);

        txt_chatLieu.setEnabled(false);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8))
                                        .addGap(113, 113, 113))
                                    .addComponent(txt_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_thuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_HoaTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(235, 235, 235)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_kieuDang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                            .addComponent(txt_mauSac, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_Gia, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lbl_QRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_soLuong)
                                    .addComponent(txt_MPH, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(txt_chatLieu)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jLabel14)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TenSp)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_HoaTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_QRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println("qr w "+lbl_QRCode.getWidth());
        System.out.println("qr h "+lbl_QRCode.getHeight());
        System.out.println("qr w "+lbl_HinhAnh.getWidth());
        System.out.println("qr w "+lbl_HinhAnh.getHeight());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel new javax.swing.JFrame(), true*/
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
            java.util.logging.Logger.getLogger(ChiTietSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietSanPhamCT dialog = new ChiTietSanPhamCT();
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_QRCode;
    private javax.swing.JLabel lbl_TenSp;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_HoaTiet;
    private javax.swing.JTextField txt_MPH;
    private javax.swing.JTextField txt_chatLieu;
    private javax.swing.JTextField txt_kichThuoc;
    private javax.swing.JTextField txt_kieuDang;
    private javax.swing.JTextField txt_mauSac;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_thuongHieu;
    // End of variables declaration//GEN-END:variables
}
