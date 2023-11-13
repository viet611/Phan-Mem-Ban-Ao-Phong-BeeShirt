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
public class HoaDonTimeLine {
    private Integer id;
    private HoaDon hoaDon;
    private Integer trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String taoBoi;
    private String suaBoi;
    private Boolean daXoa;

    public HoaDonTimeLine() {
    }

    public HoaDonTimeLine(Integer id, HoaDon hoaDon, Integer trangThai, Date ngayTao, Date ngaySua, String taoBoi, String suaBoi, Boolean daXoa) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.taoBoi = taoBoi;
        this.suaBoi = suaBoi;
        this.daXoa = daXoa;
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

    @Override
    public String toString() {
        return "HoaDonTimeLine{" + "id=" + id + ", hoaDon=" + hoaDon + ", trangThai=" + trangThai + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", taoBoi=" + taoBoi + ", suaBoi=" + suaBoi + ", daXoa=" + daXoa + '}';
    }
    
    
    
}
