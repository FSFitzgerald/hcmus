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

    public String getTypeUser() {
        return typeUser;
    }

    public GUI(){
        super();
        setTitle("My App");
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
                pack();
                invalidate();
                validate();
                repaint();
                panel = new JPanel(new GridLayout(3, 1));
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
            sinhVienImport.addActionListener(hl);
            sinhVienXem = new JMenuItem("Xem danh sách sinh viên");
            sinhVienXem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
                    JPanel panel1 = new JPanel(new GridLayout(1, 2));
                    panel1.add(choice);
                    panel1.add(select);
                    add(panel1, BorderLayout.NORTH);
                    pack();
                }
            });
            sinhVien.add(sinhVienImport);
            sinhVien.add(sinhVienXem);
            thoiKhoaBieuImport = new JMenuItem("Import thời khóa biểu");
            thoiKhoaBieuImport.addActionListener(hl);
            thoiKhoaBieuXem = new JMenuItem("Xem danh thời khóa biểu");
            thoiKhoaBieuXem.addActionListener(hl);
            thoiKhoaBieu.add(thoiKhoaBieuImport);
            thoiKhoaBieu.add(thoiKhoaBieuXem);
            hocPhanXem = new JMenuItem("Xem học phần");
            hocPhanXem.addActionListener(hl);
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
            diemXem.addActionListener(hl);
            diemSua = new JMenuItem("Sủa điểm");
            diemSua.addActionListener(hl);
            diemSo.add(diemImport);
            diemSo.add(diemXem);
            diemSo.add(diemSua);
        }else{
            sinhVienXem = new JMenuItem("Hồ sơ");
            sinhVienXem.addActionListener(hl);
            sinhVien.add(sinhVienXem);
            thoiKhoaBieuXem = new JMenuItem("Xem thời khóa biểu");
            thoiKhoaBieuXem.addActionListener(hl);
            thoiKhoaBieu.add(thoiKhoaBieuXem);
            hocPhanXem = new JMenuItem("Xem danh sách học phần");
            hocPhanXem.addActionListener(hl);
            hocPhan.add(hocPhanXem);
            diemXem = new JMenuItem("Xem điểm môn học");
            diemXem.addActionListener(hl);
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
        panel = new JPanel(new GridLayout(3, 1));
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
