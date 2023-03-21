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

import com.google.android.material.navigation.NavigationView;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyDoUongFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.DoiMatKhauFragment;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment.QuanLyNhanVienFragment;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private String mSDT="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String sdt = intent.getStringExtra("user");

        FragmentManager manager = getSupportFragmentManager();
        HomeFragment mainActivity2 = new HomeFragment();
        manager.beginTransaction().replace(R.id.flContent, mainActivity2).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.navHome:
                        setTitle("Tiệm trà chanh 57");
                        HomeFragment mainActivity2 = new HomeFragment();
                        manager.beginTransaction().replace(R.id.flContent, mainActivity2).commit();
                        break;
                    case R.id.navNhanVien:
                        setTitle("Quản lý nhân viên");
                        QuanLyNhanVienFragment quanLyNhanVienFragment = new QuanLyNhanVienFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyNhanVienFragment).commit();
                        break;
                    case R.id.navKhachHang:
                        break;
                    case R.id.navDoUong:
                        setTitle("Quản lý đồ uống");
                        QuanLyDoUongFragment quanLyDoUongFragment = new QuanLyDoUongFragment();
                        manager.beginTransaction().replace(R.id.flContent, quanLyDoUongFragment).commit();
                        break;
                    case R.id.nav_DoanhThu:
                        break;
                    case R.id.nav_Top10:
                        break;
                    case R.id.nav_DoiMatKhau:
                        setTitle("Đổi mật khẩu");
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction().replace(R.id.flContent, doiMatKhauFragment).commit();

                        break;
                    case R.id.nav_DangXuat:
                        startActivity( new Intent(MainActivity.this, ManHinhDangNhap.class));
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


}