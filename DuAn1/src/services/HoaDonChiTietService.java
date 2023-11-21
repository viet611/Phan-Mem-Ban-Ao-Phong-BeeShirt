/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonChiTietService {
    List<HoaDonChiTiet> getAllByIdHoaDon(Integer idHoaDon);
}
