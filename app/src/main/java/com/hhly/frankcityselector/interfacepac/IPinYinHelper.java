package com.hhly.frankcityselector.interfacepac;

import com.hhly.frankcityselector.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/24 15:33
 * @描述：${处理拼音的接口}
 */

public interface IPinYinHelper {


    //将城市汉语转换成拼音封装到bean里面
    IPinYinHelper convertToPinyin(List<? extends BaseIndexPinyinBean> baseIndexPinyinBeanList);

    //将tag封装到bean里面
    IPinYinHelper fillIndexTags(List<? extends BaseIndexPinyinBean> baseIndexPinyinBeanList);

    //对数据进行排序

    IPinYinHelper sortData(List<? extends BaseIndexPinyinBean> baseIndexPinyinBeen);



}
