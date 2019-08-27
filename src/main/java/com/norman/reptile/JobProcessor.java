package com.norman.reptile;

import com.norman.reptile.parse.ParseOkzyw;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class JobProcessor implements PageProcessor {
    /**
     * 解析页面
     */
    public void process(Page page) {
        System.out.println("====================");
        String url = page.getUrl().get();
        if ("https://www.okzyw.com/".equals(url)||url.startsWith("https://www.okzyw.com/?m=vod-index")) {
            ParseOkzyw.parseList(page);//首页&列表页
        }
        if (url.startsWith("https://www.okzyw.com/?m=vod-detail")) {
            ParseOkzyw.parse(page);//详情页
        }
        System.out.println("====================");
    }

    /** 配置抓取间隔，重试。。 */
    private Site site = Site.me()
            .setCharset("utf-8")//编码
            .setTimeOut(1000)//超时时间
            .setRetrySleepTime(1000)//重试间隔时间
            .setRetryTimes(2)//重试次数
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
            ;

    public Site getSite() {
        return site;
    }

    //执行爬虫
    public static void main(String[] args) {
        //Spider 组合组件
        Spider spider = Spider.create(new JobProcessor())
//                .addUrl("https://www.okzyw.com/?m=vod-detail-id-38695.html")//设置爬取数据的url
                .addUrl("https://www.okzyw.com/")//设置爬取数据的url
//                .addPipeline(new FilePipeline("/Users/v_maqinglong/Desktop/05.WebMagic"))//输出成文件
                .thread(1);//线程数
        spider.run();//执行
    }
}
