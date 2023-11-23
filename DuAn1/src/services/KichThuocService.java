/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface KichThuocService {
    String getNameByID(int id);
    List<KichThuoc> getName();
    Integer getIDbyName(String name);
    List<KichThuoc> getALL();
    void InsertKT(KichThuoc sp);
    void UpdateKT(KichThuoc sp);
    void DeleteKT(KichThuoc sp);
    List<KichThuoc> Search(String ma);
}
