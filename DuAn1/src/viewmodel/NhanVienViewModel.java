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
public class NhanVienViewModel {

    private int id;
    private int idUser;
    private String ma;
    private String ten;
    private boolean sex;
    private Date ngaySinh;
    private String sdt;
    private String password;
    private String diaChi;
    private int status;
    private Date ngayTao;
    private Date ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private boolean daXoa;
    private String email;
    private String cccd;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(int id, int idUser, String ma, String ten, boolean sex, Date ngaySinh, String sdt, String password, String diaChi, int status, Date ngayTao, Date ngaySua, String nguoiTao, String nguoiSua, boolean daXoa, String email, String cccd) {
        this.id = id;
        this.idUser = idUser;
        this.ma = ma;
        this.ten = ten;
        this.sex = sex;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.password = password;
        this.diaChi = diaChi;
        this.status = status;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.daXoa = daXoa;
        this.email = email;
        this.cccd = cccd;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "NhanVienViewModel{" + "id=" + id + ", idUser=" + idUser + ", ma=" + ma + ", ten=" + ten + ", sex=" + sex + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", password=" + password + ", diaChi=" + diaChi + ", status=" + status + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", nguoiTao=" + nguoiTao + ", nguoiSua=" + nguoiSua + ", daXoa=" + daXoa + ", email=" + email + ", cccd=" + cccd + '}';
    }

    public String tentrangThai(int trangThai) {
        if (trangThai == 1) {
            return "Đã nghỉ";
        } else {
            return "Đang làm";
        }
    }

    public Object[] toData() {
        return new Object[]{id, ma, ten, nguoiTao, ngaySinh, sdt, diaChi, password, tentrangThai(status)};
    }

}
