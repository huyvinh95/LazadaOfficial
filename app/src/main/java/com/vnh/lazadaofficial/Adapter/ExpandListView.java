package com.vnh.lazadaofficial.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vnh.lazadaofficial.Model.OjectClass.LoaiSanPham;
import com.vnh.lazadaofficial.Model.TrangChu.XuLyMenu.XuLyJsonMenu;
import com.vnh.lazadaofficial.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by HUYVINH on 31-Aug-16.
 */
public class ExpandListView extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> listLoaiSanPham;
    ViewHolder viewHolder;

    public ExpandListView(Context context, List<LoaiSanPham> listLoaiSanPham) {
        this.context = context;
        this.listLoaiSanPham = listLoaiSanPham;

        XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();

        int count = listLoaiSanPham.size();
        for (int i = 0; i < count; i++) {
            int maloaisp = listLoaiSanPham.get(i).getMALOAISP();
            listLoaiSanPham.get(i).setListCon(xuLyJsonMenu.LaySanPhamTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return listLoaiSanPham.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if (listLoaiSanPham.get(vitriGroupCha).getListCon().size() != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return listLoaiSanPham.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return listLoaiSanPham.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return listLoaiSanPham.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return listLoaiSanPham.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // tao giao dien cho group cha
    public class ViewHolder {
        TextView txtTenLoaiSanPham;
        ImageView imageView;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
        View viewGroupCha = convertView;
        if (viewGroupCha == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = inflater.inflate(R.layout.custom_layout_groupcha, parent, false);
            viewHolder.imageView = (ImageView) viewGroupCha.findViewById(R.id.imgMenu);
            viewHolder.txtTenLoaiSanPham = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSp);
            viewGroupCha.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) viewGroupCha.getTag();
        }

        viewHolder.txtTenLoaiSanPham.setText(listLoaiSanPham.get(vitriGroupCha).getTENLOAISP());
        int demsanphamcon = listLoaiSanPham.get(vitriGroupCha).getListCon().size();

        if (demsanphamcon > 0) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imageView.setVisibility(View.INVISIBLE);
        }

        if (!isExpanded) {
            viewHolder.imageView.setImageResource(R.drawable.ic_add_black_24dp);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.ic_remove_black_24dp);
            viewGroupCha.setBackgroundResource(R.color.bgGray);
        }

        // set sự kiện on Click cho ExpandListView
        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("Tenloai", listLoaiSanPham.get(vitriGroupCha).getTENLOAISP() + " " + listLoaiSanPham.get(vitriGroupCha).getMALOAISP());
                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SecondExpandListView secondExpandListView = new SecondExpandListView(context);

        secondExpandListView.setGroupIndicator(null);

        ExpandListView secondAdapter = new ExpandListView(context, listLoaiSanPham.get(vitriGroupCha).getListCon());
        secondExpandListView.setAdapter(secondAdapter);

        notifyDataSetChanged();

        return secondExpandListView;
    }

    @Override
    public boolean isChildSelectable(int vitriGroupCha, int vitriGroupCon) {
        return false;
    }

    // Custom ExpandListView cho chiều rộng vào chiều cao
    public class SecondExpandListView extends ExpandableListView {

        public SecondExpandListView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

// lấy chiều rộng và chiều cao của màn hình thông qua WindownManager

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

// set chiều rộng, cao động cho ExpandListView

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    // second adapter
//    public class SecondAdapter extends BaseExpandableListAdapter {
//        List<LoaiSanPham> listCon;
//
//        public SecondAdapter(List<LoaiSanPham> listCon) {
//            this.listCon = listCon;
//
//            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
//            int count = listCon.size();
//            for (int i = 0; i < count; i++) {
//                int maloai = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListCon(xuLyJsonMenu.LaySanPhamTheoMaLoai(maloai));
//            }
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha);
//        }
//
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewGroupCha = inflater.inflate(R.layout.custom_layout_groupcha, parent, false);
//
//            TextView txtTenLoaisp = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSp);
//            txtTenLoaisp.setText(listCon.get(vitriGroupCha).getTENLOAISP());
//
//            return viewGroupCha;
//        }
//
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isLastChild, View convertView, ViewGroup parent) {
//            TextView tv = new TextView(context);
//            tv.setText(listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15, 5, 5, 5);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//            return tv;
//
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }
}
