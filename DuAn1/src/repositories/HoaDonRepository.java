/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.HoaDon;
import domainmodels.HoaDonTimeLine;
import domainmodels.KhachHang;
import domainmodels.NhanVien;
import domainmodels.Voucher;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {
    public static void main(String[] args) {
        HoaDonTimeLineRepository abc = new HoaDonTimeLineRepository();
        abc.selectByIDHoaDon(1).forEach(n -> System.out.println(n));
    }

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don ORDER BY ngay_tao DESC ";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt("id"),new KhachHang(),new Voucher(),new NhanVien(), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        rs.getDouble("tong_tien"),rs.getDouble("tien_ship"),rs.getString("ten_khach_hang"),rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"),rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }
    public Boolean update(HoaDon hoaDon){
        Integer row = -1;
        try {
            String sql = "UPDATE hoa_don "
                    + "SET ten_khach_hang = ? , so_dien_thoai = ? , dia_chi = ? , trang_thai = ? , ngay_sua = ? , sua_boi = ? , da_xoa = ? "
                    + "WHERE id = ?";
            row = JDBCHelper.excuteUpdate(sql,
                    hoaDon.getTenKhachHang(),
                    hoaDon.getSdt(),
                    hoaDon.getDiaChi(),
                    hoaDon.getTrangThai(),
                    hoaDon.getNgaySua(),
                    hoaDon.getSuaBoi(),
                    hoaDon.getDaXoa(),
                    hoaDon.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<HoaDon> search(String key) {
        key = '%'+key+'%';
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don "
                + "WHERE ma LIKE ? "
                + "OR ten_khach_hang LIKE ? "
                + "OR so_dien_thoai LIKE ? "
                + "OR dia_chi LIKE ? "
                + "ORDER BY ngay_tao DESC";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql,key,key,key,key);
        try {
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt("id"),new KhachHang(),new Voucher(),new NhanVien(), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        rs.getDouble("tong_tien"),rs.getDouble("tien_ship"),rs.getString("ten_khach_hang"),rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"),rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }

    
}
