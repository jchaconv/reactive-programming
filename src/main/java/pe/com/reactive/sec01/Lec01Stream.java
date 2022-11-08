package pe.com.reactive.sec01;

import java.util.stream.Stream;

public class Lec01Stream {

    public static void main(String[] args) {

        //Se crea un stream que espera 3 segundos para mostrar la data
        Stream<Integer> integerStream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * 2;
                });

        integerStream.forEach(System.out::println);


    }
}
