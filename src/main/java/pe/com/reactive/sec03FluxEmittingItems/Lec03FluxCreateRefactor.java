package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.sec03FluxEmittingItems.helper.NameProducer;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxCreateRefactor {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        nameProducer.produce(); //Genera un nombre

        System.out.println("================");

        /* INICIO LECCION 04 - FluxSink :: Sharing with multiple threads */
        //Si se quiere emitir events con multiple threads se puede usar FluxSink
        NameProducer nameProducer2 = new NameProducer();

        Flux.create(nameProducer2)
                .subscribe(Util.subscriber());

        //Runnable runnable = () -> nameProducer2.produce(); Lo mismo:
        Runnable runnable = nameProducer2::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(2);
        /* FIN LECCION 04 - FluxSink :: Sharing with multiple threads */

    }


}
