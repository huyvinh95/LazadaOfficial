package com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.LoaiSanPham;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 30-Aug-16.
 */
public class XuLyJsonMenu {
    String tennguoidung = "";

    public List<LoaiSanPham> ParseJsonMenu(String dulieuJson) {

        List<LoaiSanPham> listsp = new ArrayList<>();
        try {
            Log.d("Kiem tra json", dulieuJson);
            JSONObject jsonObject = new JSONObject(dulieuJson);
            JSONArray dataloaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = dataloaisanpham.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = dataloaisanpham.getJSONObject(i);
                LoaiSanPham loaiSanPham = new LoaiSanPham();
                loaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                loaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));
                loaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));

                listsp.add(loaiSanPham);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listsp;
    }

    public List<LoaiSanPham> LaySanPhamTheoMaLoai(int maloaisp) {
        List<LoaiSanPham> loaiSanPhams = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        String dulieuJson = "";

        String duongdan = TrangChuActivity.SEVER_NAME;

        HashMap<String, String> hsmaloaicha = new HashMap<>();
        hsmaloaicha.put("maloaicha", String.valueOf(maloaisp));

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", "LayDanhSachMeNu");

        hashMapList.add(hsham);
        hashMapList.add(hsmaloaicha);

        DownloadData downloadData = new DownloadData(duongdan, hashMapList);
        downloadData.execute();

        try {
            dulieuJson = downloadData.get();
            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
            loaiSanPhams = xuLyJsonMenu.ParseJsonMenu(dulieuJson);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhams;
    }


}
