import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame{
    private int typeUser;
    private JMenuBar menuBar;
    private JMenu sinhVien;
    private JMenu thoiKhoaBieu;
    private JMenu hocPhan;
    private JMenu diemSo;
    private JMenu doiMatKhau;
    private JMenu thoat;
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

    public int getTypeUser() {
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
        setSize(500, 500);
        sinhVien = new JMenu("Sinh viên");
        thoiKhoaBieu = new JMenu("Thời khóa biểu");
        hocPhan = new JMenu("Học phần");
        diemSo = new JMenu("Điểm số");
        doiMatKhau = new JMenu("Đổi mật khẩu");
        thoat = new JMenu("Thoát");
        HibernateListener hl = new HibernateListener();
        if(typeUser == 0){
            sinhVienImport = new JMenuItem("Import danh sách siinh vien");
            sinhVienImport.addActionListener(hl);
            sinhVienXem = new JMenuItem("Xem danh sach sinh vien");
            sinhVienXem.setActionCommand("xemdanhsachsinhvien");
            sinhVienXem.addActionListener(hl);
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
        menuBar.add(doiMatKhau);
        menuBar.add(thoat);
        setJMenuBar(menuBar);
    }
    public void login(){
        JLabel userLabel = new JLabel("Tài khoản");
        JLabel passwordLabel = new JLabel("Mật khẩu");
        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton submit = new JButton("Đăng nhập");
        JLabel message = new JLabel();
        JPanel panel = new JPanel(new GridLayout(3, 1));
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
                        typeUser = 0;
                    }else{
                        typeUser = 1;
                    }
                    remove(panel);
                    intializeMenuBar();
                    invalidate();
                    validate();
                    repaint();
                }
            }
        });
        setSize(300, 100);
        setResizable(false);
        add(panel, BorderLayout.CENTER);
    }
}
