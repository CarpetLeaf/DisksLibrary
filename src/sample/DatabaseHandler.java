package sample;

import constants.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs{
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectParams = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectParams, user, password);
        return connection;
    }

    public void addFilm (Film film) {
        String insert = "INSERT INTO " + Const.FILMS_TABLE + "(" + Const.FILMS_PLACE_ID +  "," +
                            Const.FILMS_NAME + "," + Const.FILMS_COUNTRY + "," +
                            Const.FILMS_AGE_LIMIT + "," + Const.FILMS_DURATION + "," +
                            Const.FILMS_GENRE + "," + Const.FILMS_DIRECTOR + "," +
                            Const.FILMS_RELEASED + "," + Const.FILMS_DESCRIPTION + "," +
                            Const.FILMS_MEDIA_TYPE + "," + Const.FILMS_PUBLISHER + "," +
                            Const.FILMS_PRICE + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(insert);
            prepSt.setInt(1, film.getPlace_id());
            prepSt.setString(2, film.getName());
            prepSt.setString(3, film.getCountry());
            prepSt.setInt(4, film.getAge_limit());
            prepSt.setInt(5, film.getDuration());
            prepSt.setString(6, film.getGenre());
            prepSt.setString(7, film.getDirector());
            prepSt.setInt(8, film.getReleased());
            prepSt.setString(9, film.getDescription());
            prepSt.setString(10, film.getMedia_type());
            prepSt.setString(11, film.getPublisher());
            prepSt.setDouble(12, film.getPrice());

            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateFilm(Film film){
        String res = "UPDATE " + Const.FILMS_TABLE + " SET " +  Const.FILMS_PLACE_ID + "=?, " + Const.FILMS_NAME + "=?, " + Const.FILMS_COUNTRY + "=?, " + Const.FILMS_AGE_LIMIT + "=?, " +
                Const.FILMS_DURATION + "=?, " + Const.FILMS_GENRE + "=?, " + Const.FILMS_DIRECTOR + "=?, " + Const.FILMS_RELEASED + "=?, " + Const.FILMS_DESCRIPTION + "=?, " +
                Const.FILMS_MEDIA_TYPE + "=?, " + Const.FILMS_PUBLISHER + "=?, " + Const.FILMS_PRICE + "=? WHERE id=?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(res);
            prepSt.setInt(1, film.getPlace_id());
            prepSt.setString(2, film.getName());
            prepSt.setString(3, film.getCountry());
            prepSt.setInt(4, film.getAge_limit());
            prepSt.setInt(5, film.getDuration());
            prepSt.setString(6, film.getGenre());
            prepSt.setString(7, film.getDirector());
            prepSt.setInt(8, film.getReleased());
            prepSt.setString(9, film.getDescription());
            prepSt.setString(10, film.getMedia_type());
            prepSt.setString(11, film.getPublisher());
            prepSt.setDouble(12, film.getPrice());
            prepSt.setInt(13, film.getMyId());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addMusic(Music music){
        String insert = "INSERT INTO " + ConstMusic.MUSIC_TABLE + "(" + ConstMusic.MUSIC_PLACE +  "," +
                ConstMusic.MUSIC_NAME + "," + ConstMusic.MUSIC_SINGER + "," +
                ConstMusic.MUSIC_DATE + "," + ConstMusic.MUSIC_GENRE + "," +
                ConstMusic.MUSIC_FORMAT + "," + ConstMusic.MUSIC_MEDIA_TYPE + "," +
                ConstMusic.MUSIC_PUBLISHER + "," + ConstMusic.MUSIC_PRICE + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(insert);
            prepSt.setInt(1, music.getPlace_id());
            prepSt.setString(2, music.getName());
            prepSt.setString(3, music.getSinger());
            prepSt.setString(4, music.getDate_of_release());
            prepSt.setString(5, music.getGenre());
            prepSt.setString(6, music.getFormat());
            prepSt.setString(7, music.getMedia_type());
            prepSt.setString(8, music.getPublisher());
            prepSt.setDouble(9, music.getPrice());

            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateMusic(Music music){
        String res = "UPDATE " + ConstMusic.MUSIC_TABLE + " SET " +  ConstMusic.MUSIC_PLACE + "=?, " + ConstMusic.MUSIC_NAME + "=?, " + ConstMusic.MUSIC_SINGER + "=?, " +
                ConstMusic.MUSIC_DATE + "=?, " + ConstMusic.MUSIC_GENRE + "=?, " + ConstMusic.MUSIC_FORMAT + "=?, " +
                ConstMusic.MUSIC_MEDIA_TYPE + "=?, " + ConstMusic.MUSIC_PUBLISHER + "=?, " + ConstMusic.MUSIC_PRICE + "=? WHERE id=?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(res);
            prepSt.setInt(1, music.getPlace_id());
            prepSt.setString(2, music.getName());
            prepSt.setString(3, music.getSinger());
            prepSt.setString(4, music.getDate_of_release());
            prepSt.setString(5, music.getGenre());
            prepSt.setString(6, music.getFormat());
            prepSt.setString(7, music.getMedia_type());
            prepSt.setString(8, music.getPublisher());
            prepSt.setDouble(9, music.getPrice());
            prepSt.setInt(10, music.getMyId());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addAudiobook(Audiobook audiobook){
        String insert = "INSERT INTO " + ConstAudiobook.AUDIOBOOK_TABLE + "(" + ConstAudiobook.AUDIOBOOK_PLACE +  "," +
                ConstAudiobook.AUDIOBOOK_NAME + "," + ConstAudiobook.AUDIOBOOK_AUTHOR + "," +
                ConstAudiobook.AUDIOBOOK_DURATION + "," + ConstAudiobook.AUDIOBOOK_YEAR + "," +
                ConstAudiobook.AUDIOBOOK_DESCRIPTION + "," + ConstAudiobook.AUDIOBOOK_AGE_LIMIT + "," +
                ConstAudiobook.AUDIOBOOK_MEDIA_TYPE + "," + ConstAudiobook.AUDIOBOOK_PUBLISHER + "," +
                ConstAudiobook.AUDIOBOOK_PRICE + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(insert);
            prepSt.setInt(1, audiobook.getPlace_id());
            prepSt.setString(2, audiobook.getName());
            prepSt.setString(3, audiobook.getAuthor());
            prepSt.setString(4, audiobook.getDuration());
            prepSt.setInt(5, audiobook.getYear());
            prepSt.setString(6, audiobook.getDescription());
            prepSt.setInt(7, audiobook.getAge_limit());
            prepSt.setString(8, audiobook.getMedia_type());
            prepSt.setString(9, audiobook.getPublisher());
            prepSt.setDouble(10, audiobook.getPrice());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateAudiobook(Audiobook audio){
        String res = "UPDATE " + ConstAudiobook.AUDIOBOOK_TABLE + " SET " +  ConstAudiobook.AUDIOBOOK_PLACE + "=?, " + ConstAudiobook.AUDIOBOOK_NAME + "=?, " +
                ConstAudiobook.AUDIOBOOK_AUTHOR + "=?, " + ConstAudiobook.AUDIOBOOK_DURATION + "=?, " +
                ConstAudiobook.AUDIOBOOK_YEAR + "=?, " + ConstAudiobook.AUDIOBOOK_DESCRIPTION + "=?, " +
                ConstAudiobook.AUDIOBOOK_AGE_LIMIT + "=?, " + ConstAudiobook.AUDIOBOOK_MEDIA_TYPE + "=?, " +
                ConstAudiobook.AUDIOBOOK_PUBLISHER + "=?, " + ConstAudiobook.AUDIOBOOK_PRICE + "=? WHERE id=?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(res);
            prepSt.setInt(1, audio.getPlace_id());
            prepSt.setString(2, audio.getName());
            prepSt.setString(3, audio.getAuthor());
            prepSt.setString(4, audio.getDuration());
            prepSt.setInt(5, audio.getYear());
            prepSt.setString(6, audio.getDescription());
            prepSt.setInt(7, audio.getAge_limit());
            prepSt.setString(8, audio.getMedia_type());
            prepSt.setString(9, audio.getPublisher());
            prepSt.setDouble(10, audio.getPrice());
            prepSt.setInt(11, audio.getMyId());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addDocument(Document document){
        String insert = "INSERT INTO " + ConstDocument.DOCUMENT_TABLE + "(" + ConstDocument.DOCUMENT_PLACE +  "," +
                ConstDocument.DOCUMENT_NAME + "," + ConstDocument.DOCUMENT_VOLUME + "," +
                ConstDocument.DOCUMENT_DATE + "," + ConstDocument.DOCUMENT_MEDIA_TYPE + "," +
                ConstDocument.DOCUMENT_PUBLISHER + "," + ConstDocument.DOCUMENT_PRICE + ")" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(insert);
            prepSt.setInt(1, document.getPlace_id());
            prepSt.setString(2, document.getName());
            prepSt.setInt(3, document.getVolume());
            prepSt.setString(4, document.getDate());
            prepSt.setString(5, document.getMedia_type());
            prepSt.setString(6, document.getPublisher());
            prepSt.setDouble(7, document.getPrice());

            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateDocument(Document doc){
        String res = "UPDATE " + ConstDocument.DOCUMENT_TABLE + " SET " +  ConstDocument.DOCUMENT_PLACE + "=?, " + ConstDocument.DOCUMENT_NAME + "=?, " +
                ConstDocument.DOCUMENT_VOLUME + "=?, " + ConstDocument.DOCUMENT_DATE + "=?, " + ConstDocument.DOCUMENT_MEDIA_TYPE + "=?, " +
                ConstDocument.DOCUMENT_PUBLISHER + "=?, " + ConstDocument.DOCUMENT_PRICE + "=? WHERE id=?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(res);
            prepSt.setInt(1, doc.getPlace_id());
            prepSt.setString(2, doc.getName());
            prepSt.setInt(3, doc.getVolume());
            prepSt.setString(4, doc.getDate());
            prepSt.setString(5, doc.getMedia_type());
            prepSt.setString(6, doc.getPublisher());
            prepSt.setDouble(7, doc.getPrice());
            prepSt.setInt(8, doc.getMyId());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addProgram(Program program){
        String insert = "INSERT INTO " + ConstProgram.PROGRAM_TABLE + "(" + ConstProgram.PROGRAM_PLACE +  "," +
                ConstProgram.PROGRAM_NAME + "," + ConstProgram.PROGRAM_DEVELOPER + "," +
                ConstProgram.PROGRAM_LAST_UPDATE + "," + ConstProgram.PROGRAM_DESCRIPTION + "," +
                ConstDocument.DOCUMENT_MEDIA_TYPE + "," + ConstDocument.DOCUMENT_PUBLISHER + "," +
                ConstDocument.DOCUMENT_PRICE + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(insert);
            prepSt.setInt(1, program.getPlace_id());
            prepSt.setString(2, program.getName());
            prepSt.setString(3, program.getDeveloper());
            prepSt.setString(4, program.getDate());
            prepSt.setString(5, program.getDescription());
            prepSt.setString(6, program.getMedia_type());
            prepSt.setString(7, program.getPublisher());
            prepSt.setDouble(8, program.getPrice());

            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateProgram(Program prog){
        String res = "UPDATE " + ConstProgram.PROGRAM_TABLE + " SET " +  ConstProgram.PROGRAM_PLACE + "=?, " + ConstProgram.PROGRAM_NAME + "=?, " +
                ConstProgram.PROGRAM_DEVELOPER + "=?, " + ConstProgram.PROGRAM_LAST_UPDATE + "=?, " + ConstProgram.PROGRAM_DESCRIPTION + "=?, " +
                ConstProgram.PROGRAM_MEDIA_TYPE + "=?, " + ConstProgram.PROGRAM_PUBLISHER + "=?, " + ConstProgram.PROGRAM_PRICE + "=? WHERE id=?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(res);
            prepSt.setInt(1, prog.getPlace_id());
            prepSt.setString(2, prog.getName());
            prepSt.setString(3, prog.getDeveloper());
            prepSt.setString(4, prog.getDate());
            prepSt.setString(5, prog.getDescription());
            prepSt.setString(6, prog.getMedia_type());
            prepSt.setString(7, prog.getPublisher());
            prepSt.setDouble(8, prog.getPrice());
            prepSt.setInt(9, prog.getMyId());
            prepSt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Integer> getAllNumbers(String type){
        ArrayList<Integer> nums = new ArrayList<>();
        String select = "SELECT number FROM places WHERE type = ?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(select);
            prepSt.setString(1, type);
            ResultSet rs = prepSt.executeQuery();
            while(rs.next()){
                nums.add(rs.getInt(1));
            }
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return nums;
    }

    public int getPlace(String type, int num){
        String select = "SELECT id FROM places WHERE type = ? AND number = ?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(select);
            prepSt.setString(1, type);
            prepSt.setInt(2, num);
            ResultSet rs = prepSt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public String getTypePlace(int id){
        String select = "SELECT type FROM places WHERE id = ?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(select);
            prepSt.setInt(1, id);
            ResultSet rs = prepSt.executeQuery();
            rs.next();
            return rs.getString(1);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public int getNumPlace(int id){
        String select = "SELECT number FROM places WHERE id = ?";
        try {
            PreparedStatement prepSt = getConnection().prepareStatement(select);
            prepSt.setInt(1, id);
            ResultSet rs = prepSt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}
