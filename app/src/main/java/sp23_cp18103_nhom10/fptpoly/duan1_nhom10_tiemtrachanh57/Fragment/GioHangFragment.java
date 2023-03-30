package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.GioHangAdapter;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.HomeFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class GioHangFragment extends Fragment {
    ListView lv;
    TextView tvTongTien;
    Button btnTaoDonHang;
    CheckBox chkThanhToan;
    GioHangAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        lv = view.findViewById(R.id.lvGioHang);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        chkThanhToan = view.findViewById(R.id.chkThanhToan);
        btnTaoDonHang = view.findViewById(R.id.btnTaoDonHang);
        adapter = new GioHangAdapter(getActivity(), GioHangFragment.this, HomeFragment.listGioHang);
        lv.setAdapter(adapter);

        int tongTien =0;
        for (int i = 0; i<HomeFragment.listGioHang.size(); i++){
            tongTien+= HomeFragment.listGioHang.get(i).getTongTien();
        }
        tvTongTien.setText("Tổng tiền: "+tongTien+" VND");

        return view;
    }
}
