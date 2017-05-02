package com.vnh.lazadaofficial.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vnh.lazadaofficial.Adapter.AdapterDienThoaiDienTu;
import com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc.ILoadMore;
import com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc.ILoadMoreScroll;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, ILoadMore {
    Button btnDangGrid;
    boolean danggrid = false;
    int maloai;
    boolean kiemtra;
    List<SanPham> sanPhams;
    RecyclerView recyclerViewHienThiSanPham;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc pLogicHienThi;
    AdapterDienThoaiDienTu adapterDienThoaiDienTu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_san_pham_theo_danh_muc);

        btnDangGrid = (Button) findViewById(R.id.danggrid);
        recyclerViewHienThiSanPham = (RecyclerView) findViewById(R.id.recyclerHienThiSanPham);

        Intent intent = getIntent();
        maloai = intent.getIntExtra("MALOAI", 0);
        String tenloai = intent.getStringExtra("TENLOAI");
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);

        pLogicHienThi = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        pLogicHienThi.LayDanhMucSanPham(maloai, kiemtra);

        btnDangGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danggrid = !danggrid;
                pLogicHienThi.LayDanhMucSanPham(maloai, kiemtra);
            }
        });
        Toast.makeText(this, maloai + "-" + tenloai + "-" + kiemtra, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void HienThiThanhCong(List<SanPham> listSanpham) {
        sanPhams = listSanpham;

        if (danggrid) {
            layoutManager = new GridLayoutManager(this, 2);
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(this, sanPhams, R.layout.custom_layoutcon_dtvamtb);
        } else {
            layoutManager = new LinearLayoutManager(this);
            adapterDienThoaiDienTu = new AdapterDienThoaiDienTu(this, sanPhams, R.layout.customlayout_listsanpham);

        }
        recyclerViewHienThiSanPham.setLayoutManager(layoutManager);
        recyclerViewHienThiSanPham.setAdapter(adapterDienThoaiDienTu);
        recyclerViewHienThiSanPham.addOnScrollListener(new ILoadMoreScroll(layoutManager, this));
    }

    @Override
    public void HienThiLoi() {

    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> sanPhamList = pLogicHienThi.LayDanhMucSanPhamLoadMore(maloai,kiemtra,tongitem);
        sanPhams.addAll(sanPhamList);
        adapterDienThoaiDienTu.notifyDataSetChanged();
    }
}
