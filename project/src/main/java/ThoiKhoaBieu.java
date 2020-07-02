import java.io.Serializable;

public class ThoiKhoaBieu implements Serializable {
    private String maBoMon;
    private String maLop;
    private String tenMon;
    private String phongHoc;
    public ThoiKhoaBieu() {
    }
    public ThoiKhoaBieu(String maBoMon, String maLop, String tenMon, String phongHoc) {
        this.maBoMon = maBoMon;
        this.maLop = maLop;
        this.tenMon = tenMon;
        this.phongHoc = phongHoc;
    }
    public String getMaBoMon() {
        return maBoMon;
    }
    public String getMaLop() {
        return maLop;
    }
    public String getTenMon() {
        return tenMon;
    }
    public String getPhongHoc() {
        return phongHoc;
    }
    public void setMaBoMon(String maBoMon) {
        this.maBoMon = maBoMon;
    }
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }
}
