package com.vnh.lazadaofficial.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vnh.lazadaofficial.Adapter.AdapterChiTietSanPham;
import com.vnh.lazadaofficial.Adapter.AdapterDanhGia;
import com.vnh.lazadaofficial.Model.OjectClass.ChiTietSanPham;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.DanhGia.DanhGiaActiviti;
import com.vnh.lazadaofficial.View.DanhGia.XemThemNhanXet;
import com.vnh.lazadaofficial.View.GioHang.GioHangActivity;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham,ViewPager.OnPageChangeListener,View.OnClickListener {
    ViewPager viewpagerchitiet;
    PresenterChiTietSanPham presenterChiTietSanPham;
    TextView [] textViews;
    List<Fragment> fragments;
    LinearLayout linearDot;
    TextView txtTenSanPham,txtGiaSanPham,TenCHDongGoi,txtChiTietSanPham,txtXemThem,txtDanhGia,txtXemThemNhanXet,txtGioHang;
    Toolbar toolbalChiTietSanPham;
    ImageButton imgThemGioHang;
    LinearLayout lnThongSoKyThuat;
    RecyclerView rvDanhGia;
    boolean xemthem = false;
    int masp;
    SanPham sanPhamGioHang = new SanPham();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        viewpagerchitiet  = (ViewPager) findViewById(R.id.viewpagerchitiet);
        linearDot = (LinearLayout) findViewById(R.id.linearDot);
        txtTenSanPham = (TextView) findViewById(R.id.txtTenSanPham);
        txtGiaSanPham = (TextView) findViewById(R.id.txtGiaSanPham);
        toolbalChiTietSanPham  = (Toolbar) findViewById(R.id.toolbalChiTietSanPham);
        TenCHDongGoi = (TextView) findViewById(R.id.TenCHDongGoi);
        txtChiTietSanPham = (TextView) findViewById(R.id.txtChiTietSanPham);
        txtXemThem = (TextView) findViewById(R.id.txtXemThem);
        txtDanhGia = (TextView) findViewById(R.id.txtDanhGia);
        txtXemThemNhanXet = (TextView) findViewById(R.id.txtXemThemNhanXet);
        lnThongSoKyThuat = (LinearLayout) findViewById(R.id.lnThongSoKyThuat);
        rvDanhGia = (RecyclerView) findViewById(R.id.rvDanhGia);
        imgThemGioHang = (ImageButton) findViewById(R.id.imgThemGioHang);

        setSupportActionBar(toolbalChiTietSanPham);

        int masp = getIntent().getIntExtra("masp",0);

        presenterChiTietSanPham = new PresenterChiTietSanPham(this);
        presenterChiTietSanPham.LayChiTietSanPham(masp);
        presenterChiTietSanPham.LayDanhSachDanhGiaHienThi(masp,1);

        viewpagerchitiet.addOnPageChangeListener(this);
        txtDanhGia.setOnClickListener(this);
        txtXemThemNhanXet.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);

    }
    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,danhGias,3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDanhGia.setLayoutManager(layoutManager);
        rvDanhGia.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemSanPhamThanhCong() {
        Toast.makeText(getBaseContext(),"Sản phẩm đã được thêm vào giỏ hàng",Toast.LENGTH_LONG).show();
        txtGioHang.setText(String.valueOf(presenterChiTietSanPham.LaySoLuongGioHang(this)));
    }

    @Override
    public void ThemSanPhamThatBai() {
        Toast.makeText(getBaseContext(),"Sản phẩm đã có trong giỏ hàng",Toast.LENGTH_LONG).show();
    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
        txtTenSanPham.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String number = numberFormat.format(sanPham.getGIA());
        txtGiaSanPham.setText(number);
        TenCHDongGoi.setText(sanPham.getTENNHANVIEN());
        txtChiTietSanPham.setText(sanPham.getTHONGTIN().substring(0,100));
        if (sanPham.getTHONGTIN().length()< 100){
            txtXemThem.setVisibility(View.GONE);
        }else {
            txtXemThem.setVisibility(View.VISIBLE);
            txtXemThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    xemthem = !xemthem;
                    if (xemthem) {
                        txtChiTietSanPham.setText(sanPham.getTHONGTIN());
                        txtXemThem.setText("Thu Nhỏ");
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    }else {
                        txtChiTietSanPham.setText(sanPham.getTHONGTIN().substring(0,100));
                        txtXemThem.setText("Xem Thêm");
                        lnThongSoKyThuat.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View view = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) view.findViewById(R.id.txtSoLuongGioHang);
        txtGioHang.setText(String.valueOf(presenterChiTietSanPham.LaySoLuongGioHang(this)));


        View giaodiengiohang = MenuItemCompat.getActionView(iGioHang);
        giaodiengiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });


        return true;
    }

    @Override
    public void HienThiSliderChiTietSanPham(String[] linkanh) {
        fragments = new ArrayList<>();
        for (int i = 0; i< linkanh.length; i++){
            FragmentChiTietSanPham fragmentChiTietSanPham = new FragmentChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkanh", TrangChuActivity.SEVER + linkanh[i]);
            fragmentChiTietSanPham.setArguments(bundle);

            fragments.add(fragmentChiTietSanPham);
        }

        AdapterChiTietSanPham adapterChiTietSanPham = new AdapterChiTietSanPham(getSupportFragmentManager(),fragments);
        viewpagerchitiet.setAdapter(adapterChiTietSanPham);
        adapterChiTietSanPham.notifyDataSetChanged();

        ThemDotSlider(viewpagerchitiet.getCurrentItem());
        viewpagerchitiet.addOnPageChangeListener(this);


    }

    private void HienThiThongSoKyThuat(SanPham sp){
        List<ChiTietSanPham> chiTietSanPhams = sp.getListChiTietSanPham();
        lnThongSoKyThuat.removeAllViews();
        for (int i = 0; i<chiTietSanPhams.size(); i++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);



            TextView ten = new TextView(this);
            ten.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            ten.setText(chiTietSanPhams.get(i).getTENCHITIET());

            TextView giatri = new TextView(this);
            giatri.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            giatri.setText(chiTietSanPhams.get(i).getGIATRI());

            linearLayout.addView(ten);
            linearLayout.addView(giatri);
            lnThongSoKyThuat.addView(linearLayout);
        }


    }
    private void ThemDotSlider(int vitrihientai){
        textViews = new TextView[(fragments.size())];
        linearDot.removeAllViews();
        for (int i = 0; i<textViews.length; i++){
            textViews[i] = new TextView(this);
            textViews[i].setText(Html.fromHtml("&#8226;"));
            textViews[i].setTextSize(30);
            textViews[i].setTextColor(LayColor(R.color.colordotInActive));
            textViews[i].setGravity(Gravity.CENTER);
            linearDot.addView(textViews[i]);
        }
        textViews[vitrihientai].setTextColor(LayColor(R.color.backgroudtoolbar));

    }

    private int LayColor(int color){
        int colorhientai= 0;
        if (Build.VERSION.SDK_INT > 21){
            colorhientai = ContextCompat.getColor(this,color);
        }
        else {
            colorhientai = getResources().getColor(color);
        }
        return colorhientai;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtDanhGia :
                Intent itDanhGia = new Intent(getBaseContext(),DanhGiaActiviti.class);
                itDanhGia.putExtra("masp",masp);
                startActivity(itDanhGia);
                break;
            case R.id.txtXemThemNhanXet :
                Intent itNhanXet = new Intent(getBaseContext(), XemThemNhanXet.class);
                itNhanXet.putExtra("masp",masp);
                startActivity(itNhanXet);
                break;
            case R.id.imgThemGioHang :
                Fragment fragment = fragments.get(viewpagerchitiet.getCurrentItem());
                View view= fragment.getView();
                ImageView imageView = (ImageView) view.findViewById(R.id.imgChiTietSanPham);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanhsanpham =  byteArrayOutputStream.toByteArray();
                sanPhamGioHang.setHINHANH(hinhanhsanpham);
                sanPhamGioHang.setSOLUONG(1);

                presenterChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                break;
        }

    }


}
