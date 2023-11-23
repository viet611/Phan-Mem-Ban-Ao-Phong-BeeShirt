/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.KhachHang;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import viewmodel.KhachHangViewModel;

/**
 *
 * @author Administrator
 */
public interface KhachHangService {

    List<KhachHang> getAllKh();
//    List<KhachHangViewModel> getAllVMD();

    public void insert(KhachHang kh);

    public void deleteKH(Integer id);

    public void updateKH(KhachHang kh);

    public List<KhachHang> timKiem(String key);

    String addKhachHang(KhachHang kh);

    String editKhachHang(KhachHang kh, String id);

    List<KhachHang> searchKhachHang(String tenKhachHang, int rowOffset);

    List<KhachHang> searchKhachHangSDT(String sdt, int rowOffset);

    List<KhachHang> getAllKhachHang(int rowOffset);

    List<KhachHang> getAllKhachHang1();
    
    void sapXep(List<KhachHang> list);
    
    List<KhachHang> timTheoMa(List<KhachHang> listKHs, String ma);
    
    List<KhachHang> timTheoTen(List<KhachHang> listKHs, String ten);
    
    List<KhachHang> timTheoSdt(List<KhachHang> listKHs, String sdt);
    
    List<KhachHang> timTheoDiaChi(List<KhachHang> listKHs, String diaChi);
    
    void sapXepTen(List<KhachHang> list);
    
    List<KhachHang> listNam (List<KhachHang> listNam);
    
    List<KhachHang> listNu (List<KhachHang> listNu);
    
    List<KhachHang> listNew (List<KhachHang> listNew);
    
    List<KhachHang> listMember (List<KhachHang> listMember);
    
    void getAfterDelete(List<KhachHang> list);

    List<KhachHang> searchAll(String ten, String sdt, String diaChi);
    
     List<KhachHang> exportExcel ();
}
