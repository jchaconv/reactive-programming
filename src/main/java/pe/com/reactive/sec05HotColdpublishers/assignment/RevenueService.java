package pe.com.reactive.sec05HotColdpublishers.assignment;

import java.util.HashMap;
import java.util.Map;

public class RevenueService {

    private Map<String, Double> db = new HashMap<>();

    public RevenueService() {
        db.put("Kids", 0.0);
        db.put("Automotive", 0.0);
    }
}
