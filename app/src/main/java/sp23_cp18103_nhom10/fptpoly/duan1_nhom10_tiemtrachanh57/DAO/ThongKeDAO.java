package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Top10;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DbHelper.DbHelper;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;

    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //thong ke top 10
    @SuppressLint("Range")
    public List<Top10> getTop(){
        String sqlTop = "select maDoUong, sum(soLuong) as soLuong from datDoUong group by maDoUong order by soLuong desc limit 10 ";
        List<Top10> list = new ArrayList<>();
        DoUongDAO doUongDAO = new DoUongDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top10 top10 = new Top10();
            DoUong doUong = doUongDAO.getID(c.getString(c.getColumnIndex("maDoUong")));
            top10.tenDoUong = doUong.getTenDoUong();
            top10.soLuong = Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
            list.add(top10);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "select sum(tongTien) as doanhThu from hoaDon where ngayXuat between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            } catch (NumberFormatException e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
