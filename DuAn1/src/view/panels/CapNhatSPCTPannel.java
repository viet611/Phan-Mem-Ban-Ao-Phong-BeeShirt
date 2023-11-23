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
import ultilities.MsgBox;

/**
 *
 * @author Admin
 */
public class CapNhatSPCTPannel extends javax.swing.JPanel {
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
    
    
    private SanPhamPanel updateDialogForm;
    private int id_SPCT;
    private int id_hinhAnh_Load;
    private String duong_Dan;
    /**
     * Creates new form CapNhatSPCTPannel
     */
    public CapNhatSPCTPannel() {
        initComponents();
        LoadCombobox();
        lblAnhSPCT.setSize(222, 159);
        //lblAnhSPCT.setIcon(ResizeImage(String.valueOf(duong_Dan)));
    }
    
    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public void setUpadateDialogForm(SanPhamPanel createDialogForm) {
        this.updateDialogForm = createDialogForm;
    }

    public void openEditDialogForm() {
        HinhAnhSPCT editDialogForm = new HinhAnhSPCT();
        // Thiết lập tham chiếu từ editDialogForm đến createDialogForm
        editDialogForm.setUpadateForm(this);
        // Hiển thị form editDialogForm
        editDialogForm.setVisible(true);
    }
    
    public void setUpdateSPCTValue(String value) {
        //txt_MaSPCT.setText(value);
        lblAnhSPCT.setIcon(ResizeImage(String.valueOf(value)));
        duong_Dan=String.valueOf(value);
        //System.out.println("jijhfjh: "+value);
    }
    
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lblAnhSPCT.getWidth(), lblAnhSPCT.getHeight(), Image.SCALE_SMOOTH);
        int wwifth = lblAnhSPCT.getWidth();
        int height = lblAnhSPCT.getHeight();
        System.out.println("wwidth height "+wwifth +"="+height);
        //Image newImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    
    public ImageIcon ResizeImageID(String ImagePath){
        //lblAnhSPCT.setSize(222, 159);
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        //Image newImage = image.getScaledInstance(lblAnhSPCT.getWidth(), lblAnhSPCT.getHeight(), Image.SCALE_SMOOTH);
        Image newImage = image.getScaledInstance(222, 159, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    
    public String setEditData(String ma){
        txt_MaSPCT.setText(ma);
        return ma;
    }
    
    public Integer setEditIDData(int ma){
        id_SPCT=ma;
        //int test = updateDialogForm.id_SPCT;
        //System.out.println("Test updateDialogForm.id_SPCT "+updateDialogForm.id_SPCT);
        LoadData();
        return ma;
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
            cbo_chatLieu.addItem(x.toString());
        }
        //cbo_HoaTiet.setModel(new DefaultComboBoxModel(hoaTiet.toArray()));
        for (HoaTiet x : hoaTiet) {
            cbo_HoaTiet.addItem(x.toString());
        }
        //cbo_KieuDang.setModel(new DefaultComboBoxModel(kieuDang.toArray()));
        for (KieuDang x : kieuDang) {
            cbo_kieuDang.addItem(x.toString());
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
    
    private void LoadData(){
        List<SanPhamCT> listSPCT = spctService.getALLSPCTByID(id_SPCT);
        for(SanPhamCT x: listSPCT){
            //id_sanPham
            int id_sanPham = x.getId_SanPham();
            String tenSP = spService.getNameID(id_sanPham);
            //System.out.println("name SP" +MaSP);
            cbo_TenSP.setSelectedItem(tenSP);
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS=msService.getNameByID(id_mauSac);
            cbo_MauSac.setSelectedItem(nameMS);
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            cbo_ThuongHieu.setSelectedItem(nameTH);
            //id kich thuoc
            int id_kichThuoc =x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            cbo_KichThuoc.setSelectedItem(nameKT);
            //id_KieuDang;
            int id_kieuDang=x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            cbo_chatLieu.setSelectedItem(nameKD);
            //id_MuaPhuHop;
            int id_muaPhuHop =x.getId_MuaPhuHop();
            String nameMPH= mphService.getNameByID(id_muaPhuHop);
            cbo_MPH.setSelectedItem(nameMPH);
            //id_MDSD;
            int id_MDSD = x.getId_MDSD();
            String nameMDSD= msdsService.getNameByID(id_MDSD);
            cbo_MDSD.setSelectedItem(nameMDSD);
            //id_ChatLieu;
            int id_chatLieu=x.getId_ChatLieu();
            String nameCL =clService.getNameByID(id_chatLieu);
            cbo_chatLieu.setSelectedItem(nameCL);
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            cbo_chatLieu.setSelectedItem(nameHT);
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
             duong_Dan = haService.getNameByID(id_hinhAnh);
            //System.out.println("duongDanHA+ Test "+duongDanHA);
            lblAnhSPCT.setIcon(ResizeImageID(String.valueOf(duong_Dan))); 
            //lblAnhSPCT.setIcon(ResizeImage(duongDanHA)); 
            //ma;
            String ma = x.getMa();
            //goi_Tinh;
            boolean gioi_tinh = x.isGoi_Tinh();
            String gioiTinh=null;
            if(gioi_tinh==true){
                gioiTinh="nam";
                rdo_nam.setSelected(true);
            }else{
                gioiTinh="Nữ";
                rdo_nu.setSelected(false);
            }
            //so_Luong;
            int so_Luong = x.getSo_Luong();
            String so_Luong_String = Integer.toString(so_Luong);
            txt_soLuong.setText(so_Luong_String);
            //gia;
            float gia=x.getGia();
            String gia_String = Float.toString(gia);
            txt_gia.setText(gia_String);
            //mo_Ta;
            String mo_ta=x.getMo_Ta();
            //trang_thai;
            int trang_thai =x.getTrang_thai();
            boolean trangThai=true;
            if(trang_thai==0){
                trangThai=true;
                rdo_HD.setSelected(true);
            }else{
                trangThai=false;
                rdo_KHD.setSelected(false);
            }
        }
    }
    
    public SanPhamCT getUpdateData(){
        SanPhamCT spct = new SanPhamCT();
        //id 1
        System.out.println(id_SPCT);
        spct.setId(id_SPCT);
        
        System.out.println("testID_SPCT: "+id_SPCT);
        //mã
        String maSPCT =txt_MaSPCT.getText();
        System.out.println(maSPCT);
        spct.setMa(maSPCT);
        
        //tensp-->lấy id sản phẩm 2
        String tenSP = (String) cbo_TenSP.getSelectedItem();
        int id_sanPham = spService.getIDbyName(tenSP);
        System.out.println(id_sanPham);
        spct.setId_SanPham(id_sanPham);
        
        //tenTH-->lấy id Thương Hiệu 3
        String tenTH = (String) cbo_ThuongHieu.getSelectedItem();
        int id_thuongHieu = thService.getIDbyName(tenTH);
        System.out.println(id_thuongHieu);
        spct.setId_ThuongHieu(id_thuongHieu);
        
        //tenMS-->lấy id màu sắc 4 
        String tenMS = (String) cbo_MauSac.getSelectedItem();
        int id_MauSac = msService.getIDbyName(tenMS);
        System.out.println(id_MauSac);
        spct.setId_MauSac(id_MauSac);
        
        //tenKT-->lấy id kích thước 5
        String tenKT = (String) cbo_KichThuoc.getSelectedItem();
        int id_KichThuoc = ktService.getIDbyName(tenKT);
        System.out.println(id_KichThuoc);
        spct.setId_KichThuoc(id_KichThuoc);
        
        //tenCL-->lấy id chất liệu 6
        String tenCL = (String) cbo_chatLieu.getSelectedItem();
        int id_cl = clService.getIDbyName(tenCL);
        System.out.println(id_cl);
        spct.setId_ChatLieu(id_cl);
        
        //tenHT-->lấy id hoạ tiết 7
        String tenHT = (String) cbo_HoaTiet.getSelectedItem();
        int id_HT = htService.getIDbyName(tenHT);
        System.out.println(id_HT);
        spct.setId_HoaTiet(id_HT);
        
        //tenKD-->lấy id kiểu dáng 8
        String tenKD = (String) cbo_kieuDang.getSelectedItem();
        int id_KD = kdService.getIDbyName(tenKD);
        System.out.println(id_KD);
        spct.setId_KieuDang(id_KD);
        
        //tenMPH-->lấy id mph 9
        String tenMPH = (String) cbo_MPH.getSelectedItem();
        int id_MPH = mphService.getIDbyName(tenMPH);
        System.out.println(id_MPH);
        spct.setId_MuaPhuHop(id_MPH);
        
        //tenMDSD-->lấy id MDSD 10 
        String tenMDSD = (String) cbo_MDSD.getSelectedItem();
        int id_MDSD = msdsService.getIDbyName(tenMDSD);
        System.out.println(id_MDSD);
        spct.setId_MDSD(id_MDSD);
        //11
        String moTa=tAreMo_ta.getText();
        System.out.println(moTa);
        spct.setMo_Ta(moTa);
        //12
        String gia = txt_gia.getText();
        float giaFloat = Float.parseFloat(gia);
        System.out.println(giaFloat);
        spct.setGia(giaFloat);
        //13
        String soLuong = txt_soLuong.getText();
        int so_Luong = Integer.parseInt(soLuong);
        System.out.println(so_Luong);
        spct.setSo_Luong(so_Luong);
        //14
        boolean gioi_Tinh=true;
        if(rdo_nu.isSelected()){
            gioi_Tinh=false;
        }
        System.out.println(gioi_Tinh);
        spct.setGoi_Tinh(gioi_Tinh);
        //15
        int trang_Thai=0;
        if(rdo_KHD.isSelected()){
            trang_Thai=1;
        }
        System.out.println(trang_Thai);
        spct.setTrang_thai(trang_Thai);
        //16
        String taoBoi="Giang Van Hieu";
        System.out.println(taoBoi);
        spct.setTao_boi(taoBoi);
        //17
        String suaBoi = "";
        System.out.println(suaBoi);
        spct.setSua_boi(suaBoi);
        //18
        boolean daXoa =true;
        System.out.println(daXoa);
        spct.setDa_xoa(daXoa);
        //19
        //duong dan anh-->lấy id hình ảnh
        int id_hinhAnh =haService.getIDbyName(duong_Dan);
        
        System.out.println(id_hinhAnh);
        System.out.println(duong_Dan);
        spct.setId_HinhAnh(id_hinhAnh);
        return spct;
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_MaSPCT = new javax.swing.JTextField();
        cbo_TenSP = new javax.swing.JComboBox<>();
        cbo_ThuongHieu = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cbo_MauSac = new javax.swing.JComboBox<>();
        cbo_KichThuoc = new javax.swing.JComboBox<>();
        cbo_chatLieu = new javax.swing.JComboBox<>();
        cbo_HoaTiet = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbo_kieuDang = new javax.swing.JComboBox<>();
        cbo_MPH = new javax.swing.JComboBox<>();
        cbo_MDSD = new javax.swing.JComboBox<>();
        txt_soLuong = new javax.swing.JTextField();
        txt_gia = new javax.swing.JTextField();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        rdo_HD = new javax.swing.JRadioButton();
        rdo_KHD = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAreMo_ta = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblAnhSPCT = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setText("Cập Nhật");

        jLabel19.setText("Mã SPCT");

        jLabel20.setText("Tên SP");

        jLabel21.setText("Thương Hiệu");

        jLabel22.setText("Màu Sắc");

        jLabel23.setText("Kích Thước");

        jLabel24.setText("Chất Liệu");

        txt_MaSPCT.setEnabled(false);

        jLabel25.setText("Hoạ Tiết");

        jLabel1.setText("Kiểu Dáng");

        jLabel2.setText("Mùa Phù Hợp");

        jLabel3.setText("Mục Đích Sử Dụng");

        jLabel4.setText("Số Lượng");

        jLabel5.setText("Giá");

        jLabel6.setText("Giới Tính");

        jLabel7.setText("Trạng Thái");

        buttonGroup1.add(rdo_nam);
        rdo_nam.setSelected(true);
        rdo_nam.setText("Nam");

        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");

        buttonGroup2.add(rdo_HD);
        rdo_HD.setSelected(true);
        rdo_HD.setText("Hoạt Động");

        buttonGroup2.add(rdo_KHD);
        rdo_KHD.setText("Không Hoạt Động");

        jLabel8.setText("Mô Tả");

        tAreMo_ta.setColumns(20);
        tAreMo_ta.setRows(5);
        jScrollPane1.setViewportView(tAreMo_ta);

        jLabel9.setText("Ảnh Sản Phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblAnhSPCT.setText("jLabel10");

        jButton1.setText("Choose File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jLabel18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(126, 126, 126)
                                        .addComponent(jButton4))
                                    .addComponent(jButton3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_MaSPCT)
                                            .addComponent(cbo_TenSP, 0, 211, Short.MAX_VALUE)
                                            .addComponent(cbo_ThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbo_MauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbo_KichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbo_chatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbo_HoaTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(70, 70, 70))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButton1))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbo_kieuDang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_MPH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_MDSD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_soLuong)
                                    .addComponent(txt_gia)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdo_HD)
                                                    .addComponent(rdo_nam))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdo_nu)
                                                    .addComponent(rdo_KHD)))
                                            .addComponent(lblAnhSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(499, 499, 499))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_MaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbo_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbo_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbo_MPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbo_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbo_MDSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbo_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbo_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(rdo_nam)
                    .addComponent(rdo_nu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbo_HoaTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(rdo_HD)
                    .addComponent(rdo_KHD))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAnhSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(txt_MaSPCT.getText()==""||txt_gia.getText()==""||txt_soLuong.getText()==""){
            MsgBox.alert(this, "Nhập Đủ Thông Tin Yêu Cầu!");
            return;
        }
        String soLuong = txt_soLuong.getText();
        if (!isNumeric(soLuong)) {
            MsgBox.alert(this, "Dữ Liệu Nhập Vào Phải Là Số!");
            txt_soLuong.setText(""); // Xóa nội dung không hợp lệ
            return;
        }else{
            int so_Luong = Integer.parseInt(soLuong);
            if(0>so_Luong || so_Luong >99){
            MsgBox.alert(this, "Số Lượng Lớn Hơn Không Và Nhỏ Hơn 99!");
            txt_soLuong.setText("");
            return;
        }
        }
        
        if (MsgBox.confirm(this, "Bạn có muốn Sửa không?")) {
            try {
                SanPhamCT sp = getUpdateData();
                spctService.UpdateSPCT(sp);
                updateDialogForm.LoadSPCT(1);
                MsgBox.alert(this, "Sửa Thành Công!");
                //dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.setVisible(false);
        updateDialogForm.addTabPannel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        updateDialogForm.addTabPannel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_MaSPCT.setText("");
        cbo_TenSP.setSelectedIndex(0);
        cbo_ThuongHieu.setSelectedIndex(0);
        cbo_MauSac.setSelectedIndex(0);
        cbo_KichThuoc.setSelectedIndex(0);
        cbo_chatLieu.setSelectedIndex(0);
        cbo_HoaTiet.setSelectedIndex(0);
        cbo_kieuDang.setSelectedIndex(0);
        cbo_MPH.setSelectedIndex(0);
        cbo_MDSD.setSelectedIndex(0);
        txt_gia.setText("");
        txt_soLuong.setText("");
        rdo_nam.setSelected(true);
        rdo_HD.setSelected(true);
        tAreMo_ta.setText("");
//        String duongDanHA = haService.getNameByID(WIDTH)
//        System.out.println("duongDanHA+ Test "+duongDanHA);
        lblAnhSPCT.setIcon(ResizeImage(String.valueOf(duong_Dan))); 
        System.out.println(lblAnhSPCT.getWidth());
        System.out.println(lblAnhSPCT.getHeight());
        //lblAnhSPCT.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        openEditDialogForm();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbo_HoaTiet;
    private javax.swing.JComboBox<String> cbo_KichThuoc;
    private javax.swing.JComboBox<String> cbo_MDSD;
    private javax.swing.JComboBox<String> cbo_MPH;
    private javax.swing.JComboBox<String> cbo_MauSac;
    private javax.swing.JComboBox<String> cbo_TenSP;
    private javax.swing.JComboBox<String> cbo_ThuongHieu;
    private javax.swing.JComboBox<String> cbo_chatLieu;
    private javax.swing.JComboBox<String> cbo_kieuDang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhSPCT;
    private javax.swing.JRadioButton rdo_HD;
    private javax.swing.JRadioButton rdo_KHD;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTextArea tAreMo_ta;
    private javax.swing.JTextField txt_MaSPCT;
    private javax.swing.JTextField txt_gia;
    private javax.swing.JTextField txt_soLuong;
    // End of variables declaration//GEN-END:variables
}
