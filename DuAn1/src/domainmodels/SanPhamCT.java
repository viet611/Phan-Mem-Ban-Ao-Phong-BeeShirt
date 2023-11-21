/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

/**
 *
 * @author Admin
 */
public class SanPhamCT {
    private int id;
    private int id_SanPham;
    private int id_MauSac;
    private int id_ThuongHieu;
    private int id_KichThuoc;
    private int id_KieuDang;
    private int id_MuaPhuHop;
    private int id_MDSD;
    private int id_ChatLieu;
    private int id_HoaTiet;
    private int id_HinhAnh;
    private String ma;
    private boolean goi_Tinh;
    private int so_Luong;
    private float gia;
    private String mo_Ta;
    private int trang_thai;
    private String tao_boi;
    private String sua_boi;
    private boolean da_xoa;

    public SanPhamCT() {
    }

    public int getId() {
        return id;
    }

    public SanPhamCT(int id, int id_SanPham, int id_MauSac, int id_ThuongHieu, int id_KichThuoc, int id_KieuDang, int id_MuaPhuHop, int id_MDSD, int id_ChatLieu, int id_HoaTiet, int id_HinhAnh, String ma, boolean goi_Tinh, int so_Luong, float gia, String mo_Ta, int trang_thai, String tao_boi, String sua_boi, boolean da_xoa) {
        this.id = id;
        this.id_SanPham = id_SanPham;
        this.id_MauSac = id_MauSac;
        this.id_ThuongHieu = id_ThuongHieu;
        this.id_KichThuoc = id_KichThuoc;
        this.id_KieuDang = id_KieuDang;
        this.id_MuaPhuHop = id_MuaPhuHop;
        this.id_MDSD = id_MDSD;
        this.id_ChatLieu = id_ChatLieu;
        this.id_HoaTiet = id_HoaTiet;
        this.id_HinhAnh = id_HinhAnh;
        this.ma = ma;
        this.goi_Tinh = goi_Tinh;
        this.so_Luong = so_Luong;
        this.gia = gia;
        this.mo_Ta = mo_Ta;
        this.trang_thai = trang_thai;
        this.tao_boi = tao_boi;
        this.sua_boi = sua_boi;
        this.da_xoa = da_xoa;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public int getId_SanPham() {
        return id_SanPham;
    }

    public void setId_SanPham(int id_SanPham) {
        this.id_SanPham = id_SanPham;
    }

    public int getId_MauSac() {
        return id_MauSac;
    }

    public void setId_MauSac(int id_MauSac) {
        this.id_MauSac = id_MauSac;
    }

    public int getId_ThuongHieu() {
        return id_ThuongHieu;
    }

    public void setId_ThuongHieu(int id_ThuongHieu) {
        this.id_ThuongHieu = id_ThuongHieu;
    }

    public int getId_KichThuoc() {
        return id_KichThuoc;
    }

    public void setId_KichThuoc(int id_KichThuoc) {
        this.id_KichThuoc = id_KichThuoc;
    }

    public int getId_KieuDang() {
        return id_KieuDang;
    }

    public void setId_KieuDang(int id_KieuDang) {
        this.id_KieuDang = id_KieuDang;
    }

    public int getId_MuaPhuHop() {
        return id_MuaPhuHop;
    }

    public void setId_MuaPhuHop(int id_MuaPhuHop) {
        this.id_MuaPhuHop = id_MuaPhuHop;
    }

    public int getId_MDSD() {
        return id_MDSD;
    }

    public void setId_MDSD(int id_MDSD) {
        this.id_MDSD = id_MDSD;
    }

    public int getId_ChatLieu() {
        return id_ChatLieu;
    }

    public void setId_ChatLieu(int id_ChatLieu) {
        this.id_ChatLieu = id_ChatLieu;
    }

    public int getId_HoaTiet() {
        return id_HoaTiet;
    }

    public void setId_HoaTiet(int id_HoaTiet) {
        this.id_HoaTiet = id_HoaTiet;
    }

    public int getId_HinhAnh() {
        return id_HinhAnh;
    }

    public void setId_HinhAnh(int id_HinhAnh) {
        this.id_HinhAnh = id_HinhAnh;
    }

    
    
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public boolean isGoi_Tinh() {
        return goi_Tinh;
    }

    public void setGoi_Tinh(boolean goi_Tinh) {
        this.goi_Tinh = goi_Tinh;
    }

    public int getSo_Luong() {
        return so_Luong;
    }

    public void setSo_Luong(int so_Luong) {
        this.so_Luong = so_Luong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getMo_Ta() {
        return mo_Ta;
    }

    public void setMo_Ta(String mo_Ta) {
        this.mo_Ta = mo_Ta;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
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

    public boolean isDa_xoa() {
        return da_xoa;
    }

    public void setDa_xoa(boolean da_xoa) {
        this.da_xoa = da_xoa;
    }
    
    
}
