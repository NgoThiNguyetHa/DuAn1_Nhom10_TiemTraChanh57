package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.HoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class HoaDonDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SQLiteDatabase db;

    public HoaDonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("maKH", obj.getMaKH());
        values.put("maNV", obj.getMaNV());
        values.put("tongTien", obj.getTongTien());
        values.put("trangThai", obj.getTrangThai());
        values.put("ngayXuat", sdf.format(obj.getNgayXuat()));
        return db.insert("hoaDon", null, values);
    }

    public int updateHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("maKH", obj.getMaKH());
        values.put("maNV", obj.getMaNV());
        values.put("tongTien", obj.getTongTien());
        values.put("trangThai", obj.getTrangThai());
        values.put("ngayXuat", sdf.format(obj.getNgayXuat()));
        return db.update("hoaDon", values, "maHD=?",new String[]{obj.getMaHD()+""});
    }
    public int deleteHoaDon(String id){
        return db.delete("hoaDon", "maHD=?", new String[]{id});
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...selectionArgs){
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            HoaDon obj = new HoaDon();
            obj.setMaHD(Integer.parseInt(c.getString(c.getColumnIndex("maHD"))));
            obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKH"))));
            obj.setTongTien(Integer.parseInt(c.getString(c.getColumnIndex("tongTien"))));
            obj.setMaNV(Integer.parseInt(c.getString(c.getColumnIndex("maNV"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            obj.setTrangThai(Integer.parseInt(c.getString(c.getColumnIndex("trangThai"))));
            obj.setTenKH(c.getString(c.getColumnIndex("tenKH")));
            try{
                obj.setNgayXuat(sdf.parse(c.getString(c.getColumnIndex("ngayXuat"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    public List<HoaDon> getAll(){
        String sql = "select * from hoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id){
        String sql = "select * from hoaDon where maHD=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }
}
