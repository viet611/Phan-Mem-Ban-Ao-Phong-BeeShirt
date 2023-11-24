/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import services.ChucVuService;
import domainmodels.NhanVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import repositories.ChucVuRepository;
import viewmodel.ChucVuViewModel;

/**
 *
 * @author Administrator
 */
public class ChucVuServiceImpl implements ChucVuService{
    
    private ChucVuRepository rp = new ChucVuRepository();

    @Override
    public List<ChucVuViewModel> getAll() {
       return rp.getAllCV();
    }

    @Override
    public void showDaTa(List<ChucVuViewModel> listChucVu, DefaultTableModel dtm) {
        dtm.setRowCount(0);
        for (ChucVuViewModel x : listChucVu) {
            dtm.addRow(x.toDaTaRow());
        }
    }

    @Override
    public String add(ChucVuViewModel chucVu) {
         if (chucVu.getMa().isEmpty()&& chucVu.getTen().isEmpty()) {
            return "Mã chức vụ  và tên chức vụ không được để trống";
        } else if (chucVu.getMa().isEmpty()) {
            return "Mã  chức vụ không được để trống";
        } else if (chucVu.getTen().isEmpty() ) {
            return "Tên chức vụ không để trống  ";
        } else;
        boolean add = rp.add(chucVu);
        if (add == true) {
            return "Add thành công";
        } else {
            return "Add thất bại";
        }
    }

    @Override
    public ChucVuViewModel getOne(String ma) {
       return rp.getOne(ma);
    }

    @Override
    public List<NhanVien> getLisst(String ma) {
        return rp.getCV(ma);
    }
    
}
