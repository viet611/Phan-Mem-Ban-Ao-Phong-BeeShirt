/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.MuaPhuHop;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface MuaPhuHopService {
    String getNameByID(int id);
    List<MuaPhuHop> getName();
    Integer getIDbyName(String name);
    List<MuaPhuHop> getALL();
    void InsertMPH(MuaPhuHop sp);
    void UpdateMPH(MuaPhuHop sp);
    void DeleteMPH(MuaPhuHop sp);
    List<MuaPhuHop> Search(String ma);
}
