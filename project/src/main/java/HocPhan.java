import java.io.Serializable;

public class HocPhan implements Serializable {
    private String maSinhVien;
    private String maBoMon;
    private String maLop;
    private String hoVaTen;
    private String gioiTinh;
    private String cMND;
    public HocPhan() {
    }
    public HocPhan(String maSinhVien, String maBoMon, String maLop, String hoVaTen, String gioiTinh, String cMND) {
        this.maSinhVien = maSinhVien;
        this.maBoMon = maBoMon;
        this.maLop = maLop;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.cMND = cMND;
    }
    public String getMaSinhVien() {
        return maSinhVien;
    }
    public String getMaBoMon() {
        return maBoMon;
    }
    public String getMaLop() {
        return maLop;
    }
    public String getHoVaTen() {
        return hoVaTen;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }
    public String getcMND() {
        return cMND;
    }
    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
    public void setMaBoMon(String maBoMon) {
        this.maBoMon = maBoMon;
    }
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public void setcMND(String cMND) {
        this.cMND = cMND;
    }
}
