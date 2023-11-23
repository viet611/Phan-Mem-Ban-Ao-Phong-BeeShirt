/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ThuongHieu;
import java.util.List;
import repositories.ThuongHieuResponsitory;
import services.ThuongHieuService;

/**
 *
 * @author Admin
 */
public class ThuongHieuImpl implements ThuongHieuService{
    ThuongHieuResponsitory thuongHieuRepo = new ThuongHieuResponsitory();
    @Override
    public String getNameByID(int id) {
        return thuongHieuRepo.getNameByID(id);
    }

    @Override
    public List<ThuongHieu> getName() {
       return thuongHieuRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return thuongHieuRepo.getIDbyName(name);
    }

    @Override
    public List<ThuongHieu> getALL() {
        return thuongHieuRepo.getALLTH();
    }

    @Override
    public void InsertTH(ThuongHieu sp) {
        thuongHieuRepo.InsertTH(sp);
    }

    @Override
    public void UpdateTH(ThuongHieu sp) {
       thuongHieuRepo.UpdateTH(sp);
    }

    @Override
    public void DeteleTH(ThuongHieu sp) {
        thuongHieuRepo.DeleteTH(sp);
    }

    @Override
    public List<ThuongHieu> Search(String ma) {
        return thuongHieuRepo.search(ma);
    }
    
}
