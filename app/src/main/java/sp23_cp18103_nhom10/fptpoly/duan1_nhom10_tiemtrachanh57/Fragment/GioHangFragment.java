package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class GioHangFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        return view;
    }
}
