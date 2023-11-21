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
import domainmodels.SanPhamCT;
import domainmodels.ThuongHieu;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import services.impl.ChatLieuImpl;
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

/**
 *
 * @author Admin
 */
public class ThemSanPhamCT extends javax.swing.JDialog {
    SanPhamImpl spService = new SanPhamImpl();
    ThuongHieuImpl thService = new ThuongHieuImpl();
    MauSacImpl msService = new MauSacImpl();
    KichThuongImpl ktService = new KichThuongImpl();
    ChatLieuImpl clService = new ChatLieuImpl();
    KieuDangImpl kdService = new KieuDangImpl();
    MuaPhuHopImpl mphService = new MuaPhuHopImpl();
    MDSDImpl msdsService = new MDSDImpl();
    HoaTietImpl htService = new HoaTietImpl();
    SanPhamCTImpl spctService = new SanPhamCTImpl();
    SanPhamPanel spPanel;
    File fTenAnh;
    String duongDanAnh = "D:\\DUAN1\\DUAN1_QLBANGIAY\\src\\main\\java\\image\\"+fTenAnh;
    public String maPath=null;
    private SanPhamPanel createDialogForm;
    
    MsgBox msg = new MsgBox();
    /**
     * Creates new form ThemSanPhamCT
     */
    public ThemSanPhamCT() {
        initComponents();
        LoadCombobox();
//        spPanel= new SanPhamPanel();
//        String test = spPanel.textField;
//        System.out.println(test);
    }
    
    public void setCreateDialogForm(SanPhamPanel createDialogForm) {
        this.createDialogForm = createDialogForm;
    }
    
    private void LoadCombobox(){
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
        //System.out.println("Ten: "+tenTH);
        //System.out.println("id: "+id_thuongHieu);
        
        //tenMS-->lấy id màu sắc
        String tenMS = (String) cbo_MauSac.getSelectedItem();
        int id_MauSac = msService.getIDbyName(tenMS);
        spct.setId_MauSac(id_MauSac);
        //System.out.println("Ten: "+tenMS);
        //System.out.println("id: "+id_MauSac);
        
        //tenKT-->lấy id kích thước
        String tenKT = (String) cbo_KichThuoc.getSelectedItem();
        int id_KichThuoc = ktService.getIDbyName(tenKT);
        spct.setId_KichThuoc(id_KichThuoc);
        //System.out.println("Ten: "+tenKT);
        //System.out.println("id: "+id_KichThuoc);
        
        //tenCL-->lấy id chất liệu
        String tenCL = (String) cbo_ChatLieu.getSelectedItem();
        int id_cl = clService.getIDbyName(tenCL);
        spct.setId_ChatLieu(id_cl);
        //System.out.println("Ten: "+tenCL);
        //System.out.println("id: "+id_cl);
        
        //tenHT-->lấy id hoạ tiết
        String tenHT = (String) cbo_HoaTiet.getSelectedItem();
        int id_HT = htService.getIDbyName(tenHT);
        spct.setId_HoaTiet(id_HT);
        //System.out.println("Ten: "+tenHT);
        //System.out.println("id: "+id_HT);
        
        //tenKD-->lấy id kiểu dáng
        String tenKD = (String) cbo_KieuDang.getSelectedItem();
        int id_KD = kdService.getIDbyName(tenKD);
        spct.setId_KieuDang(id_KD);
        //System.out.println("Ten: "+tenKD);
        //System.out.println("id: "+id_KD);
        
        //tenMPH-->lấy id mph
        String tenMPH = (String) cbo_MPH.getSelectedItem();
        int id_MPH = mphService.getIDbyName(tenMPH);
        spct.setId_MuaPhuHop(id_MPH);
        //System.out.println("Ten: "+tenMPH);
        //System.out.println("id: "+id_MPH);
        
        //tenMDSD-->lấy id MDSD
        String tenMDSD = (String) cbo_MDSD.getSelectedItem();
        int id_MDSD = msdsService.getIDbyName(tenMDSD);
        spct.setId_MDSD(id_MDSD);
        //System.out.println("Ten: "+tenMDSD);
        //System.out.println("id: "+id_MDSD);
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
        int trang_Thai=0;
        if(rdo_KHDSPCT.isSelected()){
            trang_Thai=1;
        }
        spct.setTrang_thai(trang_Thai);
        String taoBoi="Giang Van Hieu";
        spct.setTao_boi(taoBoi);
        String suaBoi = "";
        spct.setSua_boi(suaBoi);
        //duong dan anh-->lấy id hình ảnh
        int id_hinhAnh =2;
        spct.setId_HinhAnh(id_hinhAnh);
        return spct;
    }
    public void setText(String text){
        txt_Gia.setText(text);
    }
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    
    public String setEditData(String ma){
        //txt_Gia.setText(ma);
        return ma;
    }
    public String  getEditData(){
        String ma="SP01";
        return ma;
    }
    
