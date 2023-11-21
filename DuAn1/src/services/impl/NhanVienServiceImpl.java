/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ChucVu;
import domainmodels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import repositories.ChucVuRepository;
import repositories.NhanVienRepository;
import services.NhanVienService;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Administrator
 */
public class NhanVienServiceImpl implements NhanVienService {

    NhanVienRepository rp = new NhanVienRepository();

    @Override
    public List<NhanVienViewModel> getAll() {
        return new NhanVienRepository().getAll();
    }

    @Override
    public List<NhanVien> getAlls() {
        return new NhanVienRepository().getAlls();
    }

    @Override
    public void showData(DefaultTableModel dtm, List<NhanVienViewModel> list) {
        dtm.setRowCount(0);
        for (NhanVienViewModel nhanVienView : list) {
            dtm.addRow(nhanVienView.toData());

        }
    }

    @Override
    public List<String> ChucVuCBB() {
        List<String> cbb = new ArrayList<>();
        ChucVuRepository cv = new ChucVuRepository();
        List<ChucVu> fullCH = cv.getAll();
        for (ChucVu chucVu : fullCH) {
            // cbb.add(String.valueOf(chucVu.getId()));
            cbb.add(chucVu.tenId(chucVu.getId()));
        }
        return cbb;
    }

    @Override
    public List<String> IDChucVu() {
        List<String> cbb = new ArrayList<>();
        ChucVuRepository cv = new ChucVuRepository();
        List<ChucVu> fullCH = cv.getAll();
        for (ChucVu chucVu : fullCH) {
            cbb.add(String.valueOf(chucVu.getId()));
        }
        return cbb;
    }

    @Override
    public String dangKi(NhanVien nv) {
        if (rp.addNV(nv)) {
            return "them thanh cong";
        }

        return "them that bai";
    }

    @Override
    public String update(NhanVien nv, String id) {
        boolean update = rp.updateNv(nv, id);
        if (update == true) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }

    @Override
    public String chuyenTTNV(String id) {
        boolean cTT = rp.chuyenTTNV(id);
        if (cTT == true) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }

    @Override
    public List<NhanVienViewModel> getAllNhanVienSearch(int rowOffset) {
        return rp.getAllNhanVienSearch(rowOffset);
    }

    @Override
    public List<NhanVienViewModel> searchNhanVien(String tenNhanVien) {
        List<NhanVienViewModel> listSearch = new ArrayList<>();
        listSearch = rp.searchNhanVien(tenNhanVien);
        return listSearch;
    }

    @Override
    public List<NhanVienViewModel> searchsdt(String sdt) {
        List<NhanVienViewModel> listSearch = new ArrayList<>();
        listSearch = rp.searchNhanVienSDT(sdt);
        return listSearch;
    }

    @Override
    public List<NhanVienViewModel> searchMaNV(String maNV) {
        List<NhanVienViewModel> listSearch = new ArrayList<>();
        listSearch = rp.searchNhanVienMaNV(maNV);
        return listSearch;
    }

    @Override
    public List<ChucVuViewModel> layThongTin(String ma) {
        List<ChucVuViewModel> listSearch = new ArrayList<>();
        listSearch = rp.layThongTin(ma);
        return listSearch;
    }

    @Override
    public void sapXep(List<NhanVienViewModel> list) {
        list.sort((o1, o2) -> {
            return o1.getMa().compareTo(o2.getMa());
        });
    }

    @Override
    public void sapXepTen(List<NhanVienViewModel> list) {
        list.sort((o1, o2) -> {
            return o1.getTen().compareTo(o2.getTen());
        });
    }

    @Override
    public List<NhanVienViewModel> listNam(List<NhanVienViewModel> listNam) {
        List<NhanVienViewModel> listNams = new ArrayList<>();
        for (NhanVienViewModel nv : listNam) {
            if (nv.isSex() == true) {
                listNams.add(nv);
            }
        }
        return listNams;
    }

    @Override
    public List<NhanVienViewModel> listNu(List<NhanVienViewModel> listNu) {
        List<NhanVienViewModel> listNus = new ArrayList<>();
        for (NhanVienViewModel nv : listNu) {
            if (nv.isSex() == false) {
                listNus.add(nv);
            }
        }
        return listNus;
    }

    @Override
    public List<NhanVienViewModel> listStatusOn(List<NhanVienViewModel> listStatusOn) {
        List<NhanVienViewModel> listSttOn = new ArrayList<>();
        for (NhanVienViewModel nv : listStatusOn) {
            if (nv.getStatus() == 2) {
                listSttOn.add(nv);
            }
        }
        return listSttOn;
    }

    @Override
    public List<NhanVienViewModel> listStatusOff(List<NhanVienViewModel> listStatusOff) {
        List<NhanVienViewModel> listSttOff = new ArrayList<>();
        for (NhanVienViewModel nv : listStatusOff) {
            if (nv.getStatus() == 1) {
                listSttOff.add(nv);
            }
        }
        return listSttOff;
    }

    @Override
    public List<NhanVienViewModel> listQuanLy(List<NhanVienViewModel> listQuanLy) {
        List<NhanVienViewModel> listQL = new ArrayList<>();
        for (NhanVienViewModel nv : listQuanLy) {
            if (nv.getIdUser() == 1) {
                listQL.add(nv);
            }
        }
        return listQL;
    }

    @Override
    public List<NhanVienViewModel> listNhanVien(List<NhanVienViewModel> listNhanVien) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        for (NhanVienViewModel nv : listNhanVien) {
            if (nv.getIdUser() == 2) {
                listNV.add(nv);
            }
        }
        return listNV;
    }
}
