/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;



import domainmodels.SanPham;
import java.util.List;
import repositories.SanPhamResponsitory;
import services.SanPhamService;

/**
 *
 * @author Admin
 */
public class SanPhamImpl implements SanPhamService{
    SanPhamResponsitory spRepo = new SanPhamResponsitory();

    @Override
    public List<SanPham> getAll() {
        return spRepo.getALL();
    }

    @Override
    public String getNameByIDSP(int id) {
        return spRepo.getNameByIDSP(id);
    }

    @Override
    public List<SanPham> getName() {
        return spRepo.getName();
    }

    @Override
    public void InsertSP(SanPham sp) {
        spRepo.InsertSp(sp);
    }

    @Override
    public List<SanPham> Search(String ma) {
        return spRepo.search(ma);
    }

    @Override
    public Integer getIDbyName(String name) {
        return spRepo.getIDbyName(name);
    }

    @Override
    public void UpdateSP(SanPham sp) {
        spRepo.UpdateSp(sp);
    }

    @Override
    public String getNameID(int id) {
        return spRepo.getNameID(id);
    }

    @Override
    public void DeleteSp(SanPham sp) {
        spRepo.DeleteSp(sp);
    }

    
    
}
