package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.datDoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class DatDoUongDAO {
    SQLiteDatabase db ;

    public DatDoUongDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertDatDoUong(datDoUong obj){
        ContentValues values = new ContentValues();
        values.put("maDoUong", obj.getMaDoUong());
        values.put("maHD", obj.getMaHD());
        values.put("soLuong", obj.getSoLuong());
        return db.insert("datDoUong", null, values);
    }
    public int updateDatDoUong(datDoUong obj){
        ContentValues values = new ContentValues();
        values.put("maDoUong", obj.getMaDoUong());
        values.put("maHD", obj.getMaHD());
        values.put("soLuong", obj.getSoLuong());
        return db.update("datDoUong",values, "maDatDoUong=?", new String[]{obj.getMaDatDoUong()+""});
    }
    public int deleteDatDoUong(String id){
        return db.delete("datDoUong", "maDatDoUong=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<datDoUong> getData(String sql, String...selectionArgs){
        List<datDoUong> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            datDoUong obj = new datDoUong();
            obj.setMaDatDoUong(Integer.parseInt(c.getString(c.getColumnIndex("maDatDoUong"))));
            obj.setMaDoUong(Integer.parseInt(c.getString(c.getColumnIndex("maDoUong"))));
            obj.setMaHD(Integer.parseInt(c.getString(c.getColumnIndex("maHD"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(obj);
        }
        return list;
    }

    public List<datDoUong> getAll(){
        String sql = "select * from datDoUong";
        return getData(sql);
    }

    public datDoUong getID(String id){
        String sql = "select * from datDoUong where maDatDoUong=?";
        List<datDoUong> list = getData(sql, id);
        return list.get(0);
    }

}
