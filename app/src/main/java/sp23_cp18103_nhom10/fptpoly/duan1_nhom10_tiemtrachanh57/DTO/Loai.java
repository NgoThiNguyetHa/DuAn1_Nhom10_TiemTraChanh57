package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO;

public class Loai {
    private int maLoai;
    private String tenLoai;

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Loai(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public Loai() {
    }
}
