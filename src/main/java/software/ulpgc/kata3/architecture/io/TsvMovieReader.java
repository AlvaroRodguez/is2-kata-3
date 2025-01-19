package software.ulpgc.kata3.architecture.io;

import software.ulpgc.kata3.architecture.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TsvMovieReader implements MovieReader {
    private final InputStream inputStream;
    private final TsvMovieDeserializer deserializer;

    public TsvMovieReader(InputStream inputStream, TsvMovieDeserializer deserializer) {
        this.inputStream = inputStream;
        this.deserializer = deserializer;
    }

    @Override
    public List<Movie> read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Movie> movies = new ArrayList<>();
        skipHeader(reader);
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            movies.add(deserializer.deserialize(line));
        }
        return movies;
    }

    private static void skipHeader(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
