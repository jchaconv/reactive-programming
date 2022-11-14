package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec06FixCreateIssue {

    public static void main(String[] args) {

        /*Se ve que solo se reciben 3 paises pero se siguen emitiendo los items
        por eso se agrega fluxSink.isCancelled para que no se sigan emitiendo */
        Flux.create(fluxSink -> {
                    //Cuando obtenga Canadá debe detenerse
                    String countryName;
                    do {
                        countryName = Util.faker().country().name();
                        System.out.println("emitting :: " + countryName);
                        fluxSink.next(countryName);
                    } while (!countryName.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
                    fluxSink.complete();
                })
                .take(3)
                .subscribe(Util.subscriber());


    }


}
