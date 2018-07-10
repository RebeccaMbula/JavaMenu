package listeners;

import beans.Order;

public interface OrderDoneListener {

    void onOrderDone(Order order);

}
