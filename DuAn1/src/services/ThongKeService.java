/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ThongKeService {
    List<HoaDonChiTiet> getALLHDCT();
    List<HoaDon> getALLHDCT1(String date);
    List<HoaDon> getHDByMonth(String date);
    List<HoaDon> getHDByYear(String date);
    List<HoaDonChiTiet> getSumSoLuong( );
}
