package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vnh.lazadaofficial.Model.OjectClass.DienTu;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.ChiTietSanPham.ChiTietSanPhamActivity;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by HUYVINH on 22-Sep-16.
 */

public class AdapterDienThoaiDienTu extends RecyclerView.Adapter<AdapterDienThoaiDienTu.ViewHolderDienThoai> {
    Context context;
    List<SanPham> listSanPham;
    int layout;

    public AdapterDienThoaiDienTu(Context context, List<SanPham> listSanPham, int layout) {
        this.context = context;
        this.listSanPham = listSanPham;
        this.layout = layout;

    }

    public class ViewHolderDienThoai extends RecyclerView.ViewHolder {

        ImageView imgHinhDienThoaiVaMtb;
        TextView txtTen;
        TextView txtGiaSale;
        TextView txtGiaGoc;
        CardView cardview;


        public ViewHolderDienThoai(View itemView) {
            super(itemView);

            imgHinhDienThoaiVaMtb = (ImageView) itemView.findViewById(R.id.imghinhdienthoaivamaytinh);
            txtTen = (TextView) itemView.findViewById(R.id.txtTenDTvaMT);
            txtGiaSale = (TextView) itemView.findViewById(R.id.txtGiaSaleDTvaMT);
            txtGiaGoc = (TextView) itemView.findViewById(R.id.txtGiaGocDTvaMT);
            cardview = (CardView) itemView.findViewById(R.id.cardview);

        }
    }

    @Override
    public ViewHolderDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(layout, parent, false);

        ViewHolderDienThoai vhDienThoai = new ViewHolderDienThoai(v);
        return vhDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienThoai holder, int position) {
        SanPham sanPham = listSanPham.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(120, 120).centerInside().into(holder.imgHinhDienThoaiVaMtb);
        holder.txtTen.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String number = numberFormat.format(sanPham.getGIA());
        holder.txtGiaGoc.setText(number + " VND");
        holder.cardview.setTag(sanPham.getMASP());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, (int)v.getTag() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("masp",(int)v.getTag());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }


}
