import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
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
    public static boolean themSinhVien(SinhVien sv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean importDanhSachLop(String fileName){
        List<SinhVien> ds = ReadCSV.laySinhVien(fileName);
        for(int i = 0; i < ds.size(); i++){
            SinhVienDAO.themSinhVien(ds.get(i));
        }
        return true;
    }
}