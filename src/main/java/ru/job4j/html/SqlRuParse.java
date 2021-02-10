package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Post;

import java.io.IOException;
import java.time.LocalDateTime;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int page = 1; page <= 5; page++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + page).get();
            Elements row = doc.select(".forumTable").select("tr");
            for (int i = 1; i < row.size(); i++) {
                Element href = row.get(i).child(1).tagName("a").child(0);
                Element date = row.get(i).child(5);
                String url = href.attr("href");
                String name = href.text();
                LocalDateTime dateTime = DateParse.parse(date.text());
                parsePostDesc(url, name, dateTime);
            }
        }
    }

    static Post parsePostDesc(String url, String name, LocalDateTime dateTime) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgTable");
        String desc = row.get(0).select(".msgBody").get(1).text();
        return new Post(name, desc, url, dateTime);
    }
}
