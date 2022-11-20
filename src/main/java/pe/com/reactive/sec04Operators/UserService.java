package pe.com.reactive.sec04Operators;

import pe.com.reactive.sec04Operators.helper.User;
import reactor.core.publisher.Flux;

public class UserService {

    public static Flux<User> getUsers() {
        return Flux.range(1, 3)
                .map(i -> new User(i));
    }


}
