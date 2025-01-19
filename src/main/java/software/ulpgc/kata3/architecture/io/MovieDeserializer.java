package software.ulpgc.kata3.architecture.io;

import software.ulpgc.kata3.architecture.model.Movie;

public interface MovieDeserializer {
    Movie deserialize(String text);
}
