/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.HoaDonChiTiet;
import domainmodels.PTTTChiTiet;
import domainmodels.PhuongThucThanhToan;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ultilities.JDBCHelper;

/**
 *
 * @author Admin
 */
public class PTTTChiTietRepository {
    HoaDonRepository hdr = new HoaDonRepository();
    PTTTRepository pTTTRepository = new PTTTRepository();
    public List<PTTTChiTiet> getAllByIDHoaDon(Integer id) {
        List<PTTTChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM pttt_chi_tiet WHERE id_hoa_don = ? ORDER BY ngay_tao";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql,id);
        try {
            while (rs.next()) {
                list.add(new PTTTChiTiet(rs.getInt("id"),
                        hdr.getByIDHD(id),
                        pTTTRepository.getByID(rs.getInt("id_pttt")),
                        rs.getDouble("so_tien"),
                        rs.getInt("trang_thai"),
                        rs.getString("ma_giao_dich"),
                        rs.getDate("ngay_tao"), rs.getDate("ngay_sua"), rs.getString("tao_boi"), rs.getString("sua_boi"),
                        rs.getBoolean("da_xoa")));
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn: " + sql);
            e.printStackTrace();
        }
        return list;
    }
    public Boolean add(PTTTChiTiet pTTTChiTiet) {
        try {
            String sql = "Insert into pttt_chi_tiet values (?,?,?,?,?,?,?,?,?,?)";
            int row = JDBCHelper.excuteUpdate(sql, pTTTChiTiet.getHoaDon().getId(), pTTTChiTiet.getPhuongThucThanhToan().getId(),pTTTChiTiet.getSoTien(),
                     0, pTTTChiTiet.getMaGD(), new Date(), new Date(), pTTTChiTiet.getTaoBoi(), pTTTChiTiet.getSuaBoi(), false);
        } catch (Exception x) {
            x.printStackTrace();
            return false;
        }
        return true;
    }
    
}
