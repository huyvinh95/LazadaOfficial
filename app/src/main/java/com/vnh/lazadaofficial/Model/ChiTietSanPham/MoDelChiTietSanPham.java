package com.vnh.lazadaofficial.Model.ChiTietSanPham;

import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.util.Log;

import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.ChiTietSanPham;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
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
 * Created by HUYVINH on 26-Oct-16.
 */

public class MoDelChiTietSanPham {
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

    public SanPham LayChiTietSanPham(String tenham, String tenmang, int masp) {
        String duongdan = TrangChuActivity.SEVER_NAME;
        String dulieujson = "";
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        SanPham sp = new SanPham();
        List<HashMap<String, String>> listHashmap = new ArrayList<>();

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", tenham);

        HashMap<String, String> hsmasp = new HashMap<>();
        hsmasp.put("masp", String.valueOf(masp));

        listHashmap.add(hsham);
        listHashmap.add(hsmasp);


        DownloadData dl = new DownloadData(duongdan, listHashmap);
        dl.execute();

        try {
            dulieujson = dl.get();
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int dem = jsonArray.length();
            for (int i = 0; i < dem; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                sp.setMASP(jsonObject1.getInt("MASP"));
                sp.setTENSP(jsonObject1.getString("TENSP"));
                sp.setGIA(jsonObject1.getInt("GIATIEN"));
                sp.setSOLUONG(jsonObject1.getInt("SOLUONG"));
                sp.setANHNHO(jsonObject1.getString("ANHNHO"));
                sp.setTHONGTIN(jsonObject1.getString("THONGTIN"));
                sp.setMALOAISP(jsonObject1.getInt("MALOAISP"));
                sp.setMATHUONGHIEU(jsonObject1.getInt("MATHUONGHIEU"));
                sp.setMANV(jsonObject1.getInt("MANV"));
                sp.setLUOTMUA(jsonObject1.getInt("LUOTMUA"));
                sp.setTENNHANVIEN(jsonObject1.getString("TENNV"));

                JSONArray jsonThongSoKyThuat = jsonObject1.getJSONArray("THONGSOCHITIET");
                for (int j = 0; j < jsonThongSoKyThuat.length(); j++) {
                    JSONObject objectThongSo = jsonThongSoKyThuat.getJSONObject(j);

                    for (int k = 0; k < objectThongSo.length(); k++) {
                        String thongtinchitiet = objectThongSo.names().getString(k);
                        ChiTietSanPham chitiet = new ChiTietSanPham();
                        chitiet.setTENCHITIET(thongtinchitiet);
                        chitiet.setGIATRI(objectThongSo.getString(thongtinchitiet));

                        chiTietSanPhams.add(chitiet);

                    }

                }
                sp.setListChiTietSanPham(chiTietSanPhams);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sp;
    }
}
