/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultilities.DBConnect;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Administrator
 */
public class NhanVienRepository {

    public List<NhanVienViewModel> getAll() {
        List<NhanVienViewModel> ls = new ArrayList<>();
        try ( Connection conn = new DBConnect().getConnection()) {
            String query = "SELECT        dbo.nhan_vien.id, dbo.nhan_vien.id_chuc_vu, dbo.nhan_vien.ma, dbo.nhan_vien.ten"
                    + "                 , dbo.nhan_vien.gioi_tinh"
                    + "                 , dbo.nhan_vien.ngay_sinh, dbo.nhan_vien.sdt, dbo.nhan_vien.mat_khau, dbo.nhan_vien.dia_chi\n"
                    + "                   , dbo.nhan_vien.trang_thai, dbo.nhan_vien.ngay_tao,dbo.nhan_vien.ngay_sua"
                    + "                 , dbo.nhan_vien.tao_boi, dbo.nhan_vien.sua_boi, dbo.nhan_vien.da_xoa, dbo.nhan_vien.email,dbo.nhan_vien.cccd\n"
                    + "FROM            dbo.chuc_vu RIGHT OUTER JOIN\n"
                    + "                         dbo.nhan_vien ON dbo.chuc_vu.id = dbo.nhan_vien.id_chuc_vu"
                    + " order by dbo.nhan_vien.id desc";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17)));
            }

            return ls;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<NhanVien> getAlls() {
        List<NhanVien> ls = new ArrayList<>();
        try ( Connection conn = new DBConnect().getConnection()) {
            String query = "SELECT  * from nhan_vien";
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new NhanVien(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17)));
            }
            return ls;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean addNV(NhanVien nv) {
        int check = 0;
        try ( Connection conn = new DBConnect().getConnection()) {
            String query = "INSERT INTO [dbo].[nhan_vien]\n"
                    + "           ([ma]\n"
                    + "           ,[ten]\n"
                    + "           ,[gioi_tinh]\n"
                    + "           ,[id_chuc_vu]\n"
                    + "           ,[ngay_sinh]\n"
                    + "           ,[sdt]\n"
                    + "           ,[dia_chi]\n"
                    + "           ,[trang_thai]\n"
                    + "           ,[email]\n"
                    + "           ,[cccd])"
                    + "     VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareCall(query);
            ps.setObject(1, nv.getMa());
            ps.setObject(2, nv.getTen());
            ps.setObject(3, nv.isSex());
            ps.setObject(4, nv.getIdUser());
            ps.setObject(5, nv.getNgaySinh());
            ps.setObject(6, nv.getSdt());
            ps.setObject(7, nv.getDiaChi());
            // ps.setObject(7, nv.getPassword());
            ps.setObject(8, nv.getStatus());
            ps.setObject(9, nv.getEmail());
            ps.setObject(10, nv.getCccd());
            check = ps.executeUpdate();
            return check > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return check < 0;
        }

    }

    public boolean chuyenTTNV(String id) {
        String query = "UPDATE [dbo].[NhanVien]\n"
                + "   SET[id_chuc_vu] = ?\n"
                + "      ,[trang_thai] = ?\n"
                + " WHERE id = ?";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, null);
            ps.setObject(2, "1");
            ps.setObject(3, id);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean QuenMK(String pass, String maNV) {
        String query = "UPDATE [dbo].[NhanVien]\n"
                + "   SET[mat_khau] = ?\n"
                + "\n"
                + " WHERE \n"
                + " ma_nhan_vien =?";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {

            ps.setObject(1, pass);
            ps.setObject(2, maNV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateNv(NhanVien sv, String id) {
        String query = "UPDATE [dbo].[nhan_vien]\n"
                + "   SET [ma] = ?\n"
                + "      ,[ten] = ?\n"
                + "       ,[id_chuc_vu]=? \n"
                + "       ,[gioi_tinh]=? \n"
                + "      ,[ngay_sinh] = ?\n"
                + "      ,[sdt] = ?\n"
                + "      ,[dia_chi] = ?\n"
                + "      ,[mat_khau] = ?\n"
                + "      ,[trang_thai] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[cccd] = ?"
                + " WHERE  id = ?";
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {

            ps.setObject(1, sv.getMa());
            ps.setObject(2, sv.getTen());
            ps.setObject(3, sv.getIdUser());
            ps.setObject(4, sv.isSex());
            ps.setObject(5, sv.getNgaySinh());
            ps.setObject(6, sv.getSdt());
            ps.setObject(7, sv.getDiaChi());
            ps.setObject(8, sv.getPassword());
            ps.setObject(9, sv.getStatus());
            ps.setObject(10, sv.getEmail());
            ps.setObject(11, sv.getCccd());
            ps.setObject(12, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<NhanVienViewModel> searchNhanVien(String name) {
        String query = "SELECT        dbo.nhan_vien.id, dbo.nhan_vien.id_chuc_vu, dbo.nhan_vien.ma, dbo.nhan_vien.ten"
                + ",dbo.nhan_vien.gioi_tinh, dbo.nhan_vien.ngay_sinh, dbo.nhan_vien.sdt, dbo.nhan_vien.mat_khau, \n"
                + "                          dbo.nhan_vien.dia_chi, dbo.nhan_vien.trang_thai, dbo.nhan_vien.ngay_tao, dbo.nhan_vien.ngay_sua, dbo.nhan_vien.tao_boi, dbo.nhan_vien.sua_boi, dbo.nhan_vien.da_xoa\n"
                + "FROM            dbo.nhan_vien left JOIN\n"
                + "                         dbo.chuc_vu ON dbo.nhan_vien.id_chuc_vu = dbo.chuc_vu.id where dbo.nhan_vien.ten like ?";
        List<NhanVienViewModel> list = new ArrayList<>();
        String a = "%" + name + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<NhanVienViewModel> searchNhanVienSDT(String sdt) {
        String query = "SELECT        dbo.nhan_vien.id, dbo.nhan_vien.id_chuc_vu, dbo.nhan_vien.ma, dbo.nhan_vien.ten"
                + ",dbo.nhan_vien.gioi_tinh, dbo.nhan_vien.ngay_sinh, dbo.nhan_vien.sdt, dbo.nhan_vien.mat_khau, \n"
                + "                          dbo.nhan_vien.dia_chi, dbo.nhan_vien.trang_thai, dbo.nhan_vien.ngay_tao, dbo.nhan_vien.ngay_sua, dbo.nhan_vien.tao_boi, dbo.nhan_vien.sua_boi, dbo.nhan_vien.da_xoa\n"
                + "FROM            dbo.nhan_vien left JOIN\n"
                + "                         dbo.chuc_vu ON dbo.nhan_vien.id_chuc_vu = dbo.chuc_vu.id where dbo.nhan_vien.sdt like ?";
        List<NhanVienViewModel> list = new ArrayList<>();
        String a = "%" + sdt + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<NhanVienViewModel> searchNhanVienMaNV(String maNV) {
        String query = "SELECT        dbo.nhan_vien.id, dbo.nhan_vien.id_chuc_vu, dbo.nhan_vien.ma, dbo.nhan_vien.ten"
                + ",dbo.nhan_vien.gioi_tinh, dbo.nhan_vien.ngay_sinh, dbo.nhan_vien.sdt, dbo.nhan_vien.mat_khau, \n"
                + "                          dbo.nhan_vien.dia_chi, dbo.nhan_vien.trang_thai, dbo.nhan_vien.ngay_tao, dbo.nhan_vien.ngay_sua, dbo.nhan_vien.tao_boi, dbo.nhan_vien.sua_boi, dbo.nhan_vien.da_xoa\n"
                + "FROM            dbo.nhan_vien left JOIN\n"
                + "                         dbo.chuc_vu ON dbo.nhan_vien.id_chuc_vu = dbo.chuc_vu.id where dbo.nhan_vien.ma like ?";
        List<NhanVienViewModel> list = new ArrayList<>();
        String a = "%" + maNV + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<NhanVienViewModel> searchNhanViencmnd(String cmnd) {
        String query = "SELECT        dbo.NhanVien.id, dbo.NhanVien.ma_nhan_vien, dbo.NhanVien.ten_nhan_vien, dbo.NhanVien.id_chuc_vu, dbo.ChucVu.ten_chuc_vu, dbo.NhanVien.ngay_sinh, dbo.NhanVien.sdt, dbo.NhanVien.email, dbo.NhanVien.dia_chi, \n"
                + "                         dbo.NhanVien.mat_khau, dbo.NhanVien.trang_thai, dbo.NhanVien.cmnd\n"
                + "FROM            dbo.NhanVien left JOIN\n"
                + "                         dbo.ChucVu ON dbo.NhanVien.id_chuc_vu = dbo.ChucVu.id where cmnd like ?";
        List<NhanVienViewModel> list = new ArrayList<>();
        String a = "%" + cmnd + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        List<NhanVienViewModel> l = new NhanVienRepository().searchNhanVienSDT("0");
        for (NhanVienViewModel nhanVien : l) {
            System.out.println(nhanVien.toString());
        }
    }

    public List<ChucVuViewModel> layThongTin(String ma) {
        String query = "SELECT       dbo.NhanVien.ten_nhan_vien, dbo.ChucVu.ten_chuc_vu, dbo.NhanVien.ngay_sinh, dbo.NhanVien.sdt, dbo.NhanVien.email, dbo.NhanVien.dia_chi, \n"
                + "                         dbo.NhanVien.cmnd\n"
                + "FROM            dbo.NhanVien INNER JOIN\n"
                + "                         dbo.ChucVu ON dbo.NhanVien.id_chuc_vu = dbo.ChucVu.id where  ma_nhan_vien = ?";
        List<ChucVuViewModel> list = new ArrayList<>();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChucVuViewModel nv = new ChucVuViewModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));

                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<NhanVienViewModel> getAllNhanVienSearch(int rowOffset) {
        String query = "select NhanVien.id,ma_nhan_vien,ten_nhan_vien,id_chuc_vu,ten_chuc_vu,ngay_sinh,sdt,email,dia_chi,mat_khau,NhanVien.trang_thai  \n"
                + "from NhanVien left join ChucVu on NhanVien.id_chuc_vu = ChucVu.id\n"
                + "order by id\n"
                + "offset ? rows\n"
                + "fetch next 5 rows only";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, rowOffset);
            ResultSet rs = ps.executeQuery();

            List<NhanVienViewModel> list = new ArrayList<>();
            while (rs.next()) {
                NhanVienViewModel kh = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<NhanVienViewModel> searchAll(String ma, String email, String ten, String sdt, String diaChi) {
        String query = "SELECT * FROM [dbo].[nhan_vien] WHERE ma LIKE ? or email LIKE ? or ten LIKE ? or sdt LIKE ?  or dia_chi LIKE ?";
        List<NhanVienViewModel> list = new ArrayList<>();
        String a = "%" + ma + "%";
        String b = "%" + email + "%";
        String c = "%" + ten + "%";
        String d = "%" + sdt + "%";
        String f = "%" + diaChi + "%";
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, a);
            ps.setObject(2, b);
            ps.setObject(3, c);
            ps.setObject(4, d);
            ps.setObject(5, f);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getString(16), rs.getString(17));
                list.add(nv);
            }

        } catch (Exception e) {
        }
        return list;
    }

    String query = "select *  from [dbo].[nhan_vien] ORDER BY ngay_tao desc";

    public List<NhanVienViewModel> exportExcel() {
        return this.selectBySQL(query);
    }

    protected List<NhanVienViewModel> selectBySQL(String sql, Object... args) {
        List<NhanVienViewModel> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.excuteQuery(sql, args);
            while (rs.next()) {
                NhanVienViewModel entity = new NhanVienViewModel();
                entity.setId(rs.getInt("id"));
                entity.setIdUser(rs.getInt("id_chuc_vu"));
                entity.setMa(rs.getString("ma"));
                entity.setEmail(rs.getString("email"));
                entity.setSdt(rs.getString("sdt"));
                entity.setPassword(rs.getString("mat_khau"));
                entity.setCccd(rs.getString("cccd"));
                entity.setSex(rs.getBoolean("gioi_tinh"));
                entity.setTen(rs.getString("ten"));
                entity.setDiaChi(rs.getString("dia_chi"));
                entity.setNgaySinh(rs.getDate("ngay_sinh"));
                entity.setStatus(rs.getInt("trang_thai"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String SELECT_COUNT = "SELECT count(*) FROM [dbo].[nhan_vien]";

    public int select_COUNT() {
        int count = 0;

        try {
            ResultSet rs = JDBCHelper.excuteQuery(SELECT_COUNT);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public List<NhanVienViewModel> selectAll(long trang) {
        return this.selectBySQL("select top 7 * from [dbo].[nhan_vien] where ma not in (select top " + (trang * 7 - 7) + " ma from [dbo].[nhan_vien]  ) ORDER BY id desc");
    }
}
