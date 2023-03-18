package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Loai;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class LoaiDAO {
    SQLiteDatabase db;

    public LoaiDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertLoai(Loai obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("loai", null, values);
    }
    public int updateLoai(Loai obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("loai", values, "maLoai=?",new String[]{obj.getMaLoai()+""});
    }
    public int deleteLoai(String id){
        return db.delete("loai", "maLoai=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<Loai> getData(String sql, String...selectionArgs){
        List<Loai> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Loai obj = new Loai();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<Loai> getAll(){
        String sql = "select * from loai";
        return getData(sql);
    }

    public Loai getID(String id){
        String sql = "select * from loai where maLoai=?";
        List<Loai> list = getData(sql,id);
        return list.get(0);
    }
}
