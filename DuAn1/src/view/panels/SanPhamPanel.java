/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import domainmodels.ChatLieu;
import domainmodels.HoaTiet;
import domainmodels.KichThuoc;
import domainmodels.KieuDang;
import domainmodels.MauSac;
import domainmodels.MuaPhuHop;
import domainmodels.MucDichSuDung;
import domainmodels.SanPham;
import domainmodels.SanPhamCT;
import domainmodels.ThuongHieu;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import static java.lang.Math.round;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import services.impl.ChatLieuImpl;
import services.impl.HoaTietImpl;
import services.impl.KichThuongImpl;
import services.impl.KieuDangImpl;
import services.impl.MDSDImpl;
import services.impl.MauSacImpl;
import services.impl.MuaPhuHopImpl;
import services.impl.SanPhamCTImpl;
import services.impl.SanPhamImpl;
import services.impl.ThuongHieuImpl;
import ultilities.MsgBox;
import view.model.StatusType;

/**
 *
 * @author Admin
 */
public class SanPhamPanel extends javax.swing.JPanel implements Runnable,ThreadFactory{
    SanPhamImpl spService = new SanPhamImpl();
    ThuongHieuImpl thService = new ThuongHieuImpl();
    MauSacImpl msService = new MauSacImpl();
    KichThuongImpl ktService = new KichThuongImpl();
    ChatLieuImpl clService = new ChatLieuImpl();
    KieuDangImpl kdService = new KieuDangImpl();
    MuaPhuHopImpl mphService = new MuaPhuHopImpl();
    MDSDImpl msdsService = new MDSDImpl();
    HoaTietImpl htService = new HoaTietImpl();
    
    SanPhamCTImpl spctService = new SanPhamCTImpl();
    
    public String textField;
    public String MASPCT;
    public int id_SPCT;
    public int id;
    private int id_kich_Thuoc;
    private int id_chat_lieu;
    private int id_hoa_tiet;
    private int id_kieu_dang;
    private int id_MDSD;
    private int id_mau_sac;
    private int id_MuaPhuHop;
    private int id_thuong_hieu;
    private int totalPage;
    private int limit=5;
    
