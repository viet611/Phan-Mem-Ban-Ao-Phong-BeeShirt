/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaTiet;
import java.util.List;
import repositories.HoaTietResponsitory;
import services.HoaTietService;

/**
 *
 * @author Admin
 */
public class HoaTietImpl implements HoaTietService{
    HoaTietResponsitory htRepo = new HoaTietResponsitory();
    @Override
    public String getNameByID(int id) {
        return htRepo.getNameByID(id);
    }

    @Override
    public List<HoaTiet> getName() {
        return htRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return htRepo.getIDbyName(name);
    }

    @Override
    public List<HoaTiet> getAll() {
        return htRepo.getALLHT();
    }

    @Override
    public void InsertHT(HoaTiet sp) {
        htRepo.InsertHT(sp);
    }

    @Override
    public void UpdateHT(HoaTiet sp) {
        htRepo.UpdateHT(sp);
    }

    @Override
    public void DeleteHT(HoaTiet sp) {
       htRepo.DeleteHT(sp);
               
    }

    @Override
    public List<HoaTiet> Search(String ma) {
        return htRepo.search(ma);
    }

    
    
}
