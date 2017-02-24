package com.hhly.frankcityselector.bean;

public class RegionInfo extends BaseIndexPinyinBean {

    private int id;
    private int parent;
    private String name;
    private int type;
    private boolean isTop;

    public RegionInfo() {
        super();
    }

    public RegionInfo(int id, int parent, String name) {
        super();
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getTarget() {
        return name;
    }

    @Override
    public String getSuspensionTag() {
        return tag;
    }

    @Override
    public boolean isNeedToPinYin() {
        return true;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }
}
