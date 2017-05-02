package com.vnh.lazadaofficial.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vnh.lazadaofficial.Model.OjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 17-Nov-16.
 */

public class ModelGioHang {
    Context context;
    SQLiteDatabase database;

    public void MoKetNoi(Context context) {
        GioHangSQL gioHangSQL = new GioHangSQL(context);
        database = gioHangSQL.getWritableDatabase();
    }

    public boolean ThemSanPhamGioHang(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(GioHangSQL.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(GioHangSQL.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(GioHangSQL.TB_GIOHANG_GIATIEN, sanPham.getGIA());
        contentValues.put(GioHangSQL.TB_GIOHANG_HINHANH, sanPham.getHINHANH());
        contentValues.put(GioHangSQL.TB_SOLUONG,sanPham.getSOLUONG());
        contentValues.put(GioHangSQL.TB_SOLUONGTONKHO,sanPham.getSOLUONGTONKHO());

        long id = database.insert(GioHangSQL.TB_GIOHANG, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<SanPham> LayDanhSachSanPhamTrongGioHang() {
        List<SanPham> sanPhamList = new ArrayList<>();
        String truyvan = "SELECT * FROM " + GioHangSQL.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham();
            int masp = cursor.getInt(cursor.getColumnIndex(GioHangSQL.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(GioHangSQL.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(GioHangSQL.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(GioHangSQL.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(GioHangSQL.TB_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(GioHangSQL.TB_SOLUONGTONKHO));

            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHINHANH(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    public boolean XoaSanPhamTrongGioHang(int masp){
        int xoa =  database.delete(GioHangSQL.TB_GIOHANG,GioHangSQL.TB_GIOHANG_MASP + " = " + masp,null);
        if (xoa > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean CapNhatGioHang(int soluong, int masp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GioHangSQL.TB_SOLUONG,soluong);
        int update = database.update(GioHangSQL.TB_GIOHANG,contentValues,GioHangSQL.TB_GIOHANG_MASP + " = " + masp,null);
        if (update>0){
            return true;
        }
        return false;
    }
}
