import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame{
    private String typeUser;
    private JMenuBar menuBar;
    private JMenu sinhVien;
    private JMenu thoiKhoaBieu;
    private JMenu hocPhan;
    private JMenu diemSo;
    private JMenu taiKhoan;
    private JMenuItem sinhVienImport;
    private JMenuItem sinhVienXem;
    private JMenuItem thoiKhoaBieuImport;
    private JMenuItem thoiKhoaBieuXem;
    private JMenuItem hocPhanThem;
    private JMenuItem hocPhanHuy;
    private JMenuItem hocPhanXem;
    private JMenuItem diemImport;
    private JMenuItem diemXem;
    private JMenuItem diemSua;
    private JMenuItem doiMatKhau;
    private JMenuItem thoat;
    private JPanel panel;
    private JPanel panel1;

    public String getTypeUser() {
        return typeUser;
    }

    public GUI(){
        super();
        setTitle("My App");
        panel1 = new JPanel();
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }
    public void intializeMenuBar(){
        setSize(600, 600);
        sinhVien = new JMenu("Sinh viên");
        thoiKhoaBieu = new JMenu("Thời khóa biểu");
        hocPhan = new JMenu("Học phần");
        diemSo = new JMenu("Điểm số");
        taiKhoan = new JMenu("Tài khoản");
        doiMatKhau = new JMenuItem("Đổi mật khẩu");
        thoat = new JMenuItem("Thoát");
        taiKhoan.add(doiMatKhau);
        taiKhoan.add(thoat);
        thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel1.removeAll();
                for(int i = 0; i < getComponentCount(); i++){
                    if(getComponent(i) == panel1){
                        remove(panel1);
                    }
                }
                pack();
                menuBar.removeAll();
                remove(menuBar);
                login();
                invalidate();
                validate();
                repaint();
            }
        });
        doiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel1.removeAll();
                for(int i = 0; i < getComponentCount(); i++){
                    if(getComponent(i) == panel1){
                        remove(panel1);
                    }
                }
                pack();
                panel.setLayout(new GridLayout(3, 1));
                JLabel oldPass = new JLabel("Mật khẩu cũ");
                JLabel newPass = new JLabel("Mật khẩu mới");
                JPasswordField oldC = new JPasswordField();
                JPasswordField newC = new JPasswordField();
                JLabel mes = new JLabel();
                JButton summit = new JButton("Đồng ý");
                panel.add(oldPass);
                panel.add(oldC);
                panel.add(newPass);
                panel.add(newC);
                panel.add(mes);
                panel.add(summit);
                summit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DangNhap dn = DangNhapDAO.layThongTinDangNhap(typeUser, oldC.getText());
                        if(dn == null){
                            mes.setText("Sai mật khẩu");
                        }else{
                            DangNhapDAO.doiMatKhau(typeUser, newC.getText());
                            mes.setText("Thành công");
                            remove(panel);
                            pack();
                            setSize(600, 600);
                        }
                    }
                });
                add(panel);
                pack();
            }
        });
        HibernateListener hl = new HibernateListener();
        if(typeUser == "giaovu"){
            sinhVienImport = new JMenuItem("Import danh sách sinh viên");
            sinhVienXem = new JMenuItem("Xem danh sách sinh viên");
            sinhVienXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    panel1.removeAll();
                    for(int i = 0; i < getComponentCount(); i++){
                        if(getComponent(i) == panel1){
                            remove(panel1);
                        }
                    }
                    pack();
                    JTextField choice = new JTextField();
                    JButton select = new JButton("Tìm kiếm");
                    List<SinhVien> list = SinhVienDAO.layDanhSachSinhVien();
                    List<SinhVien> ds = new ArrayList<>();
                    panel = new JPanel();
                    select.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel.removeAll();
                            ds.clear();
                            for(int i = 0; i < list.size(); i++){
                                if(choice.getText().equals(list.get(i).getMaLop())){
                                    ds.add(list.get(i));
                                }
                            }
                            GridLayout gl = new GridLayout(ds.size() + 1, 5);
                            panel.setLayout(gl);
                            JLabel bt1 = new JLabel("Mã sinh viên");
                            JLabel bt2 = new JLabel("Họ và tên");
                            JLabel bt3 = new JLabel("Giới tính");
                            JLabel bt4 = new JLabel("CMND");
                            JLabel bt5 = new JLabel("Mã lớp");
                            panel.add(bt1);
                            panel.add(bt2);
                            panel.add(bt3);
                            panel.add(bt4);
                            panel.add(bt5);
                            for(int i = 0; i < ds.size(); i++){
                                JLabel bt11 = new JLabel(ds.get(i).getMaSinhVien());
                                panel.add(bt11);
                                JLabel bt12 = new JLabel(ds.get(i).getHoVaTen());
                                panel.add(bt12);
                                JLabel bt13 = new JLabel(ds.get(i).getGioiTinh());
                                panel.add(bt13);
                                JLabel bt14 = new JLabel(ds.get(i).getcMND());
                                panel.add(bt14);
                                JLabel bt15 = new JLabel(ds.get(i).getMaLop());
                                panel.add(bt15);
                            }
                            add(panel, BorderLayout.SOUTH);
                            pack();
                        }
                    });
                    panel1.setLayout(new GridLayout(1, 2));
                    panel1.add(choice);
                    panel1.add(select);
                    add(panel1, BorderLayout.NORTH);
                    pack();
                }
            });
            sinhVien.add(sinhVienImport);
            sinhVien.add(sinhVienXem);
            thoiKhoaBieuImport = new JMenuItem("Import thời khóa biểu");
            thoiKhoaBieuXem = new JMenuItem("Xem danh thời khóa biểu");
            thoiKhoaBieuXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    panel1.removeAll();
                    for(int i = 0; i < getComponentCount(); i++){
                        if(getComponent(i) == panel1){
                            remove(panel1);
                        }
                    }
                    pack();
                    JTextField choice = new JTextField();
                    JButton select = new JButton("Tìm kiếm");
                    List<ThoiKhoaBieu> list = ThoiKhoaBieuDAO.layThoiKhoaBieu();
                    List<ThoiKhoaBieu> ds = new ArrayList<>();
                    select.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel.removeAll();
                            ds.clear();
                            for(int i = 0; i < list.size(); i++){
                                if(choice.getText().equals(list.get(i).getMaLop())){
                                    ds.add(list.get(i));
                                }
                            }
                            GridLayout gl = new GridLayout(ds.size() + 1, 4);
                            panel.setLayout(gl);
                            JLabel bt1 = new JLabel("Mã bộ môn");
                            JLabel bt2 = new JLabel("Mã lớp");
                            JLabel bt3 = new JLabel("Tên môn");
                            JLabel bt4 = new JLabel("Phòng học");
                            panel.add(bt1);
                            panel.add(bt2);
                            panel.add(bt3);
                            panel.add(bt4);
                            for(int i = 0; i < ds.size(); i++) {
                                JLabel bt11 = new JLabel(ds.get(i).getMaBoMon());
                                panel.add(bt11);
                                JLabel bt12 = new JLabel(ds.get(i).getMaLop());
                                panel.add(bt12);
                                JLabel bt13 = new JLabel(ds.get(i).getTenMon());
                                panel.add(bt13);
                                JLabel bt14 = new JLabel(ds.get(i).getPhongHoc());
                                panel.add(bt14);
                            }
                            add(panel, BorderLayout.SOUTH);
                            pack();
                        }
                    });
                    panel1.setLayout(new GridLayout(1, 2));
                    panel1.add(choice);
                    panel1.add(select);
                    add(panel1, BorderLayout.NORTH);
                    pack();
                }
            });
            thoiKhoaBieu.add(thoiKhoaBieuImport);
            thoiKhoaBieu.add(thoiKhoaBieuXem);
            hocPhanXem = new JMenuItem("Xem học phần");
            hocPhanXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    panel1.removeAll();
                    for(int i = 0; i < getComponentCount(); i++){
                        if(getComponent(i) == panel1){
                            remove(panel1);
                        }
                    }
                    pack();
                    JTextField choice = new JTextField();
                    JButton select = new JButton("Tìm kiếm");
                    List<HocPhan> list = HocPhanDAO.layDanhSachHocPhan();
                    List<HocPhan> ds = new ArrayList<>();
                    select.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel.removeAll();
                            ds.clear();
                            String str = choice.getText();
                            for(int i = 0; i < list.size(); i++){
                                if(str.contains(list.get(i).getMaLop()) && str.contains(list.get(i).getMaBoMon())){
                                    ds.add(list.get(i));
                                }
                            }
                            GridLayout gl = new GridLayout(ds.size() + 1, 6);
                            panel.setLayout(gl);
                            JLabel bt1 = new JLabel("Mã sinh viên");
                            JLabel bt2 = new JLabel("Mã bộ môn");
                            JLabel bt3 = new JLabel("Mã lớp");
                            JLabel bt4 = new JLabel("Họ tên");
                            JLabel bt5 = new JLabel("Giới tính");
                            JLabel bt6 = new JLabel("CMND");
                            panel.add(bt1);
                            panel.add(bt2);
                            panel.add(bt3);
                            panel.add(bt4);
                            panel.add(bt5);
                            panel.add(bt6);
                            for(int i = 0; i < ds.size(); i++) {
                                JLabel bt11 = new JLabel(ds.get(i).getMaSinhVien());
                                panel.add(bt11);
                                JLabel bt12 = new JLabel(ds.get(i).getMaBoMon());
                                panel.add(bt12);
                                JLabel bt13 = new JLabel(ds.get(i).getMaLop());
                                panel.add(bt13);
                                JLabel bt14 = new JLabel(ds.get(i).getHoVaTen());
                                panel.add(bt14);
                                JLabel bt15 = new JLabel(ds.get(i).getGioiTinh());
                                panel.add(bt15);
                                JLabel bt16 = new JLabel(ds.get(i).getcMND());
                                panel.add(bt16);
                            }
                            add(panel, BorderLayout.SOUTH);
                            pack();
                        }
                    });
                    panel1.setLayout(new GridLayout(1, 2));
                    panel1.add(choice);
                    panel1.add(select);
                    add(panel1, BorderLayout.NORTH);
                    pack();
                }
            });
            hocPhanHuy = new JMenuItem("Hủy học phần");
            hocPhanHuy.addActionListener(hl);
            hocPhanThem = new JMenuItem("Thêm học phần");
            hocPhanThem.addActionListener(hl);
            hocPhan.add(hocPhanXem);
            hocPhan.add(hocPhanHuy);
            hocPhan.add(hocPhanThem);
            diemImport = new JMenuItem("Import điểm");
            diemImport.addActionListener(hl);
            diemXem = new JMenuItem("Xem điểm");
            diemXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    panel1.removeAll();
                    for(int i = 0; i < getComponentCount(); i++){
                        if(getComponent(i) == panel1){
                            remove(panel1);
                        }
                    }
                    pack();
                    JTextField choice = new JTextField();
                    JButton select = new JButton("Tìm kiếm");
                    List<BangDiem> list = BangDiemDAO.layBangDiem();
                    List<BangDiem> ds = new ArrayList<>();
                    select.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel.removeAll();
                            ds.clear();
                            String str = choice.getText();
                            for(int i = 0; i < list.size(); i++){
                                if(str.contains(list.get(i).getMaLop()) && str.contains(list.get(i).getMaBoMon())){
                                    ds.add(list.get(i));
                                }
                            }
                            GridLayout gl = new GridLayout(ds.size() + 2, 9);
                            panel.setLayout(gl);
                            JLabel bt1 = new JLabel("Mã sinh viên");
                            JLabel bt2 = new JLabel("Mã bộ môn");
                            JLabel bt3 = new JLabel("Mã lớp");
                            JLabel bt4 = new JLabel("Họ tên");
                            JLabel bt5 = new JLabel("GK");
                            JLabel bt6 = new JLabel("CK");
                            JLabel bt7 = new JLabel("Khác");
                            JLabel bt8 = new JLabel("Tổng");
                            JLabel bt9 = new JLabel("Kết quả");
                            panel.add(bt1);
                            panel.add(bt2);
                            panel.add(bt3);
                            panel.add(bt4);
                            panel.add(bt5);
                            panel.add(bt6);
                            panel.add(bt7);
                            panel.add(bt8);
                            panel.add(bt9);
                            for(int i = 0; i < ds.size(); i++) {
                                JLabel bt11 = new JLabel(ds.get(i).getMaSinhVien());
                                panel.add(bt11);
                                JLabel bt12 = new JLabel(ds.get(i).getMaBoMon());
                                panel.add(bt12);
                                JLabel bt13 = new JLabel(ds.get(i).getMaLop());
                                panel.add(bt13);
                                JLabel bt14 = new JLabel(ds.get(i).getHoVaTen());
                                panel.add(bt14);
                                JLabel bt15 = new JLabel(Float.toString(ds.get(i).getDiemGK()));
                                panel.add(bt15);
                                JLabel bt16 = new JLabel(Float.toString(ds.get(i).getDiemCK()));
                                panel.add(bt16);
                                JLabel bt17 = new JLabel(Float.toString(ds.get(i).getDiemKhac()));
                                panel.add(bt17);
                                JLabel bt18 = new JLabel(Float.toString(ds.get(i).getDiemTong()));
                                panel.add(bt18);
                                String kq;
                                if(ds.get(i).getDiemTong() >= 5){
                                    kq = "Đậu";
                                }else{
                                    kq = "Rớt";
                                }
                                JLabel bt19 = new JLabel(kq);
                                panel.add(bt19);
                            }
                            double dauSL = 0.0, rotSL = 0.0;
                            for(int i = 0; i < ds.size(); i++){
                                if(ds.get(i).getDiemTong() >= 5){
                                    dauSL++;
                                }else{
                                    rotSL++;
                                }
                            }
                            double tyLeDau = 100*(dauSL/(dauSL + rotSL));
                            double tyleRot =  100*(rotSL/(dauSL + rotSL));
                            JLabel mes0 = new JLabel("Thống kê");
                            JLabel mes1 = new JLabel("SL đậu");
                            JLabel mes2 = new JLabel(Double.toString(dauSL));
                            JLabel mes3 = new JLabel("Chiếm");
                            JLabel mes4 = new JLabel(Double.toString(tyLeDau));
                            JLabel mes5 = new JLabel("SL rớt");
                            JLabel mes6 = new JLabel(Double.toString(rotSL));
                            JLabel mes7 = new JLabel("Chiếm");
                            JLabel mes8 = new JLabel(Double.toString(tyleRot));
                            panel.add(mes0);
                            panel.add(mes1);
                            panel.add(mes2);
                            panel.add(mes3);
                            panel.add(mes4);
                            panel.add(mes5);
                            panel.add(mes6);
                            panel.add(mes7);
                            panel.add(mes8);
                            add(panel, BorderLayout.SOUTH);
                            pack();
                        }
                    });
                    panel1.setLayout(new GridLayout(1, 2));
                    panel1.add(choice);
                    panel1.add(select);
                    add(panel1, BorderLayout.NORTH);
                    pack();
                }
            });
            diemSua = new JMenuItem("Sủa điểm");
            diemSua.addActionListener(hl);
            diemSo.add(diemImport);
            diemSo.add(diemXem);
            diemSo.add(diemSua);
        }else{
            sinhVienXem = new JMenuItem("Hồ sơ");
            sinhVienXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    pack();
                    panel.setLayout(new GridLayout(2, 5));
                    SinhVien sv = SinhVienDAO.laySinhVien(typeUser);
                    JLabel bt1 = new JLabel("Mã sinh viên");
                    JLabel bt2 = new JLabel("Họ và tên");
                    JLabel bt3 = new JLabel("Giới tính");
                    JLabel bt4 = new JLabel("CMND");
                    JLabel bt5 = new JLabel("Mã lớp");
                    JLabel bt11 = new JLabel(sv.getMaSinhVien());
                    JLabel bt22 = new JLabel(sv.getHoVaTen());
                    JLabel bt33 = new JLabel(sv.getGioiTinh());
                    JLabel bt44 = new JLabel(sv.getcMND());
                    JLabel bt55 = new JLabel(sv.getMaLop());
                    panel.add(bt1);
                    panel.add(bt2);
                    panel.add(bt3);
                    panel.add(bt4);
                    panel.add(bt5);
                    panel.add(bt11);
                    panel.add(bt22);
                    panel.add(bt33);
                    panel.add(bt44);
                    panel.add(bt55);
                    add(panel);
                    pack();
                }
            });
            sinhVien.add(sinhVienXem);
            thoiKhoaBieuXem = new JMenuItem("Xem thời khóa biểu");
            thoiKhoaBieuXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    pack();
                    SinhVien sv = SinhVienDAO.laySinhVien(typeUser);
                    List<HocPhan> hp = HocPhanDAO.layHocPhanSinhVien(sv.getMaSinhVien());
                    List<ThoiKhoaBieu> tkb = new ArrayList<>();
                    for(int i = 0; i < hp.size(); i++){
                        ThoiKhoaBieu t = ThoiKhoaBieuDAO.layThoiKhoaBieu(hp.get(i).getMaBoMon(), hp.get(i).getMaLop());
                        tkb.add(t);
                    }
                    panel.setLayout(new GridLayout(tkb.size() + 1, 4));
                    JLabel bt1 = new JLabel("Mã bộ môn");
                    JLabel bt2 = new JLabel("Mã lớp");
                    JLabel bt3 = new JLabel("Tên môn");
                    JLabel bt4 = new JLabel("Phòng học");
                    panel.add(bt1);
                    panel.add(bt2);
                    panel.add(bt3);
                    panel.add(bt4);
                    for(int i = 0; i < tkb.size(); i++){
                        JLabel bt11 = new JLabel(tkb.get(i).getMaBoMon());
                        JLabel bt22 = new JLabel(tkb.get(i).getMaLop());
                        JLabel bt33 = new JLabel(tkb.get(i).getTenMon());
                        JLabel bt44 = new JLabel(tkb.get(i).getPhongHoc());
                        panel.add(bt11);
                        panel.add(bt22);
                        panel.add(bt33);
                        panel.add(bt44);
                    }
                    add(panel);
                    pack();
                }
            });
            thoiKhoaBieu.add(thoiKhoaBieuXem);
            hocPhanXem = new JMenuItem("Xem danh sách học phần");
            hocPhanXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    pack();
                    List<HocPhan> hp = HocPhanDAO.layHocPhanSinhVien(typeUser);
                    panel.setLayout(new GridLayout(hp.size() + 1, 6));
                    JLabel bt1 = new JLabel("Mã sinh viên");
                    JLabel bt2 = new JLabel("Mã bộ môn");
                    JLabel bt3 = new JLabel("Mã lớp");
                    JLabel bt4 = new JLabel("Họ tên");
                    JLabel bt5 = new JLabel("Giới tính");
                    JLabel bt6 = new JLabel("CMND");
                    panel.add(bt1);
                    panel.add(bt2);
                    panel.add(bt3);
                    panel.add(bt4);
                    panel.add(bt5);
                    panel.add(bt6);
                    for(int i = 0; i < hp.size(); i++){
                        JLabel bt11 = new JLabel(hp.get(i).getMaBoMon());
                        JLabel bt22 = new JLabel(hp.get(i).getMaLop());
                        JLabel bt33 = new JLabel(hp.get(i).getMaSinhVien());
                        JLabel bt44 = new JLabel(hp.get(i).getHoVaTen());
                        JLabel bt55 = new JLabel(hp.get(i).getGioiTinh());
                        JLabel bt66 = new JLabel(hp.get(i).getcMND());
                        panel.add(bt11);
                        panel.add(bt22);
                        panel.add(bt33);
                        panel.add(bt44);
                        panel.add(bt55);
                        panel.add(bt66);
                    }
                    add(panel);
                    pack();
                }
            });
            hocPhan.add(hocPhanXem);
            diemXem = new JMenuItem("Xem điểm môn học");
            diemXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    pack();
                    List<BangDiem> bd = BangDiemDAO.layBangDiem(typeUser);
                    panel.setLayout(new GridLayout(bd.size() + 1, 8));
                    JLabel bt1 = new JLabel("Mã sinh viên");
                    JLabel bt2 = new JLabel("Mã bộ môn");
                    JLabel bt3 = new JLabel("Mã lớp");
                    JLabel bt4 = new JLabel("Họ tên");
                    JLabel bt5 = new JLabel("GK");
                    JLabel bt6 = new JLabel("CK");
                    JLabel bt7 = new JLabel("Khác");
                    JLabel bt8 = new JLabel("Tổng");
                    panel.add(bt1);
                    panel.add(bt2);
                    panel.add(bt3);
                    panel.add(bt4);
                    panel.add(bt5);
                    panel.add(bt6);
                    panel.add(bt7);
                    panel.add(bt8);
                    for(int i = 0; i < bd.size(); i++){
                        JLabel bt11 = new JLabel(bd.get(i).getMaSinhVien());
                        JLabel bt22 = new JLabel(bd.get(i).getMaBoMon());
                        JLabel bt33 = new JLabel(bd.get(i).getMaLop());
                        JLabel bt44 = new JLabel(bd.get(i).getHoVaTen());
                        JLabel bt55 = new JLabel(Float.toString(bd.get(i).getDiemGK()));
                        JLabel bt66 = new JLabel(Float.toString(bd.get(i).getDiemCK()));
                        JLabel bt77 = new JLabel(Float.toString(bd.get(i).getDiemKhac()));
                        JLabel bt88 = new JLabel(Float.toString(bd.get(i).getDiemTong()));
                        panel.add(bt11);
                        panel.add(bt22);
                        panel.add(bt33);
                        panel.add(bt44);
                        panel.add(bt55);
                        panel.add(bt66);
                        panel.add(bt77);
                        panel.add(bt88);
                    }
                    add(panel);
                    pack();
                }
            });
            diemSo.add(diemXem);
        }
        menuBar = new JMenuBar();
        menuBar.add(sinhVien);
        menuBar.add(thoiKhoaBieu);
        menuBar.add(hocPhan);
        menuBar.add(diemSo);
        menuBar.add(taiKhoan);
        setJMenuBar(menuBar);
    }
    public void login(){
        setSize(300, 100);
        JLabel userLabel = new JLabel("Tài khoản");
        JLabel passwordLabel = new JLabel("Mật khẩu");
        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton submit = new JButton("Đăng nhập");
        JLabel message = new JLabel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(userLabel);
        panel.add(user);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(message);
        panel.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangNhap dn = DangNhapDAO.layThongTinDangNhap(user.getText(), password.getText());
                if(dn == null){
                    message.setText("Sai đăng nhập");
                }else{
                    if(dn.getTaiKhoan().equals("giaovu")){
                        typeUser = "giaovu";
                    }else{
                        typeUser = dn.getTaiKhoan();
                    }
                    remove(panel);
                    intializeMenuBar();
                    invalidate();
                    validate();
                    repaint();
                }
            }
        });
        setResizable(false);
        add(panel, BorderLayout.CENTER);
    }
}
