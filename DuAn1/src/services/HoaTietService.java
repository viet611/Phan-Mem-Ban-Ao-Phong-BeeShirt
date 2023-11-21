/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.HoaTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HoaTietService {
    String getNameByID(int id);
    List<HoaTiet> getName();
    Integer getIDbyName(String name);
    List<HoaTiet> getAll();
    void InsertHT(HoaTiet sp);
    void UpdateHT(HoaTiet sp);
    void DeleteHT(HoaTiet sp);
    List<HoaTiet> Search(String ma);
}
