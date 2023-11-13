/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.HoaDonTimeLine;
import java.util.List;
import java.util.stream.Collectors;
import repositories.HoaDonTimeLineRepository;
import services.HoaDonTimeLineService;

/**
 *
 * @author Admin
 */
public class HoaDonTimeLineImpl implements HoaDonTimeLineService{

    private final HoaDonTimeLineRepository hoaDonTimeLineRepository = new HoaDonTimeLineRepository();
    
    @Override
    public List<HoaDonTimeLine> getAll() {
        return hoaDonTimeLineRepository.getAll().stream().filter(hdtl -> !hdtl.getDaXoa()).collect(Collectors.toList());
    }

    @Override
    public List<HoaDonTimeLine> getByIDHoaDon(Integer id) {
        return hoaDonTimeLineRepository.selectByIDHoaDon(id).stream().filter(hdtl -> !hdtl.getDaXoa()).collect(Collectors.toList());
    }

    @Override
    public Boolean insert(HoaDonTimeLine hoaDonTimeLine) {
        return hoaDonTimeLineRepository.insert(hoaDonTimeLine);
    }
    
    
}
