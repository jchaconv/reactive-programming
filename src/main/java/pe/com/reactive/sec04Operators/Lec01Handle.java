package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    /*
    * handle = filter + map
    * */
    public static void main(String[] args) {

        //integer sería 1 como valor inicial
        Flux.range(1, 10)
                .handle( ((integer, synchronousSink) -> {
                    if(integer % 2 == 0) //filter :: solo pares
                        synchronousSink.next(integer);
                    else
                        synchronousSink.next(integer + "a"); //map
                })).subscribe(Util.subscriber());

        System.out.println("=================");

        Flux.range(1, 10)
                .handle( ((integer, synchronousSink) -> {
                    if(integer == 7) //mostrar hasta 7
                        synchronousSink.complete();
                    else
                        synchronousSink.next(integer);
                })).subscribe(Util.subscriber());

    }

}
