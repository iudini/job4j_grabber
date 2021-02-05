package ru.job4j.html;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateParseTest extends TestCase {

    public void testParse() {
        assertThat(DateParse.parse("сегодня, 12:33"),
                is(LocalDateTime.of(LocalDate.now(), LocalTime.parse("12:33"))));
        assertThat(DateParse.parse("вчера, 12:34"),
                is(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("1 янв 09, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2009-01-01"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("3 фев 10, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2010-02-03"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("4 мар 11, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2011-03-04"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("7 апр 12, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2012-04-07"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("9 май 13, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2013-05-09"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("15 июн 14, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2014-06-15"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("14 июл 15, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2015-07-14"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("13 авг 16, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2016-08-13"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("22 сен 17, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2017-09-22"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("31 окт 18, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2018-10-31"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("10 ноя 07, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2007-11-10"), LocalTime.parse("12:34"))));
        assertThat(DateParse.parse("9 дек 20, 12:34"),
                is(LocalDateTime.of(LocalDate.parse("2020-12-09"), LocalTime.parse("12:34"))));
    }
}