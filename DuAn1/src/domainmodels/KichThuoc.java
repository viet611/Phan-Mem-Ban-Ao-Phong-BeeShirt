/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

/**
 *
 * @author Admin
 */
public class KichThuoc {
    private int id;
    private String ma;
    private String ten;
    private int trang_thai;
    private String tao_boi;
    private String sua_boi;
    private boolean da_xoa;

    public KichThuoc() {
    }

    public KichThuoc(int id, String ma, String ten, int trang_thai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.trang_thai = trang_thai;
    }

    public KichThuoc(String ten) {
        this.ten = ten;
    }

    public KichThuoc(int id, String ma, String ten, int trang_thai, String tao_boi, String sua_boi, boolean da_xoa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.trang_thai = trang_thai;
        this.tao_boi = tao_boi;
        this.sua_boi = sua_boi;
        this.da_xoa = da_xoa;
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

    @Override
    public String toString() {
        return  ten ;
    }
    
    
}
