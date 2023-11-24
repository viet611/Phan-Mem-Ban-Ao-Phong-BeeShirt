/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.ChucVu;
import domainmodels.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultilities.DBConnect;
import viewmodel.ChucVuViewModel;

/**
 *
 * @author Administrator
 */
public class ChucVuRepository {

    public List<ChucVu> getAll() {
        List<ChucVu> ls = new ArrayList<>();
        try ( Connection conn = new DBConnect().getConnection()) {
            String query = "SELECT * FROM [dbo].[chuc_vu]";

            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ls.add(new ChucVu(rs.getInt(1), rs.getString(2), rs.getString(3),
                         rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
            }
            return ls;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<ChucVuViewModel> getAllCV() {

        String query = "SELECT [id]\n"
                + "      ,[ma]\n"
                + "      ,[ten]\n"
                + "  FROM [dbo].[chuc_vu]";
        List<ChucVuViewModel> cv = new ArrayList<>();
        try ( Connection conn = new DBConnect().getConnection()) {
            PreparedStatement ps = conn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVuViewModel chucVu = new ChucVuViewModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                         rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
                cv.add(chucVu);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cv;

    }

    public List<NhanVien> getCV(String ma) {

        String query = "SELECT ma, ten\n"
                + "     FROM nhan_vien join chuc_vu on nhan_vien.id_chuc_vu = chuc_vu.id\n"
                + "     where ma =?";
        List<NhanVien> nhanVien = new ArrayList<>();
        try ( Connection conn = new DBConnect().getConnection()) {

            PreparedStatement ps = conn.prepareCall(query);
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4), rs.getBoolean(5)
                ,rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)
                , rs.getDate(11), rs.getDate(12), rs.getString(13), rs.getString(14),rs.getBoolean(15),rs.getString(16),rs.getString(17));
                nhanVien.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return nhanVien;

    }

    public boolean add(ChucVuViewModel cv) {
        String query = "INSERT INTO [dbo].[chuc_vu]\n"
                + "           ([ma]\n"
                + "           ,[ten])\n"
                + "     VALUES\n"
                + "           (?,?)";
        int check = 0;
        try ( Connection conn = new DBConnect().getConnection();  PreparedStatement ps = conn.prepareCall(query);) {
            ps.setObject(1, cv.getMa());
            ps.setObject(2, cv.getTen());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ChucVuViewModel getOne(String ma) {
        String query = "SELECT [id]\n"
                + "      ,[ma]\n"
                + "      ,[ten]\n"
                + "  FROM [dbo].[chuc_vu]"
                + "where ma =?";

        try ( Connection conn = new DBConnect().getConnection()) {

            PreparedStatement ps = conn.prepareCall(query);
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new ChucVuViewModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                         rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
