package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".forumTable").select("tr");
        for (int i = 1; i < row.size(); i++) {
            Element href = row.get(i).child(1).tagName("a").child(0);
            Element date = row.get(i).child(5);
            LocalDateTime dateTime = DateParse.parse(date.text());
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(dateTime);
        }
    }
}