    private WebcamPanel panel = null;
    private Webcam webCam=null;
    
    
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    /**
     * Creates new form Form_1
     */
    public SanPhamPanel() {
        initComponents();
        //inputSanPham.hint = "sanPham";
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.DANGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//        table.addRow(new Object[]{"1","San pham 1",500,"Adidas",StatusType.KHONGHOATDONG});
//            DefaultTableModel test = (DefaultTableModel) table.getModel();
//            test.setRowCount(0);
//            test.addRow(new Object[]{
//                1,"test","tesst2",false
//            });
        LoadDataSP();
        LoadSPCT(1);
        //LoadWithMASPCT("SPCT23");
        LoadCBOFilter();
        if(rdo_KT.isSelected()){
            LoadDataToRDOKichThuoc();
        }
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                LoadSPCT(page);
            }
        });
        //innitCam();
        //rdo_SpKHD.isSelected();
    }
    private void LoadDataSP(){
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        tb.setRowCount(0);
        List<SanPham> listSP = spService.getAll();
        int count = 1;
        for (SanPham x : listSP) {
            int trangThai=x.getTrang_thai();
            int idSP= x.getId();
            int getCount = spctService.getCountSP(idSP);
            //System.out.println("Tesst trangThai: "+trangThai);
            boolean trang_Thai=true;
            if(trangThai ==0){
                trang_Thai=true;
            }else{
                trang_Thai =false;
            }
            tb.addRow(new Object[]{
                count,
                x.getMa(),
                x.getTen(),
                getCount,
                trang_Thai
            });
            count++;
        }

        
    }
    public void LoadSPCT(int Page){
        
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<SanPhamCT> listSPCT = spctService.getALL();
        double ralPage = listSPCT.size() / limit;
        totalPage =  (int) Math.ceil(ralPage);
        System.out.println("touto "+totalPage);
        System.out.println("List  "+listSPCT.size());
        
        int count=1;
        List<SanPhamCT> subList;
        //int min = Page * limit - 1;
        int min = (Page -1)*limit ;
        int max = Page  * limit ;
        if (max >= listSPCT.size()) {
            subList = listSPCT.subList(min, listSPCT.size());
        } else {
            subList = listSPCT.subList(min, max);
        }
        for(SanPhamCT x: subList){
            //id_sanPham
            int id_sanPham = x.getId_SanPham();
            String MaSP = spService.getNameByIDSP(id_sanPham);
            //System.out.println("name SP" +MaSP);
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS=msService.getNameByID(id_mauSac);
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            //System.out.println("name TH: " +nameTH);
            //id kich thuoc
            int id_kichThuoc =x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            //id_KieuDang;
            int id_kieuDang=x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            //id_MuaPhuHop;
            int id_muaPhuHop =x.getId_MuaPhuHop();
            String nameMPH= mphService.getNameByID(id_muaPhuHop);
            //id_MDSD;
            int id_MDSD = x.getId_MDSD();
            String nameMDSD= msdsService.getNameByID(id_MDSD);
            //id_ChatLieu;
            int id_chatLieu=x.getId_ChatLieu();
            String nameCL =clService.getNameByID(id_chatLieu);
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
            //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
            //ma;
            String ma = x.getMa();
            //goi_Tinh;
            boolean gioi_tinh = x.isGoi_Tinh();
            String gioiTinh=null;
            if(gioi_tinh==true){
                gioiTinh="nam";
            }else{
                gioiTinh="Nữ";
            }
            //so_Luong;
            float so_Luong = x.getSo_Luong();
            //gia;
            float gia=x.getGia();
            //mo_Ta;
            String mo_ta=x.getMo_Ta();
            //trang_thai;
            int trang_thai =x.getTrang_thai();
            boolean trangThai=true;
            if(trang_thai==0){
                trangThai=true;
            }else{
                trangThai=false;
            }
//            tb.addRow(new Object[]{
//                id_hinhAnh, ma, id_sanPham,id_thuongHieu, id_mauSac, id_kichThuoc, so_Luong, gia, id_chatLieu, id_kieuDang,  
//                id_muaPhuHop, id_MDSD, id_hoaTiet, gioiTinh, StatusType.DANGHOATDONG
//            });
            tb.addRow(new Object[]{
                null,count+((Page-1)*limit), ma, MaSP, trangThai,nameTH, nameMS, nameKT, so_Luong, gia, nameCL,  
                nameKD, nameMPH, nameMDSD, nameHT, gioiTinh,id_hinhAnh
            });
            count++;
        }
        pagination1.setPagegination(Page, totalPage+1);
    }
    
    public void LoadWithMASPCT(String MASPXT){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<SanPhamCT> listSPCT = spctService.getallWithMaSPCT(MASPXT);
        int count=1;
        for(SanPhamCT x: listSPCT){
            //id_sanPham
            int id_sanPham = x.getId_SanPham();
            String MaSP = spService.getNameByIDSP(id_sanPham);
            //System.out.println("name SP" +MaSP);
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS=msService.getNameByID(id_mauSac);
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            //System.out.println("name TH: " +nameTH);
            //id kich thuoc
            int id_kichThuoc =x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            //id_KieuDang;
            int id_kieuDang=x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            //id_MuaPhuHop;
            int id_muaPhuHop =x.getId_MuaPhuHop();
            String nameMPH= mphService.getNameByID(id_muaPhuHop);
            //id_MDSD;
            int id_MDSD = x.getId_MDSD();
            String nameMDSD= msdsService.getNameByID(id_MDSD);
            //id_ChatLieu;
            int id_chatLieu=x.getId_ChatLieu();
            String nameCL =clService.getNameByID(id_chatLieu);
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
            //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
            //ma;
            String ma = x.getMa();
            //goi_Tinh;
            boolean gioi_tinh = x.isGoi_Tinh();
            String gioiTinh=null;
            if(gioi_tinh==true){
                gioiTinh="nam";
            }else{
                gioiTinh="Nữ";
            }
            //so_Luong;
            float so_Luong = x.getSo_Luong();
            //gia;
            float gia=x.getGia();
            //mo_Ta;
            String mo_ta=x.getMo_Ta();
            //trang_thai;
            int trang_thai =x.getTrang_thai();
            boolean trangThai=true;
            if(trang_thai==0){
                trangThai=true;
            }else{
                trangThai=false;
            }
//            tb.addRow(new Object[]{
//                id_hinhAnh, ma, id_sanPham,id_thuongHieu, id_mauSac, id_kichThuoc, so_Luong, gia, id_chatLieu, id_kieuDang,  
//                id_muaPhuHop, id_MDSD, id_hoaTiet, gioiTinh, StatusType.DANGHOATDONG
//            });
            tb.addRow(new Object[]{
                null,count, ma, MaSP, trangThai,nameTH, nameMS, nameKT, so_Luong, gia, nameCL,  
                nameKD, nameMPH, nameMDSD, nameHT, gioiTinh,id_hinhAnh
            });
            count++;
        }
    }
    
    private SanPham getInputSanPham(){
        SanPham sp = new SanPham();
        String prefix = "SP";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TENSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        if(rdo_SpKHD.isSelected()){
            daXoa=false;
        }
        sp.setDa_xoa(daXoa);
        return sp;
    }
    
    private KichThuoc getInputKichThuoc(){
        KichThuoc sp = new KichThuoc();
        String prefix = "KT";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private ChatLieu getInputChatLieu(){
        ChatLieu sp = new ChatLieu();
        String prefix = "CL";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private HoaTiet getInputHoaTiet(){
        HoaTiet sp = new HoaTiet();
        String prefix = "HT";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private KieuDang getInputKieuDang(){
        KieuDang sp = new KieuDang();
        String prefix = "KD";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private MucDichSuDung getInputMucDichSuDung(){
        MucDichSuDung sp = new MucDichSuDung();
        String prefix = "MDSD";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private MauSac getInputMauSac(){
        MauSac sp = new MauSac();
        String prefix = "MS";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private MuaPhuHop getInputMuaPhuHop(){
        MuaPhuHop sp = new MuaPhuHop();
        String prefix = "MPH";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    private ThuongHieu getInputThuongHieu(){
        ThuongHieu sp = new ThuongHieu();
        String prefix = "TH";
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        String maSP =prefix + String.format("%02d", randomNumber);
        //System.out.println(maSP);
        sp.setMa(maSP);
        String tenSP = txt_TenSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        sp.setTao_boi(tenNT);
        String tenNS =null;
        sp.setSua_boi(tenNS);
        boolean daXoa =true;
        sp.setDa_xoa(daXoa);
        int trangThai=0;
        sp.setTrang_thai(trangThai);
        return sp;
    }
    
    
    
    private SanPham getDataUpdate(){
        SanPham sp = new SanPham();
        sp.setId(id);
        sp.setMa(txt_MASP.getText());
        String tenSP = txt_TENSP.getText();
        sp.setTen(tenSP);
        String tenNT = null;
        int trangThai=0;
        if(rdo_SpKHD.isSelected()){
            trangThai=1;
        }
        sp.setTrang_thai(trangThai);
        
        sp.setTao_boi(tenNT);
        String tenNS =null;
        
        sp.setSua_boi(tenNS);
        
        boolean daXoa =true;
        if(rdo_SpKHD.isSelected()){
            daXoa=false;
        }
        sp.setDa_xoa(daXoa);
        return sp;
    }
    
    private void clearInputSP(){
        txt_MASP.setText("");
        txt_TENSP.setText("");
        id=0;
    }
    public void LoadCBOFilter(){
        List<ThuongHieu> thuongHieu = thService.getName();
        List<MauSac> mauSac = msService.getName();
        List<KichThuoc> kichThuoc = ktService.getName();
        List<ChatLieu> chatLieu = clService.getName();
        List<KieuDang> kieuDang = kdService.getName();
        List<MuaPhuHop> muaPhuHop = mphService.getName();
        //cbo_TenSP.addItem("Tất cả");
        cbo_FilterSPCTTH.setModel(new DefaultComboBoxModel(thuongHieu.toArray()));
        cbo_FilterSPCTMS.setModel(new DefaultComboBoxModel(mauSac.toArray()));
        cbo_FilterSPCTKT.setModel(new DefaultComboBoxModel(kichThuoc.toArray()));
        cbo_FilterSPCTCL.setModel(new DefaultComboBoxModel(chatLieu.toArray()));
        cbo_FilterSPCTKD.setModel(new DefaultComboBoxModel(kieuDang.toArray()));
        cbo_FilterSPCTMPH.setModel(new DefaultComboBoxModel(muaPhuHop.toArray()));
        System.out.println(muaPhuHop.toArray());
    }
    
    private void LoadDataToRDOKichThuoc(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        List<KichThuoc> listKT = ktService.getALL();
        int count=1;
        for(KichThuoc x:listKT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOMauSac(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<MauSac> listMS = msService.getALL();
        
        int count=1;
        for(MauSac x:listMS){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOChatLieu(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<ChatLieu> listCL = clService.getALL();
        int count=1;
        for(ChatLieu x:listCL){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOKieuDang(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<KieuDang> listKD = kdService.getAll();
        
        int count=1;
        for(KieuDang x:listKD){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOMPH(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<MuaPhuHop> listMPH = mphService.getALL();
        int count=1;
        for(MuaPhuHop x:listMPH){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOMDSD(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        List<MucDichSuDung> listMDSD = msdsService.getAllMDSD();
        
        int count=1;
        for(MucDichSuDung x:listMDSD){
            boolean trang_Thai=true;
            //System.out.println(trang_Thai);
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOHoaTiet(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<HoaTiet> listHT = htService.getAll();
        int count=1;
        for(HoaTiet x:listHT){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    private void LoadDataToRDOThuongHieu(){
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        
        List<ThuongHieu> listTH = thService.getALL();
        int count=1;
        for(ThuongHieu x:listTH){
            boolean trang_Thai=true;
            if(x.getTrang_thai()!=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,x.getMa(),x.getTen(),trang_Thai
            });
            count++;
        }
    }
    public void addTabPannel(){
       jTabbedPane2.setComponentAt(1, jPanel1); 
    }
    
    public void LoadByIDSp(int IDSP){
        //table2.removeColumn(table2.getColumnModel().getColumn(9));
//        table2.removeColumn(table2.getColumnModel().getColumn(9));
//        table2.removeColumn(table2.getColumnModel().getColumn(9));
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        List<SanPhamCT> listSPCT = spctService.getSPCTbyIDSP(IDSP);
        int count=1;
        for(SanPhamCT x: listSPCT){
            //id_sanPham
            int id_sanPham = x.getId_SanPham();
            String MaSP = spService.getNameByIDSP(id_sanPham);
            //System.out.println("name SP" +MaSP);
            //id mau sac
            int id_mauSac = x.getId_MauSac();
            String nameMS=msService.getNameByID(id_mauSac);
            //id thuong hieu
            int id_thuongHieu = x.getId_ThuongHieu();
            String nameTH = thService.getNameByID(id_thuongHieu);
            //System.out.println("name TH: " +nameTH);
            //id kich thuoc
            int id_kichThuoc =x.getId_KichThuoc();
            String nameKT = ktService.getNameByID(id_kichThuoc);
            //id_KieuDang;
            int id_kieuDang=x.getId_KieuDang();
            String nameKD = kdService.getNameByID(id_kieuDang);
            //id_MuaPhuHop;
            int id_muaPhuHop =x.getId_MuaPhuHop();
            String nameMPH= mphService.getNameByID(id_muaPhuHop);
            //id_MDSD;
            int id_MDSD = x.getId_MDSD();
            String nameMDSD= msdsService.getNameByID(id_MDSD);
            //id_ChatLieu;
            int id_chatLieu=x.getId_ChatLieu();
            String nameCL =clService.getNameByID(id_chatLieu);
            //id_HoaTiet;
            int id_hoaTiet = x.getId_HoaTiet();
            String nameHT = htService.getNameByID(id_hoaTiet);
            //id_HinhAnh;
            int id_hinhAnh = x.getId_HinhAnh();
            //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
            //ma;
            String ma = x.getMa();
            //goi_Tinh;
            boolean gioi_tinh = x.isGoi_Tinh();
            String gioiTinh=null;
            if(gioi_tinh==true){
                gioiTinh="nam";
            }else{
                gioiTinh="Nữ";
            }
            //so_Luong;
            float so_Luong = x.getSo_Luong();
            //gia;
            float gia=x.getGia();
            //mo_Ta;
            String mo_ta=x.getMo_Ta();
            //trang_thai;
            int trang_thai =x.getTrang_thai();
            boolean trangThai=true;
            if(trang_thai==0){
                trangThai=true;
            }else{
                trangThai=false;
            }
//            tb.addRow(new Object[]{
//                id_hinhAnh, ma, id_sanPham,id_thuongHieu, id_mauSac, id_kichThuoc, so_Luong, gia, id_chatLieu, id_kieuDang,  
//                id_muaPhuHop, id_MDSD, id_hoaTiet, gioiTinh, StatusType.DANGHOATDONG
//            });
            tb.addRow(new Object[]{
                null,count, ma, MaSP, trangThai,nameTH, nameMS, nameKT, so_Luong, gia, nameCL,  
                nameKD, nameMPH, nameMDSD, nameHT, gioiTinh,id_hinhAnh
            });
            count++;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new view.component.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_MASP = new javax.swing.JTextField();
        txt_TENSP = new javax.swing.JTextField();
        btn_ThemSp = new javax.swing.JButton();
        btn_CapNhatSP = new javax.swing.JButton();
        btn_ClearSP = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        rdo_SpHD = new javax.swing.JRadioButton();
        rdo_SpKHD = new javax.swing.JRadioButton();
        jButton11 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_searchSP = new javax.swing.JTextField();
        btn_searchSp = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new view.component.Table();
        jPanel4 = new javax.swing.JPanel();
        btn_ThemSPCT = new javax.swing.JButton();
        btn_CapNhatSPCT = new javax.swing.JButton();
        btn_XoaSPCT = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbo_FilterSPCTMS = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbo_FilterSPCTKT = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbo_FilterSPCTTH = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbo_FilterSPCTCL = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbo_FilterSPCTKD = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbo_FilterSPCTMPH = new javax.swing.JComboBox<>();
        pagination1 = new pagination.Pagination();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_MA = new javax.swing.JTextField();
        txt_TenSP = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        rdo_KT = new javax.swing.JRadioButton();
        rdo_MS = new javax.swing.JRadioButton();
        rdo_CL = new javax.swing.JRadioButton();
        rdo_KD = new javax.swing.JRadioButton();
        rdo_MPH = new javax.swing.JRadioButton();
        rdo_MDSD = new javax.swing.JRadioButton();
        rdo_HoaTiet = new javax.swing.JRadioButton();
        rdo_TH = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        btn_themDT = new javax.swing.JButton();
        btn_CapNhatDT = new javax.swing.JButton();
        btn_clearIPDT = new javax.swing.JButton();
        btn_XoaDT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDT = new view.component.Table();
        jLabel14 = new javax.swing.JLabel();
        txt_Search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số Lượng", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Mã SP");

        jLabel2.setText("Tên SP");

        txt_MASP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_MASP.setEnabled(false);

        btn_ThemSp.setText("Them");
        btn_ThemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSpActionPerformed(evt);
            }
        });

        btn_CapNhatSP.setText("Cập Nhật");
        btn_CapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatSPActionPerformed(evt);
            }
        });

        btn_ClearSP.setText("Clear");
        btn_ClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearSPActionPerformed(evt);
            }
        });

        jLabel13.setText("Trạng Thái");

        buttonGroup1.add(rdo_SpHD);
        rdo_SpHD.setSelected(true);
        rdo_SpHD.setText("Hoạt Động");

        buttonGroup1.add(rdo_SpKHD);
        rdo_SpKHD.setText("Không HĐ");

        jButton11.setText("Xoá");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(txt_MASP, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(txt_TENSP)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdo_SpHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdo_SpKHD)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_CapNhatSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemSp, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ClearSP, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addContainerGap(424, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_MASP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemSp)
                    .addComponent(btn_ClearSP)
                    .addComponent(jLabel13)
                    .addComponent(rdo_SpHD)
                    .addComponent(rdo_SpKHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TENSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhatSP)
                    .addComponent(jButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Tìm Kiếm");

        btn_searchSp.setText("Tìm Kiếm");
        btn_searchSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_searchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_searchSp))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_searchSp)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addGap(58, 58, 58))
        );

        jTabbedPane2.addTab("Sản Phẩm", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "STT", "Mã SPCT", "Mã SP", "Trạng Thái", "Thương Hiệu", "Màu Sắc", "Kich Thước", "Số Lượng", "Giá", "Chất Liệu", "Kiểu Dáng", "Hoạ Tiết"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_ThemSPCT.setBackground(new java.awt.Color(255, 255, 255));
        btn_ThemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/sanpham.png"))); // NOI18N
        btn_ThemSPCT.setText("Thêm SPCT");
        btn_ThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPCTActionPerformed(evt);
            }
        });

        btn_CapNhatSPCT.setText("Cập Nhật");
        btn_CapNhatSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatSPCTActionPerformed(evt);
            }
        });

        btn_XoaSPCT.setText("Xoá");
        btn_XoaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPCTActionPerformed(evt);
            }
        });

        jButton2.setText("Quét QR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Tắt QR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_ThemSPCT)
                .addGap(26, 26, 26)
                .addComponent(btn_CapNhatSPCT)
                .addGap(27, 27, 27)
                .addComponent(btn_XoaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(23, 23, 23)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_CapNhatSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_XoaSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Tìm Kiếm");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Màu Sắc");

        cbo_FilterSPCTMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTMSItemStateChanged(evt);
            }
        });
        cbo_FilterSPCTMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_FilterSPCTMSActionPerformed(evt);
            }
        });

        jLabel6.setText("Kích thước");

        cbo_FilterSPCTKT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTKTItemStateChanged(evt);
            }
        });

        jLabel7.setText("Thương hiệu");

        cbo_FilterSPCTTH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTTHItemStateChanged(evt);
            }
        });

        jLabel10.setText("Chất Liệu");

        cbo_FilterSPCTCL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTCLItemStateChanged(evt);
            }
        });

        jLabel11.setText("Kiểu Dáng");

        cbo_FilterSPCTKD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTKDItemStateChanged(evt);
            }
        });

        jLabel12.setText("Mùa PHù Hợp");

        cbo_FilterSPCTMPH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_FilterSPCTMPHItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbo_FilterSPCTCL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_FilterSPCTTH, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_FilterSPCTMPH, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_FilterSPCTMS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbo_FilterSPCTKD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_FilterSPCTKT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_FilterSPCTMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_FilterSPCTKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbo_FilterSPCTCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbo_FilterSPCTTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cbo_FilterSPCTMPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbo_FilterSPCTKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(463, 463, 463)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(583, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        jTabbedPane2.addTab("Sản Phẩm Chi Tiết", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("MA");

        jLabel9.setText("Tên");

        txt_MA.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MA)
                    .addComponent(txt_TenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_MA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup2.add(rdo_KT);
        rdo_KT.setSelected(true);
        rdo_KT.setText("Kích Thước");
        rdo_KT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_KTItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_MS);
        rdo_MS.setText("Màu Sắc");
        rdo_MS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MSItemStateChanged(evt);
            }
        });
        rdo_MS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_MSActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdo_CL);
        rdo_CL.setText("Chất Liệu");
        rdo_CL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_CLItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_KD);
        rdo_KD.setText("Kiểu Dáng");
        rdo_KD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_KDItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_MPH);
        rdo_MPH.setText("Mùa Phù Hợp");
        rdo_MPH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MPHItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_MDSD);
        rdo_MDSD.setText("MDSD");
        rdo_MDSD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MDSDItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_HoaTiet);
        rdo_HoaTiet.setText("Hoạ Tiết");
        rdo_HoaTiet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_HoaTietItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_TH);
        rdo_TH.setText("Thương Hiệu");
        rdo_TH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_THItemStateChanged(evt);
            }
        });
        rdo_TH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_THActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(rdo_MPH)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_MDSD))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(rdo_KT)
                        .addGap(29, 29, 29)
                        .addComponent(rdo_MS)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_CL)
                    .addComponent(rdo_HoaTiet))
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_TH)
                    .addComponent(rdo_KD))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_KT)
                    .addComponent(rdo_MS)
                    .addComponent(rdo_CL)
                    .addComponent(rdo_KD))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_MPH)
                    .addComponent(rdo_MDSD)
                    .addComponent(rdo_HoaTiet)
                    .addComponent(rdo_TH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_themDT.setText("Thêm Mới");
        btn_themDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDTActionPerformed(evt);
            }
        });

        btn_CapNhatDT.setText("Cập Nhật");
        btn_CapNhatDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatDTActionPerformed(evt);
            }
        });

        btn_clearIPDT.setText("ClearInput");
        btn_clearIPDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearIPDTActionPerformed(evt);
            }
        });

        btn_XoaDT.setText("Xoá");
        btn_XoaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_themDT)
                    .addComponent(btn_CapNhatDT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_clearIPDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_XoaDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_themDT)
                    .addComponent(btn_XoaDT))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clearIPDT)
                    .addComponent(btn_CapNhatDT))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tableDT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MÃ ", "TÊN", "TRANG THAI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDT);

        jLabel14.setText("Tim Kiem");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        jTabbedPane2.addTab("Đặc Trưng Sản Phẩm", jPanel5);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Thông Tin Sản Phẩm ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSPCTActionPerformed
        // TODO add your handling code here:
        //tring ma="SP01";
