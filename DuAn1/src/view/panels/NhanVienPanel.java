/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import domainmodels.NhanVien;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import services.NhanVienService;
import services.ChucVuService;
import services.impl.ChucVuServiceImpl;
import services.impl.NhanVienServiceImpl;
import ultilities.MsgBox;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel {

    int rowOffset = 0;
    int fetch = 5;
    String name = "";
    int index = 0;
    int o = 0;
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private NhanVienService impl = new NhanVienServiceImpl();
    private ChucVuService service = new ChucVuServiceImpl();
    private List<NhanVienViewModel> listNV = new ArrayList<>();
    private DefaultComboBoxModel modelccbCV;
    private List<NhanVien> list = new ArrayList<>();
    List<String> idCV = impl.IDChucVu();

    /**
     * Creates new form Form_1
     */
    public NhanVienPanel() {
        initComponents();
        tbQuanLyNhanVien.setModel(dtm);
        String[] hears = {"STT", "Mã NV", "Tên", "Giới Tính", "Ngày sinh", "SDT", "Địa chỉ", "Chức vụ", "Trạng thái"};
        dtm.setColumnIdentifiers(hears);
        listNV = impl.getAll();
        showdataTable(listNV);
        addCbb();
        // impl.showData(dtm, listNV);
        //loadSoTrang();
    }

    public void addCbb() {
        modelccbCV = (DefaultComboBoxModel) cbb.getModel();
        modelccbCV.addAll(impl.ChucVuCBB());

    }

    /*  private void loadSoTrang() {
        jLabel1.setText(index + 1 + "");
    }*/
    public String getStatus(int status) {
        if (status == 1) {
            return "Nghỉ việc";
        } else if (status == 2) {
            return "Đang làm việc";
        } else {
            return null;
        }
    }

    public String getIdUser(int idUser) {
        if (idUser == 1) {
            return "Quản lý";
        } else if (idUser == 2) {
            return "Nhân viên";
        } else {
            return null;
        }
    }

    public void showdataTable(List<NhanVienViewModel> list) {
        dtm.setRowCount(0);
        int stt = 1;
        for (NhanVienViewModel km : list) {
            dtm.addRow(new Object[]{
                stt++,
                km.getMa(),
                km.getTen(),
                km.isSex() ? "Nam" : "Nữ",
                km.getNgaySinh(),
                km.getSdt(),
                km.getDiaChi(),
                getIdUser(km.getIdUser()),
                //  km.getIdUser(),
                getStatus(km.getStatus())
            });
            // dtm.addRow(km.toDataRow());
        }
    }

    public boolean checkData() {
        if (txtMaNV.getText().isBlank()) {
            MsgBox.warring(this, "Mã nhân viên không được để trống!");
            return false;
        } else if (!txtMaNV.getText().matches("^[a-zA-Z0-9]*$")) {
            MsgBox.warring(this, "Mã nhân viên không được có ký tự đặc biệt!");
            return false;
        } else if (txtTenNV.getText().isBlank()) {
            MsgBox.warring(this, "Tên nhân viên không được để trống!");
            return false;
        } else if (txtDiaChiNV.getText().isBlank()) {
            MsgBox.warring(this, "Địa chỉ nhân viên không được để trống!");
            return false;
        } else if (txtNgaySinhNV.getText().isBlank()) {
            MsgBox.warring(this, "Ngày sinh nhân viên không được để trống!");
            return false;
        } else if (txtPass.getText().isBlank()) {
            MsgBox.warring(this, "Mật khẩu nhân viên được để trống!");
            return false;
        } else if (txtSDT.getText().isBlank()) {
            MsgBox.warring(this, "SĐT nhân viên không được để trống!");
            return false;
        } else if (!txtNgaySinhNV.getText().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            MsgBox.warring(this, "Sai định dạng ngày (Năm-Tháng-Ngày)!");
            return false;
        }
        return true;
    }

    public void clearFrom() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtSDT.setText("");
        txtDiaChiNV.setText("");
        txtNgaySinhNV.setText("");
        txtPass.setText("");
        id.setText("");
        rdNu.setSelected(false);
        rdNam.setSelected(false);
        rdDangLam.setSelected(false);
        rdNghi.setSelected(false);
        tbQuanLyNhanVien.clearSelection();
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
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtMaNV = new com.raven.suportSwing.TextField();
        rdNam = new com.raven.suportSwing.RadioButtonCustom();
        rdNu = new com.raven.suportSwing.RadioButtonCustom();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenNV = new com.raven.suportSwing.TextField();
        txtDiaChiNV = new com.raven.suportSwing.TextField();
        jLabel10 = new javax.swing.JLabel();
        btnCapNhap = new com.raven.suportSwing.MyButton();
        btnSua = new com.raven.suportSwing.MyButton();
        btnThem = new com.raven.suportSwing.MyButton();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new com.raven.suportSwing.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdNghi = new com.raven.suportSwing.RadioButtonCustom();
        rdDangLam = new com.raven.suportSwing.RadioButtonCustom();
        jLabel14 = new javax.swing.JLabel();
        txtNgaySinhNV = new com.raven.suportSwing.TextField();
        id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnReload = new com.raven.suportSwing.MyButton();
        jLabel17 = new javax.swing.JLabel();
        cbb = new com.raven.suportSwing.Combobox();
        txtPass = new com.raven.suportSwing.TextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTimKiem1 = new javax.swing.JLabel();
        txtSearch = new com.raven.suportSwing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQuanLyNhanVien = new com.raven.suportSwing.TableColumn();
        btnShort = new com.raven.suportSwing.MyButton();
        cbbSearch = new com.raven.suportSwing.Combobox();
        btnShort2 = new com.raven.suportSwing.MyButton();
        jLabel5 = new javax.swing.JLabel();
        cbbLocGioiTinh = new com.raven.suportSwing.Combobox();
        cbbLocTrangThai = new com.raven.suportSwing.Combobox();

        dateChooser2.setTextRefernce(txtNgaySinhNV);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(100, 149, 237), null));

        lblTimKiem.setForeground(new java.awt.Color(225, 0, 0));

        txtMaNV.setLabelText("");
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNu);
        rdNu.setSelected(true);
        rdNu.setText("Nữ");
        rdNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Họ và tên:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Địa chỉ:");

        txtTenNV.setLabelText("");
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });

        txtDiaChiNV.setLabelText("");
        txtDiaChiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiNVActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mã:");

        btnCapNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCapNhap.setText("Clear");
        btnCapNhap.setBorderColor(new java.awt.Color(100, 149, 237));
        btnCapNhap.setColorClick(new java.awt.Color(100, 149, 237));
        btnCapNhap.setColorOver(new java.awt.Color(238, 244, 244));
        btnCapNhap.setRadius(10);
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });

        btnSua.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSua.setText("Sửa");
        btnSua.setBorderColor(new java.awt.Color(100, 149, 237));
        btnSua.setColorClick(new java.awt.Color(100, 149, 237));
        btnSua.setColorOver(new java.awt.Color(238, 244, 244));
        btnSua.setRadius(10);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(100, 149, 237));
        btnThem.setColorClick(new java.awt.Color(100, 149, 237));
        btnThem.setColorOver(new java.awt.Color(238, 244, 244));
        btnThem.setRadius(10);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("SĐT:");
        jLabel11.setToolTipText("");

        txtSDT.setLabelText("");
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Ngày sinh:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Giới tính:");

        buttonGroup2.add(rdNghi);
        rdNghi.setSelected(true);
        rdNghi.setText("Nghỉ việc");
        rdNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNghiActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdDangLam);
        rdDangLam.setText("Đang làm");
        rdDangLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDangLamActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Trạng thái:");

        txtNgaySinhNV.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgaySinhNV.setLabelText("");
        txtNgaySinhNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhNVActionPerformed(evt);
            }
        });

        id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("ID:");

        btnReload.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnReload.setText("ReLoad");
        btnReload.setBorderColor(new java.awt.Color(100, 149, 237));
        btnReload.setColorClick(new java.awt.Color(100, 149, 237));
        btnReload.setColorOver(new java.awt.Color(238, 244, 244));
        btnReload.setRadius(10);
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setText("Chức vụ:");

        cbb.setLabeText("");

        txtPass.setLabelText("");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Mật khẩu");
        jLabel18.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(256, 256, 256))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChiNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnReload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(id)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChiNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(rdNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lblTimKiem))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Thiết lập thông tin nhân viên");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(888, 421));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

        lblTimKiem1.setForeground(new java.awt.Color(225, 0, 0));

        txtSearch.setLabelText("");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tbQuanLyNhanVien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbQuanLyNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID ", "Tên KH", "SĐT", "Giới tính", "Địa Chỉ", "Ngày Sinh", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQuanLyNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQuanLyNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbQuanLyNhanVien);

        btnShort.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShort.setText("Mã A-Z");
        btnShort.setBorderColor(new java.awt.Color(100, 149, 237));
        btnShort.setColorClick(new java.awt.Color(100, 149, 237));
        btnShort.setColorOver(new java.awt.Color(238, 244, 244));
        btnShort.setRadius(10);
        btnShort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShortActionPerformed(evt);
            }
        });

        cbbSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên ", "Mã NV", "SĐT" }));
        cbbSearch.setLabeText("");
        cbbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSearchActionPerformed(evt);
            }
        });

        btnShort2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShort2.setText("Tên A-Z");
        btnShort2.setBorderColor(new java.awt.Color(100, 149, 237));
        btnShort2.setColorClick(new java.awt.Color(100, 149, 237));
        btnShort2.setColorOver(new java.awt.Color(238, 244, 244));
        btnShort2.setRadius(10);
        btnShort2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShort2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Lọc:");

        cbbLocGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        cbbLocGioiTinh.setLabeText("");
        cbbLocGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocGioiTinhActionPerformed(evt);
            }
        });

        cbbLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang làm việc", "Nghỉ việc", "Quản Lý", "Nhân Viên" }));
        cbbLocTrangThai.setLabeText("");
        cbbLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(lblTimKiem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(256, 256, 256))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShort2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnShort, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShort2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShort, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLocGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimKiem1)
                .addGap(74, 74, 74))
        );

        jTabbedPane1.addTab("Thông tin nhân viên", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void txtDiaChiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiNVActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        Boolean response = MsgBox.confirm(this, "Có Update hay ko?");

        if (response == true) {
            if (checkData()) {
                String ma = txtMaNV.getText();
                String ten = txtTenNV.getText();
                String sdt = txtSDT.getText();
                String diaChi = txtDiaChiNV.getText();
                String ID = id.getText();
                String pass = txtPass.getText();
                String ngayThang = txtNgaySinhNV.getText();

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
                java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

                String status;
                if (rdNghi.isSelected()) {
                    status = "1";
                } else {
                    status = "2";
                }

                NhanVien kh = new NhanVien();

                kh.setMa(ma);
                kh.setTen(ten);
                kh.setSdt(sdt);
                kh.setDiaChi(diaChi);
                kh.setNgaySinh(ngayThangs);
                kh.setSex(rdNu.isSelected());
                kh.setSex(rdNam.isSelected());
                kh.setStatus(Integer.valueOf(status));
                kh.setPassword(pass);
                //kh.setIdUser(Integer.valueOf(cbb1));
                kh.setIdUser(Integer.valueOf(idCV.get(cbb.getSelectedIndex())));

                impl.update(kh, ID);
                listNV = impl.getAll();
                showdataTable(listNV);
                clearFrom();
                MsgBox.alert(this, "Cập nhật Thành Công");
            }

        } else if (response == false) {
            showdataTable(listNV);
            clearFrom();
        } else {

        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (checkData()) {
            String ma = txtMaNV.getText();
            String ten = txtTenNV.getText();
            String sdt = txtSDT.getText();
            String diaChi = txtDiaChiNV.getText();
            String ID = id.getText();
            String pass = txtPass.getText();
            String ngayThang = txtNgaySinhNV.getText();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
            java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

            String status;
            if (rdNghi.isSelected()) {
                status = "1";
            } else {
                status = "2";
            }

            NhanVien kh = new NhanVien();

            kh.setMa(ma);
            kh.setTen(ten);
            kh.setSdt(sdt);
            kh.setDiaChi(diaChi);
            kh.setNgaySinh(ngayThangs);
            kh.setSex(rdNu.isSelected());
            kh.setSex(rdNam.isSelected());
            kh.setStatus(Integer.valueOf(status));
            kh.setPassword(pass);
            kh.setIdUser(Integer.valueOf(idCV.get(cbb.getSelectedIndex())));
            impl.dangKi(kh);
            listNV = impl.getAll();
            showdataTable(listNV);
            MsgBox.alert(this, "Thêm Thành Công");
            clearFrom();
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void rdNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNghiActionPerformed

    private void rdDangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDangLamActionPerformed

    private void txtNgaySinhNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhNVActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        showdataTable(listNV);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

        // List<KhachHang> listSearch = service.timTheoMa(service.getAllKh(), txtTimkiem.getText());
        // showdataTable(listSearch);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        //  List<KhachHang> listSearch = service.timTheoMa(service.getAllKh(), txtTimkiem.getText());
        // showdataTable(listSearch);
        name = txtSearch.getText();
        if (cbbSearch.getSelectedIndex() == 0) {
            listNV = impl.searchNhanVien(name);
            showdataTable(listNV);
        }
        if (cbbSearch.getSelectedIndex() == 2) {
            listNV = impl.searchsdt(name);
            showdataTable(listNV);
        }
        if (cbbSearch.getSelectedIndex() == 1) {
            listNV = impl.searchMaNV(name);
            showdataTable(listNV);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tbQuanLyNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQuanLyNhanVienMouseClicked
        int row = tbQuanLyNhanVien.getSelectedRow();
        NhanVienViewModel nv = listNV.get(index);
        //  jLabel1.setText(tbQuanLyKhachHang.getValueAt(row, 0).toString());

        id.setText(String.valueOf(impl.getAll().get(row).getId()));
        txtMaNV.setText(tbQuanLyNhanVien.getValueAt(row, 1).toString());
        txtTenNV.setText(tbQuanLyNhanVien.getValueAt(row, 2).toString());

        if (tbQuanLyNhanVien.getValueAt(row, 3).equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

        txtNgaySinhNV.setText(tbQuanLyNhanVien.getValueAt(row, 4).toString());
        txtSDT.setText(tbQuanLyNhanVien.getValueAt(row, 5).toString());
        txtDiaChiNV.setText(tbQuanLyNhanVien.getValueAt(row, 6).toString());
        txtPass.setText(impl.getAll().get(row).getPassword());

        // int chucVu = impl.getAll().get(row).getIdUser();
        cbb.setSelectedItem(tbQuanLyNhanVien.getValueAt(row, 7).toString());

        String status;
        if (tbQuanLyNhanVien.getValueAt(row, 8).equals("Nghỉ việc")) {
            status = "1";
            rdNghi.setSelected(true);
        } else {
            status = "2";
            rdDangLam.setSelected(true);
        }


    }//GEN-LAST:event_tbQuanLyNhanVienMouseClicked

    private void btnShortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShortActionPerformed
        // TODO add your handling code here:
        listNV = impl.getAll();
        new NhanVienServiceImpl().sapXep(listNV);
        showdataTable(listNV);
    }//GEN-LAST:event_btnShortActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnShort2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShort2ActionPerformed
        // TODO add your handling code here:
        listNV = impl.getAll();
        new NhanVienServiceImpl().sapXepTen(listNV);
        showdataTable(listNV);
    }//GEN-LAST:event_btnShort2ActionPerformed

    private void cbbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSearchActionPerformed

    private void cbbLocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocGioiTinhActionPerformed
        // TODO add your handling code here:

        if (cbbLocGioiTinh.getSelectedIndex() == 0) {
            List<NhanVienViewModel> listNam = new NhanVienServiceImpl().listNam(listNV);
            showdataTable(listNam);
        }
        if (cbbLocGioiTinh.getSelectedIndex() == 1) {
            List<NhanVienViewModel> listNu = new NhanVienServiceImpl().listNu(listNV);
            showdataTable(listNu);
        }
    }//GEN-LAST:event_cbbLocGioiTinhActionPerformed

    private void cbbLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiActionPerformed
        // TODO add your handling code here:
        if (cbbLocTrangThai.getSelectedIndex() == 0) {
            List<NhanVienViewModel> listSttOn = new NhanVienServiceImpl().listStatusOn(listNV);
            showdataTable(listSttOn);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 1) {
            List<NhanVienViewModel> listSttOff = new NhanVienServiceImpl().listStatusOff(listNV);
            showdataTable(listSttOff);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 2) {
            List<NhanVienViewModel> listQL = new NhanVienServiceImpl().listQuanLy(listNV);
            showdataTable(listQL);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 3) {
            List<NhanVienViewModel> listNVs = new NhanVienServiceImpl().listNhanVien(listNV);
            showdataTable(listNVs);
        }
    }//GEN-LAST:event_cbbLocTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.suportSwing.MyButton btnCapNhap;
    private com.raven.suportSwing.MyButton btnReload;
    private com.raven.suportSwing.MyButton btnShort;
    private com.raven.suportSwing.MyButton btnShort2;
    private com.raven.suportSwing.MyButton btnSua;
    private com.raven.suportSwing.MyButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.raven.suportSwing.Combobox cbb;
    private com.raven.suportSwing.Combobox cbbLocGioiTinh;
    private com.raven.suportSwing.Combobox cbbLocTrangThai;
    private com.raven.suportSwing.Combobox cbbSearch;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTimKiem1;
    private com.raven.suportSwing.RadioButtonCustom rdDangLam;
    private com.raven.suportSwing.RadioButtonCustom rdNam;
    private com.raven.suportSwing.RadioButtonCustom rdNghi;
    private com.raven.suportSwing.RadioButtonCustom rdNu;
    private com.raven.suportSwing.TableColumn tbQuanLyNhanVien;
    private com.raven.suportSwing.TextField txtDiaChiNV;
    private com.raven.suportSwing.TextField txtMaNV;
    private com.raven.suportSwing.TextField txtNgaySinhNV;
    private com.raven.suportSwing.TextField txtPass;
    private com.raven.suportSwing.TextField txtSDT;
    private com.raven.suportSwing.TextField txtSearch;
    private com.raven.suportSwing.TextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
