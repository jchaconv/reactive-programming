package pe.com.reactive.sec12Context.assignment;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BookService {

    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("std", 2);
        map.put("prime", 3);
    }

    public static Mono<String> getBook() {
        return Mono.deferContextual(ctx -> {

            if(ctx.get("allow")) {
                return Mono.just(Util.faker().book().title());
            } else {
                return Mono.error(new RuntimeException("Not allowed"));
            }
        }).contextWrite(rateLimiterContext());
    }

    private static Function<Context, Context> rateLimiterContext() {
        return ctx -> {
          if(ctx.hasKey("category")) {
              String category = ctx.get("category").toString();
              Integer attemps = map.get(category);
              if(attemps > 0) {
                  map.put(category, attemps -1);
                  return ctx.put("allow", true);
              }
          }
          return ctx.put("allow", false);
        };
    }


}
