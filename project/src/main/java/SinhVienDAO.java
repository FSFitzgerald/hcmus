import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

public class SinhVienDAO {
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from SinhVien sv";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static void main(String args[]){
        List<SinhVien> ds = SinhVienDAO.layDanhSachSinhVien();
        if(ds == null){
            return;
        }
        for(int i=0; i<ds.size(); i++){
            SinhVien sv = ds.get(i);
            System.out.println("MSSV: "+sv.getMaSinhVien());
            System.out.println("Họ và tên: "+sv.getHoVaTen());
            System.out.println("Ngày sinh: " + sv.getNgaySinh());
            System.out.println("Địa chỉ: "+ sv.getDiaChi());
        }
    }
}