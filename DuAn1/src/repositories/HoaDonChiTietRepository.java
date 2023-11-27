/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import ultilities.JDBCHelper;
import domainmodels.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository {

    public static void main(String[] args) {

    }
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private SanPhamCTResponsitory spctR = new SanPhamCTResponsitory();

    public List<HoaDonChiTiet> getAllByIdHoaDon(Integer id) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don_chi_tiet WHERE id_hoa_don = ? ";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                list.add(new HoaDonChiTiet(rs.getInt("id"),
                        hoaDonRepository.getByIDHD(id),
                        spctR.getALLSPCTByID(rs.getInt("id_spct")).get(0),
                        rs.getInt("so_luong"), rs.getDouble("thanh_tien"),
                        rs.getDouble("gia_tien"), rs.getInt("trang_thai"), rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"),
                        rs.getString("sua_boi"), rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }

    public Boolean add(HoaDonChiTiet hdct) {
        try {
            String sql = "Insert into hoa_don_chi_tiet values (?,?,?,?,?,?,?,?,?,?,?)";
            int row = JDBCHelper.excuteUpdate(sql, hdct.getHoaDon().getId(), hdct.getSanPhamChiTiet().getId(), hdct.getSoLuong(), hdct.getThanhTien(),
                    hdct.getGiaTien(), 0, new Date(), new Date(), hdct.getTaoBoi(), hdct.getSuaBoi(), false);
        } catch (Exception x) {
            x.printStackTrace();
            return false;
        }
        return true;
    }
    public Boolean update(HoaDonChiTiet hdct) {
        try {
            String sql = "UPDATE hoa_don_chi_tiet "
                    + "SET so_luong = ? , thanh_tien = ?, gia_tien = ?, ngay_sua = ?, sua_boi = ? "
                    + "WHERE id = ?";
            int row = JDBCHelper.excuteUpdate(sql, hdct.getSoLuong(), hdct.getThanhTien(),
                    hdct.getGiaTien(), new Date(), hdct.getSuaBoi(),hdct.getId());
        } catch (Exception x) {
            x.printStackTrace();
            return false;
        }
        return true;
    }
    public Boolean delete(Integer id){
        try{
            String sql = "delete from hoa_don_chi_tiet where id = ?";
            int row = JDBCHelper.excuteUpdate(sql, id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
