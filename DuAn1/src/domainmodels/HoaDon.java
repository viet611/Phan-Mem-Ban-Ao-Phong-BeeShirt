/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private Integer id;
    private KhachHang khachHang;
    private KhuyenMai voucher;
    private NhanVien nhanVien;
    private String ma;
    private Double soTienGiam;
    private Double tongTien;
    private Double tienShip;
    private String tenKhachHang;
    private String sdt;
    private String diaChi;
    private Integer trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String taoBoi;
    private String suaBoi;
    private Boolean daXoa;

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", khachHang=" + khachHang + ", voucher=" + voucher + ", nhanVien=" + nhanVien + ", ma=" + ma + ", soTienGiam=" + soTienGiam + ", tongTien=" + tongTien + ", tienShip=" + tienShip + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + ", diaChi=" + diaChi + ", trangThai=" + trangThai + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", taoBoi=" + taoBoi + ", suaBoi=" + suaBoi + ", daXoa=" + daXoa + '}';
    }

    public HoaDon() {
    }

    public HoaDon(Integer id, KhachHang khachHang, KhuyenMai voucher, NhanVien nhanVien, String ma, Double soTienGiam, Double tongTien, Double tienShip, String tenKhachHang, String sdt, String diaChi, Integer trangThai, Date ngayTao, Date ngaySua, String taoBoi, String suaBoi, Boolean daXoa) {
        this.id = id;
        this.khachHang = khachHang;
        this.voucher = voucher;
        this.nhanVien = nhanVien;
        this.ma = ma;
        this.soTienGiam = soTienGiam;
        this.tongTien = tongTien;
        this.tienShip = tienShip;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.taoBoi = taoBoi;
        this.suaBoi = suaBoi;
        this.daXoa = daXoa;
    }

    public HoaDon(Double tongTien, Date ngayTao) {
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public KhuyenMai getVoucher() {
        return voucher;
    }

    public void setVoucher(KhuyenMai voucher) {
        this.voucher = voucher;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Double getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(Double soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getTienShip() {
        return tienShip;
    }

    public void setTienShip(Double tienShip) {
        this.tienShip = tienShip;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTaoBoi() {
        return taoBoi;
    }

    public void setTaoBoi(String taoBoi) {
        this.taoBoi = taoBoi;
    }

    public String getSuaBoi() {
        return suaBoi;
    }

    public void setSuaBoi(String suaBoi) {
        this.suaBoi = suaBoi;
    }

    public Boolean getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }
    
}
