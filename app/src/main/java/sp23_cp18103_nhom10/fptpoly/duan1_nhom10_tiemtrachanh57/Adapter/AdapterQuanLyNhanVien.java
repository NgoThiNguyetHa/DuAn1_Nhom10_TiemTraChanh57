package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyNhanVienFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class AdapterQuanLyNhanVien extends ArrayAdapter {
    private Context context;
    QuanLyNhanVienFragment fragment;
    private ArrayList<NhanVien> list;
    TextView tvHoTen , tvSDT , tvNamSinh , tvGioiTinh , tvMatKhau , tvTrangThai;
    ImageView img;
    public AdapterQuanLyNhanVien(@NonNull Context context, QuanLyNhanVienFragment fragment, ArrayList<NhanVien> list) {
        super(context, 0 , list);
        this.context = context;
        this.list =list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_them_nhan_vien, null);
        }
        final NhanVien item = list.get(position);
        if(view!= null){
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            NhanVien nhanVien = nhanVienDAO.getID(String.valueOf(item.getMaNV()));


            tvHoTen = view.findViewById(R.id.tvHoTen);
            tvSDT = view.findViewById(R.id.tvSDT);
            tvNamSinh = view.findViewById(R.id.tvNamSinh);
            tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
            tvMatKhau = view.findViewById(R.id.tvMatKhau);
            tvTrangThai = view.findViewById(R.id.tvTrangThai);
            img = view.findViewById(R.id.imgUpdateNhanVien);

            tvHoTen.setText("Họ tên : " + item.getHoTen());
            tvSDT.setText("Số điện thoại : " + item.getSdt());
            tvNamSinh.setText("Năm sinh : " + item.getNamSinh());
            tvMatKhau.setText("Mật khảu : " + item.getMatKhau());

            if(item.getTrangThai() == 1){
                tvTrangThai.setText("Đi làm");
                tvTrangThai.setTextColor(Color.BLUE);
            }else{
                tvTrangThai.setText("Nghỉ làm");
                tvTrangThai.setTextColor(Color.RED);
            }

            if(item.getGioiTinh() == 1){
                tvGioiTinh.setText("Giới tính : Nam " );
            }else{
                tvGioiTinh.setText("Giới tính : Nữ " );
            }
                img = view.findViewById(R.id.imgUpdateNhanVien);

        }

        return view;
    }
}
