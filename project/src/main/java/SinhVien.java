import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable {
    private String maSinhVien;
    private String hoVaTen;
    private String gioiTinh;
    private String cMND;
    private String maLop;
    public SinhVien() {
    }
    public SinhVien(String maSinhVien, String hoVaTen, String gioiTinh, String cMND, String maLop) {
        this.maSinhVien = maSinhVien;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.cMND = cMND;
        this.maLop = maLop;
    }
    public String getMaLop() {
        return maLop;
    }
    public String getMaSinhVien() {
        return maSinhVien;
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
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
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