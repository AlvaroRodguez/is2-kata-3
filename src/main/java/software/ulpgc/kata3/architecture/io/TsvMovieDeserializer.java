package software.ulpgc.kata3.architecture.io;

import software.ulpgc.kata3.architecture.model.Genre;
import software.ulpgc.kata3.architecture.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class TsvMovieDeserializer implements MovieDeserializer{
    @Override
    public Movie deserialize(String text) {
        String[] fields = text.split("\t");
        return deserialize(fields);
    }

    private Movie deserialize(String[] fields) {
        return new Movie(
                fields[0],
                fields[2],
                fields[3],
                toInt(fields[5]),
                toInt(fields[7]),
                toGenreList(fields[8])
        );
    }

    private List<Genre> toGenreList(String field) {
        List<Genre> genres = new ArrayList<>();
        for (String genreName : field.split(",")) {
            genres.add(new Genre(genreName));
        }
        return genres;
    }

    private int toInt(String field) {
        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
