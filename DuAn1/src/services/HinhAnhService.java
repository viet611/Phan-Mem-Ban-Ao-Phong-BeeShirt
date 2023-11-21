/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.HinhAnh;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HinhAnhService {
    List<HinhAnh> getAll();
    void Insert(HinhAnh ha);
}
