/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.NhanVien;
//import domainmodels.SanPhamChiTiet;
import domainmodels.Voucher;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository {
    public static void main(String[] args) {
        
    }
private final SanPhamCTResponsitory spctRepo = new SanPhamCTResponsitory();
    
    public List<HoaDonChiTiet> getAllByIdHoaDon(Integer id) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don_chi_tiet WHERE id_hoa_don = ? ";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql,id);
        try {
            while (rs.next()) {
                list.add(new HoaDonChiTiet(rs.getInt("id"),new HoaDon(),spctRepo.getALLSPCTByID(rs.getInt("id_spct")).get(0)
                        , rs.getInt("so_luong"), rs.getDouble("thanh_tien"),
                        rs.getDouble("gia_tien"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"),rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }
}
