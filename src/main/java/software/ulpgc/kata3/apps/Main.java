package software.ulpgc.kata3.apps;

import software.ulpgc.kata3.architecture.control.SelectStatisticCommand;
import software.ulpgc.kata3.architecture.io.MovieBarchartLoader;
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
                            return "Less than 30 min";
                        } else if (duration < 60) {
                            return "30 min - 1 hour";
                        } else if (duration < 90) {
                            return "1 hour - 1.5 hours";
                        } else if (duration < 120) {
                            return "1.5 hours - 2 hours";
                        } else if (duration < 150) {
                            return "2 hours - 2.5 hours";
                        } else if (duration < 180) {
                            return "2.5 hours - 3 hours";
                        } else {
                            return "More than 3 hours";
                        }
                    }, Collectors.counting()));

            MainFrame mainFrame = new MainFrame();
            MovieBarchartLoader loader = new MovieBarchartLoader(moviesByDuration,movieCountByYear);
            mainFrame.put("select", new SelectStatisticCommand(mainFrame.selectStatisticDialog(), loader, mainFrame.barchartDisplay()));

            mainFrame.barchartDisplay().show(loader.load(0));
            mainFrame.barchartDisplay().show(loader.load(1));
            mainFrame.barchartDisplay().show(loader.load(2));
            mainFrame.barchartDisplay().show(loader.load(3));
            mainFrame.barchartDisplay().show(loader.load(4));
            mainFrame.setVisible(true);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }
    }
}
