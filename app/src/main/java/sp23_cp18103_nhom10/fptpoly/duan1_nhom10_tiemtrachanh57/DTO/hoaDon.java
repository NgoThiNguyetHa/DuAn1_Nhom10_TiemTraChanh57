package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO;

import java.util.Date;

public class hoaDon {
    private int maHD;
    private int maKH;
    private int maNV;
    private int soLuong;
    private int trangThai;
    private String tenKH;
    private Date ngayXuat;

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public hoaDon(int maHD, int maKH, int maNV, int soLuong, int trangThai, String tenKH, Date ngayXuat) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.tenKH = tenKH;
        this.ngayXuat = ngayXuat;
    }

    public hoaDon() {
    }
}
