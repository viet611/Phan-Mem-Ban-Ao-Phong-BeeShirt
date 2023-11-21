/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class KhachHang {

    private int id;
    private int idCustomerRanking;
    private String ma;
    private String ten;
    private boolean sex;
    private Date ngaySinh;
    private String sdt;
    private String diaChi;
    private int status;
    private Date ngayTao;
    private Date ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private boolean daXoa;

    public KhachHang() {

    }

    public KhachHang(int id, int idCustomerRanking, String ma, String ten, boolean sex, Date ngaySinh, String sdt, String diaChi, int status, Date ngayTao, Date ngaySua, String nguoiTao, String nguoiSua, boolean daXoa) {
        this.id = id;
        this.idCustomerRanking = idCustomerRanking;
        this.ma = ma;
        this.ten = ten;
        this.sex = sex;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
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

    public int getIdCustomerRanking() {
        return idCustomerRanking;
    }

    public void setIdCustomerRanking(int idCustomerRanking) {
        this.idCustomerRanking = idCustomerRanking;
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

     public String getStatus(int status) {
        if (status == 1) {
            return "Member";
        } else if (status == 2) {
            return "New Customers";
        } else {
            return null;
        }
    }
    
    public Object[] toDataRow() {
        return new Object[]{id, ma, ten, sex ? "Nam" : "Nữ", ngaySinh, sdt, diaChi, getStatus(getStatus())};
    }

}
