package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyDoUongFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class QuanLyDoUongAdapter extends ArrayAdapter<DoUong> {
    private Context context;
    QuanLyDoUongFragment fragment;
    ArrayList<DoUong> list;
    TextView tvTenDoUong, tvGia, tvAdd;
    ImageView imgAnh;

    public QuanLyDoUongAdapter(@NonNull Context context, QuanLyDoUongFragment fragment, ArrayList<DoUong> list) {
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
            view = inflater.inflate(R.layout.layout_item_do_uong, null);
        }
        final DoUong item = list.get(position);
        if (view != null){
            tvTenDoUong = view.findViewById(R.id.tvTenDoUong);
            tvGia = view.findViewById(R.id.tvGiaDoUong);
            tvAdd = view.findViewById(R.id.tvAddToCart);
            imgAnh = view.findViewById(R.id.imgDoUong);

            tvTenDoUong.setText(item.getTenDoUong());
            tvGia.setText(item.getGiaTien());
            imgAnh.setImageResource(item.getHinhAnh());

            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return view;
    }
}
