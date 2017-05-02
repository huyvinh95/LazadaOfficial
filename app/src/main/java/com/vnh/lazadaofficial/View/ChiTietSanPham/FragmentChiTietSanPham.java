package com.vnh.lazadaofficial.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vnh.lazadaofficial.R;

/**
 * Created by HUYVINH on 27-Oct-16.
 */

public class FragmentChiTietSanPham extends Fragment {
    ImageView imgChiTietHinhSanPham;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_layout_chitietsanpham,container,false);
        imgChiTietHinhSanPham = (ImageView) view.findViewById(R.id.imgChiTietSanPham);
        Bundle bundle = getArguments();
        String linkanh = bundle.getString("linkanh");
        Picasso.with(getContext()).load(linkanh).into(imgChiTietHinhSanPham);


        return view;
    }
}
