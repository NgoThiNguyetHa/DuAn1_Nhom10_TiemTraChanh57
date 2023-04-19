package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterDSDatHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterQuanLyHoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DatDoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.HoaDonDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.ThongKeDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DatDoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.HoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;
//doanh thu
public class DoanhThuFragment extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;
    ListView lv;
    AdapterQuanLyHoaDon adapter;
    ArrayList<HoaDon> list;

    HoaDon item;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        lv = view.findViewById(R.id.lvHoaDon_DT);

        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mYear = i;
                mMonth = i1;
                mDay = i2;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edTuNgay.setText( sdf.format(c.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mYear = i;
                mMonth = i1;
                mDay = i2;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edDenNgay.setText( sdf.format(c.getTime()));
            }
        };


        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get( Calendar.YEAR);
                mMonth = c.get( Calendar.MONTH);
                mDay = c.get( Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get( Calendar.YEAR);
                mMonth = c.get( Calendar.MONTH);
                mDay = c.get( Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDAO thongKeDao = new ThongKeDAO(getActivity());
                tvDoanhThu.setText( "Doanh thu: "+thongKeDao.getDoanhThu(tuNgay, denNgay) +" VNĐ");
                Log.d("TAG", "onClick: "+tuNgay+" "+ denNgay);

                HoaDonDAO dao = new HoaDonDAO(getActivity());
                list = (ArrayList<HoaDon>) dao.getTheoNgay(tuNgay, denNgay);
                adapter = new AdapterQuanLyHoaDon(getActivity(), DoanhThuFragment.this, list);
                lv.setAdapter(adapter);
            }
        });
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

        DatDoUongDAO datDoUongDAO = new DatDoUongDAO(getActivity());
        ArrayList<DatDoUong> listDatDoUong = (ArrayList<DatDoUong>) datDoUongDAO.getMaHoaDon(String.valueOf(item.getMaHD()));
        AdapterDSDatHang adapterDSDatHang = new AdapterDSDatHang(getActivity(), listDatDoUong);
        lv.setAdapter(adapterDSDatHang);

        tvThanhTien.setText(item.getTongTien()+" VND");

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}
