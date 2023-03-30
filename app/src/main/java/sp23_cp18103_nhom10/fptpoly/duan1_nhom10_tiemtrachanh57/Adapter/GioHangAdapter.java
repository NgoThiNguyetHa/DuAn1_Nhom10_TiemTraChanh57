package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.GioHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.GioHangFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyDoUongFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class GioHangAdapter extends ArrayAdapter<GioHang> {
    private Context context;
    GioHangFragment fragment;
    ArrayList<GioHang> list;
    ImageView imgAnh;
    TextView tvTen, tvTongTien, tvCong, tvTru, tvSoLuong;


    public GioHangAdapter(@NonNull Context context, GioHangFragment fragment, ArrayList<GioHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_dat_do_uong, null);
        }
        final GioHang item = list.get(position);
        if (view != null){
            tvTen = view.findViewById(R.id.tvTenDoUong);
            tvTongTien = view.findViewById(R.id.tvTongSP);
            tvCong = view.findViewById(R.id.tvCongSL);
            tvTru = view.findViewById(R.id.tvTruSL);
            tvSoLuong = view.findViewById(R.id.tvSoLuong);
            imgAnh = view.findViewById(R.id.imgDatDoUong);

            tvTen.setText(item.getTenDoUong());
            tvTongTien.setText(item.getTongTien()+" VND");
            tvSoLuong.setText(item.getSoLuong()+"");

            //chuyen byte[] sang bitMap
            byte[] hinhAnh = item.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            imgAnh.setImageBitmap(bitmap);


        }
        return view;
    }
}
