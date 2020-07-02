import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HocPhanDAO {
    public static boolean themHocPhan(HocPhan hp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(hp);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static HocPhan layHocPhan(String maSinhVien, String maBoMon, String maLop){
        HocPhan hp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "from HocPhan hp where hp.maSinhVien=:msv and hp.maBoMon=:mbm and hp.maLop=:ml";
            Query query = session.createQuery(hql);
            query.setString("msv", maSinhVien);
            query.setString("mbm", maBoMon);
            query.setString("ml", maLop);
            hp = (HocPhan) query.uniqueResult();
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return hp;
    }
    public static boolean xoaHocPhan(String maSinhVien, String maBoMon, String maLop){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocPhan hp = HocPhanDAO.layHocPhan(maSinhVien, maBoMon, maLop);
        if(hp==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(hp);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean importHocPhan(String studentFile, String scheduleFile){
        List<ThoiKhoaBieu> tkb = ReadCSV.layThoiKhoaBieu(scheduleFile);
        List<SinhVien> ds = ReadCSV.laySinhVien(studentFile);
        for(int i = 0; i < tkb.size(); i++){
            for(int j = 0; j < ds.size(); j++){
                if(ds.get(j).getMaLop().equals(tkb.get(i).getMaLop())){
                    HocPhan hp = new HocPhan();
                    hp.setMaSinhVien(ds.get(j).getMaSinhVien());
                    hp.setMaBoMon(tkb.get(i).getMaBoMon());
                    hp.setMaLop(tkb.get(i).getMaLop());
                    hp.setHoVaTen(ds.get(j).getHoVaTen());
                    hp.setGioiTinh(ds.get(j).getGioiTinh());
                    hp.setcMND(ds.get(j).getcMND());
                    themHocPhan(hp);
                }
            }
        }
        return true;
    }
}
