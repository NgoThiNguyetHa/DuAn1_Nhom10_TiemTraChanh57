package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;


import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.NhanVienDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.NhanVien;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.BieuDoFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.DoanhSoFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.DoanhThuFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.GioHangFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyHoaDonFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyKhachHangFragment;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyDoUongFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.DoiMatKhauFragment;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyNhanVienFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.Top10Fragment;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private String mSDT="";
    ImageView imgCart;
    TextView tvTitle;

    static NhanVienDAO nhanVienDAO;
//    static String sdt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        toolbar = findViewById(R.id.toolbar);
        imgCart = findViewById(R.id.imgCart);
        tvTitle = findViewById(R.id.tvTitle);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String sdt = intent.getStringExtra("user");

        if (sdt.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nav_DoanhThu).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_BieuDoDoanhThu).setVisible(true);
            navigationView.getMenu().findItem(R.id.navNhanVien).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DoanhSoNhanVien).setVisible(false);
        }

        FragmentManager manager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.flContent, homeFragment).commit();

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangFragment gioHangFragment = new GioHangFragment();
                manager.beginTransaction().replace(R.id.flContent, gioHangFragment).commit();
                tvTitle.setText("Giỏ hàng");
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.navHome:
                        tvTitle.setText("Trang chủ");
                        setTitle("Tiệm trà chanh 57");
                        HomeFragment mainActivity2 = new HomeFragment();
                        manager.beginTransaction().replace(R.id.flContent, mainActivity2).commit();
                        break;
                    case R.id.navNhanVien:
                        tvTitle.setText("Quản lý nhân viên");
                        setTitle("Quản lý nhân viên");
                        QuanLyNhanVienFragment quanLyNhanVienFragment = new QuanLyNhanVienFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyNhanVienFragment).commit();
                        break;
                    case R.id.navKhachHang:
                        tvTitle.setText("Quản lý khách hàng");
                        setTitle("Quản lý nhân viên");
                        QuanLyKhachHangFragment quanLyKhachHangFragment = new QuanLyKhachHangFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyKhachHangFragment).commit();
                        break;
                    case R.id.navDoUong:
                        tvTitle.setText("Quản lý đồ uống");
                        setTitle("Quản lý đồ uống");
                        QuanLyDoUongFragment quanLyDoUongFragment = new QuanLyDoUongFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyDoUongFragment).commit();
                        break;
                    case R.id.nav_DoanhThu:
                        tvTitle.setText("Doanh thu");
                        setTitle("Doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.flContent, doanhThuFragment).commit();
                        break;
                    case R.id.nav_Top10:
                        tvTitle.setText("Top 10 đồ uống ");
                        setTitle("Quản lý đồ uống");
                        Top10Fragment top10Fragment = new Top10Fragment();
                        manager.beginTransaction().replace(R.id.flContent, top10Fragment).commit();
                        break;
                    case R.id.navHoaDon:
                        tvTitle.setText("Quản lý hóa đơn");
                        setTitle("Quản lý hóa đơn");
                        QuanLyHoaDonFragment quanLyHoaDonFragment = new QuanLyHoaDonFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyHoaDonFragment).commit();

                        break;
                    case R.id.nav_DoiMatKhau:
                        tvTitle.setText("Đổi mật khẩu");
                        setTitle("Đổi mật khẩu");
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction().replace(R.id.flContent, doiMatKhauFragment).commit();

                        break;
                    case R.id.nav_DangXuat:
                        startActivity( new Intent(MainActivity.this, ManHinhDangNhap.class));
                        break;
                    case R.id.nav_BieuDoDoanhThu:
                        tvTitle.setText("Biểu đồ");
                        setTitle("Biểu đồ");
                        BieuDoFragment bieuDoFragment = new BieuDoFragment();
                        manager.beginTransaction().replace(R.id.flContent, bieuDoFragment).commit();
                        break;
                    case R.id.nav_DoanhSoNhanVien:
                        tvTitle.setText("Doanh số");
                        setTitle("Doanh số");
                        DoanhSoFragment doanhSoFragment = new DoanhSoFragment();
                        manager.beginTransaction().replace(R.id.flContent, doanhSoFragment).commit();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

//    public int getmSDT(){
//        int maNV=0;
//        Intent intent = getIntent();
//        sdt = intent.getStringExtra("user");
////        nhanVienDAO = new NhanVienDAO();
//        NhanVien nv = nhanVienDAO.getSDT(sdt);
//        maNV = nv.getMaNV();
//        return maNV;
//    }


}