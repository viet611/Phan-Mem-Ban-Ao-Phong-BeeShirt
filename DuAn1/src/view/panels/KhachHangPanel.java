/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import domainmodels.KhachHang;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.KhachHangService;
import services.impl.KhachHangServiceImpl;
import ultilities.MsgBox;
import viewmodel.KhachHangViewModel;
import com.raven.datechooser.DateChooser;

/**
 *
 * @author Admin
 */
public class KhachHangPanel extends javax.swing.JPanel {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private List<KhachHang> list = new ArrayList<>();
    private KhachHangService service = new KhachHangServiceImpl();
    private DefaultTableModel dtm1 = new DefaultTableModel();

    int rowOffset = 0;
    int fetch = 5;
    String name = "";
    int index = 0;
    int o = 0;

    /**
     * Creates new form Form_1
     */
    public KhachHangPanel() {
        initComponents();
        tbQuanLyKhachHang.setModel(dtm);
        String[] hears = {"STT", "Mã KH", "Tên", "Giới Tính", "Ngày sinh", "SĐT", "Địa chỉ", "Trạng thái"};
        dtm.setColumnIdentifiers(hears);
        tbQuanLyKhachMuaHang.setModel(dtm1);
        String[] hears1 = {"STT", "Mã KH", "Tên", "Giới Tính", "SĐT", "Số tiền đã mua", "Hạng"};
        dtm1.setColumnIdentifiers(hears1);
        // loadData();
        // loadSoTrang();
        list = service.getAllKh();
        showdataTable(list);

    }

    public String getStatus(int status) {
        if (status == 1) {
            return "Member";
        } else if (status == 2) {
            return "New Customers";
        } else {
            return null;
        }
    }

    public void showdataTable(List<KhachHang> list) {
        dtm.setRowCount(0);
        int stt = 1;
        for (KhachHang km : list) {
            dtm.addRow(new Object[]{
                stt++,
                km.getMa(),
                km.getTen(),
                km.isSex() ? "Nam" : "Nữ",
                km.getNgaySinh(),
                km.getSdt(),
                km.getDiaChi(),
                getStatus(km.getStatus())
            });
            // dtm.addRow(km.toDataRow());
        }
    }

    public void loadData() {
        List<KhachHang> kh = service.getAllKh();
        DefaultTableModel tb = (DefaultTableModel) tbQuanLyKhachHang.getModel();
        tb.setRowCount(0);
        int stt = 1;
        for (KhachHang x : kh) {
            tb.addRow(new Object[]{
                stt++,
                x.getMa(),
                x.getTen(),
                x.isSex() ? "Nam" : "Nữ",
                x.getNgaySinh(),
                x.getSdt(),
                x.getDiaChi(),
                getStatus(x.getStatus()), // x.getStatus(), 
            /* x.getNgayTao(),
                x.getNgaySua(),
                x.getNguoiTao(),
                x.getNguoiSua(),
                x.isDaXoa() ? "Yes" : "No" */});
        }
    }

    /* private void loadSoTrang() {
        jLabel1.setText(index + 1 + "");
    }*/
    public void clearFrom() {
        txtMa.setText("");
        txtTen.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        txtNgayThang.setText("");
        id.setText("");
        rdNu.setSelected(false);
        rdNam.setSelected(false);
        rdMem.setSelected(false);
        rdNew.setSelected(false);
        tbQuanLyKhachHang.clearSelection();
    }

