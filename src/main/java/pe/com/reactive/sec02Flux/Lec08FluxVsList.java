package pe.com.reactive.sec02Flux;

import pe.com.reactive.sec02Flux.helper.NameGenerator;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec08FluxVsList {

    public static void main(String[] args) {

        /*List<String> names = NameGenerator.getNames(5);
        System.out.println(names); //Luego de 5 segundos muestra todos los nombres*/

        //System.out.println(" ========== ");

        //Con Flux no debo esperar tanto porque llega uno por uno como items
        //En el ejemplo con Lista llega un objeto grande y por eso hay que esperar
        //La diferencia se nota clara por el sleep del factory
        NameGenerator.getNamesFlux(5)
                .subscribe(Util.onNext());

    }


}
