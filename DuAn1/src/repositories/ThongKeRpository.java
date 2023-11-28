/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ultilities.DBConnect;
import ultilities.JDBCHelper;

/**
 *
 * @author Admin
 */
public class ThongKeRpository {

    private final SanPhamCTResponsitory spctRepo = new SanPhamCTResponsitory();

    public List<HoaDonChiTiet> getAllHD() {
        List<HoaDonChiTiet> hdct = new ArrayList<>();
        String sql = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
            sql = "select * from hoa_don_chi_tiet ORDER BY hoa_don_chi_tiet.id DESC";
            //sql = "select * from hoa_don_chi_tiet ORDER BY hoa_don_chi_tiet.id DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                double tongTien = 0;
                for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(rs.getInt("id"))) {
                    tongTien += x.getThanhTien();
                }
                hdct.add(new HoaDonChiTiet(rs.getInt("id"), new HoaDon(), spctRepo.getALLSPCTByID(rs.getInt("id_spct")).get(0),
                        rs.getInt("so_luong"), rs.getDouble("thanh_tien"),
                        rs.getDouble("gia_tien"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));

            }
            pstm.close();
            rs.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
        }
        return hdct;
    }

    public List<HoaDon> getAllHD1(String dateString) {
        //System.out.println("date: "+dateString);
        List<HoaDon> hdct = new ArrayList<>();
        String sql = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(dateString);
            sdf.applyPattern("yyyy-MM-dd");
            dateString = sdf.format(date);
//            cn = DBConnect.getConnection();
            sql = "select * from hoa_don where hoa_don.ngay_tao= '" + dateString + "' AND trang_thai >0";
            //sql = "select * from hoa_don_chi_tiet ORDER BY hoa_don_chi_tiet.id DESC

            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                double tongTien = 0;
                for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(rs.getInt("id"))) {
                    tongTien += x.getThanhTien();
                }
                hdct.add(new HoaDon(rs.getInt("id"), new KhachHang(), new KhuyenMai(), new NhanVien(), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        tongTien, rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));

            }
            //hdct.forEach(c->System.out.println(c.toString()));
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
            e.printStackTrace();
        }
        return hdct;
    }

    public List<HoaDon> getHDByMonth(String dateString) {
        //System.out.println("date: "+dateString);
        List<HoaDon> hdct = new ArrayList<>();
        String sql = null;
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            Date date = sdf.parse(dateString);
//            sdf.applyPattern("yyyy-MM-dd");
//            dateString = sdf.format(date);
//            cn = DBConnect.getConnection();
            sql = "select * from hoa_don where MONTH(hoa_don.ngay_tao)= '" + dateString + "' AND trang_thai >0";
            //sql = "select * from hoa_don_chi_tiet ORDER BY hoa_don_chi_tiet.id DESC";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                double tongTien = 0;
                for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(rs.getInt("id"))) {
                    tongTien += x.getThanhTien();
                }
                hdct.add(new HoaDon(rs.getInt("id"), new KhachHang(), new KhuyenMai(), new NhanVien(), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        tongTien, rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));

            }
            //hdct.forEach(c->System.out.println(c.toString()));
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
            e.printStackTrace();
        }
        return hdct;
    }

    public List<HoaDonChiTiet> getSumSoLuong() {
        int check = 0;
        List<HoaDonChiTiet> hdct = new ArrayList<>();
        String sql = null;
        try {
            Connection cn = null;
            PreparedStatement pstm = null;
            cn = DBConnect.getConnection();
//            sql = "SELECT  SUM(hoa_don_chi_tiet.so_luong) AS tổng_số_lượng FROM hoa_don_chi_tiet \n"
//                    + "where hoa_don_chi_tiet.id_spct =? GROUP BY hoa_don_chi_tiet.id_spct ORDER BY tổng_số_lượng DESC;";
            sql = "SELECT TOP 2 hoa_don_chi_tiet.id_spct, SUM(hoa_don_chi_tiet.so_luong) AS tổng_số_lượng FROM hoa_don_chi_tiet \n"
                    + "GROUP BY hoa_don_chi_tiet.id_spct ORDER BY tổng_số_lượng DESC";
            pstm = cn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                //check = result.getInt("tổng_số_lượng");
                hdct.add(new HoaDonChiTiet(spctRepo.getALLSPCTByID(result.getInt("id_spct")).get(0),
                        result.getInt("tổng_số_lượng")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdct;
    }

    public List<HoaDon> getHDByYear(String dateString) {
        //System.out.println("date: "+dateString);
        List<HoaDon> hdct = new ArrayList<>();
        String sql = null;
        try {
            sql = "select * from hoa_don where YEAR(hoa_don.ngay_tao)= '" + dateString + "' AND trang_thai >0";
            //sql = "select * from hoa_don_chi_tiet ORDER BY hoa_don_chi_tiet.id DESC";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                double tongTien = 0;
                for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(rs.getInt("id"))) {
                    tongTien += x.getThanhTien();
                }
                hdct.add(new HoaDon(rs.getInt("id"), new KhachHang(), new KhuyenMai(), new NhanVien(), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        tongTien, rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));

            }
            //hdct.forEach(c->System.out.println(c.toString()));
        } catch (Exception e) {
            System.out.println("Lôi truy " + sql);
            e.printStackTrace();
        }
        return hdct;
    }

}
