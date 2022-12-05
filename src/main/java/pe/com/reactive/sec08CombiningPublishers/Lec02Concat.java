package pe.com.reactive.sec08CombiningPublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {

    /*
    * Si solo se usa concat llega al error pero no al flux3
    * Si se quiere dejar el error al final para que se muestre flux3 usar:
    * concatDelayError()
    * */

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.error(new RuntimeException("oops"));
        Flux<String> flux3 = Flux.just("c", "d", "e");

        //Flux<String> flux = Flux.concat(flux1, flux2, flux3);
        Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);
        flux.subscribe(Util.subscriber());

    }


}
