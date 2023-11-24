package domainmodels;

import java.sql.Date;


public class KhuyenMai {
    private int id;
    private String ma;
    private String ten;
    private Date ngayBD;
    private Date ngayKT;
    private double tienGiam;
    private double tienTT;
    private int trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String taoBoi;
    private String suaBoi;
    private boolean daXoa;

    public KhuyenMai() {
    }

    public KhuyenMai(int id, String ma, String ten, Date ngayBD, Date ngayKT, double tienGiam, double tienTT, int trangThai, Date ngayTao, Date ngaySua, String taoBoi, String suaBoi, boolean daXoa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tienGiam = tienGiam;
        this.tienTT = tienTT;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.taoBoi = taoBoi;
        this.suaBoi = suaBoi;
        this.daXoa = daXoa;
    }

    public KhuyenMai(String ma, String ten, Date ngayBD, Date ngayKT, double tienGiam, double tienTT, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tienGiam = tienGiam;
        this.tienTT = tienTT;
        this.trangThai = trangThai;
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

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public double getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(double tienGiam) {
        this.tienGiam = tienGiam;
    }

    public double getTienTT() {
        return tienTT;
    }

    public void setTienTT(double tienTT) {
        this.tienTT = tienTT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
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

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    
    
    public String layTrangThai() {
        if (trangThai == 0) {
            return "Đang áp dụng";
        } else if (trangThai == 1) {
            return "Sắp diễn ra";
        } else {
            return "Đã kết thúc";
        }
    }

    public Object[] toDataRow() {
        return new Object[] {this.ma,this.ten,this.ngayBD,this.ngayKT,this.tienGiam,this.tienTT,layTrangThai()};
    }
}
