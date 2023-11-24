/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ChatLieu;
import java.util.List;
import repositories.ChatLieuResponsitory;
import services.ChatLieuService;

/**
 *
 * @author Admin
 */
public class ChatLieuImpl implements ChatLieuService{
    ChatLieuResponsitory clRepo = new ChatLieuResponsitory();
    @Override
    public String getNameByID(int id) {
        return clRepo.getNameByID(id);
    }

    @Override
    public List<ChatLieu> getName() {
        return clRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return clRepo.getIDbyName(name);
    }

    @Override
    public List<ChatLieu> getALL() {
        return clRepo.getALLCL();
    }

    @Override
    public void InsertCL(ChatLieu cl) {
        clRepo.InsertCL(cl);
    }

    @Override
    public void UpdateCL(ChatLieu cl) {
       clRepo.UpdateCL(cl);
    }

    @Override
    public void DeleteCl(ChatLieu cl) {
        clRepo.DeleteCL(cl);
    }

    @Override
    public List<ChatLieu> Search(String ma) {
       return clRepo.search(ma);
    }
    
}
