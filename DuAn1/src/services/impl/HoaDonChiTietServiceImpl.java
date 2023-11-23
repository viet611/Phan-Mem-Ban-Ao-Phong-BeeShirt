/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDonChiTiet;
import java.util.List;
import repositories.HoaDonChiTietRepository;
import services.HoaDonChiTietService;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService{

    private final HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    
    @Override
    public List<HoaDonChiTiet> getAllByIdHoaDon(Integer idHoaDon) {
        return hoaDonChiTietRepository.getAllByIdHoaDon(idHoaDon);
    }
    
}
