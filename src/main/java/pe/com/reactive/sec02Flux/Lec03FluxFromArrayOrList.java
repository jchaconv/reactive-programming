package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("aaron", "julio", "cesar");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext());

        System.out.println("======");

        Integer[] arr = { 2, 10, 56, 98};
        Flux.fromArray(arr)
                .subscribe(Util.onNext());


    }


}
