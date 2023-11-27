/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.PhuongThucThanhToan;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import ultilities.JDBCHelper;
/**
 *
 * @author Admin
 */
public class PTTTRepository {
    public List<PhuongThucThanhToan> getAll() {
        List<PhuongThucThanhToan> list = new ArrayList<>();
        String sql = "SELECT * FROM phuong_thuc_thanh_toan ORDER BY ngay_tao";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new PhuongThucThanhToan(
                        rs.getInt("id"),
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getString("mo_ta"),
                        rs.getInt("trang_thai"),
                        rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"), rs.getString("sua_boi"),
                        rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }
    public PhuongThucThanhToan getByID(Integer id) {
        PhuongThucThanhToan pttt = null;
        String sql = "SELECT * FROM phuong_thuc_thanh_toan WHERE id = ? ORDER BY ngay_tao";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql,id);
        try {
            while (rs.next()) {
                pttt = new PhuongThucThanhToan(
                        rs.getInt("id"),
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getString("mo_ta"),
                        rs.getInt("trang_thai"),
                        rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"), rs.getString("sua_boi"),
                        rs.getBoolean("da_xoa"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return pttt;
    }
}
