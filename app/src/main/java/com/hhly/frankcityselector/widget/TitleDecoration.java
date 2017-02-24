package com.hhly.frankcityselector.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.hhly.frankcityselector.R;
import com.hhly.frankcityselector.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/21 16:56
 * @描述：${TODO}
 */

public class TitleDecoration extends RecyclerView.ItemDecoration {

    private List<CityBean> mDatas = new ArrayList<>();
    private int mTitleHeight;
    private int mTextFontSize;
    private Paint mPaint;
    private Rect mRect;//用于文字的

    private boolean mFlag;
    private Context mContext;
    private static final String TAG = "TitleDecoration";

    public TitleDecoration(Context context, List<CityBean> datas) {
        mDatas = datas;
        mContext = context;
        mPaint = new Paint();
        mRect = new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context
                .getResources().getDisplayMetrics());
        mTextFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, context
                .getResources().getDisplayMetrics());
        mPaint.setTextSize(mTextFontSize);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = ((RecyclerView.LayoutParams) view.getLayoutParams())
                .getViewLayoutPosition();

        if (position == 0) {
            outRect.set(0, mTitleHeight, 0, 0);
        } else {
            if (null != mDatas.get(position).getSuspensionTag() && !mDatas.get(position)
                    .getSuspensionTag().equals
                    (mDatas.get(position - 1).getSuspensionTag())) {
                outRect.set(0, mTitleHeight, 0, 0);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int firstposition = ((LinearLayoutManager) parent.getLayoutManager())
                .findFirstVisibleItemPosition();

        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth() - parent.getPaddingRight();

        int childcount = parent.getChildCount();

        for (int i = 0; i < childcount; i++) {
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView
                    .getLayoutParams();

            int position = params.getViewLayoutPosition();

            if (position == 0) {
                drawTitleText(c, left, right, childView, position, params);
            } else {

                String tag = mDatas.get(position).getSuspensionTag();
                String nextTag = mDatas.get(position - 1).getSuspensionTag();
                if (null != tag && !tag.equals(nextTag)) {
                    drawTitleText(c, left, right, childView, position, params);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawTitleText(Canvas c, int left, int right, View childView, int position,
                               RecyclerView.LayoutParams params) {

        mPaint.setColor(mContext.getResources().getColor(R.color.gray,mContext.getTheme()));
        c.drawRect(left, childView.getTop() - params.topMargin - mTitleHeight, right, childView
                .getTop() - params.topMargin, mPaint);

        mPaint.setColor(mContext.getResources().getColor(R.color.black,mContext.getTheme()));

        String tag = mDatas.get(position).getSuspensionTag();
        mPaint.getTextBounds(tag, 0, tag.length(), mRect);
        c.drawText(tag, childView.getPaddingLeft(), childView.getTop() - params.topMargin -
                (mTitleHeight / 2 - mRect.height() / 2), mPaint);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int position = ((LinearLayoutManager) parent.getLayoutManager())
                .findFirstVisibleItemPosition();
        String tag = mDatas.get(position).getSuspensionTag();
        View childView = parent.findViewHolderForLayoutPosition(position).itemView;
        mFlag = false;

        //当tag不一致的时候,需要进行tag的替换操作
        //一种很特殊的情景就是tag比较宽，item比较窄，这个时候一个tag中有两个item,往上移动是没有反应的。
        // 所以需要注意一个细节，item的宽度不能太小，要等于大于decoration的宽度。
        if (!mDatas.get(position + 1).getSuspensionTag().equals(tag)) {

            //这个时候才真正的需要开始替换
            if (childView.getTop() + childView.getHeight() < mTitleHeight) {
                c.save();
                c.translate(0, childView.getTop() + childView.getHeight() - mTitleHeight);
                mFlag = true;
            }
        }

        mPaint.setColor(mContext.getResources().getColor(R.color.gray,mContext.getTheme()));
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent
                .getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(mContext.getResources().getColor(R.color.black,mContext.getTheme()));
        mPaint.getTextBounds(tag, 0, tag.length(), mRect);
        c.drawText(tag, childView.getPaddingLeft(),
                parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mRect.height() / 2),
                mPaint);

        if (mFlag) {
            c.restore();
        }
    }
}
