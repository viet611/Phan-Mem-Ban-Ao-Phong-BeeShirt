/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface SanPhamService {
    List<SanPham> getAll();
    String getNameByIDSP(int id);
    List<SanPham> getName();
    void InsertSP(SanPham sp);
    void UpdateSP(SanPham sp);
    List<SanPham> Search(String ma);
    Integer getIDbyName(String name);
    String getNameID(int id);
    void DeleteSp(SanPham sp);
}
