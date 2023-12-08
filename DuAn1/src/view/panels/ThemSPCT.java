/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
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
import domainmodels.SanPhamCT;
import domainmodels.ThuongHieu;
import java.awt.Image;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
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
import ultilities.MsgBox;
import ultilities.ZXingHelper;

/**
 *
 * @author Admin
 */
public class ThemSPCT extends javax.swing.JPanel {
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
    

    String path = "C:\\Users\\Admin\\Desktop\\";
   private String PathTest =null;
    
    private SanPhamPanel createDialogForm;
    /**
     * Creates new form ThemSPCT
     */
    public ThemSPCT() {
        initComponents();
        LoadCombobox();
    }

    public void LoadCombobox(){
        cbo_TenSP.removeAllItems();
        cbo_ChatLieu.removeAllItems();
        cbo_HoaTiet.removeAllItems();
        cbo_KichThuoc.removeAllItems();
        cbo_KieuDang.removeAllItems();
        cbo_MDSD.removeAllItems();
        cbo_MPH.removeAllItems();
        cbo_MauSac.removeAllItems();
        cbo_ThuongHieu.removeAllItems();
        
        List<SanPham> sanPham = spService.getName();
        List<ThuongHieu> thuongHieu = thService.getName();
        List<MauSac> mauSac = msService.getName();
        List<KichThuoc> kichThuoc = ktService.getName();
        List<ChatLieu> chatLieu = clService.getName();
        List<HoaTiet> hoaTiet = htService.getName();
        List<KieuDang> kieuDang = kdService.getName();
        List<MuaPhuHop> muaPhuHop = mphService.getName();
        List<MucDichSuDung> mdsd = msdsService.getAll();
        //cbo_TenSP.addItem("Tất cả");
        //cbo_TenSP.setModel(new DefaultComboBoxModel(sanPham.toArray()));
        for (SanPham x : sanPham) {
            cbo_TenSP.addItem(x.toString());
        }
        //cbo_ThuongHieu.setModel(new DefaultComboBoxModel(thuongHieu.toArray()));
        for (ThuongHieu x : thuongHieu) {
            cbo_ThuongHieu.addItem(x.toString());
        }
        //cbo_MauSac.setModel(new DefaultComboBoxModel(mauSac.toArray()));
        for (MauSac x : mauSac) {
            cbo_MauSac.addItem(x.toString());
        }
        //cbo_KichThuoc.setModel(new DefaultComboBoxModel(kichThuoc.toArray()));
        for (KichThuoc x : kichThuoc) {
            cbo_KichThuoc.addItem(x.toString());
        }
        //cbo_ChatLieu.setModel(new DefaultComboBoxModel(chatLieu.toArray()));
        for (ChatLieu x : chatLieu) {
            cbo_ChatLieu.addItem(x.toString());
        }
        //cbo_HoaTiet.setModel(new DefaultComboBoxModel(hoaTiet.toArray()));
        for (HoaTiet x : hoaTiet) {
            cbo_HoaTiet.addItem(x.toString());
        }
        //cbo_KieuDang.setModel(new DefaultComboBoxModel(kieuDang.toArray()));
        for (KieuDang x : kieuDang) {
            cbo_KieuDang.addItem(x.toString());
        }
        //cbo_MPH.setModel(new DefaultComboBoxModel(muaPhuHop.toArray()));
        for (MuaPhuHop x : muaPhuHop) {
            cbo_MPH.addItem(x.toString());
        }
        //cbo_MDSD.setModel(new DefaultComboBoxModel(mdsd.toArray()));
        for (MucDichSuDung x : mdsd) {
            cbo_MDSD.addItem(x.toString());
        }
    }
    
    public void setCreateDialogForm(SanPhamPanel createDialogForm) {
        this.createDialogForm = createDialogForm;
    }
    
