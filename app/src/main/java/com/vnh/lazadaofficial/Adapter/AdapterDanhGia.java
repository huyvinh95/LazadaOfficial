package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.DanhGia.ViewDanhGia;

import java.util.List;

/**
 * Created by HUYVINH on 08-Nov-16.
 */

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {
    Context context;
    List<DanhGia> danhGias;
    int limit;
    public AdapterDanhGia(Context context, List<DanhGia> danhGias,int limit){
        this.context = context;
        this.danhGias = danhGias;
        this.limit = limit;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txt_tieude,txt_noidung,txt_nguoidang;
        RatingBar rb_binhchonload;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);

            txt_tieude = (TextView) itemView.findViewById(R.id.txtTieuDe);
            txt_noidung = (TextView) itemView.findViewById(R.id.txt_noidung);
            txt_nguoidang = (TextView) itemView.findViewById(R.id.txt_nguoidang);
            rb_binhchonload= (RatingBar) itemView.findViewById(R.id.rb_binhchonload);


        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_layout_danhgia,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(v);

        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGias.get(position);
        holder.txt_tieude.setText(danhGia.getTIEUDE());
        holder.txt_nguoidang.setText("Bình luận được đăng bởi " + danhGia.getTENTHIETBI() + " vào ngày " + danhGia.getNGAYDANHGIA());
        holder.txt_noidung.setText(danhGia.getNOIDUNG());
        holder.rb_binhchonload.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if (danhGias.size() < limit){
            dong = danhGias.size();
        } else
        {
            if (limit != 0){
                dong = limit;
            } else{
                dong = danhGias.size();
            }
        }
        return dong;
    }


}
