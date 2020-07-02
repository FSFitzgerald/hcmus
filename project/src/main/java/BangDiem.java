import java.io.Serializable;

public class BangDiem implements Serializable {
    private String maSinhVien;
    private String maBoMon;
    private String maLop;
    private String hoVaTen;
    private float diemGK;
    private float diemCK;
    private float diemKhac;
    private float diemTong;
    public BangDiem() {
    }
    public BangDiem(String maSinhVien, String maBoMon, String maLop, String hoVaTen, float diemGK, float diemCK, float diemKhac, float diemTong) {
        this.maSinhVien = maSinhVien;
        this.maBoMon = maBoMon;
        this.maLop = maLop;
        this.hoVaTen = hoVaTen;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
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
    public float getDiemGK() {
        return diemGK;
    }
    public float getDiemCK() {
        return diemCK;
    }
    public float getDiemKhac() {
        return diemKhac;
    }
    public float getDiemTong() {
        return diemTong;
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
    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }
    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }
    public void setDiemKhac(float diemKhac) {
        this.diemKhac = diemKhac;
    }
    public void setDiemTong(float diemTong) {
        this.diemTong = diemTong;
    }
}