    public boolean checkData() {
        if (txtMa.getText().isBlank()) {
            MsgBox.warring(this, "Mã ko được để trống!");
            return false;
        } else if (!txtMa.getText().matches("^[a-zA-Z0-9]*$")) {
            MsgBox.warring(this, "Mã ko được có ký tự đặc biệt!");
            return false;
        } else if (txtAddress.getText().isBlank()) {
            MsgBox.warring(this, "Địa chỉ ko được để trống!");
            return false;
        } else if (txtNgayThang.getText().isBlank()) {
            MsgBox.warring(this, "Ngày sinh ko được để trống!");
            return false;
        } else if (txtTen.getText().isBlank()) {
            MsgBox.warring(this, "Tên ko được để trống!");
            return false;
        } else if (txtPhoneNumber.getText().isBlank()) {
            MsgBox.warring(this, "SĐT ko được để trống!");
            return false;
        } else if (!txtNgayThang.getText().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            MsgBox.warring(this, "Sai định dạng ngày (Năm-Tháng-Ngày)!");
            return false;
        }
        return true;
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
        txtMa = new com.raven.suportSwing.TextField();
        rdNam = new com.raven.suportSwing.RadioButtonCustom();
        rdNu = new com.raven.suportSwing.RadioButtonCustom();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTen = new com.raven.suportSwing.TextField();
        txtAddress = new com.raven.suportSwing.TextField();
        jLabel10 = new javax.swing.JLabel();
        btnCapNhap = new com.raven.suportSwing.MyButton();
        btnSua = new com.raven.suportSwing.MyButton();
        btnThem = new com.raven.suportSwing.MyButton();
        jLabel11 = new javax.swing.JLabel();
        txtPhoneNumber = new com.raven.suportSwing.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdNew = new com.raven.suportSwing.RadioButtonCustom();
        rdMem = new com.raven.suportSwing.RadioButtonCustom();
        jLabel14 = new javax.swing.JLabel();
        txtNgayThang = new com.raven.suportSwing.TextField();
        id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnReload = new com.raven.suportSwing.MyButton();
        btnXoa = new com.raven.suportSwing.MyButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTimKiem1 = new javax.swing.JLabel();
        txtTimkiem = new com.raven.suportSwing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQuanLyKhachHang = new com.raven.suportSwing.TableColumn();
        btnShortMa = new com.raven.suportSwing.MyButton();
        cbbSearch = new com.raven.suportSwing.Combobox();
        btnShortTen = new com.raven.suportSwing.MyButton();
        cbbLoc = new com.raven.suportSwing.Combobox();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTimKiemMuaHang = new com.raven.suportSwing.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbQuanLyKhachMuaHang = new com.raven.suportSwing.TableColumn();
        btnShort1 = new com.raven.suportSwing.MyButton();
        cbbSearch1 = new com.raven.suportSwing.Combobox();

        dateChooser2.setTextRefernce(txtNgayThang);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(100, 149, 237), null));
        jPanel1.setPreferredSize(new java.awt.Dimension(902, 262));

        lblTimKiem.setForeground(new java.awt.Color(225, 0, 0));

        txtMa.setLabelText("");
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
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

        txtTen.setLabelText("");
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtAddress.setLabelText("");
        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
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

        txtPhoneNumber.setLabelText("");
        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Ngày sinh:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Giới tính:");

        buttonGroup2.add(rdNew);
        rdNew.setSelected(true);
        rdNew.setText("New");
        rdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNewActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdMem);
        rdMem.setText("Member");
        rdMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMemActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Trạng thái:");

        txtNgayThang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgayThang.setLabelText("");
        txtNgayThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayThangActionPerformed(evt);
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

        btnXoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnXoa.setText("Xoá");
        btnXoa.setBorderColor(new java.awt.Color(100, 149, 237));
        btnXoa.setColorClick(new java.awt.Color(100, 149, 237));
        btnXoa.setColorOver(new java.awt.Color(238, 244, 244));
        btnXoa.setRadius(10);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btnCapNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btnReload, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(id)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(rdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addGap(9, 9, 9)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)))
                .addComponent(lblTimKiem))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Thiết lập thông tin khách hàng");
        jLabel4.setPreferredSize(new java.awt.Dimension(292, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Chi tiết khách hàng");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

        lblTimKiem1.setForeground(new java.awt.Color(225, 0, 0));

        txtTimkiem.setLabelText("");
        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        tbQuanLyKhachHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbQuanLyKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbQuanLyKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQuanLyKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbQuanLyKhachHang);

        btnShortMa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShortMa.setText("Mã A-Z");
        btnShortMa.setBorderColor(new java.awt.Color(100, 149, 237));
        btnShortMa.setColorClick(new java.awt.Color(100, 149, 237));
        btnShortMa.setColorOver(new java.awt.Color(238, 244, 244));
        btnShortMa.setRadius(10);
        btnShortMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShortMaActionPerformed(evt);
            }
        });

        cbbSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên ", "SĐT", "Địa chỉ", "Mã" }));
        cbbSearch.setLabeText("");
        cbbSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbSearchKeyReleased(evt);
            }
        });

        btnShortTen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShortTen.setText("Tên A-Z");
        btnShortTen.setBorderColor(new java.awt.Color(100, 149, 237));
        btnShortTen.setColorClick(new java.awt.Color(100, 149, 237));
        btnShortTen.setColorOver(new java.awt.Color(238, 244, 244));
        btnShortTen.setRadius(10);
        btnShortTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShortTenActionPerformed(evt);
            }
        });

        cbbLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ", "New Customer", "Member" }));
        cbbLoc.setLabeText("");
        cbbLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Lọc:");

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
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnShortTen, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnShortMa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnShortMa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShortTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimKiem1)
                .addGap(68, 68, 68))
        );

        jTabbedPane1.addTab("Thông tin khách hàng", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tìm kiếm:");

        txtTimKiemMuaHang.setLabelText("");
        txtTimKiemMuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMuaHangActionPerformed(evt);
            }
        });
        txtTimKiemMuaHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMuaHangKeyReleased(evt);
            }
        });

        tbQuanLyKhachMuaHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbQuanLyKhachMuaHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID ", "Tên KH", "SĐT", "Giới tính", "Địa Chỉ", "Ngày Sinh", "Hạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQuanLyKhachMuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQuanLyKhachMuaHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbQuanLyKhachMuaHang);

        btnShort1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShort1.setText("Sort");
        btnShort1.setBorderColor(new java.awt.Color(100, 149, 237));
        btnShort1.setColorClick(new java.awt.Color(100, 149, 237));
        btnShort1.setColorOver(new java.awt.Color(238, 244, 244));
        btnShort1.setRadius(10);
        btnShort1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShort1ActionPerformed(evt);
            }
        });

        cbbSearch1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên ", "SĐT", "Địa chỉ", "Mã" }));
        cbbSearch1.setLabeText("");
        cbbSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbSearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnShort1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemMuaHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnShort1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jTabbedPane1.addTab("Lịch sử mua hàng", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed

    }//GEN-LAST:event_txtTimkiemActionPerformed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

        if (cbbSearch.getSelectedIndex() == 0) {
            List<KhachHang> listSearch = service.timTheoTen(service.getAllKh(), txtTimkiem.getText());
            showdataTable(listSearch);
        }
        if (cbbSearch.getSelectedIndex() == 1) {
            List<KhachHang> listSearch = service.timTheoSdt(service.getAllKh(), txtTimkiem.getText());
            showdataTable(listSearch);
        }
        if (cbbSearch.getSelectedIndex() == 2) {
            List<KhachHang> listSearch = service.timTheoDiaChi(service.getAllKh(), txtTimkiem.getText());
            showdataTable(listSearch);
        }
        if (cbbSearch.getSelectedIndex() == 3) {
            List<KhachHang> listSearch = service.timTheoMa(service.getAllKh(), txtTimkiem.getText());
            showdataTable(listSearch);
        }

    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void tbQuanLyKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQuanLyKhachHangMouseClicked
        int row = tbQuanLyKhachHang.getSelectedRow();
        //  jLabel1.setText(tbQuanLyKhachHang.getValueAt(row, 0).toString());
        id.setText(String.valueOf(service.getAllKh().get(row).getId()));
        txtMa.setText(tbQuanLyKhachHang.getValueAt(row, 1).toString());
        txtTen.setText(tbQuanLyKhachHang.getValueAt(row, 2).toString());

        if (tbQuanLyKhachHang.getValueAt(row, 3).equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

        txtNgayThang.setText(tbQuanLyKhachHang.getValueAt(row, 4).toString());
        txtPhoneNumber.setText(tbQuanLyKhachHang.getValueAt(row, 5).toString());
        txtAddress.setText(tbQuanLyKhachHang.getValueAt(row, 6).toString());

        String status;
        if (tbQuanLyKhachHang.getValueAt(row, 7).equals("Member")) {
            status = "1";
            rdMem.setSelected(true);
        } else {
            status = "2";
            rdNew.setSelected(true);
        }


    }//GEN-LAST:event_tbQuanLyKhachHangMouseClicked

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        Boolean response = MsgBox.confirm(this, "Có Update hay ko?");

        if (response == true) {
            if (checkData()) {
                String ma = txtMa.getText();
                String ten = txtTen.getText();
                String sdt = txtPhoneNumber.getText();
                String diaChi = txtAddress.getText();
                String ID = id.getText();

                String ngayThang = txtNgayThang.getText();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
                java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

                String status;
                if (rdNew.isSelected()) {
                    status = "2";
                } else {
                    status = "1";
                }

                KhachHang kh = new KhachHang();

                kh.setMa(ma);
                kh.setTen(ten);
                kh.setSdt(sdt);
                kh.setDiaChi(diaChi);
                kh.setNgaySinh(ngayThangs);
                kh.setSex(rdNu.isSelected());
                kh.setSex(rdNam.isSelected());
                kh.setStatus(Integer.parseInt(status));

                service.editKhachHang(kh, ID);
                loadData();
                clearFrom();
                MsgBox.alert(this, "Cập nhật Thành Công");
            }

        } else if (response == false) {
            loadData();
            clearFrom();
        } else {

        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (checkData()) {
            String ma = txtMa.getText();
            String ten = txtTen.getText();
            String sdt = txtPhoneNumber.getText();
            String diaChi = txtAddress.getText();

            String ngayThang = txtNgayThang.getText();
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
            java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

            String status;
            if (rdNew.isSelected()) {
                status = "2";
            } else {
                status = "1";
            }

            KhachHang kh = new KhachHang();
            kh.setMa(ma);
            kh.setTen(ten);
            kh.setSdt(sdt);
            kh.setDiaChi(diaChi);
            kh.setNgaySinh(ngayThangs);
            kh.setSex(rdNu.isSelected());
            kh.setSex(rdNam.isSelected());
            kh.setStatus(Integer.parseInt(status));

            String kq = service.addKhachHang(kh);
            MsgBox.alert(this, "Thêm Thành Công");
            clearFrom();
            list = service.getAllKh();
            showdataTable(list);
            //service.insert(kh);
            // loadData();
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void rdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNewActionPerformed

    private void rdMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdMemActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnShortMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShortMaActionPerformed
        // TODO add your handling code here:
        list = service.getAllKh();
        new KhachHangServiceImpl().sapXep(list);
        showdataTable(list);
    }//GEN-LAST:event_btnShortMaActionPerformed

    private void txtNgayThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayThangActionPerformed

    private void txtTimKiemMuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMuaHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMuaHangActionPerformed

    private void txtTimKiemMuaHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMuaHangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMuaHangKeyReleased

    private void tbQuanLyKhachMuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQuanLyKhachMuaHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbQuanLyKhachMuaHangMouseClicked

    private void btnShort1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShort1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShort1ActionPerformed

    private void cbbSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbSearchKeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_cbbSearchKeyReleased

    private void btnShortTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShortTenActionPerformed
        // TODO add your handling code here:
        list = service.getAllKh();
        new KhachHangServiceImpl().sapXepTen(list);
        showdataTable(list);
    }//GEN-LAST:event_btnShortTenActionPerformed

    private void cbbSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbSearch1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSearch1KeyReleased

    private void cbbLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocActionPerformed
        // TODO add your handling code here:
        if (cbbLoc.getSelectedIndex() == 0) {
            List<KhachHang> listNam = new KhachHangServiceImpl().listNam(list);
            showdataTable(listNam);
        } else if (cbbLoc.getSelectedIndex() == 1) {
            List<KhachHang> listNu = new KhachHangServiceImpl().listNu(list);
            showdataTable(listNu);
        } else if (cbbLoc.getSelectedIndex() == 2) {
            List<KhachHang> listNew = new KhachHangServiceImpl().listNew(list);
            showdataTable(listNew);
        } else if (cbbLoc.getSelectedIndex() == 3) {
            List<KhachHang> listMember = new KhachHangServiceImpl().listMember(list);
            showdataTable(listMember);
        }
    }//GEN-LAST:event_cbbLocActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Boolean response = MsgBox.confirm(this, "Có Xoá hay ko?");
        if (response == true) {
            int i = tbQuanLyKhachHang.getSelectedRow();
            service.deleteKH(list.get(i).getId());
            list.removeAll(list);
            service.getAfterDelete(list);
            showdataTable(list);
        } else if (response == false) {

        }

    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.suportSwing.MyButton btnCapNhap;
    private com.raven.suportSwing.MyButton btnReload;
    private com.raven.suportSwing.MyButton btnShort1;
    private com.raven.suportSwing.MyButton btnShortMa;
    private com.raven.suportSwing.MyButton btnShortTen;
    private com.raven.suportSwing.MyButton btnSua;
    private com.raven.suportSwing.MyButton btnThem;
    private com.raven.suportSwing.MyButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.raven.suportSwing.Combobox cbbLoc;
    private com.raven.suportSwing.Combobox cbbSearch;
    private com.raven.suportSwing.Combobox cbbSearch1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTimKiem1;
    private com.raven.suportSwing.RadioButtonCustom rdMem;
    private com.raven.suportSwing.RadioButtonCustom rdNam;
    private com.raven.suportSwing.RadioButtonCustom rdNew;
    private com.raven.suportSwing.RadioButtonCustom rdNu;
    private com.raven.suportSwing.TableColumn tbQuanLyKhachHang;
    private com.raven.suportSwing.TableColumn tbQuanLyKhachMuaHang;
    private com.raven.suportSwing.TextField txtAddress;
    private com.raven.suportSwing.TextField txtMa;
    private com.raven.suportSwing.TextField txtNgayThang;
    private com.raven.suportSwing.TextField txtPhoneNumber;
    private com.raven.suportSwing.TextField txtTen;
    private com.raven.suportSwing.TextField txtTimKiemMuaHang;
    private com.raven.suportSwing.TextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
