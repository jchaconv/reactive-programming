package pe.com.reactive.sec04Operators;

import pe.com.reactive.sec04Operators.helper.OrderService;
import pe.com.reactive.util.Util;

public class Lec12FlatMap {

    //Con map retorna referencias en memoria pero con flatmap ya retorna los valores
    //Cuando se tiene un MONO o FLUX en lugar de una función, no se puede emitir con map,
    // se tiene que usar flatMap.
    public static void main(String[] args) {

        UserService.getUsers()
                //.map(user -> OrderService.getOrders(user.getUserId()))
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.subscriber());

        //Se agregó después :: Sale en orden diferente
        Util.sleepSeconds(60);

    }

}
