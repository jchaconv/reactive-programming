package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {

    public static void main(String[] args) {

        //Interval sirve para publicar un item periodicamente
        Flux.interval(Duration.ofSeconds(1)) //Puede ser cualquier tipo de Duration
                .subscribe(Util.onNext());

        Util.sleepSeconds(5); //Se agrega para poder ver resultados


    }



}