//        ThemSanPhamCT addSPCT = new ThemSanPhamCT();
//        addSPCT.setCreateDialogForm(this);
//        addSPCT.setVisible(true);
//        //addSPCT.setEditData(ma);
//        txt_MASP.setText(addSPCT.getEditData());
//        System.out.println(addSPCT.getEditData());
           ThemSPCT add= new ThemSPCT();
           add.setCreateDialogForm(this);
           //add.setVisible(true);
           jTabbedPane2.setComponentAt(1, add);
//           jPanel1.removeAll();
//           jPanel1.add(add);
//           jPanel1.repaint();
//           jPanel1.revalidate();
           
    }//GEN-LAST:event_btn_ThemSPCTActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int row = table.getSelectedRow();
        String MaSP = table.getValueAt(row, 1).toString();
        txt_MASP.setText(MaSP);
        //System.out.println(MaSP);
        String TenSP = table.getValueAt(row, 2).toString();
        txt_TENSP.setText(TenSP);
        boolean trangThai = (boolean) table.getValueAt(row, 4);
        System.out.println("trangThaiSP: "+trangThai);
        if(trangThai==true){
            //rdo_SpHD.isSelected();
            rdo_SpHD.setSelected(trangThai);
        }else{
            //rdo_SpKHD.isSelected();
            rdo_SpKHD.setSelected(true);
        }
        
        id = spService.getAll().get(row).getId();
        System.out.println("test ID: "+id);
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật Sản phẩm thất bại!");
        }
        
        if (evt.getClickCount() == 2) {
            var rowClick = table.rowAtPoint(evt.getPoint());
            int col = table.columnAtPoint(evt.getPoint());
            if (rowClick != -1 && col != -1) {
                // Xử lý sự kiện double-click tại đây
                //System.out.println("Double-clicked on row " + rowClick + ", column " + col);
                int id_row = spService.getAll().get(rowClick).getId();
                //String maCT = table.getValueAt(row, 1).toString();
                jTabbedPane2.setSelectedIndex(1);
                LoadByIDSp(id_row);
            }
        }
