/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.KhachHang;
import java.sql.Connection;
import ultilities.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viewmodel.KhachHangViewModel;

/**
 *
 * @author Administrator
 */
public class KhachHangRepository {

    public List<KhachHang> getAllKhachHang1() {
        String query = "SELECT [id]\n"
                + "      ,[id_hang_thanh_vien]\n"
                + "      ,[ma]\n"
                + "      ,[ten]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[sdt]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[[ngay_sua]\n"
                + "      ,[tao_boi]\n"
                + "      ,[sua_boi]\n"
                + "      ,[da_xoa]\n"
                + "  FROM [dbo].[khach_hang]\n";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            List<KhachHang> list = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<KhachHang> getAllKhachHang(int rowOffset) {
        String query = "SELECT [id]\n"
                + "      ,[id_hang_thanh_vien]\n"
                + "      ,[ma]\n"
                + "      ,[ten]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[sdt]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[[ngay_sua]\n"
                + "      ,[tao_boi]\n"
                + "      ,[sua_boi]\n"
                + "      ,[da_xoa]\n"
                + "  FROM [dbo].[khach_hang]\n"
                + "  order by id\n"
                + "  offset ? rows\n"
                + "  fetch next 5 rows only  ";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            List<KhachHang> list = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
        }
        return null;

    }

