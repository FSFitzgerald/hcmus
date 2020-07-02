import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BangDiemDAO {
    public static boolean themBangDiem(BangDiem bd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bd);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static BangDiem layBangDiem(String maSinhVien, String maBoMon, String maLop){
        BangDiem bd = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "from BangDiem bd where bd.maSinhVien=:msv and bd.maBoMon=:mbm and bd.maLop=:ml";
            Query query = session.createQuery(hql);
            query.setString("msv", maSinhVien);
            query.setString("mbm", maBoMon);
            query.setString("ml", maLop);
            bd = (BangDiem) query.uniqueResult();
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return bd;
    }
    public static boolean xoaBangDiem(String maSinhVien, String maBoMon, String maLop){
        Session session = HibernateUtil.getSessionFactory().openSession();
        BangDiem bd = BangDiemDAO.layBangDiem(maSinhVien, maBoMon, maLop);
        if(bd==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(bd);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean capNhatDiem(BangDiem bd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (bd == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(bd);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean importBangDiem(String fileName){
        List<BangDiem> bds = ReadCSV.layBangDiem(fileName);
        for(int i = 0; i < bds.size(); i++){
            themBangDiem(bds.get(i));
        }
        return true;
    }
}
