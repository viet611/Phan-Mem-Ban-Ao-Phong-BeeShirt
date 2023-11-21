/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.KieuDang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface KieuDangService {
    String getNameByID(int id);
    List<KieuDang> getName();
    Integer getIDbyName(String name);
    List<KieuDang> getAll();
    void InsertSP(KieuDang sp);
    void UpdateSP(KieuDang sp);
    void DeleteKD(KieuDang sp);
    List<KieuDang> Search(String ma);
}
