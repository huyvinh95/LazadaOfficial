package com.vnh.lazadaofficial.Model.TrangChu_DienTu;

import android.util.Log;

import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu.XuLyJsonMenu;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 18-Sep-16.
 */
public class ModelDienTu {
    public List<SanPham> LaySanPhamTop(String tenham,String tenmang) {
        String duongdan = TrangChuActivity.SEVER_NAME;
        String dulieujson = "";

        List<SanPham> listSanPham = new ArrayList<>();

        List<HashMap<String, String>> hssanpham = new ArrayList<>();

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", tenham);
        hssanpham.add(hsham);

        DownloadData downloadData = new DownloadData(duongdan, hssanpham);
        downloadData.execute();

        try {
            dulieujson = downloadData.get();
            Log.d("sanphamtop", dulieujson);
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


    public List<ThuongHieu> LayTenThuongHieu(String tenham,String tenmang) {

        String duongdan = TrangChuActivity.SEVER_NAME;
        String dulieuJson = "";

        List<ThuongHieu> listThuongHieu = new ArrayList<>();

        List<HashMap<String, String>> hsTenThuongHieu = new ArrayList<>();

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", tenham);

        hsTenThuongHieu.add(hsham);

        DownloadData downloadData = new DownloadData(duongdan, hsTenThuongHieu);
        downloadData.execute();

        try {
            dulieuJson = downloadData.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            JSONArray jsonArray = jsonObject.getJSONArray(tenmang);
            int dem = jsonArray.length();

            for (int i = 0; i < dem; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();

                thuongHieu.setMATHUONGHIEU(jsonObject1.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(jsonObject1.getString("TENSP"));
                thuongHieu.setHINHLOAISPTH(jsonObject1.getString("HINHSANPHAM"));

                listThuongHieu.add(thuongHieu);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return listThuongHieu;
    }

    // Lấy danh sách top phụ kiện


}
