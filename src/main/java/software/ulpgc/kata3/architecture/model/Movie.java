package software.ulpgc.kata3.architecture.model;

import java.util.List;

public record Movie(String id, String primaryTitle, String originalTitle, int year, int duration, List<Genre> genres) {
}
