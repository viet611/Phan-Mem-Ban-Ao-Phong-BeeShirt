/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.NhanVien;
import domainmodels.PTTTChiTiet;
import domainmodels.SanPham;
import domainmodels.SanPhamCT;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
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
import services.impl.HoaDonChiTietServiceImpl;
import services.impl.HoaDonServiceImpl;
import services.impl.KhachHangServiceImpl;
import services.impl.KhuyenMaiServiceIMPL;
import services.impl.KichThuongImpl;
import services.impl.MauSacImpl;
import services.impl.NhanVienServiceImpl;
import services.impl.SanPhamCTImpl;
import services.impl.SanPhamImpl;
import services.impl.ThuongHieuImpl;
import ultilities.MsgBox;

/**
 *
 * @author Admin
 */
public class BanHangPanel extends javax.swing.JPanel {

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
    public BanHangPanel() {
        initComponents();
        txtSeachSP.hint = "Mã SP";
        listSP = spctService.getALL();
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai()==0).collect(Collectors.toList());
        loadTableHDCho();
        loadTableSP();
        loadCBBTrangThai1();
    }

    public void loadCBBTrangThai1() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cbbThanhToan.getModel();
        dcbm.addElement("Chọn phương thức");
        dcbm.addElement("Tiền mặt");
        dcbm.addElement("Chuyển khoản");
    }
    
    public void loadTableHDCTTheoIDHD(int id) {
        
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
                    x.getNhanVien().getMa()
                };
                dtm.addRow(rowData);
                stt++;
            }
        }
//        tblHDDangCho.setRowSelectionInterval(0, 0);
    }

    public void loadTableSP() {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        stt = 1;
        for (SanPhamCT x : listSP) {
            Object[] obj = {
                stt,
                x.getMa(),
                sanPhamService.getNameID(x.getId_SanPham()),
                thuongHieuService.getNameByID(x.getId_KichThuoc()),
                kichThuocService.getNameByID(x.getId_KichThuoc()),
                mauSacService.getNameByID(x.getId_MauSac()),
                x.getGia(),
                x.getSo_Luong()
            };
            stt++;
            dtm.addRow(obj);
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
        btnTTThanhCong = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new view.component.Table();
        txtTongTien = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JLabel();
        txtTraTienThua = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPhieuGG = new javax.swing.JTextField();
        btnPhieuGG = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDDangCho = new view.component.Table();
        btnHoaDonMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new view.component.Table();
        jLabel19 = new javax.swing.JLabel();
        txtSeachSP = new view.component.SearchText();
        btnTimKiem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
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
        btnXoaTienTT = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPTTT = new view.component.Table();
        jLabel9 = new javax.swing.JLabel();
        txtTienDaThanhToan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtConLai = new javax.swing.JLabel();
        txtTongTien1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

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
                tblGioHangKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblGioHang);

        txtTongTien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTien.setToolTipText("");

        txtTienGiam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTienGiam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtTraTienThua.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtTraTienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTraTienThua.setText("Trả:");

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel20.setText("Nhập mã PGG:");

        btnPhieuGG.setBackground(new java.awt.Color(255, 255, 255));
        btnPhieuGG.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnPhieuGG.setText("OK");
        btnPhieuGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuGGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPhieuGG))
                    .addComponent(btnTTThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPhieuGG)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTraTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtPhieuGG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPhieuGG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTraTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(btnTTThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(461, 461, 461)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn đang chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblHDDangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày tạo", "Nhân viên"
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
        btnHoaDonMoi.setText("Hóa đơn mới");
        btnHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnHoaDonMoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã ", "Tên", "Thương hiệu", "Kích thước", "Màu sắc", "Đơn giá", "Số lượng tồn"
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(txtSeachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeachSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKH))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(135, 135, 135))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
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

        btnXoaTienTT.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTienTT.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnXoaTienTT.setText("Xóa");
        btnXoaTienTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTienTTActionPerformed(evt);
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

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Tổng tiền:");

        txtTienDaThanhToan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTienDaThanhToan.setText("Tổng tiền:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setText("Đã thanh toán:");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText("Còn lại:");

        txtConLai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtConLai.setText("Tổng tiền:");

        txtTongTien1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTien1.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienTT))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnNhanTien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaTienTT))
                    .addComponent(cbbThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTien1)
                    .addComponent(txtConLai)
                    .addComponent(txtTienDaThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhanTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaTienTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtTongTien1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTienDaThanhToan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtConLai)))))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã SP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTTThanhCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTThanhCongActionPerformed
