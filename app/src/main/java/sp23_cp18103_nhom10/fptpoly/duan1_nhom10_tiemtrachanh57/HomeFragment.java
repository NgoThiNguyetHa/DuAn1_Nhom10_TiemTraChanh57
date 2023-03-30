package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterQuanLyNhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.GridViewAdapter;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.GioHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;


public class HomeFragment extends Fragment {
    LinearLayout btnTea, btnCoffee, btnSmoothie, btnOther;
    DoUongDAO dao;
    ArrayList<DoUong> list;
    GridViewAdapter adapter;
    GridView gv;
    DoUong item;

    public static ArrayList<GioHang> listGioHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        btnTea = view.findViewById(R.id.btnTea);
        btnCoffee = view.findViewById(R.id.btnCoffee);
        btnSmoothie = view.findViewById(R.id.btnSmoothies);
        btnOther = view.findViewById(R.id.btnOther);
        gv = view.findViewById(R.id.gvPopular);

        //nếu giỏ hàng đã có dữ liệu thì k cần tạo mảng mới
        if (listGioHang != null){

        }else { //tạo mảng mới cho giỏ hàng
            listGioHang = new ArrayList<>();
        }

        dao = new DoUongDAO(getActivity());


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity());
            }
        });

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

    public void openDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chi_tiet_do_uong, null);
        builder.setView(view);

        TextView tvTen = view.findViewById(R.id.tvTenDoUongChiTiet);
        TextView tvGia = view.findViewById(R.id.tvGiaChiTiet);
        TextView tvSoLuong = view.findViewById(R.id.tvSoLuong);
        Button tvCong = view.findViewById(R.id.tvCongSL);
        Button tvTru = view.findViewById(R.id.tvTruSL);
        ImageView imgAnh = view.findViewById(R.id.imgAnhChiTiet);

        tvTen.setText(item.getTenDoUong());
        tvGia.setText(item.getGiaTien()+" VND");
        byte[] hinhAnh = item.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        imgAnh.setImageBitmap(bitmap);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listGioHang.size()> 0){
                    int sl = Integer.parseInt(tvSoLuong.getText().toString());
                    boolean check = false;
                    for (int i1=0; i1<listGioHang.size(); i1++){
                        if (listGioHang.get(i1).getMaDoUong() == item.getMaDoUong()){
                            listGioHang.get(i1).setSoLuong( listGioHang.get(i1).getSoLuong() + sl);
                            listGioHang.get(i1).setTongTien( listGioHang.get(i1).getGiaTien() * listGioHang.get(i1).getSoLuong());
                            check=true;
                        }

                    }
                    if (check == false){
                        int soLuong = Integer.parseInt(tvSoLuong.getText().toString());
                        int tongTien = soLuong*item.getGiaTien();
                        listGioHang.add(new GioHang(item.getMaDoUong(), item.getTenDoUong(), item.getGiaTien(), tongTien, sl, item.getHinhAnh()));

                    }
                }else {
                    int sl = Integer.parseInt(tvSoLuong.getText().toString());
                    int tongTien = sl * item.getGiaTien();
                    listGioHang.add(new GioHang(item.getMaDoUong(), item.getTenDoUong(), item.getGiaTien(), tongTien, sl, item.getHinhAnh()));

                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

    }

}