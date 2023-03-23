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

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.KhachHangDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.KhachHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyKhachHangFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyNhanVienFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class AdapterQuanLyKhachHang extends ArrayAdapter {
    private Context context;
    QuanLyKhachHangFragment fragment;
    private ArrayList<KhachHang> list;
    TextView tvMaKhachHang, tvHoTenKhachHang, tvSDTKhachHang, tvNamSinhKhachHang, tvGioiTinhKhachHang;
    ImageView img;


    public AdapterQuanLyKhachHang(@NonNull Context context, QuanLyKhachHangFragment fragment, ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_khachhang,null);

        }
        final KhachHang item = list.get(position);

        if(view != null){

            tvMaKhachHang = view.findViewById(R.id.tvMaKhachHang);
            tvHoTenKhachHang = view.findViewById(R.id.tvHoTenKhachHang);
            tvSDTKhachHang = view.findViewById(R.id.tvSDTKhachHang);
            tvNamSinhKhachHang = view.findViewById(R.id.tvNamSinhKhachHang);
            tvGioiTinhKhachHang = view.findViewById(R.id.tvGioiTinhKhachHang);

            tvMaKhachHang.setText("Mã KH: "+item.getMaKH());
            tvHoTenKhachHang.setText("Họ tên: "+item.getHoTen());
            tvSDTKhachHang.setText("Số điện thoại: "+item.getSdt());
            tvNamSinhKhachHang.setText("Năm sinh: "+item.getNamSinh());
            tvGioiTinhKhachHang.setText("Giới tính: "+item.getGioiTinh());

            if(item.getGioiTinh() == 1){
                tvGioiTinhKhachHang.setText("Giới tính : Nam " );
            }else{
                tvGioiTinhKhachHang.setText("Giới tính : Nữ " );
            }

        }
        return view;
    }
}