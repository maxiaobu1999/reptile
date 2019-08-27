package com.norman.reptile.parse;

import com.norman.reptile.domain.Movie;
import com.norman.reptile.domain.PlayAddress;
import com.norman.reptile.utils.OkzywDbHelper;
import com.norman.reptile.utils.TimeUtil;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/** 解析https://www.okzyw.com/ 视频网站 */
public class ParseOkzyw {
    private static Logger logger = Logger.getLogger(ParseOkzyw.class);
    //首页
    public static void parseList(Page page) {
        int repeat=0;
        Html html = page.getHtml();
        List<Selectable> all = html.css("div.xing_vb ul").nodes();
        all.remove(0);
        all.remove(all.size()-1);
        for (Selectable s : all) {
            String name = Jsoup.parse(s.css("a").get()).text().split(" ")[0];
            String url ="https://www.okzyw.com"+ Jsoup.parse(s.get()).getElementsByTag("a").attr("href");
            List<Selectable> spans = s.css("span").nodes();
            String update_time = Jsoup.parse(spans.get(spans.size()-1).get()).text();
            long time = TimeUtil.conversionToSecond(update_time);

            try {
                OkzywDbHelper helper = new OkzywDbHelper();
                boolean b = helper.checkUpdatae(name, time);
                if (helper.checkUpdatae(name, time)) {
                    page.addTargetRequest(url);
                } else {
                    repeat++;
                }
                helper.destory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if (repeat < 5) {
        List<Selectable> nodes = html.css("div.pages a.pagelink_a").nodes();
        for (Selectable node : nodes) {
            if ("下一页".equals(Jsoup.parse(node.get()).text())) {
                String nextUrl = Jsoup.parse(node.get()).getElementsByTag("a").attr("href");
                page.addTargetRequest("https://www.okzyw.com" + nextUrl);
            }
        }
//        } else {
//            logger.error("++++++++++重复》5");
//        }
    }
    /** 解析详情页 */
    public static void parse(Page page) {
        Html html = page.getHtml();
        Movie movie = new Movie();
        Selectable vodInfo = html.css("div.vodInfo");
        Selectable vodh = vodInfo.css("div.vodh");
        Document parse = Jsoup.parse(vodh.get());
        //封面图片
        String cover_url = Jsoup.parse(html.css("div.vodImg").get())
                .getElementsByTag("img").attr("src");
        movie.setCover_url(cover_url);
        //影片名称
        String name = parse.getElementsByTag("h2").text();
        movie.setName(name);
        //更新至201908220
        String title_tip = parse.getElementsByTag("span").text();
        movie.setTitle_tip(title_tip);
        //评分
        String score = parse.getElementsByTag("label").text();
        movie.setScore(Float.parseFloat(score));

        Selectable vodinfobox = html.css("div.vodinfobox");
        List<String> list = vodinfobox.css("li").all();
        String string;
        for (int i = 0; i < list.size(); i++) {
            string = list.get(i);
            parse = Jsoup.parse(string);
            switch (i ) {
                case 0:
                    //别名：
                    String alias = parse.getElementsByTag("span").text();
                    movie.setAlias(alias);
                    break;
                case 1:
                    //导演
                    String director=parse.getElementsByTag("span").text();
                    movie.setDirector(director);
                    break;
                case 2:
                    //主演：
                    String actors=parse.getElementsByTag("span").text();
                    movie.setActors(actors);
                    break;
                case 3:
                    //类型
                    String type = parse.getElementsByTag("span").text();
                    String[] strings = type.split(" ");
                    StringBuffer tag=new StringBuffer();
                    for (int j = 0; j < strings.length; j++) {
                        if (j == 0) {
                            movie.setType(strings[j]);
                        } else if (j == 1) {
                            tag.append(strings[j]);
                        } else {
                            tag.append("\\$").append(strings[j]);
                        }
                    }
                    if (!TextUtils.isEmpty(tag)) movie.setTag(tag.toString());
                    break;
                case 4:
                    //地区
                    String region = parse.getElementsByTag("span").text();
                    movie.setRegion(region);
                    break;
                case 5:
                    //语言
                    String language = parse.getElementsByTag("span").text();
                    movie.setLanguage(language);
                    break;
                case 6:
                    //上映：2010
                    String release = parse.getElementsByTag("span").text();
                    movie.setRelease_time(release);
                    break;
                case 7:
                    //片长：
                    String length = parse.getElementsByTag("span").text();
                    movie.setLength(length);
                    break;
                case 8:
                    //更新：
                    String update = parse.getElementsByTag("span").text();
                    movie.setUpdate_time(TimeUtil.conversionToSecond(update));
                    break;
                case 9:
                    //总播放量：
                    String views_total = parse.getElementsByTag("span").text();
                    movie.setViews_total(Integer.parseInt(views_total));
                    break;
                case 10:
                    //今日播放量：：
                    String views_today = parse.getElementsByTag("span").text();
                    movie.setViews_today(Integer.parseInt(views_today));
                    break;
                case 11:
                    //总评分数：
                    String score_total = parse.getElementsByTag("span").text();
                    movie.setScore_total(score_total);
                    break;
                case 12:
                    //评分次数
                    String score_num = parse.getElementsByTag("span").text();
                    movie.setScore_num(Integer.parseInt(score_num));
                    break;
            }
        }

        ArrayList<PlayAddress> playAddresses = new ArrayList<>();
        List<Selectable> nodes = html.css("div.ibox.playBox div.vodplayinfo").nodes();
        for (int i = 0; i < nodes.size(); i++) {
            Selectable node = nodes.get(i);
            if (i == 1) {
                //影片简介
                String summary = Jsoup.parse(node.get()).text();
                movie.setSummary(summary);
            }
            if (i > 1) {
                List<Selectable> addressDivs = node.css("div div div").nodes();
                for (Selectable addressDiv : addressDivs) {
                    //播放类型：ckm3u8
                    String media_type = Jsoup.parse(addressDiv.css("h3 span").get()).text();
                    List<Selectable> lis = addressDiv.css("li").nodes();
                    for (int j = 0; j < lis.size(); j++) {
                        String li = Jsoup.parse(lis.get(j).get()).text();
                        String[] arr = li.split("\\$");
                        String title = arr[0];
                        String url = arr[1];
                        PlayAddress playAddress = new PlayAddress();
                        playAddress.setTitle(title);
                        playAddress.setUrl(url);
                        playAddress.setMedia_type(media_type);
                        playAddresses.add(playAddress);

                    }
                }
            }
        }
        movie.setPlayAddresses(playAddresses);
        //保存
        try {
            OkzywDbHelper dbHelper = new OkzywDbHelper();
            dbHelper.addMovie(movie);
            dbHelper.destory();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
