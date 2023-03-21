package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.MainActivity;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class DoiMatKhauFragment extends Fragment {
    MainActivity mainActivity;
    EditText edMatKhauCu, edMatKhauMoi, edNhapLaiMatKhau;
    Button btnLuu, btnHuy;
    NhanVienDAO dao;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        edMatKhauCu = view.findViewById(R.id.edMatKhauCu);
        edMatKhauMoi = view.findViewById(R.id.edMatKhauMoi);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhau);
        btnLuu = view.findViewById(R.id.btnLuyDMK);
        btnHuy = view.findViewById(R.id.btnHuyDMK);

        mainActivity = (MainActivity) getActivity();

        dao = new NhanVienDAO(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edMatKhauCu.setText("");
                edMatKhauMoi.setText("");
                edNhapLaiMatKhau.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String tenDangNhap = pref.getString("SDT", "");
                if(validate() >0){
                    dao = new NhanVienDAO(getContext());
                    NhanVien obj = dao.getSDT(tenDangNhap);
                    obj.setMatKhau(edMatKhauMoi.getText().toString());
                    if(dao.updateNhanVien(obj)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khấu thành công", Toast.LENGTH_SHORT).show();
                        edMatKhauCu.setText("");
                        edMatKhauMoi.setText("");
                        edNhapLaiMatKhau.setText("");
                    }else{
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private int validate(){
        int check = 1;
        if(edMatKhauCu.getText().length()==0 ||
        edMatKhauMoi.getText().length()==0 ||
        edNhapLaiMatKhau.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String matKhauCu = pref.getString("PASSWORD", "");
            String matKhauMoi = edMatKhauMoi.getText().toString();
            String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString();
            if (!edMatKhauCu.getText().toString().equals(matKhauCu)){
                edMatKhauCu.setError("Mật khẩu cũ sai");
                check = -1;
            }else{
                edMatKhauCu.setError(null);
            }

            if (!matKhauMoi.equals(nhapLaiMatKhau)){
                edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp");
                check = -1;
            }else{
                edMatKhauMoi.setError(null);
                edNhapLaiMatKhau.setError(null);
            }
        }
        return check;
    }
}
