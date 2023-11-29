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
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.NhanVienService;
import services.ChucVuService;
import services.impl.ChucVuServiceImpl;
import services.impl.NhanVienServiceImpl;
import ultilities.MsgBox;
import viewmodel.NhanVienViewModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.raven.datechooser.DateChooser;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.BasicConfigurator;
import repositories.NhanVienRepository;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel implements Runnable, ThreadFactory {

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
    NhanVienRepository nvRepo = new NhanVienRepository();

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
boolean check = false;
    /**
     * Creates new form Form_1
     */
    public NhanVienPanel() {
        initComponents();
        DateChooser dateChooser = new DateChooser();
        dateChooser.setTextField(txtNgaySinhNV);
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        tbQuanLyNhanVien.setModel(dtm);
        String[] hears = {"STT", "Mã NV", "Tên", "Giới Tính", "Ngày sinh", "SĐT", "Địa chỉ", "Email", "Căn cước", "Chức vụ", "Trạng thái"};
        dtm.setColumnIdentifiers(hears);
        listNV = impl.getAll();
        showdataTable(listNV);
        addCbb();
        id.setVisible(false);
        //initWebcam();
        
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanelQr.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 180));

        executor.execute(this);
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
                new SimpleDateFormat("dd-MM-yyyy").format(km.getNgaySinh()),
                km.getSdt(),
                km.getDiaChi(),
                km.getEmail(),
                km.getCccd(),
                getIdUser(km.getIdUser()),
                getStatus(km.getStatus())
            });
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
        } else if (txtEmail.getText().isBlank()) {
            MsgBox.warring(this, "Mail nhân viên không được để trống!");
            return false;
        } else if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            MsgBox.warring(this, "Sai định dạng email!");
            return false;
        } else if (txtCccd.getText().isBlank()) {
            MsgBox.warring(this, "Căn cước không được để trống!");
            return false;
        }

        return true;
    }

    public void clearFrom() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtEmail.setText("");
        txtDiaChiNV.setText("");
        txtNgaySinhNV.setText("");
        txtSDT.setText("");
        txtCccd.setText("");
        //txtPass.setText("");
        id.setText("");
        rdNu.setSelected(false);
        rdNam.setSelected(false);
        rdDangLam.setSelected(false);
        rdNghi.setSelected(false);
        tbQuanLyNhanVien.clearSelection();
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
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
        txtEmail = new com.raven.suportSwing.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdNghi = new com.raven.suportSwing.RadioButtonCustom();
        rdDangLam = new com.raven.suportSwing.RadioButtonCustom();
        jLabel14 = new javax.swing.JLabel();
        txtNgaySinhNV = new com.raven.suportSwing.TextField();
        id = new javax.swing.JLabel();
        btnReload = new com.raven.suportSwing.MyButton();
        jLabel17 = new javax.swing.JLabel();
        cbb = new com.raven.suportSwing.Combobox();
        jLabel12 = new javax.swing.JLabel();
        txtSDT = new com.raven.suportSwing.TextField();
        txtCccd = new com.raven.suportSwing.TextField();
        btnImport = new com.raven.suportSwing.MyButton();
        btnExport = new com.raven.suportSwing.MyButton();
        jLabel15 = new javax.swing.JLabel();
        jPanelQr = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTimKiem1 = new javax.swing.JLabel();
        txtSearch = new com.raven.suportSwing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQuanLyNhanVien = new com.raven.suportSwing.TableColumn();
        btnShort = new com.raven.suportSwing.MyButton();
        btnShort2 = new com.raven.suportSwing.MyButton();
        jLabel5 = new javax.swing.JLabel();
        cbbLocGioiTinh = new com.raven.suportSwing.Combobox();
        cbbLocTrangThai = new com.raven.suportSwing.Combobox();

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
        btnCapNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Clipboard.png"))); // NOI18N
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
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btnSua.setText("Update");
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
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnThem.setText("Create");
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

        txtEmail.setLabelText("");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
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

        btnReload.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Refresh.png"))); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Email:");

        txtSDT.setLabelText("");
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtCccd.setLabelText("");
        txtCccd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCccdActionPerformed(evt);
            }
        });

        btnImport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Download.png"))); // NOI18N
        btnImport.setText("Import");
        btnImport.setBorderColor(new java.awt.Color(100, 149, 237));
        btnImport.setColorClick(new java.awt.Color(100, 149, 237));
        btnImport.setColorOver(new java.awt.Color(238, 244, 244));
        btnImport.setRadius(10);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Upload.png"))); // NOI18N
        btnExport.setText("Export");
        btnExport.setBorderColor(new java.awt.Color(100, 149, 237));
        btnExport.setColorClick(new java.awt.Color(100, 149, 237));
        btnExport.setColorOver(new java.awt.Color(238, 244, 244));
        btnExport.setRadius(10);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setText("CCCD:");

        jPanelQr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel16.setText("Quét QR");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
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
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(id))
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
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtDiaChiNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCccd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanelQr, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel16)))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChiNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(rdDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(rdNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCccd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanelQr, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(id))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
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

        txtSearch.setLabelText("Theo Mã, Email, Tên, SĐT hoặc Địa chỉ");
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
        btnShort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Down.png"))); // NOI18N
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

        btnShort2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnShort2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Down.png"))); // NOI18N
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

        cbbLocGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Nam", "Nữ" }));
        cbbLocGioiTinh.setLabeText("");
        cbbLocGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocGioiTinhActionPerformed(evt);
            }
        });

        cbbLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Đang làm việc", "Nghỉ việc", "Quản Lý", "Nhân Viên" }));
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
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShort2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShort, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

        // List<KhachHang> listSearch = service.timTheoMa(service.getAllKh(), txtTimkiem.getText());
        // showdataTable(listSearch);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        //  List<KhachHang> listSearch = service.timTheoMa(service.getAllKh(), txtTimkiem.getText());
        // showdataTable(listSearch);
        /* name = txtSearch.getText();
        
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
        }*/
        String keyword = txtSearch.getText();
        listNV = impl.searchAll(keyword, keyword, keyword, keyword, keyword);
        showdataTable(listNV);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tbQuanLyNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQuanLyNhanVienMouseClicked
        int row = tbQuanLyNhanVien.getSelectedRow();
        NhanVienViewModel nv = listNV.get(index);

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
        txtEmail.setText(tbQuanLyNhanVien.getValueAt(row, 7).toString());
        //  txtPass.setText(impl.getAll().get(row).getPassword());
        // int chucVu = impl.getAll().get(row).getIdUser();
        txtCccd.setText(tbQuanLyNhanVien.getValueAt(row, 8).toString());
        cbb.setSelectedItem(tbQuanLyNhanVien.getValueAt(row, 9).toString());

        String status;
        if (tbQuanLyNhanVien.getValueAt(row, 10).equals("Nghỉ việc")) {
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

    private void btnShort2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShort2ActionPerformed
        // TODO add your handling code here:
        listNV = impl.getAll();
        new NhanVienServiceImpl().sapXepTen(listNV);
        showdataTable(listNV);
    }//GEN-LAST:event_btnShort2ActionPerformed

    private void cbbLocGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocGioiTinhActionPerformed
        // TODO add your handling code here:
        if (cbbLocGioiTinh.getSelectedIndex() == 0) {
            showdataTable(listNV);
        }
        if (cbbLocGioiTinh.getSelectedIndex() == 1) {
            List<NhanVienViewModel> listNam = new NhanVienServiceImpl().listNam(listNV);
            showdataTable(listNam);
        }
        if (cbbLocGioiTinh.getSelectedIndex() == 2) {
            List<NhanVienViewModel> listNu = new NhanVienServiceImpl().listNu(listNV);
            showdataTable(listNu);
        }
    }//GEN-LAST:event_cbbLocGioiTinhActionPerformed

    private void cbbLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiActionPerformed
        // TODO add your handling code here:
        if (cbbLocTrangThai.getSelectedIndex() == 0) {
            showdataTable(listNV);
        }
        if (cbbLocTrangThai.getSelectedIndex() == 1) {
            List<NhanVienViewModel> listSttOn = new NhanVienServiceImpl().listStatusOn(listNV);
            showdataTable(listSttOn);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 2) {
            List<NhanVienViewModel> listSttOff = new NhanVienServiceImpl().listStatusOff(listNV);
            showdataTable(listSttOff);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 3) {
            List<NhanVienViewModel> listQL = new NhanVienServiceImpl().listQuanLy(listNV);
            showdataTable(listQL);
        }

        if (cbbLocTrangThai.getSelectedIndex() == 4) {
            List<NhanVienViewModel> listNVs = new NhanVienServiceImpl().listNhanVien(listNV);
            showdataTable(listNVs);
        }
    }//GEN-LAST:event_cbbLocTrangThaiActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        dtm = (DefaultTableModel) tbQuanLyNhanVien.getModel();
        dtm.setRowCount(0);

        int stt = 1;
        List<NhanVienViewModel> listNV = impl.exportExcel();
        for (NhanVienViewModel nv : listNV) {
            Object[] rows = {stt++, nv.getMa(), nv.getTen(), nv.isSex(), nv.getNgaySinh(),
                nv.getSdt(), nv.getDiaChi(), nv.getEmail(), nv.getCccd(), nv.getIdUser(), nv.getStatus()};
            dtm.addRow(rows);

        }

        String desktop = "C:\\Users\\Admin\\Desktop\\ExcelPM";
        JFileChooser fileChooser = new JFileChooser(desktop);
        fileChooser.setDialogTitle("Save Excel File");

        // Thiết lập bộ lọc định dạng file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        // Thiết lập mặc định đuôi file là .xlsx
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setSelectedFile(new File("exportNV.xlsx"));
        fileChooser.setApproveButtonText("Save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Tạo workbook mới
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Lấy dữ liệu từ tableModel
            int rowCount = tbQuanLyNhanVien.getRowCount();
            int columnCount = tbQuanLyNhanVien.getColumnCount();

            // Ghi dữ liệu vào sheet
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < columnCount; j++) {
                    Object value = tbQuanLyNhanVien.getValueAt(i, j);
                    Cell cell = row.createCell(j);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            try {
                // Lưu workbook vào tệp Excel
                FileOutputStream fileOut = new FileOutputStream(filePath);
                workbook.write(fileOut);
                fileOut.close();
                MsgBox.alert(this, "Xuất dữ liệu thành công!");
                // Mở tệp Excel sau khi lưu thành công
                Desktop.getDesktop().open(new File(filePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.btnReloadActionPerformed(evt);
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        // TODO add your handling code here:
        ImportExcelNhanVien excelNV = new ImportExcelNhanVien();
        excelNV.setVisible(true);
    }//GEN-LAST:event_btnImportActionPerformed

    private void txtCccdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCccdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCccdActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        // TODO add your handling code here:
        listNV = impl.getAll();
        showdataTable(listNV);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void txtNgaySinhNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhNVActionPerformed

    private void rdDangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDangLamActionPerformed

    private void rdNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNghiActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhanVien kh = new NhanVien();
        /*  if (impl.searchMaNV(kh.getMa()) != null) {
            MsgBox.warring(this, "Trùng mã"); xxx
        } else {*/
        if (checkData()) {
            String ma = txtMaNV.getText();
            String ten = txtTenNV.getText();
            String sdt = txtSDT.getText();
            String diaChi = txtDiaChiNV.getText();
            String email = txtEmail.getText();
            String ID = id.getText();
            String ngayThang = txtNgaySinhNV.getText();
            String cccd = txtCccd.getText();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
            java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

            String status;
            if (rdNghi.isSelected()) {
                status = "1";
            } else {
                status = "2";
            }

            kh.setMa(ma);
            kh.setTen(ten);
            kh.setSdt(sdt);
            kh.setDiaChi(diaChi);
            kh.setNgaySinh(ngayThangs);
            kh.setSex(rdNu.isSelected());
            kh.setSex(rdNam.isSelected());
            kh.setEmail(email);
            kh.setCccd(cccd);
            kh.setStatus(Integer.valueOf(status));
            kh.setPassword(generateRandomPassword());
            kh.setIdUser(Integer.valueOf(idCV.get(cbb.getSelectedIndex())));
            impl.dangKi(kh);
            listNV = impl.getAll();
            showdataTable(listNV);

            //send Email
            final String username = "tuanminhnguyen1411@gmail.com";
            final String password = "zjml wfwh rdqa untc";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Tạo đối tượng MimeMessage
                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress("tuanminhnguyen1411@gmail.com"));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText()));
                mimeMessage.setSubject("Tạo tài khoản thành công tại cửa hàng BeeShirt");
                mimeMessage.setText("Xin chào " + txtTenNV.getText() + " dưới đây là tài khoản và mật khẩu của bạn:"
                        + "\n"
                        + "\n"
                        + "Tài Khoản: "
                        + txtMaNV.getText()
                        + "\n" + "Mật khẩu: " + kh.getPassword()
                        + "\n" + "\n" + "\n" + "Sau khi nhận mật khẩu vui lòng đăng nhập và đổi mật khẩu theo ý của mình!!!!!"
                );

                // Gửi email
                Transport.send(mimeMessage);

            } catch (MessagingException ex) {
                JOptionPane.showMessageDialog(this, "Failed to send email: " + ex.getMessage());
            }

            MsgBox.alert(this, "Thêm và gửi Email Thành Công");
            clearFrom();

        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        NhanVien kh = new NhanVien();
        Boolean response = MsgBox.confirm(this, "Có Update hay ko?");

        if (response == true) {
            if (checkData()) {
                String ma = txtMaNV.getText();
                String ten = txtTenNV.getText();
                String sdt = txtSDT.getText();
                String diaChi = txtDiaChiNV.getText();
                String email = txtEmail.getText();
                String ID = id.getText();
                String cccd = txtCccd.getText();
                // String pass = txtPass.getText();
                String ngayThang = txtNgaySinhNV.getText();

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDate1 = LocalDate.parse(ngayThang, formatter1);
                java.sql.Date ngayThangs = java.sql.Date.valueOf(localDate1);

                String status;
                if (rdNghi.isSelected()) {
                    status = "1";
                } else {
                    status = "2";
                }

                kh.setMa(ma);
                kh.setTen(ten);
                kh.setSdt(sdt);
                kh.setDiaChi(diaChi);
                kh.setNgaySinh(ngayThangs);
                kh.setSex(rdNu.isSelected());
                kh.setSex(rdNam.isSelected());
                kh.setStatus(Integer.valueOf(status));
                kh.setEmail(email);
                kh.setPassword(generateRandomPassword());
                kh.setCccd(cccd);
                //kh.setPassword(pass);
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

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void txtDiaChiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiNVActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        
        if(!check){
            initWebcam();
            initComponents();
            check = true;
        }else{
            webcam.close();
            check = false;
        }
    }//GEN-LAST:event_jLabel16MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.suportSwing.MyButton btnCapNhap;
    private com.raven.suportSwing.MyButton btnExport;
    private com.raven.suportSwing.MyButton btnImport;
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
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelQr;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTimKiem1;
    private com.raven.suportSwing.RadioButtonCustom rdDangLam;
    private com.raven.suportSwing.RadioButtonCustom rdNam;
    private com.raven.suportSwing.RadioButtonCustom rdNghi;
    private com.raven.suportSwing.RadioButtonCustom rdNu;
    private com.raven.suportSwing.TableColumn tbQuanLyNhanVien;
    private com.raven.suportSwing.TextField txtCccd;
    private com.raven.suportSwing.TextField txtDiaChiNV;
    private com.raven.suportSwing.TextField txtEmail;
    private com.raven.suportSwing.TextField txtMaNV;
    private com.raven.suportSwing.TextField txtNgaySinhNV;
    private com.raven.suportSwing.TextField txtSDT;
    private com.raven.suportSwing.TextField txtSearch;
    private com.raven.suportSwing.TextField txtTenNV;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        String randomCode = generateRandomCode(12);
       // BasicConfigurator.configure();
        do {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);

            } catch (NotFoundException e) {
                //không trả về

            }
            if (result != null) {
                txtCccd.setText(randomCode);
                txtTenNV.setText(this.generateRandomName());
                txtSDT.setText(this.generateRandomPhoneNumber());
                txtDiaChiNV.setText(this.generateRandomProvinceName());

                if (txtTenNV.getText().contains("Thị") || txtTenNV.getText().contains("Như") || txtTenNV.getText().contains("Thủy") || txtTenNV.getText().contains("Mỹ")
                        || txtTenNV.getText().contains("Hoa") || txtTenNV.getText().contains("Hương") || txtTenNV.getText().contains("Linh") || txtTenNV.getText().contains("Nga")
                        || txtTenNV.getText().contains("Thủy") || txtTenNV.getText().contains("Trang") || txtTenNV.getText().contains("Vân")) {
                    rdNu.setSelected(true);
                } else {
                    rdNam.setSelected(true);
                }
                String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
                String randomEmail = generateRandomEmail(domains);
                txtEmail.setText(randomEmail);
            }

        } while (true || webcam.isOpen());
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public static String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10); // Sinh số ngẫu nhiên từ 0 đến 9
            codeBuilder.append(randomNumber);
        }

        return codeBuilder.toString();
    }

    public static String generateRandomEmail(String[] domains) {
        String[] nameParts = {"john", "jane", "alex", "emma", "mike", "sara"};
        String[] domainParts = domains;
        Random random = new Random();

        // Chọn một phần tên ngẫu nhiên
        String randomName = nameParts[random.nextInt(nameParts.length)];

        // Chọn một phần tên miền ngẫu nhiên
        String randomDomain = domainParts[random.nextInt(domainParts.length)];

        // Kết hợp phần tên và phần tên miền để tạo địa chỉ email ngẫu nhiên
        String randomEmail = randomName + "@" + randomDomain;

        return randomEmail;
    }
    private static final String[] lastNames = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Đặng"};
    private static final String[] middleNames = {"Văn", "Thị", "Đức", "Như", "Tiến", "Kim", "Thành", "Thủy", "Mỹ", "Quốc"};
    private static final String[] firstNames = {"An", "Bình", "Cường", "Đức", "Hải", "Hoa", "Hương", "Linh", "Minh", "Nga", "Quang", "Thủy", "Trang", "Trung", "Tuấn", "Vân", "Việt", "Xuân"};

    public static String generateRandomName() {
        Random random = new Random();
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String middleName = middleNames[random.nextInt(middleNames.length)];
        String firstName = firstNames[random.nextInt(firstNames.length)];
        return lastName + " " + middleName + " " + firstName;
    }
    private static final String[] prefixes = {"03", "05", "07", "08", "09"};

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String number = generateRandomNumber(8);
        return prefix + number;
    }

    public static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
    
     private static final String[] provinceNames = {
        "Hà Nội", "Hồ Chí Minh", "Hải Phòng", "Đà Nẵng", "Cần Thơ",
        "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
        "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước",
        "Bình Thuận", "Cà Mau", "Cao Bằng", "Đắk Lắk", "Đắk Nông",
        "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang",
        "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình",
        "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu",
        "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định",
        "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Quảng Bình",
        "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng",
        "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa",
        "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long",
        "Vĩnh Phúc", "Yên Bái"
    };

    public static String generateRandomProvinceName() {
        Random random = new Random();
        return provinceNames[random.nextInt(provinceNames.length)];
    }
}
