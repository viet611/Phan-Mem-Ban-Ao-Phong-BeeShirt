/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDonTimeLine;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonTimeLineService {
    List<HoaDonTimeLine> getAll();
    List<HoaDonTimeLine> getByIDHoaDon(Integer id);
    Boolean insert(HoaDonTimeLine hoaDonTimeLine);
}
