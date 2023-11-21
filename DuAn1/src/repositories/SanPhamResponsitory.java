/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.SanPham;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ultilities.DBConnect;

/**
 *
 * @author Admin
 */
public class SanPhamResponsitory {
    public List<SanPham> getALL(){
        List<SanPham> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        //sql ="select san_pham.id ,san_pham.ma,san_pham.ten from san_pham";
        sql ="select * from san_pham";
        pstm = cn.prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPham(result.getInt(1), result.getString(2), result.getString(3),result.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPham> getNameByID(int id){
        List<SanPham> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select san_pham.id ,san_pham.ma,san_pham.ten from san_pham where san_pham.id ='?'";
        pstm = cn.prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPham(result.getInt(1), result.getString(2), result.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public String getNameByIDSP(int id){
        String sql=null;
        String nameTest = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select san_pham.ma from san_pham where san_pham.id = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                nameTest = result.getString(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return nameTest;
    }
    
    public String getNameID(int id){
        String sql=null;
        String nameTest = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select san_pham.ten from san_pham where san_pham.id = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                nameTest = result.getString(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return nameTest;
    }
    
    public List<SanPham> getName(){
        String sql=null;
        List<SanPham> sp = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from san_pham ";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                sp.add(new SanPham( result.getString(3)));
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public void InsertSp(SanPham sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO san_pham (ma, ten,tao_boi,sua_boi,trang_thai,da_xoa) VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, sp.getMa());
            pstm.setString(2, sp.getTen());
            pstm.setString(3, sp.getTao_boi());
            pstm.setString(4, sp.getSua_boi());
            pstm.setInt(5, sp.getTrang_thai());
            pstm.setBoolean(6, sp.isDa_xoa());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        
    }
    
    public List<SanPham> search(String ma){
        List<SanPham> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM san_pham WHERE san_pham.ma LIKE ? OR san_pham.ten LIKE ?";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, "%" + ma + "%");
        pstm.setString(2, "%" + ma + "%");
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPham(result.getInt(1), result.getString(2), result.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public Integer getIDbyName(String name){
        String sql=null;
        int id_SP = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from san_pham where san_pham.ten=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                id_SP = result.getInt(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy tem ms: " + sql);
        }
        return id_SP;
    }
    
    public void UpdateSp(SanPham sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE san_pham SET ma=?, ten = ?, trang_thai=?,tao_boi=?, sua_boi=?,da_xoa=? WHERE san_pham.id=?";
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
    
    public void DeleteSp(SanPham sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE san_pham SET trang_thai=?, da_xoa=? WHERE san_pham.id=?";
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
    
}
