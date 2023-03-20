package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;

public class HomeFragment extends Fragment {
    TextView tvHi;
    LinearLayout btnTea, btnCoffee, btnSmoothie, btnOther;
    String name ="";
    NhanVienDAO dao;
    View headerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        btnTea = view.findViewById(R.id.btnTea);
        btnCoffee = view.findViewById(R.id.btnCoffee);
        btnSmoothie = view.findViewById(R.id.btnSmoothies);
        btnOther = view.findViewById(R.id.btnOther);

        tvHi = view.findViewById(R.id.tvHi);
        Bundle bundle = getArguments();
        String sdt = bundle.getString("sdt");
//        MainActivity activity = (MainActivity) getActivity();
//        String sdt = activity.getmSDT();

        dao = new NhanVienDAO(getContext());
        NhanVien obj = dao.getSDT(sdt);
        name = obj.getHoTen();
        tvHi.setText("Hi "+name+"!");


        return view;
    }
    
}