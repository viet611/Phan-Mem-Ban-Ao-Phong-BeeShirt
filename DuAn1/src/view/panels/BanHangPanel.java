/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import domainmodels.ChatLieu;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.KichThuoc;
import domainmodels.KieuDang;
import domainmodels.MauSac;
import domainmodels.MuaPhuHop;
import domainmodels.NhanVien;
import domainmodels.PTTTChiTiet;
import domainmodels.SanPham;
import domainmodels.SanPhamCT;
import domainmodels.ThuongHieu;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositories.PTTTChiTietRepository;
import repositories.PTTTRepository;
import services.HoaDonChiTietService;
import services.HoaDonService;
import services.KhachHangService;
import services.KhuyenMaiService;
import services.KichThuocService;
import services.MauSacService;
import services.SanPhamCTService;
import services.SanPhamService;
import services.ThuongHieuService;
import services.impl.ChatLieuImpl;
import services.impl.HoaDonChiTietServiceImpl;
import services.impl.HoaDonServiceImpl;
import services.impl.HoaTietImpl;
import services.impl.KhachHangServiceImpl;
import services.impl.KhuyenMaiServiceIMPL;
import services.impl.KichThuongImpl;
import services.impl.KieuDangImpl;
import services.impl.MDSDImpl;
import services.impl.MauSacImpl;
import services.impl.MuaPhuHopImpl;
import services.impl.NhanVienServiceImpl;
import services.impl.SanPhamCTImpl;
import services.impl.SanPhamImpl;
import services.impl.ThuongHieuImpl;
import ultilities.MsgBox;

/**
 *
 * @author Admin
 */
