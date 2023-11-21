/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

/**
 *
 * @author Admin
 */
public class HinhAnh {
    private int id;
    private String ma;
    private String duong_dan;
    private int trang_thai;
    private String tao_boi;
    private String sua_boi;
    private boolean da_xoa;

    public HinhAnh() {
    }

    public HinhAnh(int id, String ma, String duong_dan, int trang_thai) {
        this.id = id;
        this.ma = ma;
        this.duong_dan = duong_dan;
        this.trang_thai = trang_thai;
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

    public String getDuong_dan() {
        return duong_dan;
    }

    public void setDuong_dan(String duong_dan) {
        this.duong_dan = duong_dan;
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
