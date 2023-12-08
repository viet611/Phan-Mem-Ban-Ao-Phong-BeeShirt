/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.SanPhamCT;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface SanPhamCTService {
    List<SanPhamCT> getALL();
    List<SanPhamCT> getALLSPCTByID(int id);
    List<SanPhamCT> filterMauSac(int id_MauSac);
    List<SanPhamCT> filterKichThuoc(int id_KichThuoc);
    List<SanPhamCT> filterKieuDang(int id_KieuDang);
    List<SanPhamCT> filterChatLieu(int id_ChatLieu);
    List<SanPhamCT> filterMPH(int id_MPH);
    List<SanPhamCT> filterThuongHieu(int id_ThuongHieu);
    void InsertSPCT(SanPhamCT sp);
    void UpdateSPCT(SanPhamCT sp);
    void DeleteSpct(SanPhamCT sp);
    List<SanPhamCT>getSPCTbyIDSP(int IDSP);
    Integer getCountSP(int IDSP);
    List<SanPhamCT>getallWithMaSPCT(String MaSPCT);
    List<SanPhamCT>SearchSPCT(String MaSPCT);
}
