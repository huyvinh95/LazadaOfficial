package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.R;

import java.util.List;

/**
 * Created by HUYVINH on 26-Sep-16.
 */

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderThuongHieuLon> {

    Context context;
    List<ThuongHieu> listThuongHieu;


    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> listThuongHieu){
        this.context = context;
        this.listThuongHieu = listThuongHieu;


    }
    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {

        ImageView imgLogoThuongHieuLon;

        public ViewHolderThuongHieuLon(View itemView) {
            super(itemView);
            imgLogoThuongHieuLon = (ImageView) itemView.findViewById(R.id.imgLogoThuongHieuLon);
        }
    }

    @Override
    public ViewHolderThuongHieuLon onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_layout_thuonghieulon,parent,false);

        ViewHolderThuongHieuLon viewHolderThuongHieuLon = new ViewHolderThuongHieuLon(v);

        return viewHolderThuongHieuLon;
    }

    @Override
    public void onBindViewHolder(ViewHolderThuongHieuLon holder, int position) {
        ThuongHieu th = listThuongHieu.get(position);
        Picasso.with(context).load(th.getHINHLOAISPTH()).resize(180,90).centerInside().into(holder.imgLogoThuongHieuLon);

    }

    @Override
    public int getItemCount() {
        return listThuongHieu.size();
    }


}
