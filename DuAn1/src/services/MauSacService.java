/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface MauSacService {
    String getNameByID(int id);
    List<MauSac> getName();
    Integer getIDbyName(String name);
    List<MauSac> getALL();
    void InsertMS(MauSac sp);
    void UpdateMS(MauSac sp);
    void DeleteMS(MauSac sp);
    List<MauSac> Search(String ma);
}