//        textField = txt_MASP.getText();
//        System.out.println(textField);
    }//GEN-LAST:event_tableMouseClicked

    private void rdo_THActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_THActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_THActionPerformed

    private void btn_ThemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSpActionPerformed
        // TODO add your handling code here:
        try {
            SanPham sp = getInputSanPham();
            spService.InsertSP(sp);
            LoadDataSP();
            clearInputSP();
            MsgBox.alert(this, "Thêm Sản Phẩm Thành Công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm Sản Phẩm Thất Bại!");
        }
       
    }//GEN-LAST:event_btn_ThemSpActionPerformed

    private void btn_searchSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchSpActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        tb.setRowCount(0);
        String maSearch = txt_searchSP.getText();
        List<SanPham> listSP = spService.Search(maSearch);
        int count = 1;
        for (SanPham x : listSP) {
            int idSP= x.getId();
            int getCount = spctService.getCountSP(idSP);
            
            int trangThai=x.getTrang_thai();
            boolean trang_Thai=true;
            if(trangThai !=0){
                trang_Thai=false;
            }
            tb.addRow(new Object[]{
                count,
                x.getMa(),
                x.getTen(),
                getCount,
                trang_Thai
            });
            count++;
        }
    }//GEN-LAST:event_btn_searchSpActionPerformed

    private void btn_ClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearSPActionPerformed
        // TODO add your handling code here:
        clearInputSP();
        //jTabbedPane2.setSelectedIndex(1);