//        if(tienConLai > 0){
//            MsgBox.alert(this, "Bạn chưa đủ tiền");
//            return;
//        }
System.out.println(hd.toString());
        hd.setKhachHang(khachHang);
        hd.setNhanVien(nhanVien);
        hd.setDiaChi(txtDiaChi.getText());
        hd.setNgaySua(new Date());
        hd.setSdt(txtSDT.getText());
        if(khuyenMai != null){
            hd.setSoTienGiam(Double.valueOf(txtTienGiam.getText()));
        }else{
            hd.setSoTienGiam(0.0);
        }
        hd.setSuaBoi(nhanVien.getMa());
        hd.setTenKhachHang(txtHoTen.getText());
        hd.setVoucher(khuyenMai);
        if(cbGiaoHang.isSelected()){
            hd.setTienShip(15000.0);
            hd.setTrangThai(2);
        }else{
            hd.setTienShip(0.0);
            hd.setTrangThai(1);
        }
        System.out.println("alo");
        hdService.updateFull(hd);
        System.out.println(hd.toString());
        txtSDT.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtTimKH.setText("");
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai()==0).collect(Collectors.toList());
        loadTableHDCho();
        
        
    }//GEN-LAST:event_btnTTThanhCongActionPerformed

    private void btnXoaTienTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTienTTActionPerformed
        
    }//GEN-LAST:event_btnXoaTienTTActionPerformed

    private void btnHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonMoiActionPerformed
        txtSDT.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtTimKH.setText("");
        hd = new HoaDon(null, null, null, nhanVien, "HD", null, null, null, null, null, null, 0, null, null, nhanVien.getMa(), nhanVien.getMa(), Boolean.FALSE);
        hdService.insert(hd);
        listHDCho = hdService.getAll().stream().filter(c -> c.getTrangThai()==0).collect(Collectors.toList());
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
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        sanPham = listSP.get(row);
        if (sanPham.getSo_Luong() > 0) {
            hdct = new HoaDonChiTiet(null, hd, sanPham, 1, 1 * sanPham.getGia(), sanPham.getGia(),
                    0, null, null, nhanVien.getMa(), nhanVien.getMa(), Boolean.FALSE);
            if (listHDCT.stream().anyMatch(x -> x.getSanPhamChiTiet().getId() == sanPham.getId())) {
                for (HoaDonChiTiet x : listHDCT) {
                    if (x.getSanPhamChiTiet().getId() == sanPham.getId()) {
                        hdct.setId(x.getId());
                        hdct.setSoLuong(x.getSoLuong() + 1);
                        hdct.setThanhTien(hdct.getSoLuong() * hdct.getGiaTien());
                        hdctService.update(hdct);
                    }
                }
            } else {
                hdctService.insert(hdct);

            }
            sanPham.setSo_Luong(sanPham.getSo_Luong() - 1);
            spctService.UpdateSPCT(sanPham);
        } else {
            MsgBox.alert(this, "Sản phẩm hết hàng");
        }
        loadTableSP();
        loadTableHDCTTheoIDHD(hd.getId());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnPhieuGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuGGActionPerformed
        for(KhuyenMai x: khuyenMaiService.getAll()){
            if(txtPhieuGG.getText().equalsIgnoreCase(x.getMa())){
                if(x.getNgayBD().before(new Date()) && x.getNgayKT().after(new Date())){
                    MsgBox.alert(this, "Đã áp dụng giảm giá");
                    khuyenMai = x;
                    txtTienGiam.setText("-"+String.valueOf(x.getTienGiam()));
                    txtTongTien1.setText(String.valueOf(tongTien-x.getTienGiam()));
                }else{
                    MsgBox.alert(this, "Voucher hết hạn");
                }
                return;
            }
        }
        MsgBox.alert(this, "Không tìm thấy mã giảm giá");
    }//GEN-LAST:event_btnPhieuGGActionPerformed

    private void tblGioHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGioHangKeyPressed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        int row = tblGioHang.getSelectedRow();
        if (row == -1) {
            return;
        }
        HoaDonChiTiet hdct = listHDCT.get(row);
        for (SanPhamCT x : listSP) {
            if (x.getId() == hdct.getSanPhamChiTiet().getId()) {
                sanPham = x;
            }
        }
        if (hdct.getSoLuong() == 1) {
            hdctService.delete(hdct.getId());
        }else{
            hdct.setSoLuong(hdct.getSoLuong()-1);
            hdctService.update(hdct);
        }
        sanPham.setSo_Luong(sanPham.getSo_Luong() + 1);
        spctService.UpdateSPCT(sanPham);
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
                new PTTTRepository().getByID(cbbThanhToan.getSelectedIndex())
                , Double.valueOf(txtTienTT.getText()),
                0, "ko",
                null, null, nhanVien.getMa(), nhanVien.getMa()
                , Boolean.FALSE);        
        pTTTChiTietRepository.add(pTTTChiTiet);
        loadTableThanhToanTheoIDHD(hd.getId());
        
    }//GEN-LAST:event_btnNhanTienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHoaDonMoi;
    private javax.swing.JButton btnNhanTien;
    private javax.swing.JButton btnPhieuGG;
    private javax.swing.JButton btnTTThanhCong;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaTienTT;
    private javax.swing.JCheckBox cbGiaoHang;
    private view.component.ComboBox cbbThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JLabel txtTraTienThua;
    // End of variables declaration//GEN-END:variables
}
