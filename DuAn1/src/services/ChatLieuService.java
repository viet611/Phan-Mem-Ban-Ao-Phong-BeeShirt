/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.ChatLieu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChatLieuService {
    String getNameByID(int id);
    List<ChatLieu> getName();
    Integer getIDbyName(String name);
    List<ChatLieu> getALL();
    void InsertCL(ChatLieu cl);
    void UpdateCL(ChatLieu cl);
    void DeleteCl(ChatLieu cl);
    List<ChatLieu> Search(String ma);
}
