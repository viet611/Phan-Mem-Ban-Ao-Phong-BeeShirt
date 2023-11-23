/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import domainmodels.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ultilities.DBConnect;

/**
 *
 * @author Admin
 */
public class KichThuocResponsitory {
    public String getNameByID(int id){
        String sql=null;
        String name = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from kich_thuoc where kich_thuoc.id = ? ORDER BY kich_thuoc.id DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                name = result.getString(3);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return name;
    }
    
    public List<KichThuoc>getALL(){
        String sql=null;
        List<KichThuoc> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from kich_thuoc ORDER BY kich_thuoc.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                mdsd.add(new KichThuoc(result.getString(3)));       
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return mdsd;
    }
    
    public Integer getIDbyName(String name){
        String sql=null;
        int id_KT = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from kich_thuoc where kich_thuoc.ten=? ORDER BY kich_thuoc.id DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                id_KT = result.getInt(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy tem ms: " + sql);
        }
        return id_KT;
    }
    
    public List<KichThuoc>getALLKT(){
        String sql=null;
        List<KichThuoc> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from kich_thuoc ORDER BY kich_thuoc.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                mdsd.add(new KichThuoc(result.getInt(1), result.getString(2),result.getString(3),result.getInt(4)));       
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return mdsd;
    }
    
    public void InsertKT(KichThuoc cl){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO kich_thuoc (ma, ten,tao_boi,sua_boi,trang_thai,da_xoa) VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cl.getMa());
            pstm.setString(2, cl.getTen());
            pstm.setString(3, cl.getTao_boi());
            pstm.setString(4, cl.getSua_boi());
            pstm.setInt(5, cl.getTrang_thai());
            pstm.setBoolean(6, cl.isDa_xoa());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
    }
    
    
    public void UpdateKT(KichThuoc sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE kich_thuoc SET ma=?, ten = ?, trang_thai=?,tao_boi=?, sua_boi=?,da_xoa=? WHERE kich_thuoc.id=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, sp.getMa());
            pstm.setString(2, sp.getTen());
            pstm.setInt(3, sp.getTrang_thai());
            pstm.setString(4, sp.getTao_boi());
            pstm.setString(5, sp.getSua_boi());
            pstm.setBoolean(6, sp.isDa_xoa());
            pstm.setInt(7, sp.getId());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
    }
    
    public void DeleteKT(KichThuoc sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE kich_thuoc SET trang_thai=?, da_xoa=? WHERE kich_thuoc.id=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, sp.getTrang_thai());
            pstm.setBoolean(2, sp.isDa_xoa());
            pstm.setInt(3, sp.getId());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        
    }
    
    public List<KichThuoc> search(String ma){
        List<KichThuoc> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM kich_thuoc WHERE kich_thuoc.ma LIKE ? OR kich_thuoc.ten LIKE ? ORDER BY kich_thuoc.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, "%" + ma + "%");
        pstm.setString(2, "%" + ma + "%");
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new KichThuoc(result.getInt(1), result.getString(2),result.getString(3),result.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    
}
