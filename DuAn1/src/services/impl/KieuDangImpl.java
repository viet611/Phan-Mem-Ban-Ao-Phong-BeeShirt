/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KieuDang;
import java.util.List;
import repositories.ChatLieuResponsitory;
import repositories.KieuDangResponsitory;
import services.KieuDangService;

/**
 *
 * @author Admin
 */
public class KieuDangImpl implements  KieuDangService{
    KieuDangResponsitory kdRepo = new KieuDangResponsitory();
    @Override
    public String getNameByID(int id) {
        return kdRepo.getNameByID(id);
    }

    @Override
    public List<KieuDang> getName() {
        return kdRepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
        return kdRepo.getIDbyName(name);
    }

    @Override
    public List<KieuDang> getAll() {
        return kdRepo.getALLKD();
    }

    @Override
    public void InsertSP(KieuDang sp) {
        kdRepo.InsertKD(sp);
    }

    @Override
    public void UpdateSP(KieuDang sp) {
        kdRepo.UpdateKD(sp);
    }

    @Override
    public void DeleteKD(KieuDang sp) {
        kdRepo.DeleteKD(sp);
    }

    @Override
    public List<KieuDang> Search(String ma) {
        return kdRepo.search(ma);
    }
    
}
