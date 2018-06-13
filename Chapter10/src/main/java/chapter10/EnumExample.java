package chapter10;

/**
 * Created by alexeysoshin on 13/04/2018.
 */
public class EnumExample {
    public static void main(String[] args) {
        PizzaOrderStatus status = PizzaOrderStatus.ORDER_RECEIVED;
        System.out.println(status.nextStatus().nextStatus());
    }
}


enum PizzaOrderStatus {
    ORDER_RECEIVED,
    PIZZA_BEING_MADE,
    OUT_FOR_DELIVERY,
    COMPLETED;


    public PizzaOrderStatus nextStatus() {
        switch (this) {
            case ORDER_RECEIVED: return PIZZA_BEING_MADE;
            case PIZZA_BEING_MADE: return OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY: return COMPLETED;
            case COMPLETED: return COMPLETED;
        }
        throw new RuntimeException("Unhandled status " + this);
    }
}