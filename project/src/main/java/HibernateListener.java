import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HibernateListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        if(cmd.equals("xemdanhsachsinhvien")){
            List<SinhVien> ds = SinhVienDAO.layDanhSachSinhVien();
            DefaultListModel model = new DefaultListModel();
            JList list = new JList(model);
            for(int i = 0; i < ds.size(); i++){
                model.addElement(ds.get(i));
            }
        }
    }
}
