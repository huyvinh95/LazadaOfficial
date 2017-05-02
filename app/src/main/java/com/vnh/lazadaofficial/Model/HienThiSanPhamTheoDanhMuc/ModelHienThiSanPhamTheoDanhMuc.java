package com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc;

import android.renderscript.Sampler;
import android.util.Log;

import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 27-Sep-16.
 */

public class ModelHienThiSanPhamTheoDanhMuc {
    public List<SanPham> LaySanPhamTheoMaLoai(int masp,String tenham, String tenmang, int limit) {
        String duongdan = TrangChuActivity.SEVER_NAME;
        String dulieujson = "";

        List<SanPham> listSanPham = new ArrayList<>();
        List<HashMap<String, String>> atts = new ArrayList<>();

        HashMap<String, String> ham = new HashMap<>();
        ham.put("ham", tenham);

        HashMap<String, String> hMasp = new HashMap<>();
        hMasp.put("maloaisp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        atts.add(ham);
        atts.add(hMasp);
        atts.add(hsLimit);

        DownloadData downloadData = new DownloadData(duongdan, atts);
        downloadData.execute();

        try {
            dulieujson = downloadData.get();
            Log.d("dulieuhienthisanpham",dulieujson);

            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int dem = jsonArray.length();
            for (int i = 0; i < dem; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                SanPham sanPham = new SanPham();

                sanPham.setMASP(jsonObject1.getInt("MASP"));
                sanPham.setTENSP(jsonObject1.getString("TENSP"));
                sanPham.setGIA(jsonObject1.getInt("GIATIEN"));
                sanPham.setANHLON(jsonObject1.getString("HINHSANPHAM"));

                listSanPham.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listSanPham;
    }
}
