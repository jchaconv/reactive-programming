package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.sec03FluxEmittingItems.helper.NameProducer;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxPush {

    //Cuando solo se quiere usar un Thread Producer usar push
    //El ejemplo no queda muy claro. Al comienzo aparecían menos Threads(?)
    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        //NO es Thread safe
        Flux.push(nameProducer)
                .subscribe(Util.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(2);

    }


}
