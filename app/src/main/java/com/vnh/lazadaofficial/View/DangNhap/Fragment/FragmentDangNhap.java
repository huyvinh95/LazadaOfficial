package com.vnh.lazadaofficial.View.DangNhap.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vnh.lazadaofficial.Model.DangNhap_DangKi.ModelDangNhap;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

/**
 * Created by HUYVINH on 04-Sep-16.
 */
public class FragmentDangNhap extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    Button btnGoogle, btnFaceBook, btnDangNhap;
    EditText etTenDangNhap, etMatKhau;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    ModelDangNhap modelDangNhap = new ModelDangNhap();
    public static int RC_SIGN_IN = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ModelDangNhap modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(), this);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        btnFaceBook = (Button) view.findViewById(R.id.btnFaceBook);
        btnGoogle = (Button) view.findViewById(R.id.btnGoogle);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        etTenDangNhap = (EditText) view.findViewById(R.id.etTenDangNhap);
        etMatKhau = (EditText) view.findViewById(R.id.etMatKhau);

        btnFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangnhap = etTenDangNhap.getText().toString();
                String matkhau = etMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap, matkhau);

                if (kiemtra) {
                    Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                Intent intent = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(intent);
            }

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
