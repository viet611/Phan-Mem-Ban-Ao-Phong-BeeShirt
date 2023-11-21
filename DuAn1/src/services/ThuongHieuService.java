/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.ThuongHieu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ThuongHieuService {
    String getNameByID(int id);
    List<ThuongHieu> getName();
    Integer getIDbyName(String name);
    List<ThuongHieu> getALL();
    void InsertTH(ThuongHieu sp);
    void UpdateTH(ThuongHieu sp);
    void DeteleTH(ThuongHieu sp);
    List<ThuongHieu> Search(String ma);
}
