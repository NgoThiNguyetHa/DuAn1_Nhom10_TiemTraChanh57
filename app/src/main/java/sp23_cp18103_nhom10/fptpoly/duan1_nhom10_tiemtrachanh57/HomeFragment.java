package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterQuanLyNhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.GridViewAdapter;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;

public class HomeFragment extends Fragment {
    LinearLayout btnTea, btnCoffee, btnSmoothie, btnOther;
    DoUongDAO dao;
    ArrayList<DoUong> list;
    GridViewAdapter adapter;
    GridView gv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        btnTea = view.findViewById(R.id.btnTea);
        btnCoffee = view.findViewById(R.id.btnCoffee);
        btnSmoothie = view.findViewById(R.id.btnSmoothies);
        btnOther = view.findViewById(R.id.btnOther);
        gv = view.findViewById(R.id.gvPopular);

        dao = new DoUongDAO(getActivity());

        btnTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = (ArrayList<DoUong>) dao.getLoai("1");
                adapter = new GridViewAdapter(getActivity(), HomeFragment.this, list);
                gv.setAdapter(adapter);
            }
        });
        btnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = (ArrayList<DoUong>) dao.getLoai("2");
                adapter = new GridViewAdapter(getActivity(), HomeFragment.this, list);
                gv.setAdapter(adapter);
            }
        });
        btnSmoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = (ArrayList<DoUong>) dao.getLoai("3");
                adapter = new GridViewAdapter(getActivity(), HomeFragment.this, list);
                gv.setAdapter(adapter);
            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = (ArrayList<DoUong>) dao.getLoai("4");
                adapter = new GridViewAdapter(getActivity(), HomeFragment.this, list);
                gv.setAdapter(adapter);
            }
        });


        return view;
    }

}