package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import domainmodels.KhuyenMai;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultilities.DBConnect;

public class KhuyenMaiRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhuyenMai> getAll() {
        sql = "SELECT ma, ten, ngay_bat_dau, ngay_ket_thuc, so_tien_giam, so_tien_toi_thieu, trang_thai FROM voucher";
        List<KhuyenMai> listVC = new ArrayList();

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai vc = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
                listVC.add(vc);
            }
            return listVC;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMai> getAllvali(String ma) {
        sql = "SELECT ma, ten, ngay_bat_dau, ngay_ket_thuc, so_tien_giam, so_tien_toi_thieu, trang_thai FROM voucher WHERE ma = ?";
        List<KhuyenMai> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
                list.add(km);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean add(KhuyenMai km) {
        sql = "INSERT INTO voucher (ma,ten,ngay_bat_dau,ngay_ket_thuc,so_tien_giam,so_tien_toi_thieu,trang_thai) VALUES (?,?,?,?,?,?,?)";

        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMa());
            ps.setObject(2, km.getTen());
            ps.setObject(3, km.getNgayBD());
            ps.setObject(4, km.getNgayKT());
            ps.setObject(5, km.getTienGiam());
            ps.setObject(6, km.getTienTT());
            ps.setObject(7, km.getTrangThai());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(String ma) {
        sql = "DELETE FROM VOUCHER WHERE ma = ?";

        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KhuyenMai km, String ma) {
        sql = "UPDATE voucher SET ten = ?, ngay_bat_dau = ?, ngay_ket_thuc = ?, so_tien_giam = ?, so_tien_toi_thieu = ?, trang_thai = ? WHERE ma = ?";

        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTen());
            ps.setObject(2, km.getNgayBD());
            ps.setObject(3, km.getNgayKT());
            ps.setObject(4, km.getTienGiam());
            ps.setObject(5, km.getTienTT());
            ps.setObject(6, km.getTrangThai());
            ps.setObject(7, ma);

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public void upDateTrangThai() {
        sql = "UPDATE voucher SET trang_thai = 0 where ngay_bat_dau <=  GETDATE() and ngay_ket_thuc >= GETDATE() \n"
                + "UPDATE voucher SET trang_thai= 1  where ngay_bat_dau > GETDATE() \n"
                + "UPDATE voucher SET trang_thai= 2  where  ngay_ket_thuc < GETDATE()  ";
        int check = 0;

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public KhuyenMai getByID(Integer id) {
        sql = "SELECT ma, ten, ngay_bat_dau, ngay_ket_thuc, so_tien_giam, so_tien_toi_thieu, trang_thai FROM voucher WHERE id = ?";
        KhuyenMai khuyenMai = new KhuyenMai();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                khuyenMai = new KhuyenMai(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return khuyenMai;
    }

}
