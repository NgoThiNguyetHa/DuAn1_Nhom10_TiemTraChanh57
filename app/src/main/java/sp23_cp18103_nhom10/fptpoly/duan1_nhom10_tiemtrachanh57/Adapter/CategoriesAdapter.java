package sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.DTO.Loai;
import sp23_cp18103_nhom10.fptpoly.duan1_nhom10_tiemtrachanh57.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    ArrayList<Loai> loai;

    public CategoriesAdapter(ArrayList<Loai> loai) {
        this.loai = loai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_loai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tenLoai.setText(loai.get(position).getTenLoai());
        String anhURL = "";
        switch (position){
            case 0:
                anhURL = "img_tea";
                holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.categories_backround));
                break;
            case 1:
                anhURL = "img_coffee";
                holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.categories_backround2));
                break;
            case 2:
                anhURL = "img_smoothie";
                holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.categories_backround3));
                break;
            case 3:
                anhURL = "img_other";
                holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.categories_backround4));
                break;

        }
        int drawResource = holder.itemView.getContext().getResources().getIdentifier(anhURL, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawResource).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return loai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenLoai;
        ImageView img;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenLoai = itemView.findViewById(R.id.tvTenLoai);
            img = itemView.findViewById(R.id.imgLoai);
            linearLayout = itemView.findViewById(R.id.linearLoai);

        }
    }
}
