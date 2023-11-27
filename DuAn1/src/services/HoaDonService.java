/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDon;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaDonService {
    List<HoaDon> getAll();
    List<HoaDon> search(String key);
    Boolean update(HoaDon hd);
    Boolean insert(HoaDon hd);
    Boolean updateFull(HoaDon hd);
}
