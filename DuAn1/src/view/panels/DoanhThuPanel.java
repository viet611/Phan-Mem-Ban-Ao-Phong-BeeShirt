/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panels;

import com.raven.datechooser.DateChooser;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import services.ThongKeService;
import services.impl.HoaDonServiceImpl;
import services.impl.SanPhamImpl;
import services.impl.ThongKeImpl;
import ultilities.MsgBox;

/**
 *
 * @author Admin
 */
public class DoanhThuPanel extends javax.swing.JPanel {
    private DateChooser date = new DateChooser();
    private DateChooser date1 = new DateChooser();
    private DateChooser date2 = new DateChooser();
    private DateChooser date3 = new DateChooser();
    private DateChooser date4 = new DateChooser();
    ThongKeImpl tkService = new ThongKeImpl();
    HoaDonServiceImpl hdServiceImpl = new HoaDonServiceImpl();
    SanPhamImpl spService = new SanPhamImpl();
    
    /**
     * Creates new form Form_1
     */
    public DoanhThuPanel() {
        initComponents();
        date.setTextField(txt_DateDay);
        date.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        
        date1.setTextField(txt_DateTo);
        date1.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        date2.setTextField(txt_DateFrom);
        date2.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        
        date3.setTextField(txt_ToBD);
        date3.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        date4.setTextField(txt_FormBD);
        date4.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        
        LocalDate dt =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        txt_DateDay.setText(dt.format(formatter));
        
        jLabel2.setSize(250, 145);
        jLabel4.setSize(250, 145);
        jLabel6.setSize(250, 145);
        
        if(rdo_ngay.isSelected()){
            LoadDataThongKe();
            txt_Month.setEnabled(false);
            txt_Year.setEnabled(false);
            txt_DateFrom.setEnabled(false);
            txt_DateTo.setEnabled(false);
        }
        String relativePath = "src\\view\\icon";
        String currentWorkingDirectory = System.getProperty("user.dir");
        Path fullPath1 = Paths.get(currentWorkingDirectory, relativePath);
        String fullPath = fullPath1.toString() +"\\" + "dt2.jpg";
        String path2 = fullPath1.toString() +"\\" + "dt4.jpg";
        String path3 = fullPath1.toString() +"\\" + "dt1.jpg";
        String path1= fullPath;
        jLabel2.setIcon(ResizeImage(String.valueOf(path1)));
        jLabel4.setIcon(ResizeImage(String.valueOf(path3)));
        jLabel6.setIcon(ResizeImage(String.valueOf(path2)));
        
        LoadDefaultDate();
        LoadDefaultMoth();
        LoadDefaultYear();
        LoadTOPSP();
        jTabbedPane1.setEnabledAt(1, false);
//        rdo_MONTHDB.setSelected(true);
        if(rdo_MONTHDB.isSelected()){
            txt_YearBD.setEnabled(false);
            txt_ToBD.setEnabled(false);
            txt_FormBD.setEnabled(false);
        }
    }
    
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    private void LoadDefaultDate(){
        String date = txt_DateDay.getText();
        LocalDate currentDate = LocalDate.now();
        // Chuyển đổi ngày thành chuỗi với định dạng mong muốn (ở đây là "dd/MM/yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentDate.format(formatter);
        String TestDate ="24-11-2023";
        System.out.println("dateString "+dateString);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getALLHDCT1(dateString);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            String stringValue = String.valueOf(tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel3.setText("Tổng DT "+dateString+": "+decimalFormat.format(tong)+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LoadDefaultMoth(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String stringValue = String.valueOf(currentMonth);
        System.out.println("stringValue "+stringValue);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getHDByMonth(stringValue);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel5.setText("Tổng DT Tháng"+stringValue+": "+decimalFormat.format(tong)+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadDefaultYear(){
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String stringValue = String.valueOf(currentYear);
        System.out.println("stringValue "+stringValue);
        int tong =0;
        try {
            List<HoaDon> hdct = tkService.getHDByYear(stringValue);
            for (HoaDon x : hdct) {
                tong+=x.getTongTien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            jLabel7.setText("Tổng DT Năm"+stringValue+": "+decimalFormat.format(tong)+" VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadTOPSP(){
        DefaultTableModel tb = (DefaultTableModel) table2.getModel();
        tb.setRowCount(0);
        int checkID = 0;
        int STT = 1;
        List<HoaDonChiTiet> hdct = tkService.getSumSoLuong();
        for (HoaDonChiTiet x : hdct) {
            //System.out.println("Test getIDSP: " + x.getSanPhamChiTiet().getId());
//            int sumSoLuong = tkService.getSumSoLuong(x.getSanPhamChiTiet().getId());
//            System.out.println("Test Số lượng with IDSP: " + sumSoLuong);
            
            if (x.getSanPhamChiTiet().getId() == checkID) {

            } else {
                String TenSP = spService.getNameID(x.getSanPhamChiTiet().getId_SanPham());
                int sumSoLuong = x.getSoLuong();
                //System.out.println("Test Số lượng with IDSP: " + sumSoLuong);
                tb.addRow(new Object[]{
                    STT, TenSP, sumSoLuong,x.getSanPhamChiTiet().getGia()
                });
                STT++;
            }
            checkID = x.getSanPhamChiTiet().getId();
        }
    }

    private void LoadDataThongKe(){
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        //List<HoaDonChiTiet> hdct=tkService.getALLHDCT();
        String date = txt_DateDay.getText();
        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateNow = dateFormat.parse(date);
//            java.sql.Date sqlDate = new java.sql.Date(dateNow.getTime());
//            System.out.println("formattedDate: "+sqlDate);
            List<HoaDon> hdct = tkService.getALLHDCT1(date);
            int STT = 1;
            for (HoaDon x : hdct) {
//            int id_hoaDon= x.getHoaDon().getId();
//            System.out.println("id_hD: "+id_hoaDon);
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void LoadThongKeMonth(){
        String Month = txt_Month.getText();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        try {
            List<HoaDon> hdct = tkService.getHDByMonth(Month);
            int STT = 1;
            for (HoaDon x : hdct) {
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadThongKeYear(){
        String Year = txt_Year.getText();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        try {
            List<HoaDon> hdct = tkService.getHDByYear(Year);
            int STT = 1;
            for (HoaDon x : hdct) {
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void LoadThongKeBewwenAnd(){
        String dateTo = txt_DateTo.getText();
        String dateFrom = txt_DateFrom.getText();
        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        try {
            List<HoaDon> hd = tkService.getHDByBetweenAnd(dateTo, dateFrom);
            int STT=1;
            for(HoaDon x: hd){
                tb.addRow(new Object[]{
                    STT, x.getMa(), x.getNgayTao(), x.getTongTien()
                });
                STT++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Double LoadDoanhThuYear(String year, String month){
        double TongDT =0;
        try {
            List<HoaDon> hdct = tkService.getChartByYear(year,month);
            for (HoaDon x : hdct) {
                TongDT+=x.getTongTien();
            }
            //System.out.println("TongDT: "+TongDT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TongDT;
    }
    
    private void setDataToChartYear(JPanel jpanel){
        String Year = txt_YearBD.getText();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        List<HoaDon> hd = tkService.getHDByYear(Year);
//        for(HoaDon x:hd){
//            dataset.addValue(x.getTongTien(), "Doanh Thu", x.getNgayTao());
//        }
        //List<HoaDon> hddt=tkService.getChartByYear(Year, Year);
        double TongDT =0;
        for(int i=1;i<13;i++){
            String stringMonth = String.valueOf(i);
            //List<HoaDon> hddt=tkService.getChartByYear(Year, stringMonth);
            TongDT=LoadDoanhThuYear(Year, stringMonth);
            System.out.println("Tong Doanh Thu "+stringMonth+": "+TongDT);
            
            dataset.addValue(TongDT, "Doanh Thu", stringMonth);
        }
        
        JFreeChart chart = ChartFactory.createBarChart("Doanh Thu Nam "+txt_YearBD.getText(), "Thoi Gian", "So Tien", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel.getWidth(),jpanel.getHeight()));
        
        jpanel.removeAll();
        jpanel.setLayout(new CardLayout());
        jpanel.add(chartPanel);
        jpanel.validate();
        jpanel.repaint();
    }
    
    
    private Double LoadDoanhThuDateInMonth(String date,String month){
        double TongDT =0;
        try {
            List<HoaDon> hdct = tkService.getChartByMonth(date, month);
            for (HoaDon x : hdct) {
                TongDT+=x.getTongTien();
            }
            //System.out.println("TongDT: "+TongDT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TongDT;
    }
    private void setDataToChartMonth(JPanel jpanel){
        String month = txt_MonthBD.getText();
        String Year = "2023";
        int year = Integer.parseInt(Year);
        int Month = Integer.parseInt(month);
        YearMonth yearMonth = YearMonth.of(year, Month);
        int daysInMonth = yearMonth.lengthOfMonth();
        //System.out.println("daysInMonth:  "+daysInMonth);
        
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String stringValue = String.valueOf(currentMonth);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double TongDT =0;
        //List<HoaDon> hd = tkService.getHDByMonth(month);
        
        for(int i=1;i<daysInMonth;i++){
            
            String stringMonth = String.valueOf(i);
            //String dateMonth =stringMonth+"-"+stringValue+"-"+Year;
            //
            TongDT = LoadDoanhThuDateInMonth(stringMonth,month);
//            double checkTONGDT =0;
//            System.out.println("tesstdagda: "+TongDT);
//            if(TongDT==checkTONGDT){
//                TongDT=i*10000;
//            }
            //TongDT = LoadDoanhThuDateInMonth(date);
            dataset.addValue(TongDT, "Doanh Thu", stringMonth);
        }
        JFreeChart chart = ChartFactory.createBarChart("Doanh Thu Thang "+txt_MonthBD.getText(), "Thoi Gian", "So Tien", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel.getWidth(),jpanel.getHeight()));
        jpanel.removeAll();
        jpanel.setLayout(new CardLayout());
        jpanel.add(chartPanel);
        jpanel.validate();
        jpanel.repaint();
    }
    
    private void setDataToCharDefaulttMonth(JPanel jpanel){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String stringValue = String.valueOf(currentMonth);
        txt_MonthBD.setText(stringValue);
        
        String month = stringValue;
        String Year = "2023";
        int year = Integer.parseInt(Year);
        int Month = Integer.parseInt(month);
        YearMonth yearMonth = YearMonth.of(year, Month);
        int daysInMonth = yearMonth.lengthOfMonth();
        //System.out.println("daysInMonth:  "+daysInMonth);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double TongDT =0;
        //List<HoaDon> hd = tkService.getHDByMonth(month);
        
        for(int i=1;i<daysInMonth;i++){
            //String date =i+"-"+stringValue+"-"+Year;
            String stringMonth = String.valueOf(i);
            //System.out.println("tesstdagda: "+date);
            TongDT = LoadDoanhThuDateInMonth(stringMonth,month);
//            double checkTONGDT =0;
            //System.out.println("tesstdagda: "+TongDT);
//            if(TongDT==checkTONGDT){
//                TongDT=i*10000;
//            }
//            System.out.println("tesstdagda: "+TongDT);
            dataset.addValue(TongDT, "Doanh Thu", stringMonth);
            
        }
        JFreeChart chart = ChartFactory.createBarChart("Doanh Thu Thang "+month, "Thoi Gian", "So Tien", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel.getWidth(),jpanel.getHeight()));
        jpanel.removeAll();
        jpanel.setLayout(new CardLayout());
        jpanel.add(chartPanel);
        jpanel.validate();
        jpanel.repaint();
    }
    
    private void setDataToCharBetWeenAnd(JPanel jpanel){
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double TongDT =0;
        //List<HoaDon> hd = tkService.getHDByMonth(month);
        String dateTo = txt_ToBD.getText();
        String dateFrom = txt_FormBD.getText();
        List<HoaDon> hd = tkService.getChartByBteweenAnd(dateTo, dateFrom);
        for(HoaDon x: hd){
            TongDT = x.getTongTien();
            dataset.addValue(TongDT, "Doanh Thu" , x.getNgayTao());
        }
        
        JFreeChart chart = ChartFactory.createBarChart("Bieu Do Doanh Thu "+txt_ToBD.getText() +"-"+txt_FormBD.getText(), 
                "Thoi Gian", "So Tien", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel.getWidth(),jpanel.getHeight()));
        jpanel.removeAll();
        jpanel.setLayout(new CardLayout());
        jpanel.add(chartPanel);
        jpanel.validate();
        jpanel.repaint();
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        rdo_ngay = new javax.swing.JRadioButton();
        rdo_thang = new javax.swing.JRadioButton();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_betweenAnd = new javax.swing.JRadioButton();
        txt_DateDay = new javax.swing.JTextField();
        txt_Month = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_DateTo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_DateFrom = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_Year = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new view.component.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new view.component.Table();
        jPanel5 = new javax.swing.JPanel();
        jpanel_chart = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        rdo_MONTHDB = new javax.swing.JRadioButton();
        rdo_YEARBD = new javax.swing.JRadioButton();
        rdo_KHOANGBD = new javax.swing.JRadioButton();
        txt_MonthBD = new javax.swing.JTextField();
        txt_YearBD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_ToBD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_FormBD = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thống Kê Doanh Thu");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup2.add(rdo_ngay);
        rdo_ngay.setSelected(true);
        rdo_ngay.setText("TK Theo Ngày");
        rdo_ngay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_ngayItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_thang);
        rdo_thang.setText("TK Theo Tháng");
        rdo_thang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_thangItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_nam);
        rdo_nam.setText("TK Theo Năm");
        rdo_nam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_namItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rdo_betweenAnd);
        rdo_betweenAnd.setText("TK Theo Khoảng Ngày");
        rdo_betweenAnd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_betweenAndItemStateChanged(evt);
            }
        });

        txt_DateDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_DateDayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_DateDayFocusLost(evt);
            }
        });

        txt_Month.setText("Thống Kê Theo Tháng");
        txt_Month.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_MonthFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_MonthFocusLost(evt);
            }
        });

        jLabel8.setText("Tu");

        jLabel9.setText("Den");

        jButton1.setText("Thống Kê");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_Year.setText("Thống Kê Theo Năm");
        txt_Year.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_YearFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_YearFocusLost(evt);
            }
        });

        jButton3.setText("View Bieu Do");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_DateDay)
                    .addComponent(rdo_ngay, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdo_thang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Month, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdo_nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Year, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdo_betweenAnd))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DateFrom))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(21, 21, 21))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_ngay)
                    .addComponent(rdo_thang)
                    .addComponent(rdo_nam)
                    .addComponent(rdo_betweenAnd)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_DateDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_DateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_DateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma HD", "Ngay Tao", "Thanh Tien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ten SP", "So Luong", "Gia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thống Kê Doanh Thu", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpanel_chartLayout = new javax.swing.GroupLayout(jpanel_chart);
        jpanel_chart.setLayout(jpanel_chartLayout);
        jpanel_chartLayout.setHorizontalGroup(
            jpanel_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanel_chartLayout.setVerticalGroup(
            jpanel_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup3.add(rdo_MONTHDB);
        rdo_MONTHDB.setSelected(true);
        rdo_MONTHDB.setText("Tháng");
        rdo_MONTHDB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_MONTHDBItemStateChanged(evt);
            }
        });

        buttonGroup3.add(rdo_YEARBD);
        rdo_YEARBD.setText("Năm");
        rdo_YEARBD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_YEARBDItemStateChanged(evt);
            }
        });

        buttonGroup3.add(rdo_KHOANGBD);
        rdo_KHOANGBD.setText("Khoảng Thời Gian");
        rdo_KHOANGBD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_KHOANGBDItemStateChanged(evt);
            }
        });

        txt_MonthBD.setText("Biểu Đồ Theo Tháng");
        txt_MonthBD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_MonthBDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_MonthBDFocusLost(evt);
            }
        });

        txt_YearBD.setText("Biểu Đồ Theo Năm");
        txt_YearBD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_YearBDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_YearBDFocusLost(evt);
            }
        });

        jLabel11.setText("Từ");

        jLabel12.setText("Đến");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Bieu Do");
        jButton2.setActionCommand("Biểu Đồ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_MONTHDB, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MonthBD, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_YEARBD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_YearBD, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ToBD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdo_KHOANGBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_FormBD, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_MONTHDB)
                    .addComponent(rdo_YEARBD)
                    .addComponent(rdo_KHOANGBD)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MonthBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_YearBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_ToBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_FormBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpanel_chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpanel_chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Biểu Đồ Thống Kê Doanh Thu", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Thống Kê Doanh Thu");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(txt_DateDay.getText());
        if(rdo_ngay.isSelected()){
            LoadDataThongKe();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            //float floatValue = Float.parseFloat(Tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            //String formattedNumber = decimalFormat.format(Tong);
            jLabel3.setText("Tổng Doanh Thu Ngày "+txt_DateDay.getText()+":"+decimalFormat.format(Tong)+" VND");
        }
        
        if(rdo_thang.isSelected()){
            int checkMonth = Integer.parseInt(txt_Month.getText());
            if(checkMonth<=0||checkMonth>12){
                MsgBox.warring(this, "Nhập Lại Tháng!");
                return;
            }
            LoadThongKeMonth();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            //float floatValue = Float.parseFloat(Tong);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            //String formattedNumber = decimalFormat.format(Tong);
            //lbl_Tong.setText("Tổng Doanh Thu: "+decimalFormat.format(Tong)+" VND");
            jLabel5.setText("Tổng Doanh Thu Tháng "+txt_Month.getText()+":"+decimalFormat.format(Tong)+" VND");
        }
        
        if(rdo_nam.isSelected()){
            int checkYear = Integer.parseInt(txt_Year.getText());
            if(checkYear<2021||checkYear>2023){
                MsgBox.warring(this, "Nhap Lai Nam!");
                return;
            }
            LoadThongKeYear();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                System.out.println("Tong: "+Tong);
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            //lbl_Tong.setText("Tổng Doanh Thu: "+decimalFormat.format(Tong)+" VND"); 
            jLabel7.setText("Tổng Doanh Thu Năm "+txt_Year.getText()+": "+decimalFormat.format(Tong)+" VND");
            
        }
        
        if(rdo_betweenAnd.isSelected()){
            LoadThongKeBewwenAnd();
            double Tong = 0 ;
            for(int i=0;i<table1.getRowCount();i++){
                Tong += Double.parseDouble(table1.getValueAt(i, 3).toString());
                //System.out.println("Tong: "+Tong);
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        }
        //System.out.println("width: "+jLabel2.getWidth() +" +"+jLabel4.getWidth() +"+"+jLabel6.getWidth() );
        //System.out.println("Height: "+jLabel2.getHeight()+" +"+jLabel4.getHeight()+"+"+jLabel6.getHeight());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdo_ngayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_ngayItemStateChanged
        // TODO add your handling code here:
        LocalDate dt =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        txt_DateDay.setText(dt.format(formatter));
        //txt_Year.setText("");
        //txt_Month.setText("");
        txt_DateTo.setText("");
        txt_DateFrom.setText("");
        txt_DateDay.setEnabled(true);
        txt_Month.setEnabled(false);
        txt_Year.setEnabled(false);
        txt_DateFrom.setEnabled(false);
        txt_DateTo.setEnabled(false);
    }//GEN-LAST:event_rdo_ngayItemStateChanged

    private void rdo_namItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_namItemStateChanged
        // TODO add your handling code here:
        txt_DateDay.setText("");
        //txt_Month.setText("");
        txt_DateTo.setText("");
        txt_DateFrom.setText("");
        txt_DateDay.setEnabled(false);
        txt_Month.setEnabled(false);
        txt_Year.setEnabled(true);
        txt_DateFrom.setEnabled(false);
        txt_DateTo.setEnabled(false);
    }//GEN-LAST:event_rdo_namItemStateChanged

    private void rdo_thangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_thangItemStateChanged
        // TODO add your handling code here:
        txt_DateDay.setText("");
        //txt_Month.setText("");
        //txt_Year.setText("");
        txt_DateTo.setText("");
        txt_DateFrom.setText("");
        txt_Month.setEnabled(true);
        txt_Year.setEnabled(false);
        txt_DateDay.setEnabled(false);
        txt_DateFrom.setEnabled(false);
        txt_DateTo.setEnabled(false);
    }//GEN-LAST:event_rdo_thangItemStateChanged

    private void rdo_betweenAndItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_betweenAndItemStateChanged
        // TODO add your handling code here:
        txt_DateDay.setText("");
        txt_Month.setText("");
        txt_Year.setText("");
        txt_Month.setEnabled(false);
        txt_Year.setEnabled(false);
        txt_DateDay.setEnabled(false);
        
        txt_DateFrom.setEnabled(true);
        txt_DateTo.setEnabled(true);
    }//GEN-LAST:event_rdo_betweenAndItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(rdo_YEARBD.isSelected()){
            int checkYear= Integer.parseInt(txt_YearBD.getText());
            if(checkYear<2021||checkYear>2023){
                MsgBox.warring(this, "Nhap Lai Nam!");
                return;
            }
            setDataToChartYear(jpanel_chart);
        }
        
        if(rdo_KHOANGBD.isSelected()){
            setDataToCharBetWeenAnd(jpanel_chart);
        }
        
        if(rdo_MONTHDB.isSelected()){
            int checkMonth = Integer.parseInt(txt_MonthBD.getText());
            if(checkMonth<=0||checkMonth>12){
                MsgBox.warring(this, "Nhap Lai Thang!");
                return;
            }
            setDataToChartMonth(jpanel_chart);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rdo_MONTHDBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_MONTHDBItemStateChanged
        // TODO add your handling code here:
        txt_FormBD.setText("");
        txt_ToBD.setText("");
        
        txt_MonthBD.setText("Biểu Đồ Theo Tháng");
        
        //txt_YearBD.setText("");
        txt_FormBD.setEnabled(false);
        txt_ToBD.setEnabled(false);
        txt_YearBD.setEnabled(false);
        txt_MonthBD.setEnabled(true);
    }//GEN-LAST:event_rdo_MONTHDBItemStateChanged

    private void rdo_YEARBDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_YEARBDItemStateChanged
        // TODO add your handling code here:
        txt_FormBD.setText("");
        txt_ToBD.setText("");
        //txt_MonthBD.setText("");
        txt_FormBD.setEnabled(false);
        txt_ToBD.setEnabled(false);
        txt_MonthBD.setEnabled(false);
        txt_YearBD.setEnabled(true);
    }//GEN-LAST:event_rdo_YEARBDItemStateChanged

    private void rdo_KHOANGBDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_KHOANGBDItemStateChanged
        // TODO add your handling code here:
        //txt_MonthBD.setText("");
        //txt_YearBD.setText("");
        txt_MonthBD.setEnabled(false);
        txt_YearBD.setEnabled(false);
        txt_ToBD.setEnabled(true);
        txt_FormBD.setEnabled(true);
    }//GEN-LAST:event_rdo_KHOANGBDItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(1, true);
        setDataToCharDefaulttMonth(jpanel_chart);
        jTabbedPane1.setSelectedIndex(1);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_DateDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DateDayFocusGained
        // TODO add your handling code here:
        if (txt_DateDay.getText().equalsIgnoreCase("Thong Ke Theo Ngay")) {
            txt_DateDay.setText(null);
        }
    }//GEN-LAST:event_txt_DateDayFocusGained

    private void txt_DateDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DateDayFocusLost
        // TODO add your handling code here:
        if(txt_DateDay.getText().length()==0){
            txt_DateDay.setText("Thong Ke Theo Ngay");
        }
    }//GEN-LAST:event_txt_DateDayFocusLost

    private void txt_MonthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MonthFocusGained
        // TODO add your handling code here:
        if (txt_Month.getText().equalsIgnoreCase("Thống Kê Theo Tháng")) {
            txt_Month.setText(null);
        }
    }//GEN-LAST:event_txt_MonthFocusGained

    private void txt_MonthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MonthFocusLost
        // TODO add your handling code here:
        if(txt_Month.getText().length()==0){
            txt_Month.setText("Thống Kê Theo Tháng");
        }
    }//GEN-LAST:event_txt_MonthFocusLost

    private void txt_YearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_YearFocusGained
        // TODO add your handling code here:
        if(txt_Year.getText().equalsIgnoreCase("Thống Kê Theo Năm")){
            txt_Year.setText(null);
        }
            
    }//GEN-LAST:event_txt_YearFocusGained

    private void txt_YearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_YearFocusLost
        // TODO add your handling code here:
        if(txt_Year.getText().length()==0){
            txt_Year.setText("Thống Kê Theo Năm");
        }
    }//GEN-LAST:event_txt_YearFocusLost

    private void txt_MonthBDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MonthBDFocusGained
        // TODO add your handling code here:
        if(txt_MonthBD.getText().equalsIgnoreCase("Biểu Đồ theo Tháng")){
            txt_MonthBD.setText(null);
        }
    }//GEN-LAST:event_txt_MonthBDFocusGained

    private void txt_MonthBDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MonthBDFocusLost
        // TODO add your handling code here:
        if(txt_MonthBD.getText().length()==0){
            txt_MonthBD.setText("Biểu Đồ Theo Tháng");
        }
    }//GEN-LAST:event_txt_MonthBDFocusLost

    private void txt_YearBDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_YearBDFocusGained
        // TODO add your handling code here:
        if(txt_YearBD.getText().equalsIgnoreCase("Biểu Đồ Theo Năm")){
            txt_YearBD.setText(null);
        }
    }//GEN-LAST:event_txt_YearBDFocusGained

    private void txt_YearBDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_YearBDFocusLost
        // TODO add your handling code here:
        if(txt_YearBD.getText().length()==0){
            txt_YearBD.setText("Biểu Đồ Theo Năm");
        }
    }//GEN-LAST:event_txt_YearBDFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel_chart;
    private javax.swing.JRadioButton rdo_KHOANGBD;
    private javax.swing.JRadioButton rdo_MONTHDB;
    private javax.swing.JRadioButton rdo_YEARBD;
    private javax.swing.JRadioButton rdo_betweenAnd;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_ngay;
    private javax.swing.JRadioButton rdo_thang;
    private view.component.Table table1;
    private view.component.Table table2;
    private javax.swing.JTextField txt_DateDay;
    private javax.swing.JTextField txt_DateFrom;
    private javax.swing.JTextField txt_DateTo;
    private javax.swing.JTextField txt_FormBD;
    private javax.swing.JTextField txt_Month;
    private javax.swing.JTextField txt_MonthBD;
    private javax.swing.JTextField txt_ToBD;
    private javax.swing.JTextField txt_Year;
    private javax.swing.JTextField txt_YearBD;
    // End of variables declaration//GEN-END:variables
}