//        TestWebCamQrcode test = new TestWebCamQrcode();
//        test.setVisible(true);
    }//GEN-LAST:event_btn_ClearSPActionPerformed

    private void cbo_FilterSPCTMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTMSItemStateChanged
        // TODO add your handling code here:
        String mauSacName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTMS.getSelectedItem();
            if (selectedItem instanceof MauSac) {
                MauSac mauSac = (MauSac) selectedItem;
                // Now you can work with the MauSac object
                mauSacName = mauSac.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + mauSacName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_MauSac = msService.getIDbyName(mauSacName);
            System.out.println("id ms: "+id_MauSac);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterMauSac(id_MauSac);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_sanPham
                int id_sanPham = x.getId_SanPham();
                String MaSP = spService.getNameByIDSP(id_sanPham);
                //id mau sac
                int id_mauSac = x.getId_MauSac();
                String nameMS = msService.getNameByID(id_mauSac);
                //id thuong hieu
                int id_thuongHieu = x.getId_ThuongHieu();
                String nameTH = thService.getNameByID(id_thuongHieu);
                //id kich thuoc
                int id_kichThuoc = x.getId_KichThuoc();
                String nameKT = ktService.getNameByID(id_kichThuoc);
                //id_KieuDang;
                int id_kieuDang = x.getId_KieuDang();
                String nameKD = kdService.getNameByID(id_kieuDang);
                //id_MuaPhuHop;
                int id_muaPhuHop = x.getId_MuaPhuHop();
                String nameMPH = mphService.getNameByID(id_muaPhuHop);
                //id_MDSD;
                int id_MDSD = x.getId_MDSD();
                String nameMDSD = msdsService.getNameByID(id_MDSD);
                //id_ChatLieu;
                int id_chatLieu = x.getId_ChatLieu();
                String nameCL = clService.getNameByID(id_chatLieu);
                //id_HoaTiet;
                int id_hoaTiet = x.getId_HoaTiet();
                String nameHT = htService.getNameByID(id_hoaTiet);
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count, ma, MaSP, trangThai, nameTH, nameMS, nameKT, so_Luong, gia, nameCL,
                    nameKD, nameMPH, nameMDSD, nameHT, gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTMSItemStateChanged

    private void cbo_FilterSPCTMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_FilterSPCTMSActionPerformed

    private void cbo_FilterSPCTKTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTKTItemStateChanged
        // TODO add your handling code here:
        String ktName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTKT.getSelectedItem();
            if (selectedItem instanceof KichThuoc) {
                KichThuoc kichThuoc = (KichThuoc) selectedItem;
                // Now you can work with the MauSac object
                ktName = kichThuoc.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + ktName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_KT = ktService.getIDbyName(ktName);
            System.out.println("id ms: "+id_KT);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterKichThuoc(id_KT);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_sanPham
                int id_sanPham = x.getId_SanPham();
                String MaSP = spService.getNameByIDSP(id_sanPham);
                //id mau sac
                int id_mauSac = x.getId_MauSac();
                String nameMS = msService.getNameByID(id_mauSac);
                //id thuong hieu
                int id_thuongHieu = x.getId_ThuongHieu();
                String nameTH = thService.getNameByID(id_thuongHieu);
                //id kich thuoc
                int id_kichThuoc = x.getId_KichThuoc();
                String nameKT = ktService.getNameByID(id_kichThuoc);
                //id_KieuDang;
                int id_kieuDang = x.getId_KieuDang();
                String nameKD = kdService.getNameByID(id_kieuDang);
                //id_MuaPhuHop;
                int id_muaPhuHop = x.getId_MuaPhuHop();
                String nameMPH = mphService.getNameByID(id_muaPhuHop);
                //id_MDSD;
                int id_MDSD = x.getId_MDSD();
                String nameMDSD = msdsService.getNameByID(id_MDSD);
                //id_ChatLieu;
                int id_chatLieu = x.getId_ChatLieu();
                String nameCL = clService.getNameByID(id_chatLieu);
                //id_HoaTiet;
                int id_hoaTiet = x.getId_HoaTiet();
                String nameHT = htService.getNameByID(id_hoaTiet);
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count, ma, MaSP, trangThai, nameTH, nameMS, nameKT, so_Luong, gia, nameCL,
                    nameKD, nameMPH, nameMDSD, nameHT, gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTKTItemStateChanged

    private void cbo_FilterSPCTKDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTKDItemStateChanged
        // TODO add your handling code here:
        
        String kdName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTKD.getSelectedItem();
            if (selectedItem instanceof KieuDang) {
                KieuDang kieuDang = (KieuDang) selectedItem;
                // Now you can work with the MauSac object
                kdName = kieuDang.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + kdName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_KD = kdService.getIDbyName(kdName);
            System.out.println("id ms: "+id_KD);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterKieuDang(id_KD);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count,ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()), msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()), 
                    so_Luong, gia,clService.getNameByID(x.getId_ChatLieu()),kdService.getNameByID(x.getId_KieuDang()), 
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()), 
                    htService.getNameByID(x.getId_HoaTiet()),gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTKDItemStateChanged

    private void cbo_FilterSPCTCLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTCLItemStateChanged
        // TODO add your handling code here:
        String clName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTCL.getSelectedItem();
            if (selectedItem instanceof ChatLieu) {
                ChatLieu chatLieu = (ChatLieu) selectedItem;
                // Now you can work with the MauSac object
                clName = chatLieu.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + clName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_CL = clService.getIDbyName(clName);
            System.out.println("id ms: "+id_CL);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterChatLieu(id_CL);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count,ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()),
                    msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()), 
                    so_Luong, gia,clService.getNameByID(x.getId_ChatLieu()),kdService.getNameByID(x.getId_KieuDang()), 
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()), 
                    htService.getNameByID(x.getId_HoaTiet()),gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTCLItemStateChanged

    private void cbo_FilterSPCTTHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTTHItemStateChanged
        // TODO add your handling code here:
        String thName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTTH.getSelectedItem();
            if (selectedItem instanceof ThuongHieu) {
                ThuongHieu thuongHieu = (ThuongHieu) selectedItem;
                // Now you can work with the MauSac object
                thName = thuongHieu.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + thName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_CL = thService.getIDbyName(thName);
            System.out.println("id ms: "+id_CL);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterThuongHieu(id_CL);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count,ma, spService.getNameByIDSP(x.getId_SanPham()), trangThai, thService.getNameByID(x.getId_ThuongHieu()) 
                    , msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()), 
                    so_Luong, gia,clService.getNameByID(x.getId_ChatLieu()),kdService.getNameByID(x.getId_KieuDang()), 
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()), 
                    htService.getNameByID(x.getId_HoaTiet()),gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTTHItemStateChanged

    private void cbo_FilterSPCTMPHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_FilterSPCTMPHItemStateChanged
        // TODO add your handling code here:
        String mphName =null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object selectedItem = cbo_FilterSPCTMPH.getSelectedItem();
            if (selectedItem instanceof MuaPhuHop) {
                MuaPhuHop mph = (MuaPhuHop) selectedItem;
                // Now you can work with the MauSac object
                mphName = mph.getTen(); // Assuming there is a method to get the name from MauSac
                System.out.println("Selected MauSac name: " + mphName);
            } else {
                System.out.println("Selected item is not an instance of MauSac");
            }
            int id_MPH = mphService.getIDbyName(mphName);
            System.out.println("id ms: "+id_MPH);
            
            DefaultTableModel tb = (DefaultTableModel) table1.getModel();
            tb.setRowCount(0);
            List<SanPhamCT> listSPCT = spctService.filterMPH(id_MPH);
            int count = 1;
            for (SanPhamCT x : listSPCT) {
                //id_HinhAnh;
                int id_hinhAnh = x.getId_HinhAnh();
                //String duongDanHA = hinhAnhRepo.getNameByID(id_hinhAnh);
                //ma;
                String ma = x.getMa();
                //goi_Tinh;
                boolean gioi_tinh = x.isGoi_Tinh();
                String gioiTinh = null;
                if (gioi_tinh == true) {
                    gioiTinh = "nam";
                } else {
                    gioiTinh = "Nữ";
                }
                //so_Luong;
                float so_Luong = x.getSo_Luong();
                //gia;
                float gia = x.getGia();
                //mo_Ta;
                String mo_ta = x.getMo_Ta();
                //trang_thai;
                int trang_thai = x.getTrang_thai();
                boolean trangThai = true;
                if (trang_thai == 0) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }
                tb.addRow(new Object[]{
                    null,count,ma, spService.getNameByIDSP(x.getId_SanPham()),trangThai, thService.getNameByID(x.getId_ThuongHieu()), 
                    msService.getNameByID(x.getId_MauSac()), ktService.getNameByID(x.getId_KichThuoc()), 
                    so_Luong, gia,clService.getNameByID(x.getId_ChatLieu()),kdService.getNameByID(x.getId_KieuDang()), 
                    mphService.getNameByID(x.getId_MuaPhuHop()), msdsService.getNameByID(x.getId_MDSD()), 
                    htService.getNameByID(x.getId_HoaTiet()),gioiTinh, id_hinhAnh
                });
                count++;
            }
        }
    }//GEN-LAST:event_cbo_FilterSPCTMPHItemStateChanged

    private void rdo_MSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_MSActionPerformed
        // TODO add your handling code here:
        LoadDataToRDOMauSac();
    }//GEN-LAST:event_rdo_MSActionPerformed

    private void rdo_KTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_KTItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOKichThuoc();
        }
    }//GEN-LAST:event_rdo_KTItemStateChanged

    private void rdo_MSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MSItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOMauSac();
        }
    }//GEN-LAST:event_rdo_MSItemStateChanged

    private void rdo_CLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_CLItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOChatLieu();
        }
    }//GEN-LAST:event_rdo_CLItemStateChanged

    private void rdo_KDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_KDItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOKieuDang();
        }
    }//GEN-LAST:event_rdo_KDItemStateChanged

    private void rdo_MPHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MPHItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOMPH();
        }
    }//GEN-LAST:event_rdo_MPHItemStateChanged

    private void rdo_MDSDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MDSDItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOMDSD();
        }
    }//GEN-LAST:event_rdo_MDSDItemStateChanged

    private void rdo_HoaTietItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_HoaTietItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOHoaTiet();
        }
    }//GEN-LAST:event_rdo_HoaTietItemStateChanged

    private void rdo_THItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_THItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Gọi hàm để load dữ liệu kích thước lên table
            LoadDataToRDOThuongHieu();
        }
    }//GEN-LAST:event_rdo_THItemStateChanged

    private void btn_CapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatSPActionPerformed
        // TODO add your handling code here:
        String maSP = txt_MASP.getText();
        if(maSP.equalsIgnoreCase("")){
            MsgBox.alert(this, "Chọn dòng cần sửa!");
            return ;
        }
        String ten = txt_TENSP.getText();
        if(ten.equalsIgnoreCase("")){
            MsgBox.alert(this, "Vui lòng nhập tên sản phẩm!");
            return ;
        }
        try {
            SanPham sp =getDataUpdate();
            spService.UpdateSP(sp);
            LoadDataSP();
            clearInputSP();
            MsgBox.alert(this, "Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật sản phẩm thất bại!");
        }
    }//GEN-LAST:event_btn_CapNhatSPActionPerformed

    private void btn_CapNhatDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatDTActionPerformed
        // TODO add your handling code here:
        String maSP =txt_MA.getText();
        String tenSP = txt_TenSP.getText();
        String tenNT = null;
        String tenNS =null;
        boolean daXoa =true;
        int trangThai=0;
        if(rdo_KT.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    KichThuoc kt = new KichThuoc();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_kich_Thuoc);
                    ktService.UpdateKT(kt);
                    LoadDataToRDOKichThuoc();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MS.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    MauSac kt = new MauSac();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_mau_sac);
                    msService.UpdateMS(kt);
                    LoadDataToRDOMauSac();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_CL.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    ChatLieu kt = new ChatLieu();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_chat_lieu);
                    clService.UpdateCL(kt);
                    LoadDataToRDOChatLieu();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
            
        }
        if(rdo_KD.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    KieuDang kt = new KieuDang();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_kieu_dang);
                    kdService.UpdateSP(kt);
                    LoadDataToRDOKieuDang();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MPH.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    MuaPhuHop kt = new MuaPhuHop();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_MuaPhuHop);
                    mphService.UpdateMPH(kt);
                    LoadDataToRDOMPH();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MDSD.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    MucDichSuDung kt = new MucDichSuDung();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_MDSD);
                    msdsService.UpdateMDSD(kt);
                    LoadDataToRDOMDSD();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_HoaTiet.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    HoaTiet kt = new HoaTiet();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_hoa_tiet);
                    htService.UpdateHT(kt);
                    LoadDataToRDOHoaTiet();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_TH.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Cập Nhật Những Thay Đổi Không?")){
                try {
                    //set kt
                    ThuongHieu kt = new ThuongHieu();
                    kt.setMa(maSP);
                    kt.setTen(tenSP);
                    kt.setTao_boi(tenNT);
                    kt.setSua_boi(tenNS);
                    kt.setDa_xoa(daXoa);
                    kt.setTrang_thai(trangThai);
                    kt.setId(id_thuong_hieu);
                    thService.UpdateTH(kt);
                    LoadDataToRDOThuongHieu();
                    txt_TenSP.setText("");
                    txt_MA.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }//GEN-LAST:event_btn_CapNhatDTActionPerformed

    private void btn_themDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDTActionPerformed
        // TODO add your handling code here:
        if(rdo_KT.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    KichThuoc kt = getInputKichThuoc();
                    ktService.InsertKT(kt);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOKichThuoc();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MS.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    MauSac ms = getInputMauSac();
                    msService.InsertMS(ms);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOMauSac();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_CL.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    ChatLieu cl = getInputChatLieu();
                    clService.InsertCL(cl);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOChatLieu();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
            
        }
        if(rdo_KD.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    KieuDang kd = getInputKieuDang();
                    kdService.InsertSP(kd);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOKieuDang();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
            
        }
        if(rdo_MPH.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    MuaPhuHop mph = getInputMuaPhuHop();
                    mphService.InsertMPH(mph);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOMPH();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MDSD.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    MucDichSuDung mdsd = getInputMucDichSuDung();
                    msdsService.InsertMDSD(mdsd);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOMDSD();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_HoaTiet.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    HoaTiet ht = getInputHoaTiet();
                    htService.InsertHT(ht);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOHoaTiet();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_TH.isSelected()){
            if(MsgBox.confirm(this, "Bạn Có Muốn Thêm Không")){
                try {
                    ThuongHieu th = getInputThuongHieu();
                    thService.InsertTH(th);
                    MsgBox.alert(this, "Thêm Thành Công!");
                    LoadDataToRDOThuongHieu();
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }//GEN-LAST:event_btn_themDTActionPerformed

    private void tableDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDTMouseClicked
         // TODO add your handling code here:
        int row = tableDT.getSelectedRow();
        String MaSP = tableDT.getValueAt(row, 1).toString();
        txt_MA.setText(MaSP);
        //System.out.println(MaSP);
        String TenSP = tableDT.getValueAt(row, 2).toString();
        txt_TenSP.setText(TenSP);
        //id = spService.getAll().get(row).getId();
        if(rdo_KT.isSelected()){
            id_kich_Thuoc = ktService.getALL().get(row).getId();
        }
        if(rdo_MS.isSelected()){
           id_mau_sac = msService.getALL().get(row).getId();
        }
        if(rdo_CL.isSelected()){
            id_chat_lieu = clService.getALL().get(row).getId();
        }
        if(rdo_KD.isSelected()){
            id_kieu_dang = kdService.getAll().get(row).getId();
        }
        if(rdo_MPH.isSelected()){
            id_MuaPhuHop = mphService.getALL().get(row).getId();
        }
        if(rdo_MDSD.isSelected()){
            id_MDSD = msdsService.getAll().get(row).getId();
            System.out.println("id_MDSD: "+id_MDSD);
        }
        if(rdo_HoaTiet.isSelected()){
            id_hoa_tiet = htService.getAll().get(row).getId();
        }
        if(rdo_TH.isSelected()){
            id_thuong_hieu = thService.getALL().get(row).getId();
        }
    }//GEN-LAST:event_tableDTMouseClicked

    private void btn_clearIPDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearIPDTActionPerformed
        // TODO add your handling code here:
        txt_MA.setText("");
        txt_TenSP.setText("");
        rdo_KT.isSelected();
    }//GEN-LAST:event_btn_clearIPDTActionPerformed

    private void btn_CapNhatSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatSPCTActionPerformed
        // TODO add your handling code here:
