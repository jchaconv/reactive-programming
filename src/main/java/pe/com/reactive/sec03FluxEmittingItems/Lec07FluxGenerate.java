package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerate {

    //Emite items constantemente (loop infinito). Funciona como una daemon
    //Se puede pausar usando complete, emitiendo un error o con el operador take
    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
                    System.out.println("Emitting");
                    synchronousSink.next(Util.faker().country().name());
                    //synchronousSink.complete();
                    //synchronousSink.error(new RuntimeException("oops"));
                })
                .take(8) //Lo uso para que no sea loop infinito
                .subscribe(Util.subscriber());

    }


}
