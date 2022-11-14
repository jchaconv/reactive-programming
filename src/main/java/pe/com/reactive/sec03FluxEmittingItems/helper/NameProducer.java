package pe.com.reactive.sec03FluxEmittingItems.helper;

import pe.com.reactive.util.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    //este método es obligatorio y andThen no
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        String name = Util.faker().name().fullName();
        /* INICIO LECCION 04 - FluxSink :: Sharing with multiple threads */
        String threadName = Thread.currentThread().getName();
        this.fluxSink.next(threadName + " :: " + name);
        //this.fluxSink.next(name);
        /* FIN LECCION 04 - FluxSink :: Sharing with multiple threads */
    }

}
