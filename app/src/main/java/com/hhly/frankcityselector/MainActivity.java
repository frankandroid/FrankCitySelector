package com.hhly.frankcityselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hhly.frankcityselector.bean.PinYinHelperImpl;
import com.hhly.frankcityselector.bean.RegionInfo;
import com.hhly.frankcityselector.db.RegionDAO;
import com.hhly.frankcityselector.widget.SlideBar;
import com.hhly.frankcityselector.widget.TitleDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<RegionInfo> mCityBeanList = new ArrayList<>();
    private TextView mTextView;
    private SlideBar mSlideBar;

    RegionInfo mCityBean;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<RegionInfo> citys = RegionDAO.getProvencesOrCity(2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mTextView = (TextView) findViewById(R.id.center_tv);
        mSlideBar = (SlideBar) findViewById(R.id.slidebar);
        mSlideBar.setTextView(mTextView);

        PinYinHelperImpl pinYinHelper = new PinYinHelperImpl();
        pinYinHelper.sortData(citys);

        MainAdapter mainAdapter = new MainAdapter(citys);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new TitleDecoration(this, citys));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));
        mRecyclerView.setAdapter(mainAdapter);

        mSlideBar.setOnTouchingLetterChangedListener(new SlideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {

                char c = s.toUpperCase().charAt(0);

                for (int i = 0; i < citys.size(); i++) {
                    if (citys.get(i).getSuspensionTag().toUpperCase().charAt(0) == c) {
                        mLinearLayoutManager.scrollToPositionWithOffset(i,0);
                        return;
                    }
                }
            }
        });
    }
}
