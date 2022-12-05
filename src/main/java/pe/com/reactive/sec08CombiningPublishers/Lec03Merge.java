package pe.com.reactive.sec08CombiningPublishers;

import pe.com.reactive.sec08CombiningPublishers.helper.AmericanAirlines;
import pe.com.reactive.sec08CombiningPublishers.helper.Emirates;
import pe.com.reactive.sec08CombiningPublishers.helper.Qatar;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec03Merge {

    public static void main(String[] args) {

        Flux<String> merge = Flux.merge(
                Qatar.getFlights(),
                Emirates.getFlights(),
                AmericanAirlines.getFlights()
        );

        merge.subscribe(Util.subscriber());

        Util.sleepSeconds(10);


    }


}