    public List<KhachHang> searchKhachHang(String id, int rowOffset) {
        String query = "SELECT\n"
                + "      id,[ten]\n"
                + "      ,[id_hang_thanh_vien]\n"
                + "      ,[ma]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[sdt]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[[ngay_sua]\n"
                + "      ,[tao_boi]\n"
                + "      ,[sua_boi]\n"
                + "      ,[da_xoa]\n"
                + "  FROM [dbo].[khach_hang]\n"
                + "     WHERE ten like ?"
                + "  order by id\n"
                + "  offset ? rows\n"
                + "  fetch next 5 rows only  ";
        List<KhachHang> list = new ArrayList<>();
        String a = "%" + id + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ps.setObject(2, rowOffset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(kh);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Boolean addKhachHang(KhachHang kh) {
        String query = "INSERT INTO [dbo].[khach_hang]\n"
                + "      ([ten]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[sdt]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai])\n"
                + "       VALUES \n"
                + " (?,?,?,?,?,?)";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            // ps.setObject(1, kh.getMa());
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.isSex());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getDiaChi());
            ps.setObject(6, kh.getStatus());
            check = ps.executeUpdate();
            return check > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return check < 0;
        }

    }

    public Boolean editKhachHang(KhachHang kh, String id) {
        String query = "UPDATE [dbo].[khach_hang]\n"
                + " SET [ten] = ?\n"
                + "      ,[ma] = ?\n"
                + "      ,[gioi_tinh]= ?\n"
                + "      ,[ngay_sinh]= ?\n"
                + "      ,[sdt]= ?\n"
                + "      ,[dia_chi]= ?\n"
                + "      ,[trang_thai]= ?\n"
                + "WHERE id = ?";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getMa());
            ps.setObject(3, kh.isSex());
            ps.setObject(4, kh.getNgaySinh());
            ps.setObject(5, kh.getSdt());
            ps.setObject(6, kh.getDiaChi());
            ps.setObject(7, kh.getStatus());
            ps.setObject(8, id);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public boolean deleteKhachHang(String id) {
        String query = "DELETE FROM [dbo].[khach_hang]\n"
                + "      WHERE id = ?";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<KhachHang> searchKhachSDT(String sdt, int rowOffset) {
        String query = "SELECT\n"
                + "      id,[ten]\n"
                + "      ,[id_hang_thanh_vien]\n"
                + "      ,[ma]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[sdt]\n"
                + "      ,[dia_chi]\n"
                + "      ,[trang_thai]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[[ngay_sua]\n"
                + "      ,[tao_boi]\n"
                + "      ,[sua_boi]\n"
                + "      ,[da_xoa]\n"
                + "  FROM [dbo].[khach_hang]\n"
                + "     WHERE sdt like ?"
                + "  order by id\n"
                + "  offset ? rows\n"
                + "  fetch next 5 rows only  ";
        List<KhachHang> list = new ArrayList<>();
        String a = "%" + sdt + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ps.setObject(2, rowOffset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(kh);
            }

        } catch (Exception e) {
        }
        return list;
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ///////////////////////////////////////
    public List<KhachHang> getAllKHModels() {
        List<KhachHang> kh = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[khach_hang]"
                + "order by id desc";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                kh.add(new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    public void insert(KhachHang kh) {
        try {
            String sql = "insert into [dbo].[khach_hang] (ma, ten, gioi_tinh, ngay_sinh, sdt, dia_chi, trang_thai )\n"
                    + "                    values (?,?,?,?,?,?,?)";
            JDBCHelper.excuteUpdate(sql,
                    kh.getMa(), kh.getTen(), kh.isSex(), kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(), kh.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi truy vấn:");
        }
    }

    public void delete(Integer id) {
        try {
            String hql = "Delete from [dbo].[khach_hang] where id=?";
            JDBCHelper.excuteUpdate(hql, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi");
        }
    }

    public void update(KhachHang kh) {
        try {
            String sql = "UPDATE [dbo].[khach_hang] SET ma = ?, ten = ?, gioi_tinh = ?, ngay_sinh = ?, sdt = ?, dia_chi = ?, trang_thai = ?, ngay_tao = ?, ngay_sua = ?, tao_boi = ?, sua_boi = ?, da_xoa = ?  WHERE id = ?";
            JDBCHelper.excuteUpdate(sql, kh.getMa(), kh.getTen(), kh.isSex(), kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(), kh.getStatus(), kh.getNgayTao(), kh.getNgaySua(), kh.getNguoiTao(), kh.getNguoiSua(), kh.isDaXoa(), kh.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi");
        }
    }

    protected List<KhachHang> selectByID(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.excuteQuery(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("id"));
                kh.setIdCustomerRanking(rs.getInt("id_hang_thanh_vien"));
                kh.setMa(rs.getString("sdt"));
                kh.setTen(rs.getString("ten"));
                kh.setSex(rs.getBoolean("gioi_tinh"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setStatus(rs.getInt("trang_thai"));
                kh.setNgayTao(rs.getDate("ngay_tao"));
                kh.setNgaySua(rs.getDate("ngay_sua"));
                kh.setNguoiTao(rs.getString("tao_boi"));
                kh.setNguoiSua(rs.getString("sua_boi"));
                kh.setDaXoa(rs.getBoolean("da_xoa"));

                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHang> timKiem(String key) {
        String sql = "SELECT * from [dbo].[khach_hang]  where phoneNumber like ? ";
        return selectByID(sql, "%" + key + "%");
    }

    public List<KhachHang> getAfterDelete() {
        String sql = "SELECT * FROM [dbo].[khach_hang]"
                + "order by id desc";
        List<KhachHang> list = new ArrayList<>();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang km = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public List<KhachHang> searchAll(String ten, String sdt, String diaChi) {
        String sql = "SELECT * from [dbo].[khach_hang] where ten like ? or sdt like ? or dia_chi like ?";
        List<KhachHang> list = new ArrayList<>();
        String a = "%" + ten + "%";
        String b = "%" + sdt + "%";
        String c = "%" + diaChi + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, a);
            ps.setObject(2, b);
            ps.setObject(3, c);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang km = new KhachHang(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    String query = "select *  from [dbo].[khach_hang] ORDER BY id desc";

    public List<KhachHang> exportExcel() {
        return this.selectBySQL(query);
    }

    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.excuteQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setId(rs.getInt("id"));
                entity.setMa(rs.getString("ma"));
                entity.setTen(rs.getString("ten"));
                entity.setSdt(rs.getString("sdt"));
                entity.setSex(rs.getBoolean("gioi_tinh"));
                entity.setDiaChi(rs.getString("dia_chi"));
                entity.setNgaySinh(rs.getDate("ngay_sinh"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    ///////////////////////////////////////////////
    private String qry = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<Object> listLSGDByMaKH(String maKh) {
        try {
            qry = "SELECT HD.id AS ID , HD.ma AS MaHoaDon , hd.ngay_tao AS NgayThanhToan , \n"
                    + "                  	HD.tong_tien AS ThanhTien , HD.trang_thai AS TrangThai from hoa_don as HD\n"
                    + "                    	JOIN khach_hang ON khach_hang.id = HD.id_khach_hang\n"
                    + "                    	WHERE khach_hang.ma = '" + maKh + "'";
            con = DBConnect.getConnection();
            pstm = con.prepareStatement(qry);
            rs = pstm.executeQuery();
            List<Object> listLSGD = new ArrayList<>();
            int i = 1;
            DecimalFormat decimalFormat = new DecimalFormat("#,##");
            while (rs.next()) {
                
                Object[] lSGD = new Object[]{
                    i,
                    rs.getString("MaHoaDon"),
                    rs.getDate("NgayThanhToan"),
                    decimalFormat.format(rs.getFloat("ThanhTien")) + " VNĐ",
                    rs.getInt("TrangThai") == 1 ? "Đã thanh toán" : "Đang cập nhập"
                };
                listLSGD.add(lSGD);
                i++;
            }
            return listLSGD;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {

                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stm != null && !stm.isClosed()) {
                    stm.close();
                }
                if (pstm != null && !pstm.isClosed()) {
                    pstm.close();
                }
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
