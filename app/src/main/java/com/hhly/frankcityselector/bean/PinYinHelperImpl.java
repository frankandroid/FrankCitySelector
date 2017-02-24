package com.hhly.frankcityselector.bean;

import com.github.promeg.pinyinhelper.Pinyin;
import com.hhly.frankcityselector.interfacepac.IPinYinHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @创建者 frank
 * @时间 2017/2/24 15:34
 * @描述：${TODO}
 */

public class PinYinHelperImpl implements IPinYinHelper {
    @Override
    public IPinYinHelper convertToPinyin(List<? extends BaseIndexPinyinBean>
                                                 baseIndexPinyinBeanList) {
        if (null == baseIndexPinyinBeanList || baseIndexPinyinBeanList.isEmpty()) {
            return this;
        }
        for (int i = 0; i < baseIndexPinyinBeanList.size(); i++) {
            if (baseIndexPinyinBeanList.get(i).isNeedToPinYin()) {
                String target = baseIndexPinyinBeanList.get(i).getTarget();
                StringBuilder pinYinSb = new StringBuilder();

                for (int i1 = 0; i1 < target.length(); i1++) {
                    pinYinSb.append(Pinyin.toPinyin(target.charAt(i1)).toUpperCase());
                }
                baseIndexPinyinBeanList.get(i).setPinyin(pinYinSb.toString());
            }
        }
        return this;
    }

    @Override
    public IPinYinHelper fillIndexTags(List<? extends BaseIndexPinyinBean> baseIndexPinyinBeanList) {

        if (null == baseIndexPinyinBeanList || baseIndexPinyinBeanList.isEmpty()) {
            return this;
        }

        for (int i = 0; i < baseIndexPinyinBeanList.size(); i++) {
            if (baseIndexPinyinBeanList.get(i).isNeedToPinYin()) {
                String first = baseIndexPinyinBeanList.get(i).getPinyin().substring(0, 1);
                if (first.matches("[A-Z]")) {
                    baseIndexPinyinBeanList.get(i).setIndexTag(first);
                } else {
                    baseIndexPinyinBeanList.get(i).setIndexTag("#");
                }
            }
        }
        return this;
    }

    @Override
    public IPinYinHelper sortData(List<? extends BaseIndexPinyinBean> baseIndexPinyinBeen) {

        convertToPinyin(baseIndexPinyinBeen);
        fillIndexTags(baseIndexPinyinBeen);

        Collections.sort(baseIndexPinyinBeen, new Comparator<BaseIndexPinyinBean>() {
            @Override
            public int compare(BaseIndexPinyinBean o1, BaseIndexPinyinBean o2) {

                if (!o1.isNeedToPinYin()) {
                    return 0;
                } else if (!o2.isNeedToPinYin()) {
                    return 0;
                } else if (o1.getSuspensionTag().equals("#")) {
                    return 1;
                } else if (o2.getSuspensionTag().equals("#")) {
                    return -1;
                } else {
                    return o1.getPinyin().compareTo(o2.getPinyin());
                }
            }
        });
        return this;
    }
}
