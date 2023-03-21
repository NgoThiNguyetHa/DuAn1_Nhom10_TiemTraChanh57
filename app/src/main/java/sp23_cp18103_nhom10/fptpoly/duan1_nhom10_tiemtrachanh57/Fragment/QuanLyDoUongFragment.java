package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;


import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterLoaiSpinner;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.DoUongDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.LoaiDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoUong;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Loai;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class QuanLyDoUongFragment extends Fragment {
    FloatingActionButton fabDoUong;
    ListView lvDoUong;
    Dialog dialog;
    ArrayList<DoUong> list;
    DoUong item;
    DoUongDAO dao;

    EditText edMaDoUong, edTenDoUong, edGiaTien;
    CheckBox chkTrangThai;
    Spinner spnLoai;
    ImageView imgCamera, imgFileUpload, imgAnh;
    Button btnLuu, btnHuy;

    LoaiDAO loaiDAO;
    ArrayList<Loai> listLoai;
    AdapterLoaiSpinner adapterLoaiSpinner;
    int maLoai;

    int requestCodeCamera = 123;
    int requestCodeFolder =456;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_do_uong, container, false);
        fabDoUong = view.findViewById(R.id.fabDoUong);
        lvDoUong = view.findViewById(R.id.lvDoUong);

        dao = new DoUongDAO(getActivity());

        fabDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        return view;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_do_uong);
        edMaDoUong = dialog.findViewById(R.id.edMaDoUong);
        edTenDoUong = dialog.findViewById(R.id.edTenDoUong);
        edGiaTien = dialog.findViewById(R.id.edGia);
        chkTrangThai = dialog.findViewById(R.id.chkTrangThai);
        spnLoai = dialog.findViewById(R.id.spnMaLoai);
        imgCamera = dialog.findViewById(R.id.imgCamera);
        imgFileUpload = dialog.findViewById(R.id.imgFile);
        imgAnh = dialog.findViewById(R.id.imgAnh);
        btnHuy = dialog.findViewById(R.id.btnHuyDoUong);
        btnLuu = dialog.findViewById(R.id.btnLuuDoUong);

        loaiDAO = new LoaiDAO(context);
        listLoai = new ArrayList<>();
        listLoai = (ArrayList<Loai>) loaiDAO.getAll();
        adapterLoaiSpinner = new AdapterLoaiSpinner(context, listLoai);
        spnLoai.setAdapter(adapterLoaiSpinner);
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai = listLoai.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaDoUong.setEnabled(false);
        if (type!=0){
            edMaDoUong.setText(item.getMaDoUong());
            edTenDoUong.setText(item.getTenDoUong());
            edGiaTien.setText(item.getGiaTien());
            if (item.getTrangThai() == 1){
                chkTrangThai.setChecked(true);
            }else {
                chkTrangThai.setChecked(false);
            }
        }


        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, requestCodeCamera);
            }
        });
        imgFileUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, requestCodeFolder);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển data imageview sang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                item = new DoUong();
                item.setTenDoUong(edTenDoUong.getText().toString().trim());
                item.setGiaTien(Integer.parseInt(edGiaTien.getText().toString().trim()));
                if (chkTrangThai.isChecked()){
                    item.setTrangThai(1);
                }else {
                    item.setTrangThai(0);
                }
                item.setMaLoai(maLoai);
                item.setHinhAnh(hinhAnh);

                if (dao.insertDU(item) >0){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    void capNhatLv(){
        list = (ArrayList<DoUong>) dao.getAll();
        adapter = new A(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestCodeCamera && resultCode == RESULT_OK && data!=null ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgAnh.setImageBitmap(bitmap);
        }
        if (requestCode == requestCodeFolder && resultCode == RESULT_OK && data!=null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
