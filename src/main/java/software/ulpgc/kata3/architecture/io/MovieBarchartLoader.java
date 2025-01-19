package software.ulpgc.kata3.architecture.io;

import software.ulpgc.kata3.architecture.model.Barchart;

import java.util.Map;

public class MovieBarchartLoader implements BarchartLoader {
    private final Map<String, Long> moviesByDuration;
    private final Map<Integer, Long> movieCountByYear;

    public MovieBarchartLoader(Map<String, Long> moviesByDuration, Map<Integer, Long> movieCountByYear) {
        this.moviesByDuration = moviesByDuration;
        this.movieCountByYear = movieCountByYear;
    }

    @Override
    public Barchart load(int id) {
        return switch (id){
            case 0 -> moviesByDurationBarchart();
            case 1 -> moviesByYearRangeBarchart(1850,1900);
            case 2 -> moviesByYearRangeBarchart(1901,1950);
            case 3 -> moviesByYearRangeBarchart(1951,2000);
            case 4 -> moviesByYearRangeBarchart(2000,2050);
            default -> null;
        };
    }

    private Barchart moviesByYearRangeBarchart(int startYear, int endYear) {
        Barchart barchart = new Barchart("Movies from " + startYear + " to " + endYear, "Year", "Movies");
        movieCountByYear.entrySet().stream()
                .filter(entry -> entry.getKey() >= startYear && entry.getKey() <= endYear)
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> barchart.put(entry.getKey().toString(), entry.getValue().intValue()));
        return barchart;
    }

    private Barchart moviesByDurationBarchart() {
        Barchart barchart = new Barchart("Movies by Duration", "Duration", "Movies");
        moviesByDuration.forEach((duration, count) -> barchart.put(duration, count.intValue()));

        return barchart;
    }
}
