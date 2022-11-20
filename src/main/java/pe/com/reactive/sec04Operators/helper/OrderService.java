package pe.com.reactive.sec04Operators.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> list1 = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
        );
        List<PurchaseOrder> list2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        List<PurchaseOrder> list3 = Arrays.asList(
                new PurchaseOrder(3),
                new PurchaseOrder(3),
                new PurchaseOrder(3),
                new PurchaseOrder(3),
                new PurchaseOrder(3)
        );
        db.put(1, list1);
        db.put(2, list2);
        db.put(3, list3);
    }

    //Primero se probó con este método
    /*public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create(purchaseOrderFluxSink -> {
            db.get(userId).forEach(purchaseOrderFluxSink::next);
            purchaseOrderFluxSink.complete();
        });
    }*/

    //Se cambió a Object y se agregó el delay
    public static Flux<Object> getOrders(int userId) {
        return Flux.create(purchaseOrderFluxSink -> {
            db.get(userId).forEach(purchaseOrderFluxSink::next);
            purchaseOrderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }

}
