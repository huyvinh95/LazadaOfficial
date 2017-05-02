package com.vnh.lazadaofficial.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vnh.lazadaofficial.Adapter.ExpandListView;
import com.vnh.lazadaofficial.Adapter.ViewPageAdapter;
import com.vnh.lazadaofficial.Model.DangNhap_DangKi.ModelDangNhap;
import com.vnh.lazadaofficial.Model.OjectClass.LoaiSanPham;
import com.vnh.lazadaofficial.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.vnh.lazadaofficial.Presenter.TrangChu.XuLyMenu.IPresenterLogicXuLyMenu;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.DangNhap.DangNhapActivity;
import com.vnh.lazadaofficial.View.GioHang.GioHangActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HUYVINH on 28-Aug-16.
 */
public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {

    public static final String SEVER_NAME = "http://192.168.1.102/weblazadatest/loaisanpham.php";
    public static final String SEVER = "http://192.168.1.102/weblazadatest";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ExpandableListView expandListView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    IPresenterLogicXuLyMenu logicXuLyMenu;
    PresenterChiTietSanPham presenterChiTietSanPham;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangnhap, itemDangxuat;
    GoogleApiClient mgoogleApiClient;
    GoogleSignInResult googleSignInResult;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String tennv = "";
    TextView txtGioHang;
    Boolean onPause = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // dang nhap facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.trangchuactivity_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbals);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        expandListView = (ExpandableListView) findViewById(R.id.epMenu);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.cltoolbar);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new IPresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();

        modelDangNhap = new ModelDangNhap();
        mgoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);

        appBarLayout.addOnOffsetChangedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;

        presenterChiTietSanPham = new PresenterChiTietSanPham();

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View view = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) view.findViewById(R.id.txtSoLuongGioHang);
        txtGioHang.setText(String.valueOf(presenterChiTietSanPham.LaySoLuongGioHang(this)));

        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });



        itemDangnhap = menu.findItem(R.id.itDangNhap);
        itemDangxuat = menu.findItem(R.id.itDangXuat);

        accessToken = logicXuLyMenu.LayToKenHienTai();
        googleSignInResult = modelDangNhap.LayThongTinDangNhap(mgoogleApiClient);

        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        itemDangnhap.setTitle(tennguoidung);

                        Log.d("Ten nguoi dung", tennguoidung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("fields", "name");

            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
        }
        if (googleSignInResult != null) {
            itemDangnhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());

        }
        tennv = modelDangNhap.layCacche(this);
        if (!tennv.equals("")) {
            itemDangnhap.setTitle(tennv);
        }

        if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {

            itemDangxuat.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                if (accessToken == null && googleSignInResult == null && modelDangNhap.layCacche(this).equals("")) {
                    Intent iDangNhap = new Intent(getBaseContext(), DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.itDangXuat:
                if (accessToken != null || googleSignInResult != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                    Toast.makeText(getBaseContext(), "Đăng xuất thành công", Toast.LENGTH_LONG).show();
                }
                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mgoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (!modelDangNhap.layCacche(this).equals(null)){
                    modelDangNhap.CapNhatCache(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> listLoaiSanPham) {
        ExpandListView listLoaiSp = new ExpandListView(this, listLoaiSanPham);
        expandListView.setAdapter(listLoaiSp);
        listLoaiSp.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnsearch);
            linearLayout.setAlpha(0);
        } else {
            LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnsearch);
            linearLayout.setAlpha(1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause){
            presenterChiTietSanPham = new PresenterChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterChiTietSanPham.LaySoLuongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
