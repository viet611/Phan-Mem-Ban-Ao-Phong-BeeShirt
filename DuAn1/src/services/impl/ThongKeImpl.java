/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import java.util.Date;
import java.util.List;
import repositories.ThongKeRpository;
import services.ThongKeService;

/**
 *
 * @author Admin
 */
public class ThongKeImpl implements ThongKeService{
    ThongKeRpository thongKeRepo = new ThongKeRpository();
    @Override
    public List<HoaDonChiTiet> getALLHDCT() {
        return thongKeRepo.getAllHD();
        
    }

    @Override
    public List<HoaDon> getALLHDCT1(String date) {
        return thongKeRepo.getAllHD1(date);
    }

    @Override
    public List<HoaDon> getHDByMonth(String month) {
        return thongKeRepo.getHDByMonth(month);
    }

    @Override
    public List<HoaDonChiTiet> getSumSoLuong( ) {
        return thongKeRepo.getSumSoLuong();
    }

    @Override
    public List<HoaDon> getHDByYear(String date) {
        return thongKeRepo.getHDByYear(date);
    }
    
}
