package sample;

public class Film {
    int myId, place_id, age_limit, duration, released;
    String name, country, genre, director, description, media_type, publisher;
    double price;

    public Film(int place_id, String name, String country, int age_limit, int duration,
                String genre, String director, int released, String description, String media_type,
                String publisher, double price) {
        this.place_id = place_id;
        this.age_limit = age_limit;
        this.duration = duration;
        this.released = released;
        this.name = name;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.description = description;
        this.media_type = media_type;
        this.publisher = publisher;
        this.price = price;
    }

    public Film(){

    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int id) {
        this.myId = id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public int getDuration() {
        return duration;
    }

    public int getReleased() {
        return released;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return this.place_id + "; " + this.name + "; " + this.country + "; " +
                this.age_limit + "; " + this.duration + "; " + this.genre + "; " +
                this.director + "; " + this.released + "; " + this.description + "; " +
                this.media_type + "; " + this.publisher + "; " + this.price;
    }
}
