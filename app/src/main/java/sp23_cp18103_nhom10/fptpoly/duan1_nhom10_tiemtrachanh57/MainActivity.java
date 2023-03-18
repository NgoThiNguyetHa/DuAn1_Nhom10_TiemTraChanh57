package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.CategoriesAdapter;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Loai;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView rycCategories, rycPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rycCategories = findViewById(R.id.rycCategories);
        rycPopular = findViewById(R.id.rycPopular);

    }

    private void setRycCategories(){
        ArrayList<Loai> loai = new ArrayList<>();
        loai.add( new Loai(1, "Tea", "img_tea"));
        loai.add( new Loai(2, "Coffee", "img_coffee"));
        loai.add( new Loai(3, "Smoothies", "img_smoothie"));
        loai.add( new Loai(4, "Other", "img_other"));

        adapter = new CategoriesAdapter(loai);
        rycCategories.setAdapter(adapter);
    }
}