package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO;

public class datDoUong {
    private  int maDatDoUong;
    private int maDoUong;
    private int maHD;
    private int soLuong;

    public int getMaDatDoUong() {
        return maDatDoUong;
    }

    public void setMaDatDoUong(int maDatDoUong) {
        this.maDatDoUong = maDatDoUong;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public datDoUong(int maDatDoUong, int maDoUong, int maHD, int soLuong) {
        this.maDatDoUong = maDatDoUong;
        this.maDoUong = maDoUong;
        this.maHD = maHD;
        this.soLuong = soLuong;
    }

    public datDoUong() {
    }
}
