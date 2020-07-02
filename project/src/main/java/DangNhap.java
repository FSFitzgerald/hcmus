import java.io.Serializable;

public class DangNhap implements Serializable {
    private String taiKhoan;
    private String matKhau;
    public DangNhap() {
    }
    public DangNhap(String taiKhoan, String matKhau) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }
    public String getTaiKhoan() {
        return taiKhoan;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
