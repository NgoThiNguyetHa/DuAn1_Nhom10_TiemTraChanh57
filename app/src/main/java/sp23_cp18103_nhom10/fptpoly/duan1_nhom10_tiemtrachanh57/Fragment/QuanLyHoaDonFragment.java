package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterDSDatHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterQuanLyHoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DatDoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.HoaDonDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DatDoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.HoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class QuanLyHoaDonFragment extends Fragment {
    ListView lv;
    HoaDonDAO dao;
    HoaDon item;
    ArrayList<HoaDon> list;
    AdapterQuanLyHoaDon adapter;

    ArrayList<DatDoUong> listDatDoUong;
    AdapterDSDatHang adapterDSDatHang;
    DatDoUongDAO datDoUongDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don , container , false);
        lv = view.findViewById(R.id.lvHoaDon);

        dao = new HoaDonDAO(getActivity());
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new AdapterQuanLyHoaDon(getActivity(), this, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog();
            }
        });
        return view;
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ds_dat_hang, null);
        builder.setView(view);
        ListView lv = view.findViewById(R.id.lvDSDatHang);
        TextView tvThanhTien = view.findViewById(R.id.tvThanhTien);

        datDoUongDAO = new DatDoUongDAO(getActivity());
        listDatDoUong = (ArrayList<DatDoUong>) datDoUongDAO.getMaHoaDon(String.valueOf(item.getMaHD()));
        adapterDSDatHang = new AdapterDSDatHang(getActivity(), listDatDoUong);
        lv.setAdapter(adapterDSDatHang);

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        tvThanhTien.setText(decimalFormat.format(item.getTongTien())+" VND");

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}
