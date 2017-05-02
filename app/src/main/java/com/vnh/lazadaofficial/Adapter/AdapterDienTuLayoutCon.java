package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 19-Sep-16.
 */
public class AdapterDienTuLayoutCon extends RecyclerView.Adapter<AdapterDienTuLayoutCon.ViewHolderNoiBat> {

    Context context;
    List<ThuongHieu> listThuongHieu;
    boolean kiemTra;


    public AdapterDienTuLayoutCon(Context context, List<ThuongHieu> listThuongHieu, boolean kiemTra) {
        this.context = context;
        this.listThuongHieu = listThuongHieu;
        this.kiemTra = kiemTra;
    }

    public class ViewHolderNoiBat extends RecyclerView.ViewHolder {
        TextView txtTenThuongHieuLon;
        ImageView imgHinhThuongHieuLon;
        ProgressBar pbLoadThuongHieu;
        LinearLayout llThuongHieu;


        public ViewHolderNoiBat(View itemView) {
            super(itemView);

            txtTenThuongHieuLon = (TextView) itemView.findViewById(R.id.txtTenThuongHieuLon);
            imgHinhThuongHieuLon = (ImageView) itemView.findViewById(R.id.imgThuongHieu);
            pbLoadThuongHieu = (ProgressBar) itemView.findViewById(R.id.pbLoadThuongHieu);
            llThuongHieu = (LinearLayout) itemView.findViewById(R.id.llThuongHieu);
        }
    }

    @Override
    public ViewHolderNoiBat onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cutom_layoutcon_thuonghieu, parent, false);
        ViewHolderNoiBat viewHolderNoiBat = new ViewHolderNoiBat(v);

        return viewHolderNoiBat;
    }

    @Override
    public void onBindViewHolder(final ViewHolderNoiBat holder, int position) {
        final ThuongHieu thuongHieu = listThuongHieu.get(position);
        holder.txtTenThuongHieuLon.setText(thuongHieu.getTENTHUONGHIEU());
        holder.llThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                intent.putExtra("MALOAI",thuongHieu.getMATHUONGHIEU());
                intent.putExtra("TENLOAI",thuongHieu.getTENTHUONGHIEU());
                intent.putExtra("KIEMTRA",kiemTra);
                context.startActivity(intent);
//                Log.d("maloai",thuongHieu.getMATHUONGHIEU() + "-" + thuongHieu.getTENTHUONGHIEU());
            }
        });
        Picasso.with(context).load(thuongHieu.getHINHLOAISPTH()).resize(180,180).into(holder.imgHinhThuongHieuLon, new Callback() {
            @Override
            public void onSuccess() {
                holder.pbLoadThuongHieu.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listThuongHieu.size();
    }


}
