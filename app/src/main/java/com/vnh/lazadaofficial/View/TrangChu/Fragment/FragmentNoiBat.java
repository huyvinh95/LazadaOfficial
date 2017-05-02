package com.vnh.lazadaofficial.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnh.lazadaofficial.Adapter.AdapterNoiBat;
import com.vnh.lazadaofficial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 29-Aug-16.
 */
public class FragmentNoiBat extends Fragment {
    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.demo);

        List<String> stringList = new ArrayList<>();

        for (int i = 0 ; i < 20 ; i++){
            String add= "Noi Bat " + i;
            stringList.add(add);
        }
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        adapterNoiBat = new AdapterNoiBat(getActivity(),stringList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);

        adapterNoiBat.notifyDataSetChanged();
        return view;
    }
}
