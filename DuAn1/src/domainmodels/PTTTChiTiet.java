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
public class PTTTChiTiet {
    private int id;
    private HoaDon hoaDon;
    private PhuongThucThanhToan phuongThucThanhToan;
    private double soTien;
    private int  trangThai;
    private String maGD;
    private Date ngayTao;
    private Date ngaySua;
    private String taoBoi;
    private String suaBoi;
    private Boolean daXoa;

    public PTTTChiTiet() {
    }

    public PTTTChiTiet(int id, HoaDon hoaDon, PhuongThucThanhToan phuongThucThanhToan, double soTien, int trangThai, String maGD, Date ngayTao, Date ngaySua, String taoBoi, String suaBoi, Boolean daXoa) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.soTien = soTien;
        this.trangThai = trangThai;
        this.maGD = maGD;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.taoBoi = taoBoi;
        this.suaBoi = suaBoi;
        this.daXoa = daXoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public PhuongThucThanhToan getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
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
