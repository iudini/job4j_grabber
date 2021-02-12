package ru.job4j.grabber;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("login"),
                    cfg.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "INSERT INTO post(name, text, link, created) VALUES (?, ?, ?, ?)"
                       + "ON CONFLICT (link) DO NOTHING")) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDesc());
            statement.setString(3, post.getUrl());
            statement.setTimestamp(4, Timestamp.valueOf(post.getDateTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (Statement statement = cnn.createStatement()) {
            String sql = "SELECT * FROM post";
            statement.execute(sql);
            try (ResultSet posts = statement.getResultSet()) {
                while (posts.next()) {
                    list.add(new Post(
                            posts.getInt("id"),
                            posts.getString("name"),
                            posts.getString("text"),
                            posts.getString("link"),
                            posts.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement statement = cnn.prepareStatement(
                "SELECT * FROM post WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    post = new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Properties cfg = new Properties();
        cfg.load(PsqlStore.class.getResourceAsStream("/grabber.properties"));
        PsqlStore store = new PsqlStore(cfg);
        store.save(new Post("Post #12", "Smth bla bla bla", "ya.ru", LocalDateTime.now()));
        store.save(new Post("Post #21", "Smth else bla bla bla", "yandex.com",
                LocalDateTime.parse("2012-01-02T19:32")));
        List<Post> postList = store.getAll();
        for (var post : postList) {
            System.out.println(post);
        }
        System.out.println(store.findById(1));
    }
}
