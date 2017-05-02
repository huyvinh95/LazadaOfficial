package com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by HUYVINH on 19-Oct-16.
 */

public class ILoadMoreScroll extends RecyclerView.OnScrollListener {
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    int itemandautien = 0;
    int tongitem = 0;
    int itemloadtruoc = 10;


    public ILoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int tong = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (tongitem <= (itemandautien + itemloadtruoc)){
            iLoadMore.LoadMore(tong);
            Log.d("loadmore", tong + "+" + itemandautien);
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
