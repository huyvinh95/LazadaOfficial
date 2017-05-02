package com.vnh.lazadaofficial.Model.DangNhap_DangKi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.vnh.lazadaofficial.ConnectInternet.DownloadData;
import com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu.XuLyJsonMenu;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HUYVINH on 07-Sep-16.
 */
public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken getToken() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener) {
        // dang nhap google

        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity) context), failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhap(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            return opr.get();
        } else {
            return null;
        }
    }

    public void HuyToken() {
        accessTokenTracker.stopTracking();
    }

    public String layCacche(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = sharedPreferences.getString("tennv", "");

        return tennv;
    }

    public void CapNhatCache(Context context, String tennv) {
        SharedPreferences cachedangnhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedangnhap.edit();
        editor.putString("tennv", tennv);
        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context, String tendangnhap, String matkhau) {
        boolean kiemtra = false;

        String duongdan = TrangChuActivity.SEVER_NAME;
        List<HashMap<String, String>> attr = new ArrayList<>();

        HashMap<String, String> hsham = new HashMap<>();
        hsham.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsten = new HashMap<>();
        hsten.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsmatkhau = new HashMap<>();
        hsmatkhau.put("matkhau", matkhau);

        attr.add(hsham);
        attr.add(hsten);
        attr.add(hsmatkhau);

        DownloadData downloadData = new DownloadData(duongdan, attr);
        downloadData.execute();

        try {
            String dulieu = downloadData.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonketqua = jsonObject.getString("ketqua");
            if (jsonketqua.equals("true")) {
                kiemtra = true;

                String tennv = jsonObject.getString("tennv");
                CapNhatCache(context, tennv);

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
