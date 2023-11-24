package view.panels;

import com.raven.datechooser.DateChooser;
import services.impl.KhuyenMaiServiceIMPL;
import domainmodels.KhuyenMai;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VoucherPanel extends javax.swing.JPanel {
    private KhuyenMaiServiceIMPL service = new KhuyenMaiServiceIMPL();
    private int index=-1;
    DefaultTableModel dtm = new DefaultTableModel();
    public VoucherPanel() {
        initComponents();
        DateChooser dateChooser = new DateChooser();
        dateChooser.setTextField(txtNgayBatDau);
        dateChooser.setTextField(txtNgayKetThuc);
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        service.upDateTrangThai();
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        cbbTrangThai.addItem("Đang áp dụng");
        cbbLocTrangThai.addItem("Đang áp dụng");
        cbbTrangThai.addItem("Sắp diễn ra");
        cbbLocTrangThai.addItem("Sắp diễn ra");
        cbbTrangThai.addItem("Đã kết thúc");
        cbbLocTrangThai.addItem("Đã kết thúc");
        List<KhuyenMai> listKM = service.getAll();
        this.fillTable(listKM);
    }
    void fillTable(List<KhuyenMai> list) {
        dtm = (DefaultTableModel) tblPhieuGiamGia.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai x : list) {
            dtm.addRow(x.toDataRow());
        }
    }
    void fillData(int index) {
        KhuyenMai km = service.getAll().get(index);
        txtMa.setText(km.getMa());
        txtTen.setText(km.getTen());
        txtNgayBatDau.setText(String.valueOf(km.getNgayBD()));
        txtNgayKetThuc.setText(String.valueOf(km.getNgayKT()));
        txtGiaGiam.setText(String.valueOf(km.getTienGiam()));
        txtGiaToiThieu.setText(String.valueOf(km.getTienTT()));
        cbbTrangThai.setSelectedItem(service.getAll().get(index).layTrangThai());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFormPhieuGiamGia = new view.PanelBorder();
        txtTen = new com.raven.suportSwing.TextField();
        txtGiaGiam = new com.raven.suportSwing.TextField();
        txtGiaToiThieu = new com.raven.suportSwing.TextField();
        txtNgayBatDau = new javax.swing.JTextField();
        lblNgayBatDau = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        cbbTrangThai = new com.raven.suportSwing.Combobox();
        btnThem = new com.raven.suportSwing.MyButton();
        btnSua = new com.raven.suportSwing.MyButton();
        btnXoa = new com.raven.suportSwing.MyButton();
        btnLamMoi = new com.raven.suportSwing.MyButton();
        txtMa = new com.raven.suportSwing.TextField();
        panelDanhSachPhieuGiamGia = new view.PanelBorder();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiem = new com.raven.suportSwing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuGiamGia = new view.component.Table();
        btnTim = new com.raven.suportSwing.MyButton();
        lblLocTrangThai = new javax.swing.JLabel();
        cbbLocTrangThai = new com.raven.suportSwing.Combobox();
        btnLoc = new com.raven.suportSwing.MyButton();
        lblTieuDe = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelFormPhieuGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        panelFormPhieuGiamGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu giảm giá", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 204))); // NOI18N

        txtTen.setForeground(new java.awt.Color(153, 0, 204));
        txtTen.setLabelText("Tên");

        txtGiaGiam.setForeground(new java.awt.Color(153, 0, 204));
        txtGiaGiam.setLabelText("Giá giảm");

        txtGiaToiThieu.setForeground(new java.awt.Color(153, 0, 204));
        txtGiaToiThieu.setLabelText("Giá tối thiểu");
        txtGiaToiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaToiThieuActionPerformed(evt);
            }
        });

        txtNgayBatDau.setForeground(new java.awt.Color(153, 0, 204));

        lblNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayBatDau.setForeground(new java.awt.Color(153, 0, 204));
        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayKetThuc.setForeground(new java.awt.Color(153, 0, 204));
        lblNgayKetThuc.setText("Ngày kết thúc");

        txtNgayKetThuc.setForeground(new java.awt.Color(153, 0, 204));

        cbbTrangThai.setForeground(new java.awt.Color(153, 0, 204));
        cbbTrangThai.setLabeText("Trạng thái");

        btnThem.setForeground(new java.awt.Color(153, 0, 204));
        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(153, 0, 204));
        btnThem.setRadius(20);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(153, 0, 204));
        btnSua.setText("Sửa");
        btnSua.setBorderColor(new java.awt.Color(153, 0, 204));
        btnSua.setRadius(20);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(153, 0, 204));
        btnXoa.setText("Xoá");
        btnXoa.setBorderColor(new java.awt.Color(153, 0, 204));
        btnXoa.setRadius(20);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setForeground(new java.awt.Color(153, 0, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorderColor(new java.awt.Color(153, 0, 204));
        btnLamMoi.setRadius(20);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtMa.setForeground(new java.awt.Color(153, 0, 204));
        txtMa.setLabelText("Mã giảm giá");

        javax.swing.GroupLayout panelFormPhieuGiamGiaLayout = new javax.swing.GroupLayout(panelFormPhieuGiamGia);
        panelFormPhieuGiamGia.setLayout(panelFormPhieuGiamGiaLayout);
        panelFormPhieuGiamGiaLayout.setHorizontalGroup(
            panelFormPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormPhieuGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGiaGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGiaToiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayBatDau)
                    .addComponent(txtNgayKetThuc)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayBatDau)
                    .addComponent(lblNgayKetThuc)
                    .addGroup(panelFormPhieuGiamGiaLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelFormPhieuGiamGiaLayout.setVerticalGroup(
            panelFormPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormPhieuGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGiaToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNgayBatDau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNgayKetThuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelFormPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDanhSachPhieuGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        panelDanhSachPhieuGiamGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu giảm giá", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 204))); // NOI18N

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTimKiem.setForeground(new java.awt.Color(153, 0, 204));
        lblTimKiem.setText("Tìm kiếm: ");

        txtTimKiem.setForeground(new java.awt.Color(153, 0, 204));
        txtTimKiem.setLabelText("Nhập mã giảm giá hoặc tên");

        tblPhieuGiamGia.setForeground(new java.awt.Color(153, 0, 204));
        tblPhieuGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Ngày bắt đầu", "Ngày kết thúc", "Giá giảm", "Giá tối thiểu", "Trạng thái"
            }
        ));
        tblPhieuGiamGia.setSelectionBackground(new java.awt.Color(153, 0, 204));
        tblPhieuGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuGiamGia);
        if (tblPhieuGiamGia.getColumnModel().getColumnCount() > 0) {
            tblPhieuGiamGia.getColumnModel().getColumn(0).setHeaderValue("Mã");
            tblPhieuGiamGia.getColumnModel().getColumn(1).setHeaderValue("Tên");
            tblPhieuGiamGia.getColumnModel().getColumn(2).setHeaderValue("Ngày bắt đầu");
            tblPhieuGiamGia.getColumnModel().getColumn(3).setHeaderValue("Ngày kết thúc");
            tblPhieuGiamGia.getColumnModel().getColumn(4).setHeaderValue("Giá giảm");
            tblPhieuGiamGia.getColumnModel().getColumn(5).setHeaderValue("Giá tối thiểu");
            tblPhieuGiamGia.getColumnModel().getColumn(6).setHeaderValue("Trạng thái");
        }

        btnTim.setForeground(new java.awt.Color(153, 0, 204));
        btnTim.setText("Tìm");
        btnTim.setBorderColor(new java.awt.Color(153, 0, 204));
        btnTim.setRadius(20);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        lblLocTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLocTrangThai.setForeground(new java.awt.Color(153, 0, 204));
        lblLocTrangThai.setText("Lọc trạng thái: ");

        cbbLocTrangThai.setForeground(new java.awt.Color(153, 0, 204));
        cbbLocTrangThai.setLabeText("Trạng thái");

        btnLoc.setForeground(new java.awt.Color(153, 0, 204));
        btnLoc.setText("Lọc");
        btnLoc.setBorderColor(new java.awt.Color(153, 0, 204));
        btnLoc.setRadius(20);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDanhSachPhieuGiamGiaLayout = new javax.swing.GroupLayout(panelDanhSachPhieuGiamGia);
        panelDanhSachPhieuGiamGia.setLayout(panelDanhSachPhieuGiamGiaLayout);
        panelDanhSachPhieuGiamGiaLayout.setHorizontalGroup(
            panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachPhieuGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDanhSachPhieuGiamGiaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(panelDanhSachPhieuGiamGiaLayout.createSequentialGroup()
                        .addComponent(lblTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblLocTrangThai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 85, Short.MAX_VALUE))))
        );
        panelDanhSachPhieuGiamGiaLayout.setVerticalGroup(
            panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachPhieuGiamGiaLayout.createSequentialGroup()
                .addGroup(panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDanhSachPhieuGiamGiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTimKiem)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachPhieuGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLocTrangThai)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(153, 0, 204));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("PHIẾU GIẢM GIÁ");
        lblTieuDe.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(panelFormPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDanhSachPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTieuDe)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFormPhieuGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDanhSachPhieuGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        KhuyenMai vc = new KhuyenMai();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String ngayBD = txtNgayBatDau.getText();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(ngayBD, formatter1);
        java.sql.Date ngayBDs = java.sql.Date.valueOf(localDate1);
        String ngayKT = txtNgayKetThuc.getText();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate2 = LocalDate.parse(ngayKT, formatter2);
        java.sql.Date ngayKTs = java.sql.Date.valueOf(localDate2);
        double soTienGiam = Double.parseDouble(txtGiaGiam.getText());
        double soTienTT = Double.parseDouble(txtGiaToiThieu.getText());
        int trangThai = 0;
        if (cbbTrangThai.getSelectedItem() == "Đang áp dụng") {
            trangThai = 0;
        } else if (cbbTrangThai.getSelectedItem() == "Sắp diễn ra") {
            trangThai = 1;
        } else {
            trangThai = 2;
        }
        KhuyenMai km1 = new KhuyenMai(ma, ten, ngayBDs, ngayKTs, soTienGiam, soTienTT, trangThai);
        JOptionPane.showMessageDialog(this, service.add(km1));
        service.upDateTrangThai();
        service.getAll();
        fillTable(service.getAll());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMa.setText("");
        txtTen.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtGiaGiam.setText("");
        txtGiaToiThieu.setText("");
        cbbTrangThai.setSelectedIndex(0);
        txtTimKiem.setText("");
        cbbLocTrangThai.setSelectedIndex(0);
        fillTable(service.getAll());
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        KhuyenMai km = new KhuyenMai();
        String id = txtMa.getText();
        km.setMa(txtMa.getText());
        km.setTen(txtTen.getText());
        String ngayBD = txtNgayBatDau.getText();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(ngayBD, formatter1);
        java.sql.Date ngayBDs = java.sql.Date.valueOf(localDate1);
        km.setNgayBD(ngayBDs);
        String ngayKT = txtNgayKetThuc.getText();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate2 = LocalDate.parse(ngayKT, formatter2);
        java.sql.Date ngayKTs = java.sql.Date.valueOf(localDate2);
        km.setNgayKT(ngayKTs);
        try {
            Double giaGiam = Double.valueOf(txtGiaGiam.getText());
            Double giaTT = Double.valueOf(txtGiaToiThieu.getText());
            km.setTienGiam(giaGiam);
            km.setTienTT(giaTT);
            if (giaGiam <= 0 || giaTT <= 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm phải lớn hơn 0");
                return;
            }
            if (giaTT < giaGiam) {
                JOptionPane.showMessageDialog(this, "Giá tối thiểu phải lớn hơn giá giảm");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số");
            return;
        }
        int trangThai = 0;
        if (cbbTrangThai.getSelectedItem() == "Đang áp dụng") {
            trangThai = 0;
        } else if (cbbTrangThai.getSelectedItem() == "Sắp diễn ra") {
            trangThai = 1;
        } else {
            trangThai = 2;
        }
        km.setTrangThai(trangThai);
        JOptionPane.showMessageDialog(this, service.update(km, id));
        service.getAll();
        fillTable(service.getAll());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int i = tblPhieuGiamGia.getSelectedRow();
        JOptionPane.showMessageDialog(this, service.delete(service.getAll().get(i).getMa()));
        service.getAll();
        fillTable(service.getAll());
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        List<KhuyenMai> listSearch = service.timKiem(service.getAll(), txtTimKiem.getText(), txtTimKiem.getText());
        fillTable(listSearch);
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblPhieuGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuGiamGiaMouseClicked
        index = tblPhieuGiamGia.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tblPhieuGiamGiaMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        List<KhuyenMai> listSearch = service.locTrangThai(service.getAll(), cbbLocTrangThai.getSelectedIndex());
        fillTable(listSearch);
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtGiaToiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaToiThieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaToiThieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.suportSwing.MyButton btnLamMoi;
    private com.raven.suportSwing.MyButton btnLoc;
    private com.raven.suportSwing.MyButton btnSua;
    private com.raven.suportSwing.MyButton btnThem;
    private com.raven.suportSwing.MyButton btnTim;
    private com.raven.suportSwing.MyButton btnXoa;
    private com.raven.suportSwing.Combobox cbbLocTrangThai;
    private com.raven.suportSwing.Combobox cbbTrangThai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLocTrangThai;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTimKiem;
    private view.PanelBorder panelDanhSachPhieuGiamGia;
    private view.PanelBorder panelFormPhieuGiamGia;
    private view.component.Table tblPhieuGiamGia;
    private com.raven.suportSwing.TextField txtGiaGiam;
    private com.raven.suportSwing.TextField txtGiaToiThieu;
    private com.raven.suportSwing.TextField txtMa;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private com.raven.suportSwing.TextField txtTen;
    private com.raven.suportSwing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
