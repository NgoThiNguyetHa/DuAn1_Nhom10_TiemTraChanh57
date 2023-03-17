package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.khachHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class KhachHangDAO {
    SQLiteDatabase db;

    public KhachHangDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertKhachHang(khachHang obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("sdt", obj.getSdt());
        values.put("namSinh", obj.getNamSinh());
        values.put("gioiTinh", obj.getGioiTinh());
        return db.insert("khachHang", null, values);
    }
    public int updateKhachHang(khachHang obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("sdt", obj.getSdt());
        values.put("namSinh", obj.getNamSinh());
        values.put("gioiTinh", obj.getGioiTinh());
        return  db.update("khachHang", values, "maKH=?", new String[]{obj.getMaKH()+""});
    }
    public int deleteKhachHang(String id){
        return db.delete("khachHang", "maKH=?", new String[]{id});
    }

    @SuppressLint("Range")
    private List<khachHang> getData(String sql, String...selectionArgs){
        List<khachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            khachHang obj = new khachHang();
            obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKH"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setSdt(c.getString(c.getColumnIndex("sdt")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            obj.setGioiTinh(Integer.parseInt(c.getString(c.getColumnIndex("gioiTinh"))));
            list.add(obj);
        }
        return list;
    }

    public List<khachHang> getAll(){
        String sql = "select * from khachHang";
        return getData(sql);
    }

    public khachHang getID(String id){
        String sql = "select * from khachHang where maKH=?";
        List<khachHang> list = getData(sql, id);
        return list.get(0);
    }
}
