/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDon;
import java.util.List;
import repositories.HoaDonRepository;
import services.HoaDonService;

/**
 *
 * @author Admin
 */
public class HoaDonServiceImpl implements HoaDonService{

    private final HoaDonRepository hoaDonRepository = new HoaDonRepository();
    
    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.getAll();
    }

    @Override
    public List<HoaDon> search(String key) {
        return hoaDonRepository.search(key);
    }

    @Override
    public Boolean update(HoaDon hd) {
        return hoaDonRepository.update(hd);
    }

    @Override
    public Boolean insert(HoaDon hd) {
        return hoaDonRepository.insert(hd);
    }

    @Override
    public Boolean updateFull(HoaDon hd) {
        return  hoaDonRepository.updateFull(hd);
    }
    
    
}
