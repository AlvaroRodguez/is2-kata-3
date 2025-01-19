package software.ulpgc.kata3.architecture.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Barchart {
    private final Map<String, Integer> count;
    private final String title;
    private final String xAxis;
    private final String yAxis;

    public Barchart(String title, String xAxis, String yAxis) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.count = new TreeMap<>();
    }

    public int get(String category) {
        return count.get(category);
    }

    public void put(String category, int value) {
        count.put(category, value);
    }

    public Set<String> keySet() {
        return count.keySet();
    }

    public String title() {
        return title;
    }

    public String xAxis() {
        return xAxis;
    }

    public String yAxis() {
        return yAxis;
    }
}