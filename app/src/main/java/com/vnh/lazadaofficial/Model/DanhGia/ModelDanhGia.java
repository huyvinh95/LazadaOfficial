package com.vnh.lazadaofficial.Model.DanhGia;

import android.util.Log;

import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.View.DanhGia.DanhGiaActiviti;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 05-Nov-16.
 */

public class ModelDanhGia {
    public List<DanhGia> LayDanhSachDanhGia (int masp, int limit){
    String duongdan = TrangChuActivity.SEVER_NAME;
    List<DanhGia> listDanhGia = new ArrayList<>();

    List<HashMap<String,String>> listhashmapdanhgia = new ArrayList<>();

    HashMap<String,String> hsham = new HashMap<>();
    hsham.put("ham","LayDanhSachDanhGiaTheoMa");

    HashMap<String,String> hsmasp = new HashMap<>();
    hsmasp.put("masp", String.valueOf(masp));

    HashMap<String,String> hslimit = new HashMap<>();
    hslimit.put("limit", String.valueOf(limit));

    listhashmapdanhgia.add(hsham);
    listhashmapdanhgia.add(hsmasp);
    listhashmapdanhgia.add(hslimit);

    DownloadData downloadData = new DownloadData(duongdan,listhashmapdanhgia);
    downloadData.execute();

    try {
        String dulieu = downloadData.get();
        Log.d("laydulieu",dulieu);
        JSONObject jsonObject = new JSONObject(dulieu);
        JSONArray jsonArray = jsonObject.getJSONArray("DANHSACHDANHGIA");
        for (int i = 0; i<jsonArray.length();i++){
            DanhGia danhGia = new DanhGia();
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            danhGia.setMADG(jsonObject1.getString("MADG"));
            danhGia.setMASP(jsonObject1.getInt("MASP"));
            danhGia.setNOIDUNG(jsonObject1.getString("NOIDUNG"));
            danhGia.setTIEUDE(jsonObject1.getString("TIEUDE"));
            danhGia.setTENTHIETBI(jsonObject1.getString("TENTHIETBI"));
            danhGia.setSOSAO(jsonObject1.getInt("SOSAO"));
            danhGia.setNGAYDANHGIA(jsonObject1.getString("NGAYDANHGIA"));

            listDanhGia.add(danhGia);

        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return listDanhGia;
}


    public boolean DanhGiaChiTiet(DanhGia danhGia){
        boolean kiemtra = false;
        String duongdan  = TrangChuActivity.SEVER_NAME;
        List<HashMap<String,String>> listhashmap = new ArrayList<>();

        HashMap<String,String> hsham = new HashMap<>();
        hsham.put("ham","ThemDanhGia");

        HashMap<String,String> hsmadg = new HashMap<>();
        hsmadg.put("madg",danhGia.getMADG());

        HashMap<String,String> hsmasp = new HashMap<>();
        hsmasp.put("masp", String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hstenthietbi = new HashMap<>();
        hstenthietbi.put("tenthietbi",danhGia.getTENTHIETBI());

        HashMap<String,String> hstieude = new HashMap<>();
        hstieude.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsnoidung = new HashMap<>();
        hsnoidung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hssosao = new HashMap<>();
        hssosao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        listhashmap.add(hsham);
        listhashmap.add(hsmadg);
        listhashmap.add(hsmasp);
        listhashmap.add(hstenthietbi);
        listhashmap.add(hstieude);
        listhashmap.add(hsnoidung);
        listhashmap.add(hssosao);

        DownloadData downloadData = new DownloadData(duongdan,listhashmap);
        downloadData.execute();

        try {
            String dulieu = downloadData.get();
            Log.d("danhgia",dulieu);
            JSONObject jsonObject = new JSONObject(dulieu);
            String ketqua = jsonObject.getString("ketqua");
            if (ketqua.equals("true")){
                kiemtra = true;
            } else {
                kiemtra = false;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
