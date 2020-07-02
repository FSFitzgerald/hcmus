import org.hibernate.HibernateException;
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
}
