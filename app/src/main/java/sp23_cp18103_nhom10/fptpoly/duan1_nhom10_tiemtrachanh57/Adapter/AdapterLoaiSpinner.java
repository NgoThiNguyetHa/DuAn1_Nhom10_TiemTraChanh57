package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Loai;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class AdapterLoaiSpinner extends ArrayAdapter<Loai> {
    private Context context;
    private ArrayList<Loai> list;
    TextView tvmaLoai, tvTenLoai;

    public AdapterLoaiSpinner(@NonNull Context context, ArrayList<Loai> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_layout_item_loai, null);
        }
        final  Loai item = list.get(position);
        if (item != null){
            tvmaLoai = view.findViewById(R.id.tvMaLoaiSp);
            tvTenLoai = view.findViewById(R.id.tvTenLoaiSp);

            tvmaLoai.setText(item.getMaLoai()+". ");
            tvTenLoai.setText(item.getTenLoai());
        }
        return view;
    }
}
