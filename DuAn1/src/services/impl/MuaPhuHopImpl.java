/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.MuaPhuHop;
import java.util.List;
import repositories.MuaPHResponsitory;
import services.MuaPhuHopService;

/**
 *
 * @author Admin
 */
public class MuaPhuHopImpl implements MuaPhuHopService{
    MuaPHResponsitory mphRepo = new MuaPHResponsitory();
    @Override
    public String getNameByID(int id) {
        return mphRepo.getNameByID(id);
    }

    @Override
    public List<MuaPhuHop> getName() {
        return mphRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return mphRepo.getIDbyName(name);
    }

    @Override
    public List<MuaPhuHop> getALL() {
        return mphRepo.getALLMPH();
    }

    @Override
    public void InsertMPH(MuaPhuHop sp) {
        mphRepo.InsertMPH(sp);
    }

    @Override
    public void UpdateMPH(MuaPhuHop sp) {
        mphRepo.UpdateMPH(sp);
    }

    @Override
    public void DeleteMPH(MuaPhuHop sp) {
        mphRepo.DeleteMPH(sp);
    }

    @Override
    public List<MuaPhuHop> Search(String ma) {
        return mphRepo.search(ma);
    }
    
}
