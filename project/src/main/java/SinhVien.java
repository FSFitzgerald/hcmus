import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable {
    private String maSinhVien;
    private String hoVaTen;
    private Date ngaySinh;
    private String diaChi;
    public SinhVien() {

    }
    public SinhVien(String maSinhVien, String hoVaTen, Date ngaySinh, String diaChi) {
        this.maSinhVien = maSinhVien;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }
    public String getMaSinhVien() {
        return maSinhVien;
    }
    public String getHoVaTen() {
        return hoVaTen;
    }
    public Date getNgaySinh() {
        return ngaySinh;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}