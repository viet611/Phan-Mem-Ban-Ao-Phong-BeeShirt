/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import repositories.KhachHangRepository;
import services.KhachHangService;
import viewmodel.KhachHangViewModel;

/**
 *
 * @author Administrator
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository repo = new KhachHangRepository();

    @Override
    public List<KhachHang> getAllKh() {
        return repo.getAllKHModels();
    }

    @Override
    public void insert(KhachHang kh) {
        repo.insert(kh);
    }

    @Override
    public void deleteKH(Integer id) {
        repo.delete(id);
    }

    @Override
    public void updateKH(KhachHang kh) {
        repo.update(kh);
    }

    @Override
    public List<KhachHang> timKiem(String key) {
        return repo.timKiem(key);
    }



    @Override
    public String addKhachHang(KhachHang kh) {
        if (repo.addKhachHang(kh)) {
            return "Them khach hang thanh cong";
        } else {
            return "Them khach hang that bai";
        }
    }

    @Override
    public String editKhachHang(KhachHang kh, String id) {
        if (repo.editKhachHang(kh, id)) {
            return "Sua khach hang thanh cong";
        } else {
            return "Sua khach hang that bai";
        }
    }

    @Override
    public List<KhachHang> searchKhachHang(String tenKhachHang, int rowOffset) {
        List<KhachHang> listSearch = new ArrayList<>();
        listSearch = repo.searchKhachHang(tenKhachHang, rowOffset);
        return listSearch;
    }

    @Override
    public List<KhachHang> searchKhachHangSDT(String sdt, int rowOffset) {
        List<KhachHang> listSearch = new ArrayList<>();
        listSearch = repo.searchKhachSDT(sdt, rowOffset);
        return listSearch;
    }

    @Override
    public List<KhachHang> getAllKhachHang(int rowOffset) {
        return repo.getAllKhachHang(rowOffset);
    }

    @Override
    public List<KhachHang> getAllKhachHang1() {
        return repo.getAllKhachHang1();
    }

    @Override
    public void sapXep(List<KhachHang> list) {
        list.sort((o1, o2) -> {
            return o1.getMa().compareTo(o2.getMa());
        });
    }

    @Override
    public List<KhachHang> timTheoMa(List<KhachHang> listKHs, String ma) {
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang kh : listKHs) {
            if (kh.getMa().contains(ma)) {
                listSearch.add(kh);
            } else {

            }

        }

        return listSearch;
    }

    @Override
    public List<KhachHang> timTheoTen(List<KhachHang> listKHs, String ten) {
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang kh : listKHs) {
            if (kh.getTen().contains(ten)) {
                listSearch.add(kh);
            }
        }
        return listSearch;
    }

    @Override
    public List<KhachHang> timTheoSdt(List<KhachHang> listKHs, String sdt) {
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang kh : listKHs) {
            if (kh.getSdt().contains(sdt)) {
                listSearch.add(kh);
            }
        }
        return listSearch;
    }

    @Override
    public List<KhachHang> timTheoDiaChi(List<KhachHang> listKHs, String diaChi) {
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang kh : listKHs) {
            if (kh.getDiaChi().contains(diaChi)) {
                listSearch.add(kh);
            }
        }
        return listSearch;

    }

    @Override
    public void sapXepTen(List<KhachHang> list) {
        list.sort((o1, o2) -> {
            return o1.getTen().compareTo(o2.getTen());
        });

    }

    @Override
    public List<KhachHang> listNam(List<KhachHang> listNam) {
        List<KhachHang> listNams = new ArrayList<>();
        for (KhachHang nv : listNam) {
            if (nv.isSex() == true) {
                listNams.add(nv);
            }
        }
        return listNams;
    }

    @Override
    public List<KhachHang> listNu(List<KhachHang> listNu) {
        List<KhachHang> listNus = new ArrayList<>();
        for (KhachHang nv : listNu) {
            if (nv.isSex() == false) {
                listNus.add(nv);
            }
        }
        return listNus;
    }

    @Override
    public List<KhachHang> listNew(List<KhachHang> listNew) {
        List<KhachHang> listNews = new ArrayList<>();
        for (KhachHang nv : listNew) {
            if (nv.getStatus() == 2) {
                listNews.add(nv);
            }
        }
        return listNews;
    }

    @Override
    public List<KhachHang> listMember(List<KhachHang> listMember) {
        List<KhachHang> listMembers = new ArrayList<>();
        for (KhachHang nv : listMember) {
            if (nv.getStatus() == 1) {
                listMembers.add(nv);
            }
        }
        return listMembers;
    }

    @Override
    public void getAfterDelete(List<KhachHang> list) {
        list.addAll(repo.getAfterDelete());
    }

    @Override
    public List<KhachHang> searchAll(String ten, String sdt, String diaChi) {
         List<KhachHang> listSearch = new ArrayList<>();
        listSearch = repo.searchAll(ten, sdt, diaChi);
        return listSearch;
    }

    @Override
    public List<KhachHang> exportExcel() {
        return repo.exportExcel();
    }

}
