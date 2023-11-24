package services;

import domainmodels.KhuyenMai;
import java.util.List;

public interface KhuyenMaiService {
    List<KhuyenMai> getAll();
    
    String add(KhuyenMai km);
    
    String delete(String ma);
    
    String update(KhuyenMai km, String ma);
    
    List<KhuyenMai> timKiem(List<KhuyenMai> listKMs, String ma, String ten);
    
    List<KhuyenMai> locTrangThai(List<KhuyenMai> listKMs, int trangThai);
    
    public void upDateTrangThai();
}
