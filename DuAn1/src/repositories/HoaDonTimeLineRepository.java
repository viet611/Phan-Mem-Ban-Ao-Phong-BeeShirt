/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import Ultilities.JDBCHelper;
import domainmodels.HoaDon;
import domainmodels.HoaDonTimeLine;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonTimeLineRepository {

    public List<HoaDonTimeLine> getAll() {
        List<HoaDonTimeLine> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don_timeline ORDER BY id_hoa_don";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new HoaDonTimeLine(rs.getInt("id"), new HoaDon(), rs.getInt("trang_thai"),
                        rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"), rs.getString("sua_boi"),
                        rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }

    public Boolean insert(HoaDonTimeLine hdtl) {
        try {
            String sql = "INSERT INTO hoa_don_timeline values(?,?,?,?,?,?,?)";
            JDBCHelper.excuteUpdate(sql, hdtl.getHoaDon(), hdtl.getTrangThai(), hdtl.getNgayTao(), hdtl.getNgaySua(), hdtl.getTaoBoi(), hdtl.getDaXoa());
        } catch (Exception e) {
            System.out.println("Loi truy van");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HoaDonTimeLineRepository abc = new HoaDonTimeLineRepository();
        abc.selectByIDHoaDon(1).forEach(n -> System.out.println(n));
    }

    public List<HoaDonTimeLine> selectByIDHoaDon(Integer idHoaDon) {
        List<HoaDonTimeLine> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoa_don_timeline"
                    + " WHERE id_hoa_don = ?"
                    + " ORDER BY trang_thai";
            ResultSet rs = JDBCHelper.excuteQuery(sql, idHoaDon);
            while (rs.next()) {
                list.add(new HoaDonTimeLine(
                        rs.getInt("id"),
                        new HoaDon(),
                        rs.getInt("trang_thai"),
                        rs.getDate("ngay_tao"),
                        rs.getDate("ngay_sua"),
                        rs.getString("tao_boi"),
                        rs.getString("sua_boi"),
                        rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
