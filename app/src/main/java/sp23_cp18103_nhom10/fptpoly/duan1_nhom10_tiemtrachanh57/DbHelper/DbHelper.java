package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DbName = "Nhom10";
    public static final int DbVersion = 1;
    public DbHelper(@Nullable Context context) {
        super(context, DbName, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableLoai = "create table loai(maLoai integer primary key autoincrement," +
                "tenLoai text not null)";
        sqLiteDatabase.execSQL(tableLoai);

        String tableDoUong = "create table doUong(maDoUong integer primary key autoincrement ," +
                "maLoai integer references loai(maLoai)," +
                "tenDoUong text not null," +
                "giaTien integer not null,"+
                "size text not null," +
                "trangThai integer not null)";
        sqLiteDatabase.execSQL(tableDoUong);

        String tableDatDoUong = "create table datDoUong(maDatDoUong integer primary key autoincrement," +
                "maDoUong integer references doUong(maDoUong)," +
                "tongTien integer not null,"+
                "maHD integer not null," +
                "soLuong integer not null)";
        sqLiteDatabase.execSQL(tableDatDoUong);

        String tableKhachHang = "create table khachHang(maKH integer primary key autoincrement," +
                "hoTen text not null," +
                "sdt text not null," +
                "namSinh text not null ," +
                "gioiTinh integer not null)";
        sqLiteDatabase.execSQL(tableKhachHang);

        String tableHoaDon = "create table hoaDon(maHD integer primary key autoincrement ," +
                "maKH integer references khachHang(maKH)," +
                "maNV integer references nhanVien(maNV) ," +
                "soLuong integer not null," +
                "trangThai integer not null," +
                "tenKH text not null," +
                "ngayXuat date not null," +
                "tongTien integer not null)";
                ;
        sqLiteDatabase.execSQL(tableHoaDon);

        String tableNhanVien = "create table nhanVien(maNV integer primary key autoincrement ," +
                "hoTen text not null ," +
                "sdt text not null," +
                "namSinh text not null ," +
                "trangThai integer not null ," +
                "gioiTinh integer not null ," +
                "matKhau text not null)";
        sqLiteDatabase.execSQL(tableNhanVien);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
