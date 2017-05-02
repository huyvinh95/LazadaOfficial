package com.vnh.lazadaofficial.Presenter.TrangChu.XuLyMenu;

import android.util.Log;

import com.facebook.AccessToken;
import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.DangNhap_DangKi.ModelDangNhap;
import com.vnh.lazadaofficial.Model.OjectClass.LoaiSanPham;
import com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu.XuLyJsonMenu;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;
import com.vnh.lazadaofficial.View.TrangChu.ViewXuLyMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 31-Aug-16.
 */
public class IPresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;
    String tennguoidung;

    public IPresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhams;
        String dulieuJson = "";
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
//        String duongdan = "http://192.168.1.2/weblazadatest/loaisanpham.php?maloaicha=0";
//        DownloadData downloadData = new DownloadData(duongdan);

        String duongdan = TrangChuActivity.SEVER_NAME;

        HashMap<String, String> hsmaloaicha = new HashMap<>();
        hsmaloaicha.put("maloaicha", "0");

        HashMap<String,String> hsham = new HashMap<>();
        hsham.put("ham","LayDanhSachMeNu");

        hashMapList.add(hsham);
        hashMapList.add(hsmaloaicha);

        DownloadData downloadData = new DownloadData(duongdan, hashMapList);
        downloadData.execute();

        try {
            dulieuJson = downloadData.get();
            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
            loaiSanPhams = xuLyJsonMenu.ParseJsonMenu(dulieuJson);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhams);
            Log.d("kiemtrajson",dulieuJson.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    @Override
    public AccessToken LayToKenHienTai() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.getToken();

        return accessToken;
    }
}
