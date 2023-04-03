package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class BieuDoFragment extends Fragment {
    LineChart lineChart;
    @Nullable

    public View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bieu_do_fragment, container, false);
        lineChart = view.findViewById(R.id.lineChart);

        LineDataSet lineDataSet = new LineDataSet(dataValues(),"Data Set");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
        return view;
    }

    private List<Entry> dataValues() {
        ArrayList<Entry> dataValue = new ArrayList<>();
        dataValue.add(new Entry(0 , 10));
        dataValue.add(new Entry(1 , 15));
        dataValue.add(new Entry(2 , 20));
        dataValue.add(new Entry(3 , 30));
        dataValue.add(new Entry(4 , 25));
        return dataValue;
    }
}
