package p1;
import java.sql.*;

public class JDBC {
    public static void main(String[] args){
        try{
            String url = "jdbc:mysql://localhost/demo";
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "Thejazzage1920");
            if(conn != null) {
                System.out.println("Connected");
            }
            Statement st = conn.createStatement();
            String strsql = "select * from student where id  = ?";
            PreparedStatement ps = conn.prepareStatement(strsql);
            ps.setInt(1, 2);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getNString(2);
                System.out.println("Id " + id + ", name " + name);
            }
            ps.setInt(1, 1);
            ps.execute();
            rs = ps.getResultSet();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getNString(2);
                System.out.println("Id " + id + ", name " + name);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}