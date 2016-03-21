package com.example.hoada921.readrss.models;

import java.util.ArrayList;

/**
 * Created by Hoada921 on 2016-03-19.
 */
public class LinkUrl {
    private String name;
    private String url;
    private ArrayList<LinkUrl> linkUrlArrayList = new ArrayList<>();

    public LinkUrl(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public LinkUrl() {
        LinkUrl item = null;
        item = new LinkUrl("all", "http://www.voanews.com/api/epiqq");
        linkUrlArrayList.add(item);
        item = new LinkUrl("USA", "http://www.voanews.com/api/zq$omekvi_");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Africa", "http://www.voanews.com/api/z-$otevtiq");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Asia", "http://www.voanews.com/api/zo$o_egviy");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Middle East", "http://www.voanews.com/api/zr$opeuvim");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Europe", "http://www.voanews.com/api/zj$oveytit");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Americas", "http://www.voanews.com/api/zoripegtim");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Science & Technology", "http://www.voanews.com/api/zyritequir");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Economy", "http://www.voanews.com/api/zy$oqeqti");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Health", "http://www.voanews.com/api/zt$opeitim");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Arts & Entertainment", "http://www.voanews.com/api/zp$ove-vir");
        linkUrlArrayList.add(item);
        item = new LinkUrl("2016 USA Votes", "http://www.voanews.com/api/zuriqiepuiqm");
        linkUrlArrayList.add(item);
        item = new LinkUrl("One-Minute Features", "http://www.voanews.com/api/z$roqmetuoqm");
        linkUrlArrayList.add(item);
        item = new LinkUrl("VOA Editors Picks", "http://www.voanews.com/api/zgkoq_e_miqv");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Day in Photos", "http://www.voanews.com/api/z$-jqetv-i");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Shaka: Extra Time", "http://www.voanews.com/api/zpuoqre-iiqq");
        linkUrlArrayList.add(item);
        item = new LinkUrl("Visiting the USA", "http://www.voanews.com/api/zj$qqmeytoqm");
        linkUrlArrayList.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String link) {
        this.url = link;
    }

    public ArrayList<LinkUrl> getUrlArrayList() {
        return linkUrlArrayList;
    }


}
