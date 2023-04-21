package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter.AdapterTop10;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.ThongKeDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Top10;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class Top10Fragment extends Fragment {
    ListView lv;
    ArrayList<Top10> list, list2;
    AdapterTop10 adapterTop10;
    EditText edThangTim;
    ImageView imgSearch;
    ThongKeDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_10,container,false);
        lv = view.findViewById(R.id.lvTop);
        dao  = new ThongKeDAO(getActivity());
        list = (ArrayList<Top10>) dao.getTop();
        adapterTop10 = new AdapterTop10(getActivity(), this, list);
        lv.setAdapter(adapterTop10);


        edThangTim = view.findViewById(R.id.edThangTim);
        imgSearch = view.findViewById(R.id.img_sreach);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list2 = (ArrayList<Top10>) dao.getTop10TheoThang(edThangTim.getText().toString());
                adapterTop10 = new AdapterTop10(getActivity(), Top10Fragment.this, list2);
                lv.setAdapter(adapterTop10);
            }
        });
        return  view;

    }
}
