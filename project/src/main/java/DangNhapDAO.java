import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DangNhapDAO {
    public static boolean themTaiKhoan(DangNhap dn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dn);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean doiMatKhau(String taiKhoan, String matKhauMoi){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (matKhauMoi == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE DangNhap set matKhau=:mk WHERE taiKhoan=:tk";
            Query query = session.createQuery(hql);
            query.setString("mk", matKhauMoi);
            query.setString("tk", taiKhoan);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean importTaiKhoan(String fileName){
        List<SinhVien> ds = ReadCSV.laySinhVien(fileName);
        for(int i = 0; i < ds.size(); i++){
            DangNhap dn = new DangNhap(ds.get(i).getMaSinhVien(), ds.get(i).getMaSinhVien());
            DangNhapDAO.themTaiKhoan(dn);
        }
        return true;
    }
    public static DangNhap layThongTinDangNhap(String tk, String mk) {
        DangNhap dn = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from DangNhap dn where dn.taiKhoan=:tk and dn.matKhau=:mk";
            Query query = session.createQuery(hql);
            query.setString("tk", tk);
            query.setString("mk", mk);
            dn = (DangNhap) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dn;
    }
}