//        int width = jPanel1.getWidth();
//        int height = jPanel1.getHeight();
//        System.out.println("Width: "+width);
//        System.out.println("Width: "+height);
        
        CapNhatSPCTPannel add = new CapNhatSPCTPannel();
        add.setUpadateDialogForm(this);
        add.setEditData(MASPCT);
        add.setEditIDData(id_SPCT);
        jTabbedPane2.setComponentAt(1, add);
        
//        CapNhatSanPhamCT addSPCT = new CapNhatSanPhamCT();
//        addSPCT.setUpadateDialogForm(this);
          
//        addSPCT.setVisible(true);
    }//GEN-LAST:event_btn_CapNhatSPCTActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int rowClick = table1.getSelectedRow();
        String MaSP = table1.getValueAt(rowClick, 1).toString();
        MASPCT = table1.getValueAt(rowClick, 2).toString();
        id_SPCT = spctService.getALL().get(rowClick).getId();
        if (evt.getClickCount() == 2) {
            var row = table.rowAtPoint(evt.getPoint());
            int col = table.columnAtPoint(evt.getPoint());
            if (row != -1 && col != -1) {
                // Xử lý sự kiện double-click tại đây
                System.out.println("Double-clicked on row " + row + ", column " + col);
                int id_row = spctService.getALL().get(row).getId();
                ChiTietSanPhamCT chiTiet = new ChiTietSanPhamCT();
                chiTiet.setEditIDData(id_row);
                chiTiet.setVisible(true);
            }
        }
