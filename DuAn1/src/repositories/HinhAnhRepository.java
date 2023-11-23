/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.sql.Connection;
import domainmodels.HinhAnh;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ultilities.DBConnect;

/**
 *
 * @author Admin
 */
public class HinhAnhRepository {
    public List<HinhAnh> getAll(){
        String sql=null;
        List<HinhAnh> hinhAnh = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from hinh_anh ORDER BY hinh_anh.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {  
                hinhAnh.add(new HinhAnh(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4)));       
            }
            pstm.close();
            result.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return hinhAnh;
    }
    
    public void InserHA(HinhAnh ha){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO hinh_anh(ma, duong_dan,trang_thai)VALUES (?, ?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, ha.getMa());
            pstm.setString(2, ha.getDuong_dan());
            pstm.setInt(3, ha.getTrang_thai());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
    }
    
    public String getNameByID(int id){
        String sql=null;
        String name = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from hinh_anh where hinh_anh.id = ? ORDER BY hinh_anh.id DESC";
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
    
    public Integer getIDbyName(String name){
        String sql=null;
        int id_CL = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from hinh_anh where hinh_anh.duong_dan=? ORDER BY hinh_anh.id DESC";
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
}