    public void openEditDialogForm() {
        HinhAnhSPCT editDialogForm = new HinhAnhSPCT();
        // Thiết lập tham chiếu từ editDialogForm đến createDialogForm
        editDialogForm.setCreateSPDialogForm(this);
        // Hiển thị form editDialogForm
        editDialogForm.setVisible(true);
    }
    public void setSPCTValue(String value) {
        //txt_MaSPCT.setText(value);
        lblAnhADD.setIcon(ResizeImage(String.valueOf(value)));
        PathTest =String.valueOf(value);
        System.out.println("jijhfjh: "+PathTest);
    }
     public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lblAnhADD.getWidth(), lblAnhADD.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
     public static void saveQRCodeImage(byte[] imageBytes, String filePath) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public SanPhamCT getData(){
        SanPhamCT spct = new SanPhamCT();
        //mã
        SanPham sp = new SanPham();
        String prefix = "SPCT";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSPCT =prefix + String.format("%02d", randomNumber);
        spct.setMa(maSPCT);
        
        //tensp-->lấy id sản phẩm
        String tenSP = (String) cbo_TenSP.getSelectedItem();
        //System.out.println("id them: "+tenSP);
        int id_sanPham = spService.getIDbyName(tenSP);
        //System.out.println("id them: "+id_sanPham);
        spct.setId_SanPham(id_sanPham);
        
        //tenTH-->lấy id Thương Hiệu
        String tenTH = (String) cbo_ThuongHieu.getSelectedItem();
        int id_thuongHieu = thService.getIDbyName(tenTH);
        spct.setId_ThuongHieu(id_thuongHieu);
        
        //tenMS-->lấy id màu sắc
        String tenMS = (String) cbo_MauSac.getSelectedItem();
        int id_MauSac = msService.getIDbyName(tenMS);
        spct.setId_MauSac(id_MauSac);
        
        //tenKT-->lấy id kích thước
        String tenKT = (String) cbo_KichThuoc.getSelectedItem();
        int id_KichThuoc = ktService.getIDbyName(tenKT);
        spct.setId_KichThuoc(id_KichThuoc);
        
        //tenCL-->lấy id chất liệu
        String tenCL = (String) cbo_ChatLieu.getSelectedItem();
        int id_cl = clService.getIDbyName(tenCL);
        spct.setId_ChatLieu(id_cl);
        
        //tenHT-->lấy id hoạ tiết
        String tenHT = (String) cbo_HoaTiet.getSelectedItem();
        int id_HT = htService.getIDbyName(tenHT);
        spct.setId_HoaTiet(id_HT);
        
        //tenKD-->lấy id kiểu dáng
        String tenKD = (String) cbo_KieuDang.getSelectedItem();
        int id_KD = kdService.getIDbyName(tenKD);
        spct.setId_KieuDang(id_KD);
        
        //tenMPH-->lấy id mph
        String tenMPH = (String) cbo_MPH.getSelectedItem();
        int id_MPH = mphService.getIDbyName(tenMPH);
        spct.setId_MuaPhuHop(id_MPH);
        
        //tenMDSD-->lấy id MDSD
        String tenMDSD = (String) cbo_MDSD.getSelectedItem();
        int id_MDSD = msdsService.getIDbyName(tenMDSD);
        spct.setId_MDSD(id_MDSD);
        
        String moTa=tArea_moTa.getText();
        spct.setMo_Ta(moTa);
        
        String gia = txt_Gia.getText();
        float giaFloat = Float.parseFloat(gia);
        spct.setGia(giaFloat);
        
        String soLuong = txt_SoLuong.getText();
        int so_Luong = Integer.parseInt(soLuong);
        spct.setSo_Luong(so_Luong);
        
        boolean gioi_Tinh=true;
        if(rdo_Nu.isSelected()){
            gioi_Tinh=false;
        }
        spct.setGoi_Tinh(gioi_Tinh);
        
        String taoBoi="Giang Van Hieu";
        spct.setTao_boi(taoBoi);
        String suaBoi = "";
        spct.setSua_boi(suaBoi);
        //duong dan anh-->lấy id hình ảnh
        String duong_dan=PathTest;
        int id_hinhAnh = haService.getIDbyName(duong_dan);
         System.out.println("Test ID hinhf ANh: "+id_hinhAnh);
        spct.setId_HinhAnh(id_hinhAnh);
        
        
        
        String relativePath = "src\\view\\icon";
        String currentWorkingDirectory = System.getProperty("user.dir");
        Path fullPath1 = Paths.get(currentWorkingDirectory, relativePath);
        String fullPath = fullPath1.toString() +"\\" +maSPCT+ ".png";
        System.out.println("FullPath: "+fullPath);
        byte[] resultQRCode = ZXingHelper.getQRCodeImage(maSPCT, lblAnhADD.getWidth(), lblAnhADD.getHeight());
        if (resultQRCode != null) {
            saveQRCodeImage(resultQRCode, fullPath);
            System.out.println("QR Code saved successfully!");
        } else {
            System.out.println("Failed to generate QR Code.");
        }
        spct.setQrCode(fullPath);
//        lblAnhADD.setIcon(ResizeImage(fullPath));
        
        return spct;
    }
     private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
     public void getLoad(){
         createDialogForm.LoadDataToRDOKichThuoc();
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
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_MaSPCT = new javax.swing.JTextField();
        cbo_TenSP = new javax.swing.JComboBox<>();
        cbo_ThuongHieu = new javax.swing.JComboBox<>();
        cbo_MauSac = new javax.swing.JComboBox<>();
        cbo_KichThuoc = new javax.swing.JComboBox<>();
        cbo_ChatLieu = new javax.swing.JComboBox<>();
        cbo_HoaTiet = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tArea_moTa = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbo_KieuDang = new javax.swing.JComboBox<>();
        cbo_MPH = new javax.swing.JComboBox<>();
        cbo_MDSD = new javax.swing.JComboBox<>();
        txt_SoLuong = new javax.swing.JTextField();
        txt_Gia = new javax.swing.JTextField();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblAnhADD = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Thêm Thông Tin Sản Phẩm Chi Tiết");

        jLabel1.setText("Mã SPCT");

        jLabel2.setText("Tên SP");

        jLabel3.setText("Thương Hiệu");

        jLabel4.setText("Màu Sắc");

        jLabel5.setText("Kích Thước");

        jLabel6.setText("Chất Liệu");

        jLabel13.setText("Hoạ Tiết");

        jLabel15.setText("Mô Tả");

        txt_MaSPCT.setEnabled(false);

        tArea_moTa.setColumns(20);
        tArea_moTa.setRows(5);
        jScrollPane1.setViewportView(tArea_moTa);

        jLabel7.setText("Kiểu Dáng");

        jLabel8.setText("Mùa Phù Hợp");

        jLabel9.setText("Mục Đích Sử Dụng");

        jLabel10.setText("Số Lượng");

        jLabel11.setText("Giá");

        jLabel12.setText("Giới Tính");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setSelected(true);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");

        jLabel16.setText("Ảnh Sản Phẩm");

        jButton3.setText("Choose File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Save.png"))); // NOI18N
        jButton1.setText("Thêm SP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Exit button.png"))); // NOI18N
        jButton2.setText("Huỷ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/phieugiamgia.png"))); // NOI18N
        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );

        jButton6.setText("Thêm Đặc Trưng Sản Phẩm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel17)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaSPCT)
                    .addComponent(cbo_TenSP, 0, 290, Short.MAX_VALUE)
                    .addComponent(cbo_ThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_MauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_KichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_ChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_HoaTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdo_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addGap(50, 50, 50)
                                .addComponent(rdo_Nu, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                .addGap(165, 165, 165))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_KieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAnhADD, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbo_MPH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbo_MDSD, javax.swing.GroupLayout.Alignment.LEADING, 0, 263, Short.MAX_VALUE)))
                                .addContainerGap(36, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(73, 73, 73)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(cbo_KieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(cbo_MPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(cbo_MDSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(rdo_Nam)
                    .addComponent(rdo_Nu))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbo_HoaTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jButton3)))
                            .addComponent(lblAnhADD, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        openEditDialogForm();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txt_Gia.getText().equalsIgnoreCase("")||txt_SoLuong.getText().equalsIgnoreCase("")){
            MsgBox.warring(this, "Vui Lòng Nhập Đủ Thông Tin!");
            return;
        }
        String soLuong = txt_SoLuong.getText();
        if (!isNumeric(soLuong)) {
            MsgBox.warring(this, "Dữ Liệu Nhập Vào Phải Là Số!");
            txt_SoLuong.setText(""); // Xóa nội dung không hợp lệ
            return;
        } else {
            int so_Luong = Integer.parseInt(soLuong);
            if (0 > so_Luong || so_Luong > 99) {
                MsgBox.warring(this, "Số Lượng Lớn Hơn 0 Và Nhỏ Hơn 99!");
                txt_SoLuong.setText("99");
                return;
            }
        }
        
        
        if (MsgBox.confirm(this, "Bạn có muốn thêm sản phẩm không?")) {
            try {
                SanPhamCT sp = getData();
                spctService.InsertSPCT(sp);
                MsgBox.alert(this, "Thêm Thành Công!");
                createDialogForm.LoadSPCT(1);
                createDialogForm.LoadDataSP();
                createDialogForm.LoadDataToRDOKichThuoc();
                //dispose();
            } catch (Exception e) {
                e.printStackTrace();
                //MsgBox.alert(this, "Thêm Thất Bại!");
                MsgBox.warring(this, "Thêm Thất Bại!");
            }
        }
        this.setVisible(false);
        createDialogForm.addTabPannel();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //dispose();
        this.setVisible(false);
        createDialogForm.LoadCBOFilter();
        createDialogForm.LoadSPCT(1);
        createDialogForm.LoadDataToRDOKichThuoc();
        createDialogForm.LoadDataSP();
        createDialogForm.addTabPannel();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_MaSPCT.setText("");
        cbo_TenSP.setSelectedIndex(0);
        cbo_ThuongHieu.setSelectedIndex(0);
        cbo_MauSac.setSelectedIndex(0);
        cbo_KichThuoc.setSelectedIndex(0);
        cbo_ChatLieu.setSelectedIndex(0);
        cbo_HoaTiet.setSelectedIndex(0);
        cbo_KieuDang.setSelectedIndex(0);
        cbo_MPH.setSelectedIndex(0);
        cbo_MDSD.setSelectedIndex(0);
        txt_Gia.setText("");
        txt_SoLuong.setText("");
        rdo_Nam.setSelected(true);
        tArea_moTa.setText("");
        lblAnhADD.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        ThemDacTrungSPCT addDTSPCT = new ThemDacTrungSPCT();
        addDTSPCT.setCreateDialogForm(this);
        addDTSPCT.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_ChatLieu;
    private javax.swing.JComboBox<String> cbo_HoaTiet;
    private javax.swing.JComboBox<String> cbo_KichThuoc;
    private javax.swing.JComboBox<String> cbo_KieuDang;
    private javax.swing.JComboBox<String> cbo_MDSD;
    private javax.swing.JComboBox<String> cbo_MPH;
    private javax.swing.JComboBox<String> cbo_MauSac;
    private javax.swing.JComboBox<String> cbo_TenSP;
    private javax.swing.JComboBox<String> cbo_ThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhADD;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTextArea tArea_moTa;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_MaSPCT;
    private javax.swing.JTextField txt_SoLuong;
    // End of variables declaration//GEN-END:variables
}