//        if(evt.getClickCount()==2){
//            ChiTietSanPhamCT chiTiet = new ChiTietSanPhamCT();
//            chiTiet.setVisible(true);
//        }
    }//GEN-LAST:event_table1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        SanPham sp =new SanPham();
        int trangThai=1;
        sp.setTrang_thai(trangThai);
        boolean daXoa =false;
        sp.setDa_xoa(daXoa);
        
        sp.setId(id);
        if(MsgBox.confirm(this, "Ban co muon xoa khong?")){
            try {
                spService.DeleteSp(sp);
                MsgBox.alert(this, "Xoa Thanh Cong!");
                LoadDataSP();
                clearInputSP();
            } catch (Exception e) {
                e.printStackTrace();
                MsgBox.alert(this, "Xoa That Bai!");
            }
        }
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btn_XoaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPCTActionPerformed
        // TODO add your handling code here:
        SanPhamCT spct = new SanPhamCT();
        
        System.out.println("test id_SPCT "+id_SPCT );
        int trangThai=1;
        spct.setTrang_thai(trangThai);
        boolean daXoa =false;
        spct.setDa_xoa(daXoa);
        
        spct.setId(id_SPCT);
        if(MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")){
            try {
                spctService.DeleteSpct(spct);
                MsgBox.alert(this, "Xoá Thành Công!");
                LoadSPCT(1);
                clearInputSP();
            } catch (Exception e) {
                e.printStackTrace();
                MsgBox.alert(this, "Xoá Thất Bại!");
            }
        }
    }//GEN-LAST:event_btn_XoaSPCTActionPerformed

    private void btn_XoaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaDTActionPerformed
        // TODO add your handling code here:
        int trang_Thai =1;
        boolean daXoa= false;
        
        if(rdo_KT.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    KichThuoc kt = new KichThuoc();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_kich_Thuoc);
                    ktService.DeleteKT(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOKichThuoc();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(rdo_MS.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt

                    MauSac kt = new MauSac();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_mau_sac);
                    msService.DeleteMS(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOMauSac();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_CL.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    ChatLieu kt = new ChatLieu();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_chat_lieu);
                    clService.DeleteCl(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOChatLieu();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_KD.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    KieuDang kt = new KieuDang();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_kieu_dang);
                    kdService.DeleteKD(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOKieuDang();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MPH.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    MuaPhuHop kt = new MuaPhuHop();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_MuaPhuHop);
                    mphService.DeleteMPH(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOMPH();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_MDSD.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    MucDichSuDung mdsd = new MucDichSuDung();
                    mdsd.setTrang_thai(trang_Thai);
                    mdsd.setDa_xoa(daXoa);
                    mdsd.setId(id_MDSD);
                    //System.out.println(id_MDSD);
                    msdsService.DeleteMDSD(mdsd);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOMDSD();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_HoaTiet.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    HoaTiet kt = new HoaTiet();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_hoa_tiet);
                    htService.DeleteHT(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOHoaTiet();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        if(rdo_TH.isSelected()){
            if (MsgBox.confirm(this, "Bạn Có Muốn Xoá Không?")) {
                try {
                    //set kt
                    ThuongHieu kt = new ThuongHieu();
                    kt.setTrang_thai(trang_Thai);
                    kt.setDa_xoa(daXoa);
                    kt.setId(id_thuong_hieu);
                    thService.DeteleTH(kt);
                    MsgBox.alert(this, "Xoá Thành Công!");
                    LoadDataToRDOThuongHieu();
                    txt_MA.setText("");
                    txt_TenSP.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }//GEN-LAST:event_btn_XoaDTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tb = (DefaultTableModel) tableDT.getModel();
        tb.setRowCount(0);
        String textSearch = txt_Search.getText();
        if (rdo_KT.isSelected()) {
            try {
                List<KichThuoc> listKT=ktService.Search(textSearch);
                int count = 1;
                for (KichThuoc x : listKT) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_MS.isSelected()) {
            try {

                List<MauSac> listMS = msService.Search(textSearch);
                int count = 1;
                for (MauSac x : listMS) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_CL.isSelected()) {
            try {
                List<ChatLieu> listCL = clService.Search(textSearch);
                int count = 1;
                for (ChatLieu x : listCL) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_KD.isSelected()) {
            try {
                List<KieuDang> listKD = kdService.Search(textSearch);

                int count = 1;
                for (KieuDang x : listKD) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_MPH.isSelected()) {
            try {
                List<MuaPhuHop> listMPH = mphService.Search(textSearch);
                int count = 1;
                for (MuaPhuHop x : listMPH) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_MDSD.isSelected()) {
            try {
                List<MucDichSuDung> listMDSD = msdsService.Search(textSearch);

                int count = 1;
                for (MucDichSuDung x : listMDSD) {
                    boolean trang_Thai = true;
                    //System.out.println(trang_Thai);
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_HoaTiet.isSelected()) {
            try {
                List<HoaTiet> listHT = htService.Search(textSearch);
                int count = 1;
                for (HoaTiet x : listHT) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (rdo_TH.isSelected()) {
            try {
                List<ThuongHieu> listTH = thService.Search(textSearch);
                int count = 1;
                for (ThuongHieu x : listTH) {
                    boolean trang_Thai = true;
                    if (x.getTrang_thai() != 0) {
                        trang_Thai = false;
                    }
                    tb.addRow(new Object[]{
                        count, x.getMa(), x.getTen(), trang_Thai
                    });
                    count++;
                }
                //txt_TenSP.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        TestWebCamQrcode webCam= new TestWebCamQrcode();
//        webCam.setUpadateDialogForm(this);
//        webCam.setVisible(true);
            innitCam();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (webCam.isOpen()) {
            webCam.close();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhatDT;
    private javax.swing.JButton btn_CapNhatSP;
    private javax.swing.JButton btn_CapNhatSPCT;
    private javax.swing.JButton btn_ClearSP;
    private javax.swing.JButton btn_ThemSPCT;
    private javax.swing.JButton btn_ThemSp;
    private javax.swing.JButton btn_XoaDT;
    private javax.swing.JButton btn_XoaSPCT;
    private javax.swing.JButton btn_clearIPDT;
    private javax.swing.JButton btn_searchSp;
    private javax.swing.JButton btn_themDT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_FilterSPCTCL;
    private javax.swing.JComboBox<String> cbo_FilterSPCTKD;
    private javax.swing.JComboBox<String> cbo_FilterSPCTKT;
    private javax.swing.JComboBox<String> cbo_FilterSPCTMPH;
    private javax.swing.JComboBox<String> cbo_FilterSPCTMS;
    private javax.swing.JComboBox<String> cbo_FilterSPCTTH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField4;
    private pagination.Pagination pagination1;
    private javax.swing.JRadioButton rdo_CL;
    private javax.swing.JRadioButton rdo_HoaTiet;
    private javax.swing.JRadioButton rdo_KD;
    private javax.swing.JRadioButton rdo_KT;
    private javax.swing.JRadioButton rdo_MDSD;
    private javax.swing.JRadioButton rdo_MPH;
    private javax.swing.JRadioButton rdo_MS;
    private javax.swing.JRadioButton rdo_SpHD;
    private javax.swing.JRadioButton rdo_SpKHD;
    private javax.swing.JRadioButton rdo_TH;
    private view.component.Table table;
    private view.component.Table table1;
    private view.component.Table tableDT;
    private javax.swing.JTextField txt_MA;
    private javax.swing.JTextField txt_MASP;
    private javax.swing.JTextField txt_Search;
    private javax.swing.JTextField txt_TENSP;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextField txt_searchSP;
    // End of variables declaration//GEN-END:variables

    private void innitCam(){
        Dimension size= WebcamResolution.QVGA.getSize();
        webCam= Webcam.getWebcams().get(0);
        webCam.setViewSize(size);
        panel = new WebcamPanel(webCam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        //jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 350));
        //jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 350));
        jPanel11.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 220));
        executor.execute(this);
    }
    
    @Override
    public void run() {
        do{
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Result result=null;
            BufferedImage image =null;
            if(webCam.isOpen()){
                if((image = webCam.getImage())==null){
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitMap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitMap);
                
            } catch (Exception e) {
                //e.printStackTrace();
            }
            if(result!=null){
                System.out.println(result.getText());
                LoadWithMASPCT(result.getText());
            }
        }while(true);
    }

    @Override
    public Thread newThread(Runnable r) {
       Thread t = new Thread(r,"My Thread");
        t.setDaemon(true);//rootPaneCheckingEnabled
        return t;
    }
}
