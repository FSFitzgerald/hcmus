import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.*;

public class ReadCSV {
    public static List<SinhVien> layThongTin(String fileName){
        CSVReader reader = null;
        List<SinhVien> ds = new ArrayList<>();
        try{
            reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                SinhVien sv = new SinhVien();
                sv.setMaSinhVien(nextLine[0]);
                sv.setHoVaTen(nextLine[1]);
                sv.setGioiTinh(nextLine[2]);
                sv.setcMND(nextLine[3]);
                sv.setMaLop(nextLine[4]);
                ds.add(sv);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ds;
    }
}
