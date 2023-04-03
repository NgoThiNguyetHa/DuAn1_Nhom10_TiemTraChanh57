package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterGioHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterQuanLyKhachHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DatDoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.HoaDonDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.KhachHangDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DatDoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.GioHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.HoaDon;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.KhachHang;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.HomeFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.MainActivity;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class GioHangFragment extends Fragment {
    ListView lv;
    static TextView tvTongTien;
    Button btnTaoDonHang;
    CheckBox chkThanhToan;
    AdapterGioHang adapter;
    GioHang gioHang;

    NhanVien nhanVien;
    NhanVienDAO nhanVienDAO;

    DatDoUongDAO datDoUongDAO;
    DatDoUong datDoUong;

    HoaDonDAO hoaDonDAO;
    HoaDon hoaDon;

    ListView lvKhachHang;
    ArrayList<KhachHang> list;
    KhachHangDAO khachHangDAO;
    KhachHang khachHang;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        lv = view.findViewById(R.id.lvGioHang);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        chkThanhToan = view.findViewById(R.id.chkThanhToan);
        btnTaoDonHang = view.findViewById(R.id.btnTaoDonHang);
        adapter = new AdapterGioHang(getActivity(), HomeFragment.listGioHang);
        lv.setAdapter(adapter);

        khachHangDAO = new KhachHangDAO(getActivity());
        datDoUongDAO = new DatDoUongDAO(getActivity());
        hoaDonDAO = new HoaDonDAO(getActivity());
        nhanVienDAO = new NhanVienDAO(getActivity());

        btnTaoDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taoDonHang();
            }
        });

        xoaSP();
        tinhTong();
        return view;
    }

    private void taoDonHang() {


        if (HomeFragment.listGioHang.size() >0){
            //hiện dialog để thêm khách hàng
            openDialog(getContext());



        }else {
            Toast.makeText(getContext(), "Giỏ hàng của bạn chưa có sản phâm", Toast.LENGTH_SHORT).show();
        }
    }

    private void xoaSP(){
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            HomeFragment.listGioHang.remove(position);
                            adapter.notifyDataSetChanged();
                            lv.setAdapter(adapter);
                            tinhTong();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    public static void tinhTong(){
        int tongTien =0;
        for (int i = 0; i<HomeFragment.listGioHang.size(); i++){
            tongTien+= HomeFragment.listGioHang.get(i).getTongTien();
        }
        tvTongTien.setText("Tổng tiền: "+tongTien+" VND");
    }
    public int tongTien(){
        int tongTien =0;
        for (int i = 0; i<HomeFragment.listGioHang.size(); i++){
            tongTien+= HomeFragment.listGioHang.get(i).getTongTien();
        }
        return tongTien;
    }

    public void openDialog(final Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_khachhang);
        //dialog khach hàng

        EditText edMaKhachHang = dialog.findViewById(R.id.edMaKhachHang);
        EditText edHoTenKhachHang = dialog.findViewById(R.id.edHoTenKhachHang);
        EditText edSDTKhachHang = dialog.findViewById(R.id.edSDTKhachHang);
        EditText edNamSinhKhachHang = dialog.findViewById(R.id.edNamSinhKhachHang);

        Button btnLuuKhachHang = dialog.findViewById(R.id.btnLuuKhachHang);
        Button btnHuyKhachHang = dialog.findViewById(R.id.btnHuyKhachHang);

        RadioButton rdoNamKH = dialog.findViewById(R.id.rdoNamKH);
        RadioButton rdoNuKH = dialog.findViewById(R.id.rdoNuKH);

        RadioGroup rdgGioiTinhKhạchHang = dialog.findViewById(R.id.rdgGioiTinhKhạchHang);

        edMaKhachHang.setEnabled(false);

        btnHuyKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuuKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                khachHang = new KhachHang();
                khachHang.setHoTen(edHoTenKhachHang.getText().toString());
                khachHang.setSdt(edSDTKhachHang.getText().toString());
                khachHang.setNamSinh(edNamSinhKhachHang.getText().toString());

                if (rdoNamKH.isChecked()) {
                    khachHang.setGioiTinh(1);
                } else {
                    khachHang.setGioiTinh(0);
                }

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String sdt = sharedPreferences.getString("SDT", "");
                NhanVienDAO nhanVienDAO = new NhanVienDAO(getContext());
                NhanVien nv = nhanVienDAO.getSDT(sdt);
                if (validate() > 0) {
                    if (khachHangDAO.insertKhachHang(khachHang) > 0) {
                        //tạo hóa đơn
                        hoaDon = new HoaDon();
                        khachHang = khachHangDAO.getKHLast(); //maKH
                        hoaDon.setMaKH(khachHang.getMaKH());
                        hoaDon.setMaNV(nv.getMaNV());//maNV

                        if (chkThanhToan.isChecked()){ //trangThai
                            hoaDon.setTrangThai(1);
                        }else {
                            hoaDon.setTrangThai(0);
                        }
                        //lấy ngày hiện tại
                        Date date = new Date(System.currentTimeMillis());
                        DateFormat dateFormat = new DateFormat();
                        String ngayHT = (String) dateFormat.format("yyyy-MM-dd", date);
                        //chuyển chuỗi ngày thành Date
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date objNgay = format.parse(ngayHT);
                            hoaDon.setNgayXuat(objNgay);
//                String ngayXuat = (String) dateFormat.format("yyyy-MM-dd", objNgay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        hoaDon.setTongTien(tongTien()); //tongTien
                        if (hoaDonDAO.insertHoaDon(hoaDon) >0){
                            for(int i=0; i<HomeFragment.listGioHang.size(); i++){
                                datDoUong = new DatDoUong();
                                datDoUong.setMaDoUong(HomeFragment.listGioHang.get(i).getMaDoUong());
                                hoaDon = hoaDonDAO.getHDLast();
                                datDoUong.setMaHD(hoaDon.getMaHD());
                                datDoUong.setSoLuong(HomeFragment.listGioHang.get(i).getSoLuong());
                                datDoUong.setTongTien(HomeFragment.listGioHang.get(i).getTongTien());
                                datDoUong.setHinhAnh(HomeFragment.listGioHang.get(i).getHinhAnh());
                                datDoUongDAO.insertDatDoUong(datDoUong);
                            }
                        }
                        HomeFragment.listGioHang.removeAll(HomeFragment.listGioHang);
                        adapter = new AdapterGioHang(getActivity(), HomeFragment.listGioHang);
                        lv.setAdapter(adapter);
                        tinhTong();
                        Toast.makeText(context, "Tạo đơn hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Tạo đơn hàng không thành công", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
    public int validate(){
        int check = 1;
        return check;
    }
}
