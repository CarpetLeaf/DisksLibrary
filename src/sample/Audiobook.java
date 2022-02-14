package sample;

import java.sql.Time;

public class Audiobook {
    int myId, place_id, year, age_limit;
    String name, author, description, media_type, publisher, duration;
    double price;

    public Audiobook(int place_id, String name, String author, String duration, int year, String description, int age_limit, String media_type, String publisher, double price) {
        this.place_id = place_id;
        this.year = year;
        this.age_limit = age_limit;
        this.name = name;
        this.author = author;
        this.description = description;
        this.media_type = media_type;
        this.publisher = publisher;
        this.duration = duration;
        this.price = price;
    }

    public Audiobook(){

    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
