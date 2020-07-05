import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ThoiKhoaBieuDAO {
    public static boolean themThoiKhoaBieu(ThoiKhoaBieu tkb){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tkb);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean importThoiKhoaBieu(String fileName){
        List<ThoiKhoaBieu> ds = ReadCSV.layThoiKhoaBieu(fileName);
        for(int i = 0; i < ds.size(); i++){
            ThoiKhoaBieuDAO.themThoiKhoaBieu(ds.get(i));
        }
        return true;
    }
    public static List<ThoiKhoaBieu> layThoiKhoaBieu() {
        List<ThoiKhoaBieu> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select tkb from ThoiKhoaBieu tkb";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static ThoiKhoaBieu layThoiKhoaBieu(String mon, String lop) {
        ThoiKhoaBieu ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select tkb from ThoiKhoaBieu tkb where tkb.maBoMon=:mon and tkb.maLop=:lop";
            Query query = session.createQuery(hql);
            query.setString("mon", mon);
            query.setString("lop", lop);
            ds = (ThoiKhoaBieu) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}
