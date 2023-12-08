/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.SanPhamCT;
import java.util.List;
import repositories.SanPhamCTResponsitory;
import services.SanPhamCTService;

/**
 *
 * @author Admin
 */
public class SanPhamCTImpl implements SanPhamCTService{
    private SanPhamCTResponsitory sanPhamCTRepo = new SanPhamCTResponsitory();
    @Override
    public List<SanPhamCT> getALL() {
        return sanPhamCTRepo.getALL();
    }

    @Override
    public List<SanPhamCT> filterMauSac(int id_MauSac) {
        return sanPhamCTRepo.filterMauSac(id_MauSac);
    }

    @Override
    public List<SanPhamCT> filterKichThuoc(int id_KichThuoc) {
        return sanPhamCTRepo.FilterKichThuoc(id_KichThuoc);
    }

    @Override
    public List<SanPhamCT> filterKieuDang(int id_KieuDang) {
        return sanPhamCTRepo.filterKieuDang(id_KieuDang);
    }

    @Override
    public List<SanPhamCT> filterChatLieu(int id_ChatLieu) {
        return sanPhamCTRepo.filterChatLieu(id_ChatLieu);
    }

    @Override
    public List<SanPhamCT> filterMPH(int id_MPH) {
        return sanPhamCTRepo.filterMPH(id_MPH);
    }

    @Override
    public List<SanPhamCT> filterThuongHieu(int id_ThuongHieu) {
        return sanPhamCTRepo.filterThuongHieu(id_ThuongHieu);
    }

    @Override
    public void InsertSPCT(SanPhamCT sp) {
        sanPhamCTRepo.InsertSp(sp);
    }

    @Override
    public List<SanPhamCT> getALLSPCTByID(int id) {
        return sanPhamCTRepo.getALLSPCTByID(id);
    }

    @Override
    public void UpdateSPCT(SanPhamCT sp) {
        sanPhamCTRepo.UpdateSpct(sp);
    }

    @Override
    public void DeleteSpct(SanPhamCT sp) {
        sanPhamCTRepo.DeleteSpct(sp);
    }

    @Override
    public List<SanPhamCT> getSPCTbyIDSP(int iDSP) {
        return sanPhamCTRepo.getSPCTbyIDSP(iDSP);
    }

    @Override
    public Integer getCountSP(int IDSP) {
        return sanPhamCTRepo.getCountSP(IDSP);
    }

    @Override
    public List<SanPhamCT> getallWithMaSPCT(String MaSPCT) {
        return sanPhamCTRepo.getallWithMaSPCT(MaSPCT);
    }

    @Override
    public List<SanPhamCT> SearchSPCT(String MaSPCT) {
         return sanPhamCTRepo.search(MaSPCT);
    }

    
    
}
