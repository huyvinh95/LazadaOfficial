package com.vnh.lazadaofficial.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnh.lazadaofficial.Adapter.AdapterDienThoaiDienTu;
import com.vnh.lazadaofficial.Adapter.AdapterDienTuTong;
import com.vnh.lazadaofficial.Adapter.AdapterThuongHieuLonDienTu;
import com.vnh.lazadaofficial.Model.OjectClass.DienTu;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.DienTu.View_DienTu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by HUYVINH on 29-Aug-16.
 */
public class FragmentDienTu extends Fragment implements View_DienTu {
    RecyclerView recyclerDienTuTong,recyclerTopCacThuongHieuLon,recyclerHangMoiVe;
    List<DienTu> listDienTu = new ArrayList<>();
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView anh1,anh2,anh3;
    TextView txt1,txt2,txt3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu, container, false);

        recyclerDienTuTong = (RecyclerView) view.findViewById(R.id.recyclerDienTuTong);
        recyclerTopCacThuongHieuLon = (RecyclerView) view.findViewById(R.id.recyclerTopCacThuongHieuLon);
        recyclerHangMoiVe = (RecyclerView) view.findViewById(R.id.recyclerHangMoiVe);

        anh1 = (ImageView) view.findViewById(R.id.anh1);
        anh2 = (ImageView) view.findViewById(R.id.anh2);
        anh3 = (ImageView) view.findViewById(R.id.anh3);

        txt1  = (TextView) view.findViewById(R.id.txt1);
        txt2  = (TextView) view.findViewById(R.id.txt2);
        txt3  = (TextView) view.findViewById(R.id.txt3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        presenterLogicDienTu.LayDanhSachThuongHieu();
        presenterLogicDienTu.LayLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSachThuongHieu(List<DienTu> dienTus) {
        listDienTu = dienTus;

        AdapterDienTuTong adapterDienTuTong = new AdapterDienTuTong(getContext(), listDienTu);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerDienTuTong.setLayoutManager(layoutManager);
        recyclerDienTuTong.setAdapter(adapterDienTuTong);

        adapterDienTuTong.notifyDataSetChanged();
    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> listThuongHieu) {
        AdapterThuongHieuLonDienTu adapterTopThuongHieu = new AdapterThuongHieuLonDienTu(getContext(),listThuongHieu);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.HORIZONTAL,false);

        recyclerTopCacThuongHieuLon.setLayoutManager(layoutManager);
        recyclerTopCacThuongHieuLon.setAdapter(adapterTopThuongHieu);

        adapterTopThuongHieu.notifyDataSetChanged();

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> listSanPham) {
        AdapterDienThoaiDienTu adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(getContext(),listSanPham,R.layout.custom_layoutcon_dtvamtb);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerHangMoiVe.setLayoutManager(layoutManager);
        recyclerHangMoiVe.setAdapter(adapterDienThoaiDienTu);

        adapterDienThoaiDienTu.notifyDataSetChanged();


        Random rd = new Random();
        int sanphamrandom = rd.nextInt(listSanPham.size());

        Picasso.with(getContext()).load(listSanPham.get(sanphamrandom).getANHLON()).fit().centerInside().into(anh1);
        txt1.setText(listSanPham.get(sanphamrandom).getTENSP());

        int sanphamrandom1 = rd.nextInt(listSanPham.size());
        Picasso.with(getContext()).load(listSanPham.get(sanphamrandom1).getANHLON()).fit().centerInside().into(anh2);
        txt2.setText(listSanPham.get(sanphamrandom1).getTENSP());

        int sanphamrandom2 = rd.nextInt(listSanPham.size());
        Picasso.with(getContext()).load(listSanPham.get(sanphamrandom2).getANHLON()).fit().centerInside().into(anh3);
        txt3.setText(listSanPham.get(sanphamrandom2).getTENSP());


    }

}
