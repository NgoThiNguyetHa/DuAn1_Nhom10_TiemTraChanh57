package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DAO.ThongKeDAO;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.DoanhThu;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class BieuDoFragment extends Fragment  {
    LineChart lineChart;
    ArrayList<DoanhThu> list;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bieu_do_fragment, container, false);
        lineChart = view.findViewById(R.id.lineChart);

        LineDataSet lineDataSet = new LineDataSet(dataValues(),"");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);

        lineDataSet.setColors( ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);

        lineChart.invalidate(); //refesh
        return view;
    }

    private List<Entry> dataValues() {
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        list = (ArrayList<DoanhThu>) thongKeDAO.getBieuDo();

        List<Entry> dataValue = new ArrayList<>();
        dataValue.add(new Entry(0,0));

        for (int i=0; i<list.size(); i++){
            int thang = list.get(i).getThang();
            int tongTien = list.get(i).getTongTien();
            dataValue.add(new Entry(list.get(i).getThang(), list.get(i).getTongTien()));
            Log.d("zzzzz", "dataValues: "+ thang+ " tổng: "+ tongTien);
        }


        return dataValue;
    }
}
