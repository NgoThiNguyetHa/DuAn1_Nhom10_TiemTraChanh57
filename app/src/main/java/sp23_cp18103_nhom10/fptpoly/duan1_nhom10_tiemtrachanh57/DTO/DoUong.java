package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO;

public class DoUong {
    private int maDoUong;
    private int maLoai;
    private String tenDoUong;
    private String size;
    private int trangThai;

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public DoUong(int maDoUong, int maLoai, String tenDoUong, String size, int trangThai) {
        this.maDoUong = maDoUong;
        this.maLoai = maLoai;
        this.tenDoUong = tenDoUong;
        this.size = size;
        this.trangThai = trangThai;
    }

    public DoUong() {
    }
}
