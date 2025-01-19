package software.ulpgc.kata3.apps;

import software.ulpgc.kata3.architecture.io.MovieReader;
import software.ulpgc.kata3.architecture.io.TsvMovieDeserializer;
import software.ulpgc.kata3.architecture.io.TsvMovieReader;
import software.ulpgc.kata3.architecture.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("title.basics.tsv")) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo 'title.basics.tsv' no encontrado en resources");
            }

            MovieReader reader = new TsvMovieReader(inputStream, new TsvMovieDeserializer());
            List<Movie> movieList = reader.read();

            Map<Integer, Long> movieCountByYear = movieList.stream()
                    .collect(Collectors.groupingBy(Movie::year, Collectors.counting()));

            Map<String, Long> moviesByDuration = movieList.stream()
                    .collect(Collectors.groupingBy(movie -> {
                        int duration = movie.duration();
                        if (duration < 30) {
                            return "Menos de 30 min";
                        } else if (duration < 60) {
                            return "30 min - 1 hora";
                        } else if (duration < 90) {
                            return "1 hora - 1.5 horas";
                        } else if (duration < 120) {
                            return "1.5 horas - 2 horas";
                        } else if (duration < 150) {
                            return "2 horas - 2.5 horas";
                        } else if (duration < 180) {
                            return "2.5 horas - 3 horas";
                        } else {
                            return "Más de 3 horas";
                        }
                    }, Collectors.counting()));

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }
    }
}
