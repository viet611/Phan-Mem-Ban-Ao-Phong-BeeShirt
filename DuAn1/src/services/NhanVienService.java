/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.NhanVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Administrator
 */
public interface NhanVienService {
    List<NhanVienViewModel> getAll();

    List<NhanVien> getAlls();

    void showData(DefaultTableModel dtm, List<NhanVienViewModel> list);

    List<String> ChucVuCBB();

    List<String> IDChucVu();

    String dangKi(NhanVien nv);

    String update(NhanVien nv, String id);

    String chuyenTTNV(String id);

    List<NhanVienViewModel> getAllNhanVienSearch(int rowOffset);

    List<NhanVienViewModel> searchNhanVien(String tenNhanVien);

    List<NhanVienViewModel> searchsdt(String sdt);

 //   List<NhanVienViewModel> searchcmnd(String cmnd);

    List<NhanVienViewModel> searchMaNV(String maNV);

    List<ChucVuViewModel> layThongTin(String ma);

  /*  void checkTrungCMND(String cmnd, List<CMNDViewModel> list);

    void checkTrungsdt(String sdt, List<CheckSDT> list);*/
    
    void sapXep(List<NhanVienViewModel> list);
    
    void sapXepTen(List<NhanVienViewModel> list);
    
    List<NhanVienViewModel> listNam (List<NhanVienViewModel> listNam);
    
    List<NhanVienViewModel> listNu (List<NhanVienViewModel> listNu);
    
    List<NhanVienViewModel> listStatusOn (List<NhanVienViewModel> listStatusOn);
    
    List<NhanVienViewModel> listStatusOff (List<NhanVienViewModel> listStatusOff);
    
    List<NhanVienViewModel> listQuanLy (List<NhanVienViewModel> listQuanLy);
    
    List<NhanVienViewModel> listNhanVien (List<NhanVienViewModel> listNhanVien);

}
