package pe.com.reactive.sec12Context;

import pe.com.reactive.sec12Context.assignment.BookService;
import pe.com.reactive.sec12Context.assignment.UserService;
import pe.com.reactive.util.Util;
import reactor.util.context.Context;

/*
* Si reemplazo el usuario por "cesar" si recibe los libros
* esto pasa por la categoría a la que pertenecen y el número de
* repeticiones que permite
*
* */

public class CtxRateDelimiterDemo {

    public static void main(String[] args) {

        BookService.getBook()
                .repeat(2)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "julio"))
                .subscribe(Util.subscriber());

    }

}
