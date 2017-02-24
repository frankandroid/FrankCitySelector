package com.hhly.frankcityselector.bean;

/**
 * @创建者 frank
 * @时间 2017/2/21 17:03
 * @描述：${TODO}
 */

public class CityBean extends BaseIndexPinyinBean {

    private String city;
    private boolean isTop;

    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinYin() {
        return !isTop;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    @Override
    public void setIndexTag(String tag) {
        super.setIndexTag(tag);
    }
}
