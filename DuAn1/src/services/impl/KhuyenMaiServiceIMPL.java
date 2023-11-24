package services.impl;

import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import repositories.KhuyenMaiRepository;


public class KhuyenMaiServiceIMPL implements services.KhuyenMaiService{

    private KhuyenMaiRepository rs = new KhuyenMaiRepository();
    
    @Override
    public List<KhuyenMai> getAll() {
        return rs.getAll();
    }

    @Override
    public String add(KhuyenMai km) {
        List<KhuyenMai> list = new ArrayList<>();
        list.addAll(rs.getAllvali(km.getMa()));
        
        if (km.getMa().isEmpty()) {
            return "Mã Khuyến mại không được để trống ";
        }
        if (km.getTen().isEmpty()) {
            return "Mô tả không được để trống";
        }
        if (String.valueOf(km.getTienGiam()).isEmpty()) {
            return "Giá giảm không được để trống";
        }
        if (String.valueOf(km.getTienTT()).isEmpty()) {
            return "Giá tối thiểu không được để trống";
        }
        if (!list.isEmpty()) {
            return "Mã khuyến mại bị trùng";
        }
        
        
        
        boolean add = rs.add(km);
        if (add == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        boolean delete = rs.delete(ma);
        if (delete == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String update(KhuyenMai km, String ma) {
        boolean update = rs.update(km, ma);
        if (update == true) {
            return "Sửa thành công";
        } else {
            return "Sủa thất bại";
        }
    }

    @Override
    public List<KhuyenMai> locTrangThai(List<KhuyenMai> listKMs, int trangThai) {
        List<KhuyenMai> listSearch = new ArrayList<>();
        for (KhuyenMai km : listKMs) {
            if (km.getTrangThai() ==  trangThai) {
                listSearch.add(km);
            } else {

            }

        }

        return listSearch;
    }

    @Override
    public List<KhuyenMai> timKiem(List<KhuyenMai> listKMs, String ma, String ten) {
        List<KhuyenMai> listSearch = new ArrayList<>();
        for (KhuyenMai km : listKMs) {
            if (km.getMa().contains(ma)) {
                listSearch.add(km);
            }
            if (km.getTen().contains(ten)) {
                listSearch.add(km);
            }
            else {

            }

        }

        return listSearch;
    }

    @Override
    public void upDateTrangThai() {
        rs.upDateTrangThai();
    }
    
}
