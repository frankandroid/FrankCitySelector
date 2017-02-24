package com.hhly.frankcityselector;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhly.frankcityselector.bean.RegionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/22 12:01
 * @描述：${TODO}
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<RegionInfo> mCityBeanList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public MainAdapter(List<RegionInfo> cityBeanList) {
        mCityBeanList = cityBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());

        View rootView = mLayoutInflater.inflate(R.layout.item_main, parent,false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mCityBeanList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCityBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.content_tv);

        }
    }
}
