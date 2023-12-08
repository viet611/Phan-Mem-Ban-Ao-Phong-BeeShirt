/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;


import domainmodels.MucDichSuDung;
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
public class MucDichSDResponsitory {
    public String getNameByID(int id){
        String sql=null;
        String name = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from muc_dich_su_dung where muc_dich_su_dung.id = ? ORDER BY muc_dich_su_dung.id DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                name = result.getString(2);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return name;
    }
    
    public List<MucDichSuDung>getALL(){
        String sql=null;
        List<MucDichSuDung> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from muc_dich_su_dung ORDER BY muc_dich_su_dung.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            //MucDichSuDung(int id, String ma, String ten, int trang_thai)
            while (result.next()) {  
                mdsd.add(new MucDichSuDung(result.getInt(1),result.getString(3),result.getString(2),result.getInt(4)));       
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
        int id_MDSD = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from muc_dich_su_dung where muc_dich_su_dung.ten=? ORDER BY muc_dich_su_dung.id DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, name);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                id_MDSD = result.getInt(1);
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy tem ms: " + sql);
        }
        return id_MDSD;
    }
    
    public List<MucDichSuDung>getALLMDSD(){
        String sql=null;
        List<MucDichSuDung> mdsd = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from muc_dich_su_dung ORDER BY muc_dich_su_dung.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                mdsd.add(new MucDichSuDung(result.getInt(1), result.getString(3),result.getString(2),result.getInt(4)));       
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return mdsd;
    }
    
    public void InsertMDSD(MucDichSuDung cl){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO muc_dich_su_dung (ma, ten,tao_boi,sua_boi,trang_thai,da_xoa) VALUES (?, ?, ?, ?, ?, ?)";
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
    
    public void UpdateMDSD(MucDichSuDung sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE muc_dich_su_dung SET ma=?, ten = ?, trang_thai=?,tao_boi=?, sua_boi=?,da_xoa=? WHERE muc_dich_su_dung.id=?";
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
    
    public void DeleteMDSD(MucDichSuDung sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE muc_dich_su_dung SET trang_thai=?, da_xoa=? WHERE muc_dich_su_dung.id=?";
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
    
    public List<MucDichSuDung> search(String ma){
        List<MucDichSuDung> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM muc_dich_su_dung WHERE muc_dich_su_dung.ma LIKE ? OR muc_dich_su_dung.ten LIKE ? ORDER BY muc_dich_su_dung.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, "%" + ma + "%");
        pstm.setString(2, "%" + ma + "%");
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new MucDichSuDung(result.getInt(1), result.getString(3),result.getString(2),result.getInt(4)));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
}
