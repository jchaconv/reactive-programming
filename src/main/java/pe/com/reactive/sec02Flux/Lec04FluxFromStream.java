package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4);
        Stream<Integer> stream = list.stream();

        //stream.forEach(System.out::println); //En este punto el stream ya está cerrado
        //stream.forEach(System.out::println); //Aquí saldría un error porque no es reutilizable

        System.out.println("==============");

        //Para poder usar el stream no debe estar cerrado por lo que ln 16 y 17 están comentadas
        Flux<Integer> flux = Flux.fromStream(stream);
        flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete()); //En este punto el stream ya está cerrado
        //flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete()); //Aquí sale error

        System.out.println("==============");

        //Si quiero que un stream funcione al llamarlo desde subscribe debo hacer lo siguiente:
        List<Integer> integers = List.of(1, 2, 3, 4); //Creo un nuevo stream para hacer el ejemplo desde cero
        Stream<Integer> integerStream = integers.stream();

        //Con este supplier no funcionará porque en el 2do subscriber toma la misma referencia
        //Flux<Integer> integerFlux = Flux.fromStream( () -> integerStream);

        //Por eso creo una nueva referencia en el Supplier
        Flux<Integer> integerFlux = Flux.fromStream( () -> integers.stream());
        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        //Prueba exitosa



    }


}
