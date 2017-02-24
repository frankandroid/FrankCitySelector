package com.hhly.frankcityselector.bean;

/**
 * @创建者 frank
 * @时间 2017/2/23 18:55
 * @描述：${TODO}
 */

public abstract class BaseIndexPinyinBean extends BaseIndexBean {

    private String pinyin;


    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    //需要被转换成拼音的目标字段
    public abstract String getTarget();


    public boolean isNeedToPinYin() {
        return true;
    }


}
