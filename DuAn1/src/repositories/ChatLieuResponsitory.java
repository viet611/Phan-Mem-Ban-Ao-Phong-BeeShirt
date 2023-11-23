/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import domainmodels.ChatLieu;
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
public class ChatLieuResponsitory {
    public String getNameByID(int id){
        String sql=null;
        String name = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from chat_lieu where chat_lieu.id = ? ORDER BY chat_lieu.id DESC";
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
    
    public List<ChatLieu>getALL(){
        String sql=null;
        List<ChatLieu> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from chat_lieu ORDER BY chat_lieu.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                mdsd.add(new ChatLieu(result.getString(3)));       
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
        int id_CL = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from chat_lieu where chat_lieu.ten=? ORDER BY chat_lieu.id DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                id_CL = result.getInt(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy tem ms: " + sql);
        }
        return id_CL;
    }
    
    public List<ChatLieu>getALLCL(){
        String sql=null;
        List<ChatLieu> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from chat_lieu ORDER BY chat_lieu.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                mdsd.add(new ChatLieu(result.getInt(1), result.getString(2),result.getString(3),result.getInt(4)));       
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return mdsd;
    }
    
    public void InsertCL(ChatLieu cl){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO chat_lieu (ma, ten,tao_boi,trang_thai,da_xoa) VALUES (?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cl.getMa());
            pstm.setString(2, cl.getTen());
            pstm.setString(3, cl.getTao_boi());
            pstm.setInt(4, cl.getTrang_thai());
            pstm.setBoolean(5, cl.isDa_xoa());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        
    }
    
    public void UpdateCL(ChatLieu sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE chat_lieu SET ma=?, ten = ?, trang_thai=?,tao_boi=?,da_xoa=? WHERE chat_lieu.id=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, sp.getMa());
            pstm.setString(2, sp.getTen());
            pstm.setInt(3, sp.getTrang_thai());
            pstm.setString(4, sp.getTao_boi());
            //pstm.setString(5, sp.getSua_boi());
            pstm.setBoolean(5, sp.isDa_xoa());
            pstm.setInt(6, sp.getId());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
    }
    
    public void DeleteCL(ChatLieu sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE chat_lieu SET trang_thai=?, da_xoa=? WHERE chat_lieu.id=?";
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
    
    public List<ChatLieu> search(String ma){
        List<ChatLieu> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM chat_lieu WHERE chat_lieu.ma LIKE ? OR chat_lieu.ten LIKE ? ORDER BY chat_lieu.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, "%" + ma + "%");
        pstm.setString(2, "%" + ma + "%");
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new ChatLieu(result.getInt(1), result.getString(2),result.getString(3),result.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    
}
