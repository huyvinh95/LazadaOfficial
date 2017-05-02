package com.vnh.lazadaofficial.Model.DangNhap_DangKi;

import android.util.Log;

import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.OjectClass.NhanVien;
import com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu.XuLyJsonMenu;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 12-Sep-16.
 */
public class ModelDangKi {
    public Boolean DangKiThanhVien(NhanVien nhanVien){
        String duongdan= TrangChuActivity.SEVER_NAME;
        String dulieujson = "";

        List<HashMap<String,String>> attr = new ArrayList<>();

        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("ham","DangKyNhanVien");

        HashMap<String,String> tenNV = new HashMap<>();
        tenNV.put("tennv",nhanVien.getTenNv());

        HashMap<String,String> tenDangNhap = new HashMap<>();
        tenDangNhap.put("tendangnhap",nhanVien.getTenDangNhap());

        HashMap<String,String> matKhau = new HashMap<>();
        matKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> maLoaiNV = new HashMap<>();
        maLoaiNV.put("maloainv",String.valueOf(nhanVien.getMaLoaiNv()));

        HashMap<String,String> emailDocQuyen = new HashMap<>();
        emailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());

        attr.add(goiham);
        attr.add(tenNV);
        attr.add(tenDangNhap);
        attr.add(matKhau);
        attr.add(maLoaiNV);
        attr.add(emailDocQuyen);

        DownloadData downloadData = new DownloadData(duongdan,attr);
        downloadData.execute();

        try {
            dulieujson = downloadData.get();
            Log.d("dulieudangki",dulieujson);
//            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
//            xuLyJsonMenu.ParseJsonMenu(dulieujson);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;

    }
}
