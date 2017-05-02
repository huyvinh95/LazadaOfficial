package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnh.lazadaofficial.Model.GioHang.ModelGioHang;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by HUYVINH on 24-Nov-16.
 */

public class AdapterChiTietGioHang extends RecyclerView.Adapter<AdapterChiTietGioHang.ViewHolderGioHang> {
    Context context;
    List<SanPham> sanPhams;
    ModelGioHang modelGioHang = new ModelGioHang();

    public AdapterChiTietGioHang(Context context, List<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenSanPham, txtGiaSanPham, txtSoLuong;
        ImageView imgHinhSanPham, imgXoa;
        ImageButton btnTangSoLuong, btnGiamSoLuong;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            txtTenSanPham = (TextView) itemView.findViewById(R.id.txtTenSanPhamGioHang);
            txtGiaSanPham = (TextView) itemView.findViewById(R.id.txtGiaSanPhamGioHang);
            txtSoLuong = (TextView) itemView.findViewById(R.id.txtSoLuong);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imgHinhSanPham);
            imgXoa = (ImageView) itemView.findViewById(R.id.imgXoa);
            btnGiamSoLuong = (ImageButton) itemView.findViewById(R.id.giamSanPham);
            btnTangSoLuong = (ImageButton) itemView.findViewById(R.id.tangSanPham);
        }
    }

    @Override
    public AdapterChiTietGioHang.ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_giohang, parent, false);
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(v);
        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final AdapterChiTietGioHang.ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhams.get(position);
        holder.txtTenSanPham.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String number = numberFormat.format(sanPham.getGIA());
        holder.txtGiaSanPham.setText(number + "VND");

        byte[] hinhsanpham = sanPham.getHINHANH();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhSanPham.setImageBitmap(bitmap);

        holder.imgXoa.setTag(sanPham.getMASP());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoi(context);
                modelGioHang.XoaSanPhamTrongGioHang((int) v.getTag());
                sanPhams.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        holder.txtSoLuong.setText(String.valueOf(sanPham.getSOLUONG()));

        holder.btnGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.txtSoLuong.getText().toString());
                if (count > 1) {
                    count--;
                    holder.txtSoLuong.setText(String.valueOf(count));
                    modelGioHang.MoKetNoi(context);
                    modelGioHang.CapNhatGioHang(count,sanPham.getMASP());
                }
            }
        });

        holder.btnTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.txtSoLuong.getText().toString());
                int soluong = sanPham.getSOLUONGTONKHO();
                if (count <= soluong){
                    count++;
                    holder.txtSoLuong.setText(String.valueOf(count));
                    modelGioHang.MoKetNoi(context);
                    modelGioHang.CapNhatGioHang(count,sanPham.getMASP());
                }else {
                    Toast.makeText(context, "Số lượng đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }


}
