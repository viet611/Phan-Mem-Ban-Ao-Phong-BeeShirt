/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.MauSac;
import java.util.List;
import repositories.MauSacResponsitory;
import services.MauSacService;

/**
 *
 * @author Admin
 */
public class MauSacImpl implements MauSacService{
    MauSacResponsitory msRepo = new MauSacResponsitory();
    @Override
    public String getNameByID(int id) {
        return msRepo.getNameByID(id);
    }

    @Override
    public List<MauSac> getName() {
        return msRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return msRepo.getIDbyName(name);
    }

    @Override
    public List<MauSac> getALL() {
        return msRepo.getALLMS();
    }

    @Override
    public void InsertMS(MauSac sp) {
        msRepo.InsertMS(sp);
    }

    @Override
    public void UpdateMS(MauSac sp) {
       msRepo.UpdateMS(sp);
    }

    @Override
    public void DeleteMS(MauSac sp) {
        msRepo.DeleteMS(sp);
    }

    @Override
    public List<MauSac> Search(String ma) {
        return msRepo.search(ma);
    }
    
}
