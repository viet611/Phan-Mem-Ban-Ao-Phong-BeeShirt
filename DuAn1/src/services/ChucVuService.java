/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.NhanVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import viewmodel.ChucVuViewModel;

/**
 *
 * @author Administrator
 */
public interface ChucVuService {

    List<ChucVuViewModel> getAll();

    void showDaTa(List<ChucVuViewModel> listChucVu, DefaultTableModel dtm);

    String add(ChucVuViewModel chucVu);

    ChucVuViewModel getOne(String ma);

    List<NhanVien> getLisst(String ma);
}
