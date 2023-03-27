package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyDoUongFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.HomeFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    ArrayList<DoUong> list;
    HomeFragment fragment;
    TextView tvTenDoUong, tvGia, tvAdd;
    ImageView imgAnh;

    public GridViewAdapter(Context context, HomeFragment fragment, ArrayList<DoUong> list) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_item_grid_view, null);
        imgAnh = view.findViewById(R.id.imgAnhDoUong);
        tvTenDoUong = view.findViewById(R.id.tvName);
        tvGia = view.findViewById(R.id.tvGiaDoUong);
        tvAdd = view.findViewById(R.id.tvAddToCart);

        final DoUong item = list.get(i);

        byte[] hinhAnh = item.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        imgAnh.setImageBitmap(bitmap);

        tvTenDoUong.setText(item.getTenDoUong());
        tvGia.setText(item.getGiaTien()+" VND");
        return view;
    }
}