public class BanHangPanel extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form Form_Home
     */
    private HoaDonService hdService = new HoaDonServiceImpl();
    private SanPhamCTService spctService = new SanPhamCTImpl();
    private SanPhamService sanPhamService = new SanPhamImpl();
    private ThuongHieuService thuongHieuService = new ThuongHieuImpl();
    private KichThuocService kichThuocService = new KichThuongImpl();
    private MauSacService mauSacService = new MauSacImpl();
    private HoaDonChiTietService hdctService = new HoaDonChiTietServiceImpl();
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private PTTTChiTietRepository pTTTChiTietRepository = new PTTTChiTietRepository();
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceIMPL();
    private MauSacImpl msService = new MauSacImpl();
    private SanPhamImpl spService = new SanPhamImpl();
    ThuongHieuImpl thService = new ThuongHieuImpl();
    KichThuongImpl ktService = new KichThuongImpl();
    ChatLieuImpl clService = new ChatLieuImpl();
    KieuDangImpl kdService = new KieuDangImpl();
    MuaPhuHopImpl mphService = new MuaPhuHopImpl();
    MDSDImpl msdsService = new MDSDImpl();
    HoaTietImpl htService = new HoaTietImpl();
    private List<SanPhamCT> listSP;
    private List<HoaDon> listHDCho;
    private List<HoaDonChiTiet> listHDCT;
    private List<PTTTChiTiet> listPTTTCT;
    private KhuyenMai khuyenMai;
    private HoaDon hd;
    private int stt = 1;
    private NhanVien nhanVien = new NhanVienServiceImpl().getAlls().get(0);
    private KhachHang khachHang;
    private SanPhamCT sanPham;
    double tongTien = 0;
    double tienDaTT = 0;
    double tienConLai = 0;
    private WebcamPanel panel = null;
    private Webcam webCam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    
    public BanHangPanel() {
        initComponents();
        txtSeachSP.hint = "Mã SP";
        listSP = spctService.getALL();
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai() == 0).collect(Collectors.toList());
        loadTableHDCho();
        loadTableSP();
        loadCBBTrangThai1();
        LoadCBOFilter();
        innitCam();
    }

    private void innitCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webCam = Webcam.getWebcams().get(0);
        webCam.setViewSize(size);
        panel = new WebcamPanel(webCam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panelQuetMa.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 210));
        executor.execute(this);
    }
    
    public void loadCBBTrangThai1() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cbbThanhToan.getModel();
        dcbm.addElement("Chọn phương thức");
        dcbm.addElement("Tiền mặt");
        dcbm.addElement("Chuyển khoản");
    }

    public void LoadCBOFilter() {
        List<ThuongHieu> thuongHieu = thService.getName();
        List<MauSac> mauSac = msService.getName();
        List<KichThuoc> kichThuoc = ktService.getName();
        List<ChatLieu> chatLieu = clService.getName();
        List<KieuDang> kieuDang = kdService.getName();
        List<MuaPhuHop> muaPhuHop = mphService.getName();
        //cbo_TenSP.addItem("Tất cả");
        cbo_FilterSPCTTH.setModel(new DefaultComboBoxModel(thuongHieu.toArray()));
        cbo_FilterSPCTMS.setModel(new DefaultComboBoxModel(mauSac.toArray()));
        cbo_FilterSPCTKT.setModel(new DefaultComboBoxModel(kichThuoc.toArray()));
        cbo_FilterSPCTCL.setModel(new DefaultComboBoxModel(chatLieu.toArray()));
        cbo_FilterSPCTKD.setModel(new DefaultComboBoxModel(kieuDang.toArray()));
        cbo_FilterSPCTMPH.setModel(new DefaultComboBoxModel(muaPhuHop.toArray()));
        System.out.println(muaPhuHop.toArray());
    }

    public void loadTableHDCTTheoIDHD(int id) {
        tongTien = 0;
        listHDCT = hdctService.getAllByIdHoaDon(id);
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
        stt = 1;
        dtm.setRowCount(0);
        for (HoaDonChiTiet x : listHDCT) {
            Object[] rowData = {
                stt,
                sanPhamService.getNameID(x.getSanPhamChiTiet().getId_SanPham()),
                x.getSoLuong(),
                x.getGiaTien()
            };
            dtm.addRow(rowData);
            stt++;
            tongTien += x.getGiaTien() * x.getSoLuong();
        }
        txtTongTien.setText(String.valueOf(tongTien));
        txtTongTien1.setText(String.valueOf(tongTien));
        tienConLai = Double.valueOf(txtTongTien1.getText()) - tienDaTT;
        txtConLai.setText(String.valueOf(tienConLai));
    }

    public void loadTableThanhToanTheoIDHD(int id) {
        listPTTTCT = pTTTChiTietRepository.getAllByIDHoaDon(id);
        DefaultTableModel dtm = (DefaultTableModel) tblPTTT.getModel();
        stt = 1;
        dtm.setRowCount(0);
        for (PTTTChiTiet x : listPTTTCT) {
            Object[] rowData = {
                stt,
                x.getPhuongThucThanhToan().getTen(),
                x.getSoTien(),
                x.getMaGD()
            };
            dtm.addRow(rowData);
            stt++;
            tienDaTT += x.getSoTien();
        }
        txtTienDaThanhToan.setText(String.valueOf(tienDaTT));
        tienConLai = Double.valueOf(txtTongTien1.getText()) - tienDaTT;
        txtConLai.setText(String.valueOf(tienConLai));
    }

    public void loadTableHDCho() {
        DefaultTableModel dtm = (DefaultTableModel) tblHDDangCho.getModel();
        stt = 1;
        dtm.setRowCount(0);
        for (HoaDon x : listHDCho) {
            System.out.println(x.toString());
            if (x.getTrangThai() == 0) {
                Object[] rowData = {
                    stt,
                    x.getMa(),
                    x.getNgayTao(),
                    x.getNhanVien().getMa(),
                    x.getTrangThai() == 0 ? "Chưa thanh toán" : null
                };
                dtm.addRow(rowData);
                stt++;
            }
        }
//        tblHDDangCho.setRowSelectionInterval(0, 0);
    }

    public void loadTableSP() {
        DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
        tb.setRowCount(0);
        int count = 1;
        for (SanPhamCT x : listSP) {
            int id_sanPham = x.getId_SanPham();
            String MaSP = spService.getNameByIDSP(id_sanPham);
            //System.out.println("name SP" +MaSP);
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS = msService.getNameByID(id_mauSac);
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            //System.out.println("name TH: " +nameTH);
            //id kich thuoc
            int id_kichThuoc = x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            //id_KieuDang;
            int id_kieuDang = x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            //id_MuaPhuHop;
            int id_muaPhuHop = x.getId_MuaPhuHop();
            String nameMPH = mphService.getNameByID(id_muaPhuHop);
            //id_MDSD;
            int id_MDSD = x.getId_MDSD();
            String nameMDSD = msdsService.getNameByID(id_MDSD);
            //id_ChatLieu;
            int id_chatLieu = x.getId_ChatLieu();
            String nameCL = clService.getNameByID(id_chatLieu);
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
            //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
            //ma;
            String ma = x.getMa();
            //goi_Tinh;
            boolean gioi_tinh = x.isGoi_Tinh();
            String gioiTinh = null;
            if (gioi_tinh == true) {
                gioiTinh = "nam";
            } else {
                gioiTinh = "Nữ";
            }
            //so_Luong;
            float so_Luong = x.getSo_Luong();
            //gia;
            float gia = x.getGia();
            //mo_Ta;
            String mo_ta = x.getMo_Ta();
            //trang_thai;
            int trang_thai = x.getTrang_thai();
            boolean trangThai = true;
            if (trang_thai == 0) {
                trangThai = true;
            } else {
                trangThai = false;
            }
//            tb.addRow(new Object[]{
//                id_hinhAnh, ma, id_sanPham,id_thuongHieu, id_mauSac, id_kichThuoc, so_Luong, gia, id_chatLieu, id_kieuDang,  
//                id_muaPhuHop, id_MDSD, id_hoaTiet, gioiTinh, StatusType.DANGHOATDONG
//            });
            tb.addRow(new Object[]{
                count, ma, MaSP, trangThai, nameTH, nameMS, nameKT, so_Luong, gia, nameCL,
                nameKD, nameMPH, nameMDSD, nameHT, gioiTinh, id_hinhAnh
            });
            count++;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new view.component.Table();
        txtTongTien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDDangCho = new view.component.Table();
        btnHoaDonMoi = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new view.component.Table();
        jLabel19 = new javax.swing.JLabel();
        txtSeachSP = new view.component.SearchText();
        btnTimKiem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        cbo_FilterSPCTMS = new javax.swing.JComboBox<>();
        cbo_FilterSPCTKT = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbo_FilterSPCTCL = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbo_FilterSPCTTH = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbo_FilterSPCTMPH = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbo_FilterSPCTKD = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnTimKH = new javax.swing.JButton();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        cbGiaoHang = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        txtTienTT = new javax.swing.JTextField();
        cbbThanhToan = new view.component.ComboBox();
        jLabel7 = new javax.swing.JLabel();
        btnNhanTien = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPTTT = new view.component.Table();
        panelQuetMa = new javax.swing.JPanel();
        btnTTThanhCong = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtPhieuGG = new javax.swing.JTextField();
        btnPhieuGG = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTienDaThanhToan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Số lượng", "Đơn giá"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        tblGioHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                none(evt);
            }
        });
        jScrollPane5.setViewportView(tblGioHang);

        txtTongTien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTien.setText("tientong");
        txtTongTien.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("BÁN HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(487, 487, 487)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn đang chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblHDDangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày tạo", "Nhân viên", "Trạng thái"
            }
        ));
        tblHDDangCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDDangChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHDDangCho);

        btnHoaDonMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnHoaDonMoi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnHoaDonMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/add-file-32.png"))); // NOI18N
        btnHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonMoiActionPerformed(evt);
            }
        });

        jLabel16.setText("Bật/Tắt Cam");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHoaDonMoi)
                .addGap(40, 40, 40)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Mã SP", "Trạng thái", "Thương hiệu", "Màu sắc", "Kích thước", "Số lượng", "Đơn giá", "Chất liệu", "Kiểu dáng", "Họa tiết"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel19.setText("Tìm kiếm:");

        btnTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        cbo_FilterSPCTMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTMSItemStateChanged(evt);
            }
        });

        cbo_FilterSPCTKT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTKTItemStateChanged(evt);
            }
        });

        jLabel6.setText("Màu sắc:");

        jLabel8.setText("Kích thước:");

        cbo_FilterSPCTCL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTCLItemStateChanged(evt);
            }
        });
        cbo_FilterSPCTCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_FilterSPCTCLActionPerformed(evt);
            }
        });

        jLabel10.setText("Chất liệu:");

        cbo_FilterSPCTTH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTTHItemStateChanged(evt);
            }
        });

        jLabel13.setText("Thương hiệu:");

        cbo_FilterSPCTMPH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTMPHItemStateChanged(evt);
            }
        });

        jLabel14.setText("Mùa phù hợp:");

        jLabel15.setText("Kiểu dáng:");

        cbo_FilterSPCTKD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTKDItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbo_FilterSPCTMS, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cbo_FilterSPCTKT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSeachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimKiem)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbo_FilterSPCTMPH, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbo_FilterSPCTKD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cbo_FilterSPCTCL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)
                                .addComponent(cbo_FilterSPCTTH, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_FilterSPCTMPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(cbo_FilterSPCTKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_FilterSPCTMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_FilterSPCTKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(cbo_FilterSPCTCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cbo_FilterSPCTTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Nhập SDT:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Họ tên:");

        btnTimKH.setBackground(new java.awt.Color(255, 255, 255));
        btnTimKH.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnTimKH.setText("Tìm");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("SDT:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        cbGiaoHang.setBackground(new java.awt.Color(255, 255, 255));
        cbGiaoHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbGiaoHang.setText("Giao hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtHoTen)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKH))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(121, 121, 121))
                            .addComponent(jScrollPane2)))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbGiaoHang)
                .addGap(12, 12, 12))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("Số tiền:");

        btnNhanTien.setBackground(new java.awt.Color(255, 255, 255));
        btnNhanTien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnNhanTien.setText("Đã nhận được tiền");
        btnNhanTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanTienActionPerformed(evt);
            }
        });

        tblPTTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Phương thức", "Số tiền", "Mã GD"
            }
        ));
        jScrollPane4.setViewportView(tblPTTT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNhanTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(cbbThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanTien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        panelQuetMa.setBackground(new java.awt.Color(255, 255, 255));
        panelQuetMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelQuetMaMouseClicked(evt);
            }
        });
        panelQuetMa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTTThanhCong.setBackground(new java.awt.Color(153, 255, 102));
        btnTTThanhCong.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTTThanhCong.setForeground(new java.awt.Color(255, 255, 255));
        btnTTThanhCong.setText("Đã nhận đủ tiền");
        btnTTThanhCong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
        btnTTThanhCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTThanhCongActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        btnPhieuGG.setBackground(new java.awt.Color(255, 255, 255));
        btnPhieuGG.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnPhieuGG.setText("OK");
        btnPhieuGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuGGActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel20.setText("Nhập mã PGG:");

        txtTienGiam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTienGiam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTienGiam.setText("tienGiam");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPhieuGG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhieuGG)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtPhieuGG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhieuGG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Tổng tiền:");

        txtTongTien1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTien1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTien1.setText("Tổng tiền:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setText("Đã thanh toán:");

        txtTienDaThanhToan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTienDaThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTienDaThanhToan.setText("Tổng tiền:");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText("Còn lại:");

        txtConLai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtConLai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtConLai.setText("Tổng tiền:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien1)
                            .addComponent(txtConLai)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTTThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienDaThanhToan))))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelQuetMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTongTien1))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTienDaThanhToan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtConLai))
                        .addGap(18, 18, 18)
                        .addComponent(btnTTThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTTThanhCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTThanhCongActionPerformed
        if (tienConLai > 0) {
            MsgBox.alert(this, "Bạn chưa đủ tiền");
            return;
        }
        hd.setKhachHang(khachHang);
        hd.setNhanVien(nhanVien);
        hd.setDiaChi(txtDiaChi.getText());
        hd.setNgaySua(new Date());
        hd.setSdt(txtSDT.getText());
        if (khuyenMai != null) {
            hd.setSoTienGiam(Double.valueOf(txtTienGiam.getText()));
        } else {
            hd.setSoTienGiam(0.0);
        }
        hd.setSuaBoi(nhanVien.getMa());
        hd.setTenKhachHang(txtHoTen.getText());
        if (khuyenMai != null) {
            hd.setVoucher(khuyenMai);
        }

        if (cbGiaoHang.isSelected()) {
            hd.setTienShip(15000.0);
            hd.setTrangThai(2);
        } else {
            hd.setTienShip(0.0);
            hd.setTrangThai(1);
        }
        hdService.updateFull(hd);
        txtSDT.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtTimKH.setText("");
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai() == 0).collect(Collectors.toList());
        tongTien = 0;
        tienDaTT = 0;
        tienConLai = 0;
        loadTableHDCho();
        loadTableSP();
        MsgBox.alert(this, "Thanh toán thành công");

    }//GEN-LAST:event_btnTTThanhCongActionPerformed

    private void btnHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonMoiActionPerformed
        txtSDT.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtTimKH.setText("");
        hd = new HoaDon(null, null, null, nhanVien, "HD", null, null, null, null, null, null, 0, null, null, nhanVien.getMa(), nhanVien.getMa(), Boolean.FALSE);
        hdService.insert(hd);
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai() == 0).collect(Collectors.toList());
        loadTableHDCho();
        MsgBox.alert(this, "Tạo thành công");
    }//GEN-LAST:event_btnHoaDonMoiActionPerformed

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        String sdt = txtTimKH.getText();
        for (KhachHang x : khachHangService.getAllKh()) {
            if (x.getSdt().equals(sdt)) {
                khachHang = x;
            }
        }
        if (khachHang == null) {
            MsgBox.alert(this, "Không tìm thấy tài khoản");
            return;
        }
        txtHoTen.setText(khachHang.getTen());
        txtSDT.setText(khachHang.getSdt());
        txtDiaChi.setText(khachHang.getDiaChi());
        MsgBox.alert(this, "Đã tìm thấy khách hàng");

    }//GEN-LAST:event_btnTimKHActionPerformed

    private void tblHDDangChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDDangChoMouseClicked
        int row = tblHDDangCho.getSelectedRow();
        if (row == -1) {
            return;
        }
        hd = listHDCho.get(row);
        if (hd.getKhachHang() != null) {
            KhachHang kh = hd.getKhachHang();
            txtHoTen.setText(kh.getTen());
            txtSDT.setText(kh.getSdt());
            txtDiaChi.setText(kh.getDiaChi());
        } else {
            txtHoTen.setText(hd.getTenKhachHang());
            txtSDT.setText(hd.getSdt());
            txtDiaChi.setText(hd.getDiaChi());
        }
        if (hd.getVoucher() != null) {
            khuyenMai = hd.getVoucher();
            txtTienGiam.setText(String.valueOf(khuyenMai.getTienGiam()));
        } else {
            khuyenMai = null;
        }
        tongTien = 0;
        tienConLai = 0;
        tienDaTT = 0;
        txtTienGiam.setText("");
        loadTableHDCTTheoIDHD(hd.getId());
        loadTableThanhToanTheoIDHD(hd.getId());

    }//GEN-LAST:event_tblHDDangChoMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
        int sl = Integer.valueOf(JOptionPane.showInputDialog("Nhập số lượng:"));
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        sanPham = listSP.get(row);
        if (sanPham.getSo_Luong() > sl) {
            hdct = new HoaDonChiTiet(null, hd, sanPham, sl, sl * sanPham.getGia(), sanPham.getGia(),
                    0, null, null, nhanVien.getMa(), nhanVien.getMa(), Boolean.FALSE);
            if (listHDCT.stream().anyMatch(x -> x.getSanPhamChiTiet().getId() == sanPham.getId())) {
                for (HoaDonChiTiet x : listHDCT) {
                    if (x.getSanPhamChiTiet().getId() == sanPham.getId()) {
                        hdct.setId(x.getId());
                        hdct.setSoLuong(x.getSoLuong() + sl);
                        hdct.setThanhTien(hdct.getSoLuong() * hdct.getGiaTien());
                        hdctService.update(hdct);
                    }
                }
            } else {
                hdctService.insert(hdct);

            }
            sanPham.setSo_Luong(sanPham.getSo_Luong() - sl);
            spctService.UpdateSPCT(sanPham);
        } else {
            MsgBox.alert(this, "Số lượng không đủ. Vui lòng nhập lại");
            return;
        }
        loadTableSP();
        loadTableHDCTTheoIDHD(hd.getId());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnPhieuGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuGGActionPerformed
        for (KhuyenMai x : khuyenMaiService.getAll()) {
            if (txtPhieuGG.getText().equalsIgnoreCase(x.getMa())) {
                if (x.getNgayBD().before(new Date()) && x.getNgayKT().after(new Date())) {
                    MsgBox.alert(this, "Đã áp dụng giảm giá");
                    khuyenMai = x;
                    tongTien -= x.getTienGiam();
                    tienConLai -= x.getTienGiam();
                    txtTienGiam.setText("-" + String.valueOf(x.getTienGiam()));
                    txtTongTien1.setText(String.valueOf(tongTien));
                    txtTienGiam.setText(String.valueOf(tienConLai));
                } else {
                    MsgBox.alert(this, "Voucher hết hạn");
                }
                return;
            }
        }
        MsgBox.alert(this, "Không tìm thấy mã giảm giá");
    }//GEN-LAST:event_btnPhieuGGActionPerformed

    private void none(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_none
        // TODO add your handling code here:
    }//GEN-LAST:event_none

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        int row = tblGioHang.getSelectedRow();
        if (row == -1) {
            return;
        }
        int sl = Integer.valueOf(JOptionPane.showInputDialog("Nhập số lượng:"));
        HoaDonChiTiet hdct = listHDCT.get(row);
        for (SanPhamCT x : spctService.getALL()) {
            if (x.getId() == hdct.getSanPhamChiTiet().getId()) {
                sanPham = x;
            }
        }
        if (hdct.getSoLuong() <= sl) {
            hdctService.delete(hdct.getId());
        } else {
            hdct.setSoLuong(hdct.getSoLuong() - sl);
            hdctService.update(hdct);
        }
        sanPham.setSo_Luong(sanPham.getSo_Luong() + sl);
        spctService.UpdateSPCT(sanPham);
        listSP = spctService.getALL();
        loadTableSP();
        loadTableHDCTTheoIDHD(hd.getId());
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        listSP = spctService.getallWithMaSPCT(txtSeachSP.getText());
        loadTableSP();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        listSP = spctService.getALL();
        loadTableSP();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnNhanTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanTienActionPerformed
        PTTTChiTiet pTTTChiTiet = new PTTTChiTiet(0, hd,
                new PTTTRepository().getByID(cbbThanhToan.getSelectedIndex()),
                Double.valueOf(txtTienTT.getText()),
                0, "ko",
                null, null, nhanVien.getMa(), nhanVien.getMa(),
                Boolean.FALSE);
        pTTTChiTietRepository.add(pTTTChiTiet);
        loadTableThanhToanTheoIDHD(hd.getId());

    }//GEN-LAST:event_btnNhanTienActionPerformed

    private void cbo_FilterSPCTMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTMSItemStateChanged
        String mauSacName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTMS.getSelectedItem();
            if (selectedItem instanceof MauSac) {
                MauSac mauSac = (MauSac) selectedItem;
                // Now you can work with the MauSac object
                mauSacName = mauSac.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + mauSacName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_MauSac = msService.getIDbyName(mauSacName);
            System.out.println("id ms: " + id_MauSac);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterMauSac(id_MauSac);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_sanPham
                int id_sanPham = x.getId_SanPham();
                String MaSP = spService.getNameByIDSP(id_sanPham);
                //id mau sac
                int id_mauSac = x.getId_MauSac();
                String nameMS = msService.getNameByID(id_mauSac);
                //id thuong hieu
                int id_thuongHieu = x.getId_ThuongHieu();
                String nameTH = thService.getNameByID(id_thuongHieu);
                //id kich thuoc
                int id_kichThuoc = x.getId_KichThuoc();
                String nameKT = ktService.getNameByID(id_kichThuoc);
                //id_KieuDang;
                int id_kieuDang = x.getId_KieuDang();
                String nameKD = kdService.getNameByID(id_kieuDang);
                //id_MuaPhuHop;
                int id_muaPhuHop = x.getId_MuaPhuHop();
                String nameMPH = mphService.getNameByID(id_muaPhuHop);
                //id_MDSD;
                int id_MDSD = x.getId_MDSD();
                String nameMDSD = msdsService.getNameByID(id_MDSD);
                //id_ChatLieu;
                int id_chatLieu = x.getId_ChatLieu();
                String nameCL = clService.getNameByID(id_chatLieu);
                //id_HoaTiet;
                int id_hoaTiet = x.getId_HoaTiet();
                String nameHT = htService.getNameByID(id_hoaTiet);
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, MaSP, trangThai, nameTH, nameMS, nameKT, so_Luong, gia, nameCL,
                    nameKD, nameMPH, nameMDSD, nameHT, gioiTinh, id_hinhAnh
                });
                count++;
            }
            listSP = listSPCT;
        }
    }//GEN-LAST:event_cbo_FilterSPCTMSItemStateChanged

    private void cbo_FilterSPCTKTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTKTItemStateChanged
        // TODO add your handling code here:
        String ktName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTKT.getSelectedItem();
            if (selectedItem instanceof KichThuoc) {
                KichThuoc kichThuoc = (KichThuoc) selectedItem;
                // Now you can work with the MauSac object
                ktName = kichThuoc.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + ktName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_KT = ktService.getIDbyName(ktName);
            System.out.println("id ms: " + id_KT);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterKichThuoc(id_KT);
            listSP = listSPCT;
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_sanPham
                int id_sanPham = x.getId_SanPham();
                String MaSP = spService.getNameByIDSP(id_sanPham);
                //id mau sac
                int id_mauSac = x.getId_MauSac();
                String nameMS = msService.getNameByID(id_mauSac);
                //id thuong hieu
                int id_thuongHieu = x.getId_ThuongHieu();
                String nameTH = thService.getNameByID(id_thuongHieu);
                //id kich thuoc
                int id_kichThuoc = x.getId_KichThuoc();
                String nameKT = ktService.getNameByID(id_kichThuoc);
                //id_KieuDang;
                int id_kieuDang = x.getId_KieuDang();
                String nameKD = kdService.getNameByID(id_kieuDang);
                //id_MuaPhuHop;
                int id_muaPhuHop = x.getId_MuaPhuHop();
                String nameMPH = mphService.getNameByID(id_muaPhuHop);
                //id_MDSD;
                int id_MDSD = x.getId_MDSD();
                String nameMDSD = msdsService.getNameByID(id_MDSD);
                //id_ChatLieu;
                int id_chatLieu = x.getId_ChatLieu();
                String nameCL = clService.getNameByID(id_chatLieu);
                //id_HoaTiet;
                int id_hoaTiet = x.getId_HoaTiet();
                String nameHT = htService.getNameByID(id_hoaTiet);
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, MaSP, trangThai, nameTH, nameMS, nameKT, so_Luong, gia, nameCL,
                    nameKD, nameMPH, nameMDSD, nameHT, gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTKTItemStateChanged

    private void cbo_FilterSPCTCLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTCLItemStateChanged
        String clName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTCL.getSelectedItem();
            if (selectedItem instanceof ChatLieu) {
                ChatLieu chatLieu = (ChatLieu) selectedItem;
                // Now you can work with the MauSac object
                clName = chatLieu.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + clName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_CL = clService.getIDbyName(clName);
            System.out.println("id ms: " + id_CL);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterChatLieu(id_CL);
            listSP = listSPCT;
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()),
                    msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()),
                    so_Luong, gia, clService.getNameByID(x.getId_ChatLieu()), kdService.getNameByID(x.getId_KieuDang()),
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()),
                    htService.getNameByID(x.getId_HoaTiet()), gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTCLItemStateChanged

    private void cbo_FilterSPCTTHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTTHItemStateChanged
        // TODO add your handling code here:
        String thName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTTH.getSelectedItem();
            if (selectedItem instanceof ThuongHieu) {
                ThuongHieu thuongHieu = (ThuongHieu) selectedItem;
                // Now you can work with the MauSac object
                thName = thuongHieu.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + thName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_CL = thService.getIDbyName(thName);
            System.out.println("id ms: " + id_CL);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterThuongHieu(id_CL);
            listSP = listSPCT;
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()),
                    msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()),
                    so_Luong, gia, clService.getNameByID(x.getId_ChatLieu()), kdService.getNameByID(x.getId_KieuDang()),
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()),
                    htService.getNameByID(x.getId_HoaTiet()), gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTTHItemStateChanged

    private void cbo_FilterSPCTMPHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTMPHItemStateChanged
        // TODO add your handling code here:
        String mphName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTMPH.getSelectedItem();
            if (selectedItem instanceof MuaPhuHop) {
                MuaPhuHop mph = (MuaPhuHop) selectedItem;
                // Now you can work with the MauSac object
                mphName = mph.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + mphName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_MPH = mphService.getIDbyName(mphName);
            System.out.println("id ms: " + id_MPH);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterMPH(id_MPH);
            listSP = listSPCT;
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()),
                    msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()),
                    so_Luong, gia, clService.getNameByID(x.getId_ChatLieu()), kdService.getNameByID(x.getId_KieuDang()),
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()),
                    htService.getNameByID(x.getId_HoaTiet()), gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTMPHItemStateChanged

    private void cbo_FilterSPCTKDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTKDItemStateChanged
        // TODO add your handling code here:

        String kdName = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTKD.getSelectedItem();
            if (selectedItem instanceof KieuDang) {
                KieuDang kieuDang = (KieuDang) selectedItem;
                // Now you can work with the MauSac object
                kdName = kieuDang.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + kdName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_KD = kdService.getIDbyName(kdName);
            System.out.println("id ms: " + id_KD);

            DefaultTableModel tb = (DefaultTableModel) tblSanPham.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterKieuDang(id_KD);
            listSP = listSPCT;
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    count, ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()), msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()),
                    so_Luong, gia, clService.getNameByID(x.getId_ChatLieu()), kdService.getNameByID(x.getId_KieuDang()),
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()),
                    htService.getNameByID(x.getId_HoaTiet()), gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTKDItemStateChanged

    private void cbo_FilterSPCTCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_FilterSPCTCLActionPerformed

    
    boolean c = true;
    private void panelQuetMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelQuetMaMouseClicked




    }//GEN-LAST:event_panelQuetMaMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        if(webCam.isOpen()){
            webCam.close();
        }else{
            innitCam();
        }
        
    }//GEN-LAST:event_jLabel16MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHoaDonMoi;
    private javax.swing.JButton btnNhanTien;
    private javax.swing.JButton btnPhieuGG;
    private javax.swing.JButton btnTTThanhCong;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JCheckBox cbGiaoHang;
    private view.component.ComboBox cbbThanhToan;
    private javax.swing.JComboBox<String> cbo_FilterSPCTCL;
    private javax.swing.JComboBox<String> cbo_FilterSPCTKD;
    private javax.swing.JComboBox<String> cbo_FilterSPCTKT;
    private javax.swing.JComboBox<String> cbo_FilterSPCTMPH;
    private javax.swing.JComboBox<String> cbo_FilterSPCTMS;
    private javax.swing.JComboBox<String> cbo_FilterSPCTTH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panelQuetMa;
    private view.component.Table tblGioHang;
    private view.component.Table tblHDDangCho;
    private view.component.Table tblPTTT;
    private view.component.Table tblSanPham;
    private javax.swing.JLabel txtConLai;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtPhieuGG;
    private javax.swing.JTextField txtSDT;
    private view.component.SearchText txtSeachSP;
    private javax.swing.JLabel txtTienDaThanhToan;
    private javax.swing.JLabel txtTienGiam;
    private javax.swing.JTextField txtTienTT;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTongTien1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;
            if (webCam.isOpen()) {
                if ((image = webCam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitMap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitMap);

            } catch (Exception e) {
                //e.printStackTrace();
            }
            if (result != null) {
                System.out.println(result.getText());
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);//rootPaneCheckingEnabled
        return t;
    }
}
