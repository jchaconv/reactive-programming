package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Delay {

    public static void main(String[] args) {

        //Delay por defecto produce 32 items
        //Cuando se entregó el 75% de items vuelve a solicitar 24(corresponde a cierto porcentaje)

        System.setProperty("reactor.bufferSize.x", "9"); //Si quiero modificar el 32 por defecto

        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1)) // Para emitir cada segundo
                .subscribe(Util.subscriber());

        Util.sleepSeconds(30); //Agrego para que lleguen los elementos al subscribe y se vea en logs


    }


}
