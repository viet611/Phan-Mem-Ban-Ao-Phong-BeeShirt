/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.MucDichSuDung;
import java.util.List;
import repositories.MucDichSDResponsitory;
import services.MDSDService;

/**
 *
 * @author Admin
 */
public class MDSDImpl implements MDSDService{
    MucDichSDResponsitory mdsdRepo = new MucDichSDResponsitory();
    @Override
    public String getNameByID(int id) {
        return mdsdRepo.getNameByID(id);
    }

    @Override
    public List<MucDichSuDung> getAll() {
        return mdsdRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return mdsdRepo.getIDbyName(name);
    }

    @Override
    public List<MucDichSuDung> getAllMDSD() {
        return mdsdRepo.getALLMDSD();
    }

    @Override
    public void InsertMDSD(MucDichSuDung sp) {
         mdsdRepo.InsertMDSD(sp);
    }

    @Override
    public void UpdateMDSD(MucDichSuDung sp) {
        mdsdRepo.UpdateMDSD(sp);
    }

    @Override
    public void DeleteMDSD(MucDichSuDung sp) {
        mdsdRepo.DeleteMDSD(sp);
    }

    @Override
    public List<MucDichSuDung> Search(String ma) {
        return  mdsdRepo.search(ma);
    }
    
}
