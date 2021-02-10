package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.html.DateParse;
import ru.job4j.html.Post;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {
    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".forumTable").select("tr");
            for (int i = 1; i < row.size(); i++) {
                Element href = row.get(i).child(1).tagName("a").child(0);
                Element date = row.get(i).child(5);
                String url = href.attr("href");
                result.add(detail(url));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".msgTable");
            String name = row.get(0).select(".messageHeader").get(0).text();
            String desc = row.get(0).select(".msgBody").get(1).text();
            String time = row.get(0).select(".msgFooter").get(0).text();
            LocalDateTime dateTime = DateParse.parse(time.substring(0, time.indexOf("[")).strip());
            post = new Post(name, desc, dateTime, link);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}
