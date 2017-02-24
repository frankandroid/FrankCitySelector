package com.hhly.frankcityselector.bean;

import com.hhly.frankcityselector.interfacepac.ISuspensionInterface;

/**
 * @创建者 frank
 * @时间 2017/2/23 18:54
 * @描述：${TODO}
 */

public class BaseIndexBean implements ISuspensionInterface {

    private String tag;

    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return tag;
    }

    public void setIndexTag(String tag) {
        this.tag = tag;
    }
}
