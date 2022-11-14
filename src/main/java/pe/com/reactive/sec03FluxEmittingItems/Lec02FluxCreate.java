package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

//SECCION 03 - [FLUX] Emitting items programmatically
public class Lec02FluxCreate {

    //Lec01 en clase Util

    public static void main(String[] args) {

        //Acepta un FluxSink
        Flux.create(fluxSink -> {

            //Emit items programmatically
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();

        }).subscribe(Util.subscriber("my-first-subscriber")); //subscriber's name optional

        System.out.println("==========");

        Flux.create(fluxSink -> {
            //Cuando obtenga Canadá debe detenerse
            String countryName;
            do {
                countryName = Util.faker().country().name();
                fluxSink.next(countryName);
            } while (!countryName.equalsIgnoreCase("canada"));
            fluxSink.complete();

        }).subscribe(Util.subscriber());

    }


}
