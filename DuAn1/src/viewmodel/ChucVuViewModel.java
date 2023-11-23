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
public class ChucVuViewModel {

    private int id;
    private String ma;
    private String ten;
    private int status;
    private Date ngayTao;
    private Date ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private boolean daXoa;

    public ChucVuViewModel() {
    }

    public ChucVuViewModel(int id, String ma, String ten, int status, Date ngayTao, Date ngaySua, String nguoiTao, String nguoiSua, boolean daXoa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.status = status;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.daXoa = daXoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    @Override
    public String toString() {
        return "ChucVuViewModel{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", status=" + status + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", nguoiTao=" + nguoiTao + ", nguoiSua=" + nguoiSua + ", daXoa=" + daXoa + '}';
    }
    
        public Object[] toDaTaRow() {
        return new Object[]{ma,ten};
    }
    
}
