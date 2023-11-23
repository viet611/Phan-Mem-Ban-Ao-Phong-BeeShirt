/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HinhAnh;
import java.util.List;
import repositories.HinhAnhRepository;
import services.HinhAnhService;

/**
 *
 * @author Admin
 */
public class HinhAnhImpl implements HinhAnhService{
    HinhAnhRepository haRepo = new HinhAnhRepository();
    @Override
    public List<HinhAnh> getAll() {
        return haRepo.getAll();
    }

    @Override
    public void Insert(HinhAnh ha) {
        haRepo.InserHA(ha);
    }

    @Override
    public String getNameByID(int id) {
        return haRepo.getNameByID(id);
    }

    @Override
    public Integer getIDbyName(String name) {
        return haRepo.getIDbyName(name);
    }
    
}
