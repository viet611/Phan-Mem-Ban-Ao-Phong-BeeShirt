/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.SanPhamCT;
import java.util.List;
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
public class SanPhamCTResponsitory {
    public List<SanPhamCT> getALL(){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> FilterKichThuoc(int id_KichThuoc){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_kich_thuoc = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_KichThuoc);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));
                
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> filterMauSac(int id_MauSac){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_mau_sac = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_MauSac);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));

                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> filterKieuDang(int id_kieuDang){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_kieu_dang = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_kieuDang);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));
                
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> filterChatLieu(int id_ChatLieu){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_chat_lieu = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_ChatLieu);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));
                
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> filterThuongHieu(int id_ThuongHieu){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_thuong_hieu = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_ThuongHieu);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));
                
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> filterMPH(int id_MPH){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id_mua_phu_hop = ? ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id_MPH);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
//                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
//                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
//                        result.getInt(9),result.getInt(10),result.getInt(20),result.getString(11),result.getBoolean(12),
//                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
//                        result.getString(20),result.getBoolean(21)
//                ));
                
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public void InsertSp(SanPhamCT sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "INSERT INTO san_pham_chi_tiet(id_san_pham, id_mau_sac, id_thuong_hieu, id_kich_thuoc, id_kieu_dang, id_mua_phu_hop, id_mdsd,\n"
                    + "id_chat_lieu, id_hoa_tiet, ma, gioi_tinh, so_luong_ton, gia, mo_ta, trang_thai,tao_boi,sua_boi, da_xoa, id_hinh_anh,qr_code\n"
                    + ")\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, sp.getId_SanPham());
            pstm.setInt(2, sp.getId_MauSac());
            pstm.setInt(3, sp.getId_ThuongHieu());
            pstm.setInt(4, sp.getId_KichThuoc());
            pstm.setInt(5, sp.getId_KieuDang());
            pstm.setInt(6, sp.getId_MuaPhuHop());
            pstm.setInt(7, sp.getId_MDSD());
            pstm.setInt(8, sp.getId_ChatLieu());
            pstm.setInt(9, sp.getId_HoaTiet());
            pstm.setString(10, sp.getMa());
            pstm.setBoolean(11, sp.isGoi_Tinh());
            pstm.setFloat(12, sp.getSo_Luong());
            pstm.setFloat(13, sp.getGia());
            pstm.setString(14, sp.getMo_Ta());
            pstm.setInt(15, sp.getTrang_thai());
            pstm.setString(16, sp.getTao_boi());
            pstm.setString(17, sp.getSua_boi());
            pstm.setBoolean(18, sp.isDa_xoa());
            pstm.setInt(19, sp.getId_HinhAnh());
            pstm.setString(20, sp.getQrCode());
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lôi truy " + sql);
        }
    }
    
    public List<SanPhamCT> getALLSPCTByID(int id){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="select * from san_pham_chi_tiet where san_pham_chi_tiet.id=?";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
     public void UpdateSpct(SanPhamCT sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE san_pham_chi_tiet SET id_san_pham=?, id_mau_sac=?, id_thuong_hieu=?, id_kich_thuoc=?, id_kieu_dang=?, id_mua_phu_hop=?,id_mdsd=?, id_chat_lieu=?, ma=?, gioi_tinh=?, so_luong_ton=?, gia=?,trang_thai =?, tao_boi =?, sua_boi=?, da_xoa =?, id_hinh_anh=? WHERE id = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, sp.getId_SanPham());
            pstm.setInt(2, sp.getId_MauSac());
            pstm.setInt(3, sp.getId_ThuongHieu());
            pstm.setInt(4, sp.getId_KichThuoc());
            pstm.setInt(5, sp.getId_KieuDang());
            pstm.setInt(6, sp.getId_MuaPhuHop());
            pstm.setInt(7, sp.getId_MDSD());
            pstm.setInt(8, sp.getId_ChatLieu());
            pstm.setString(9, sp.getMa());
            pstm.setBoolean(10, sp.isGoi_Tinh());
            pstm.setInt(11, sp.getSo_Luong());
            pstm.setFloat(12, sp.getGia());
            pstm.setInt(13, sp.getTrang_thai());
            pstm.setString(14, sp.getTao_boi());
            pstm.setString(15, sp.getSua_boi());
            pstm.setBoolean(16, sp.isDa_xoa());
            pstm.setInt(17, sp.getId_HinhAnh());
            pstm.setInt(18, sp.getId());
            
            pstm.executeUpdate();
            //pstm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
    
     }
     
    public void DeleteSpct(SanPhamCT sp){
        String sql=null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "UPDATE san_pham_chi_tiet SET trang_thai=?, da_xoa=? WHERE san_pham_chi_tiet.id=?";
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
    
    public List<SanPhamCT> getSPCTbyIDSP(int iDSP){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM san_pham_chi_tiet WHERE san_pham_chi_tiet.id_san_pham = ? ORDER BY id DESC;";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, iDSP);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public Integer getCountSP(int IDSP){
        String sql=null;
        int id_SP = 0 ;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select COUNT(*) from san_pham_chi_tiet where san_pham_chi_tiet.id_san_pham =?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, IDSP);
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
    
    public List<SanPhamCT> getallWithMaSPCT(String MaSPCT){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM san_pham_chi_tiet WHERE san_pham_chi_tiet.ma = ? ORDER BY id DESC;";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, MaSPCT);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        pstm.close();
        result.close();
        cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    public List<SanPhamCT> search(String ma){
        List<SanPhamCT> sp = new ArrayList<>();
        String sql=null;
        try {
        Connection cn = null;
        PreparedStatement pstm = null;
        cn = DBConnect.getConnection();
        sql ="SELECT * FROM san_pham_chi_tiet WHERE san_pham_chi_tiet.ma LIKE ?  ORDER BY san_pham_chi_tiet.id DESC";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, "%" + ma + "%");
        //pstm.setString(2, "%" + ma + "%");
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
                sp.add(new SanPhamCT(result.getInt(1), result.getInt(2), result.getInt(3), 
                        result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8),
                        result.getInt(9),result.getInt(10),result.getInt(22),result.getString(11),result.getBoolean(12),
                        result.getInt(13),result.getFloat(14),result.getString(15),result.getInt(16),result.getString(19),
                        result.getString(20),result.getBoolean(21),result.getString(23)
                ));
            }
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return sp;
    }
    
    
    
}
