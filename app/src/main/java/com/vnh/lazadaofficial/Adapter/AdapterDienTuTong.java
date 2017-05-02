package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnh.lazadaofficial.Model.OjectClass.DienTu;
import com.vnh.lazadaofficial.R;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by HUYVINH on 19-Sep-16.
 */
public class AdapterDienTuTong extends RecyclerView.Adapter<AdapterDienTuTong.ViewHolderDienTuTong> {
    Context context;
    List<DienTu> listDienTu;

    public AdapterDienTuTong(Context context, List<DienTu> listDienTu) {
        this.context = context;
        this.listDienTu = listDienTu;
    }

    public class ViewHolderDienTuTong extends RecyclerView.ViewHolder {
        ImageView imgHinhQuangcao;
        RecyclerView rvThuongHieu;
        RecyclerView rvSanPham;
        TextView txtThuongHieu,txtNoiBat;

        public ViewHolderDienTuTong(View itemView) {
            super(itemView);
            imgHinhQuangcao = (ImageView) itemView.findViewById(R.id.imgQuangCao);
            rvThuongHieu = (RecyclerView) itemView.findViewById(R.id.recyclerThuongHieuLon);
            rvSanPham = (RecyclerView) itemView.findViewById(R.id.recyclerTopDTvaMTB);
            txtThuongHieu = (TextView) itemView.findViewById(R.id.txtTenThuongHieu);
            txtNoiBat = (TextView) itemView.findViewById(R.id.txtTopNoiBat);
        }
    }

    @Override
    public ViewHolderDienTuTong onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_recyclerview_dientu, parent, false);
        ViewHolderDienTuTong viewHolderDienTuTong = new ViewHolderDienTuTong(v);

        return viewHolderDienTuTong;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTuTong holder, int position) {
        DienTu dienTu = listDienTu.get(position);

        holder.txtThuongHieu.setText(dienTu.getTennoibat());
        holder.txtNoiBat.setText(dienTu.getTenTopnoibat());

        AdapterDienTuLayoutCon adapterDienTuLayoutCon = new AdapterDienTuLayoutCon(context, dienTu.getListThuongHieu(),dienTu.isThuongHieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false);

        holder.rvThuongHieu.setLayoutManager(layoutManager);
        holder.rvThuongHieu.setAdapter(adapterDienTuLayoutCon);
        adapterDienTuLayoutCon.notifyDataSetChanged();

        // set adapter dien thoai va may tinh bang

        AdapterDienThoaiDienTu adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(context,dienTu.getListSanPham(),R.layout.custom_layoutcon_dtvamtb);
        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        holder.rvSanPham.setLayoutManager(layoutManagerTop);
        holder.rvSanPham.setAdapter(adapterDienThoaiDienTu);

    }

    @Override
    public int getItemCount() {
        return listDienTu.size();
    }


}
