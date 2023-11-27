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
public class HoaDonChiTiet {
    private Integer id;
    private HoaDon hoaDon;
    private SanPhamCT sanPhamChiTiet;
    private Integer soLuong;
    private double thanhTien;
    private double giaTien;
    private Integer trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String taoBoi;
    private String suaBoi;
    private Boolean daXoa;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer id, HoaDon hoaDon, SanPhamCT sanPhamChiTiet, Integer soLuong, double thanhTien, double giaTien, Integer trangThai, Date ngayTao, Date ngaySua, String taoBoi, String suaBoi, Boolean daXoa) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.sanPhamChiTiet = sanPhamChiTiet;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.taoBoi = taoBoi;
        this.suaBoi = suaBoi;
        this.daXoa = daXoa;
    }

    public HoaDonChiTiet(SanPhamCT sanPhamChiTiet, Integer soLuong) {
        this.sanPhamChiTiet = sanPhamChiTiet;
        this.soLuong = soLuong;
    }
    
    

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPhamCT getSanPhamChiTiet() {
        return sanPhamChiTiet;
    }

    public void setSanPhamChiTiet(SanPhamCT sanPhamChiTiet) {
        this.sanPhamChiTiet = sanPhamChiTiet;
    }

    

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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
