/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.MucDichSuDung;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface MDSDService {
    String getNameByID(int id);
    List<MucDichSuDung> getAll();
    Integer getIDbyName(String name);
    List<MucDichSuDung> getAllMDSD();
    void InsertMDSD(MucDichSuDung sp);
    void UpdateMDSD(MucDichSuDung sp);
    void DeleteMDSD(MucDichSuDung sp);
    List<MucDichSuDung> Search(String ma);
}
