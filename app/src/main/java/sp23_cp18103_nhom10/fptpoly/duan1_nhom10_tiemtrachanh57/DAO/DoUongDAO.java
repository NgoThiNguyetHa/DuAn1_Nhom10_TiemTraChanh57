package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class DoUongDAO {
    SQLiteDatabase db;

    public DoUongDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertDoUong(DoUong obj){
        ContentValues values = new ContentValues();
        values.put("maLoai", obj.getMaLoai());
        values.put("tenDoUong", obj.getTenDoUong());
        values.put("giaTien", obj.getGiaTien());
        values.put("size", obj.getSize());
        values.put("trangThai", obj.getTrangThai());
        return db.insert("doUong", null, values);
    }
    public int updateDoUong(DoUong obj){
        ContentValues values = new ContentValues();
        values.put("maLoai", obj.getMaLoai());
        values.put("tenDoUong", obj.getTenDoUong());
        values.put("giaTien", obj.getGiaTien());
        values.put("size", obj.getSize());
        values.put("trangThai", obj.getTrangThai());
        return db.update("doUong", values, "maDoUong=?", new String[]{obj.getMaDoUong()+""});
    }
    public int deleteDoUong(String id){
        return db.delete("doUong","maDoUong=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<DoUong> getData(String sql, String...selectionArgs){
        List<DoUong> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            DoUong obj = new DoUong();
            obj.setMaDoUong(Integer.parseInt(c.getString(c.getColumnIndex("maDoUong"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenDoUong(c.getString(c.getColumnIndex("tenDoUong")));
            obj.setGiaTien(Integer.parseInt(c.getString(c.getColumnIndex("giaTien"))));
            obj.setSize(c.getString(c.getColumnIndex("size")));
            obj.setTrangThai(Integer.parseInt(c.getString(c.getColumnIndex("trangThai"))));
            list.add(obj);
        }
        return list;
    }
    public List<DoUong> getAll(){
        String sql = "select * from doUong";
        return getData(sql);
    }

    public DoUong getID(String id){
        String sql = "select * from doUong where maDoUong=?";
        List<DoUong> list = getData(sql, id);
        return list.get(0);
    }
}
