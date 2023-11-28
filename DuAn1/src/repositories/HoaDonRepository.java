/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.HoaDonTimeLine;
import domainmodels.KhachHang;
import domainmodels.NhanVien;
import domainmodels.KhuyenMai;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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

    private final KhachHangRepository khachHangRepository = new KhachHangRepository();
    private final KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    private final NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private final HoaDonTimeLineRepository hdtlRepository = new HoaDonTimeLineRepository();

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don ORDER BY ngay_tao DESC ";
        ResultSet rs;
        String sqlKH = "SELECT * from [dbo].[khach_hang]  where id = ?";
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                double tongTien = 0;
                for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(rs.getInt("id"))) {
                    tongTien += x.getThanhTien();
                }
                list.add(new HoaDon(
                        rs.getInt("id"),
                        rs.getInt("id_khach_hang") != 0 ? khachHangRepository.selectByID(sqlKH, rs.getInt("id_khach_hang")).get(0) : null,
                        rs.getInt("id_voucher") != 0 ? khuyenMaiRepository.getByID(rs.getInt("id_voucher")) : null,
                        nhanVienRepository.getById(rs.getInt("id_nhan_vien")),
                        rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        tongTien, rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }

    public HoaDon getByIDHD(Integer id) {
        HoaDon hd = null;
        String sql = "SELECT * FROM hoa_don WHERE id = ? ORDER BY ngay_tao DESC ";
        ResultSet rs;
        String sqlKH = "SELECT * from [dbo].[khach_hang]  where id = ?";
        rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                hd = new HoaDon(
                        rs.getInt("id"),
                        rs.getInt("id_khach_hang") != 0 ? khachHangRepository.selectByID(sqlKH, rs.getInt("id_khach_hang")).get(0) : null,
                        rs.getInt("id_voucher") != 0 ? khuyenMaiRepository.getByID(rs.getInt("id_voucher")) : null,
                        nhanVienRepository.getById(rs.getInt("id_nhan_vien")),
                        rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        rs.getDouble("tong_tien"), rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return hd;
    }

    public Boolean insert(HoaDon hoaDon) {
        Integer row = -1;
        try {
            String sql = "insert into hoa_don "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            row = JDBCHelper.excuteUpdate(sql,
                    null,
                    null,
                    hoaDon.getNhanVien().getId(),
                    hoaDon.getMa(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    0,
                    new Date(),
                    new Date(),
                    hoaDon.getTaoBoi(),
                    null,
                    0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        hdtlRepository.insert(new HoaDonTimeLine(null, hoaDon, 0, new Date(), new Date(), hoaDon.getTaoBoi(), hoaDon.getSuaBoi(), Boolean.FALSE));
        return true;
    }

    public Boolean updateFull(HoaDon hoaDon) {
        Integer row = -1;
        try {
            String sql = "UPDATE hoa_don "
                    + "SET id_khach_hang = ?, id_voucher = ?, id_nhan_vien = ?, "
                    + "so_tien_giam = ?, tong_tien = ?, tien_ship = ?, "
                    + " ten_khach_hang = ? , so_dien_thoai = ? , dia_chi = ? , trang_thai = ? , ngay_sua = ? , sua_boi = ? , da_xoa = ? "
                    + "WHERE id = ?";
            double tongTien = 0;
            for (HoaDonChiTiet x : new HoaDonChiTietRepository().getAllByIdHoaDon(hoaDon.getId())) {
                tongTien += x.getThanhTien();
            }
            hoaDon.setTongTien(tongTien);
            row = JDBCHelper.excuteUpdate(sql,
                    hoaDon.getKhachHang() != null ? hoaDon.getKhachHang().getId() : null,
                    hoaDon.getVoucher() != null ? hoaDon.getVoucher().getId() : null,
                    hoaDon.getNhanVien().getId(),
                    hoaDon.getSoTienGiam(),
                    hoaDon.getTongTien(),
                    hoaDon.getTienShip(),
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

    public Boolean update(HoaDon hoaDon) {
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
        key = '%' + key + '%';
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don "
                + "WHERE ma LIKE ? "
                + "OR ten_khach_hang LIKE ? "
                + "OR so_dien_thoai LIKE ? "
                + "OR dia_chi LIKE ? "
                + "ORDER BY ngay_tao DESC";
        ResultSet rs;
        String sqlKH = "SELECT * from [dbo].[khach_hang]  where id = ?";
        rs = JDBCHelper.excuteQuery(sql, key, key, key, key);
        try {
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt("id"), 
                        rs.getInt("id_khach_hang") != 0 ? khachHangRepository.selectByID(sqlKH, rs.getInt("id_khach_hang")).get(0) : null,
                        rs.getInt("id_voucher") != 0 ? khuyenMaiRepository.getByID(rs.getInt("id_voucher")) : null,
                        nhanVienRepository.getById(rs.getInt("id_nhan_vien")), rs.getString("ma"), rs.getDouble("so_tien_giam"),
                        rs.getDouble("tong_tien"), rs.getDouble("tien_ship"), rs.getString("ten_khach_hang"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }

}
