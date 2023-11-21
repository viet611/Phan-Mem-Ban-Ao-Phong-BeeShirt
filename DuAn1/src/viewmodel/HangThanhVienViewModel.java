/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class HangThanhVienViewModel {

    private int id;
    private int id_voucher;
    private String ma;
    private String ten;
    private double so_tien_toi_thieu;
    private int trangThai;
    private Date ngay_tao;
    private Date ngay_sua;
    private String tao_boi;
    private String sua_boi;
    private Boolean da_xoa;

    public HangThanhVienViewModel(int id, int id_voucher, String ma, String ten, double so_tien_toi_thieu, int trangThai, Date ngay_tao, Date ngay_sua, String tao_boi, String sua_boi, Boolean da_xoa) {
        this.id = id;
        this.id_voucher = id_voucher;
        this.ma = ma;
        this.ten = ten;
        this.so_tien_toi_thieu = so_tien_toi_thieu;
        this.trangThai = trangThai;
        this.ngay_tao = ngay_tao;
        this.ngay_sua = ngay_sua;
        this.tao_boi = tao_boi;
        this.sua_boi = sua_boi;
        this.da_xoa = da_xoa;
    }

    public HangThanhVienViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(int id_voucher) {
        this.id_voucher = id_voucher;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getSo_tien_toi_thieu() {
        return so_tien_toi_thieu;
    }

    public void setSo_tien_toi_thieu(double so_tien_toi_thieu) {
        this.so_tien_toi_thieu = so_tien_toi_thieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_sua() {
        return ngay_sua;
    }

    public void setNgay_sua(Date ngay_sua) {
        this.ngay_sua = ngay_sua;
    }

    public String getTao_boi() {
        return tao_boi;
    }

    public void setTao_boi(String tao_boi) {
        this.tao_boi = tao_boi;
    }

    public String getSua_boi() {
        return sua_boi;
    }

    public void setSua_boi(String sua_boi) {
        this.sua_boi = sua_boi;
    }

    public Boolean getDa_xoa() {
        return da_xoa;
    }

    public void setDa_xoa(Boolean da_xoa) {
        this.da_xoa = da_xoa;
    }

    @Override
    public String toString() {
        return "HangThanhVien{" + "id=" + id + ", id_voucher=" + id_voucher + ", ma=" + ma + ", ten=" + ten + ", so_tien_toi_thieu=" + so_tien_toi_thieu + ", trangThai=" + trangThai + ", ngay_tao=" + ngay_tao + ", ngay_sua=" + ngay_sua + ", tao_boi=" + tao_boi + ", sua_boi=" + sua_boi + ", da_xoa=" + da_xoa + '}';
    }
    
    

}
