package pe.com.reactive.sec07Backpressure;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.util.ArrayList;
import java.util.List;

public class Lec02Drop {

    /*
    * Con Drop se evita que la aplicación lance el OutOfMemoryException
    * El mismo código que en la lección anterior pero se agregó .onBackpressureDrop()
    * Se emiten todos los items(en el ejemplo del profe se empiezan a consumir poco
    * antes que acabe la emisión). Se termina el consuming en el item 256(Es porque en la
    * clase Queues está la propiedad SMALL_BUFFER_SIZE con un valor de 256)
    * Por eso luego de setear la propiedad en 16 y establecer emit item cada milisegundo,
    * empieza el consuming "en paralelo" con la emisión; y cuando llega al Received :: 16 pasa
    * al Received :: 123(En el video pasa al Received :: 99). Esto se da porque el 75% de 16 es 12
    * y el item emitido luego de que se consumo el 12 es 123. Quiere decir que hay 12 posiciones libres
    * en la queue por lo tanto los items emitidos luego de recibir el 12(Del 123 al 129) irán a la queue
    * Y cuando la emisión llegue al 75% se cierra la queue y se vuelven a pedir items y los que quedaron
    * por fuera son descartados con drop().
    *
    *
    *
    * */


    public static void main(String[] args) {

        //Queues
        System.setProperty("reactor.bufferSize.small", "16"); //75% de 16 = 12

        /* Inicio Sec07-Lec03 :: Drop - Capturing Dropped Values */
        List<Object> list = new ArrayList<>();
        /* Fin Sec07-Lec03 :: Drop - Capturing Dropped Values */

        Flux.create(fluxSink -> {
                    /* Inicio Sec07-Lec03 :: Drop - Capturing Dropped Values */
                    //for (int i = 1; i < 501; i++) {
                    for (int i = 1; i < 201; i++) {
                    /* Fin Sec07-Lec03 :: Drop - Capturing Dropped Values */
                        fluxSink.next(i);
                        System.out.println("Pushed :: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                /* Inicio Sec07-Lec03 :: Drop - Capturing Dropped Values */
                //.onBackpressureDrop()
                .onBackpressureDrop(list::add)
                /* Fin Sec07-Lec03 :: Drop - Capturing Dropped Values */
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        /* Inicio Sec07-Lec03 :: Drop - Capturing Dropped Values */
        //Util.sleepSeconds(60);
        Util.sleepSeconds(10);
        System.out.println(list); //Muestra los valores que fueron descartados
        /* Fin Sec07-Lec03 :: Drop - Capturing Dropped Values */

    }

}
