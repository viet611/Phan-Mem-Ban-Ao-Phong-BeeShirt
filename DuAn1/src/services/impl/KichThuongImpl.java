 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KichThuoc;
import java.util.List;
import repositories.KichThuocResponsitory;
import services.KichThuocService;

/**
 *
 * @author Admin
 */
public class KichThuongImpl implements KichThuocService{
    KichThuocResponsitory ktrepo = new KichThuocResponsitory();
    @Override
    public String getNameByID(int id) {
        return ktrepo.getNameByID(id);
    }

    @Override
    public List<KichThuoc> getName() {
        return ktrepo.getALL();
    }

    @Override
    public Integer getIDbyName(String name) {
         return ktrepo.getIDbyName(name);
    }

    @Override
    public List<KichThuoc> getALL() {
        return ktrepo.getALLKT();
    }

    @Override
    public void InsertKT(KichThuoc sp) {
        ktrepo.InsertKT(sp);
    }

    @Override
    public void UpdateKT(KichThuoc sp) {
        ktrepo.UpdateKT(sp);
    }

    @Override
    public void DeleteKT(KichThuoc sp) {
        ktrepo.DeleteKT(sp);
    }

    @Override
    public List<KichThuoc> Search(String ma) {
        return ktrepo.search(ma);
    }
    
}