    public void setTxt_MaSPCTValue(String value) {
        //txt_MaSPCT.setText(value);
        lblAnh.setIcon(ResizeImage(String.valueOf(value)));
        //System.out.println("jijhfjh: "+value);
    }
    
    public void openEditDialogForm() {
        HinhAnhSPCT editDialogForm = new HinhAnhSPCT();
        // Thiết lập tham chiếu từ editDialogForm đến createDialogForm
        editDialogForm.setCreateDialogForm(this);
        // Hiển thị form editDialogForm
        editDialogForm.setVisible(true);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        rdo_Nu = new javax.swing.JRadioButton();
        rdo_Nam = new javax.swing.JRadioButton();
        txt_Gia = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        cbo_MDSD = new javax.swing.JComboBox<>();
        cbo_MPH = new javax.swing.JComboBox<>();
        cbo_KieuDang = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbo_HoaTiet = new javax.swing.JComboBox<>();
        cbo_ChatLieu = new javax.swing.JComboBox<>();
        cbo_KichThuoc = new javax.swing.JComboBox<>();
        cbo_MauSac = new javax.swing.JComboBox<>();
        cbo_ThuongHieu = new javax.swing.JComboBox<>();
        cbo_TenSP = new javax.swing.JComboBox<>();
        txt_MaSPCT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdo_HDSPCT = new javax.swing.JRadioButton();
        rdo_KHDSPCT = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tArea_moTa = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setSelected(true);
        rdo_Nam.setText("Nam");

        jLabel7.setText("Kiểu Dáng");

        jLabel8.setText("Mùa Phù Hợp");

        jLabel9.setText("Mục Đích Sử Dụng");

        jLabel10.setText("Số Lượng");

        jLabel11.setText("Giá");

        jLabel12.setText("Giới Tính");

        jLabel14.setText("Trạng Thái");

        txt_MaSPCT.setEnabled(false);

        jLabel1.setText("Mã SPCT");

        jLabel2.setText("Tên SP");

        jLabel3.setText("Thương Hiệu");

        jLabel4.setText("Màu Sắc");

        jLabel5.setText("Kích Thước");

        jLabel6.setText("Chất Liệu");

        jLabel13.setText("Hoạ Tiết");

        buttonGroup2.add(rdo_HDSPCT);
        rdo_HDSPCT.setSelected(true);
        rdo_HDSPCT.setText("Hoạt Động");

        buttonGroup2.add(rdo_KHDSPCT);
        rdo_KHDSPCT.setText("Không Hoạt Động");

        jLabel15.setText("Mô Tả");

        tArea_moTa.setColumns(20);
        tArea_moTa.setRows(5);
        jScrollPane1.setViewportView(tArea_moTa);

        jLabel16.setText("Ảnh Sản Phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/sanpham.png"))); // NOI18N
        jButton1.setText("Thêm SP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/thoat.png"))); // NOI18N
        jButton2.setText("Huỷ");

        jButton3.setText("Choose File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Thêm Thông Tin Sản Phẩm Chi Tiết");

        jButton4.setText("Clear Form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_MaSPCT)
                                    .addComponent(cbo_TenSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_ThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_MauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_KichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_ChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_HoaTiet, 0, 241, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addGap(23, 23, 23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(149, 149, 149)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton2))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdo_HDSPCT)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_KHDSPCT)
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(rdo_Nam)
                                .addGap(50, 50, 50)
                                .addComponent(rdo_Nu)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbo_KieuDang, javax.swing.GroupLayout.Alignment.LEADING, 0, 225, Short.MAX_VALUE)
                            .addComponent(cbo_MPH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_MDSD, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Gia, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_MaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbo_KieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbo_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbo_MPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbo_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbo_MDSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(rdo_Nam)
                    .addComponent(rdo_Nu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(cbo_HoaTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_HDSPCT)
                    .addComponent(rdo_KHDSPCT))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(34, 34, 34)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (MsgBox.confirm(this, "Bạn có muốn thêm sản phẩm không?")) {
            try {
                SanPhamCT sp = getData();
                spctService.InsertSPCT(sp);
                createDialogForm.LoadSPCT();
                MsgBox.alert(this, "Thêm Thành Công!");
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                //MsgBox.alert(this, "Thêm Thất Bại!");
                MsgBox.warring(this, "Thêm Thất Bại!");
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
           openEditDialogForm();
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSanPhamCT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemSanPhamCT dialog = new ThemSanPhamCT();
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
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdo_HDSPCT;
    private javax.swing.JRadioButton rdo_KHDSPCT;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTextArea tArea_moTa;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_MaSPCT;
    private javax.swing.JTextField txt_SoLuong;
    // End of variables declaration//GEN-END:variables

   
}
