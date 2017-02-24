package com.hhly.frankcityselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hhly.frankcityselector.bean.CityBean;
import com.hhly.frankcityselector.bean.RegionInfo;
import com.hhly.frankcityselector.db.RegionDAO;
import com.hhly.frankcityselector.widget.SlideBar;
import com.hhly.frankcityselector.widget.TitleDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CityBean> mCityBeanList = new ArrayList<>();
    private TextView mTextView;
    private SlideBar mSlideBar;

    CityBean mCityBean;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<RegionInfo> citys = RegionDAO.getProvencesOrCity(2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mTextView = (TextView) findViewById(R.id.center_tv);
        mSlideBar = (SlideBar) findViewById(R.id.slidebar);
        mSlideBar.setTextView(mTextView);
        for (int i = 0; i < 50; i++) {
            mCityBean = new CityBean("hello");
            mCityBean.setIndexTag("H");
            mCityBeanList.add(mCityBean);
        }

        for (int i = 0; i < 50; i++) {
            mCityBean = new CityBean("world");
            mCityBean.setIndexTag("W");
            mCityBeanList.add(mCityBean);
        }

        MainAdapter mainAdapter = new MainAdapter(mCityBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new TitleDecoration(this, mCityBeanList));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mainAdapter);

    }
}
