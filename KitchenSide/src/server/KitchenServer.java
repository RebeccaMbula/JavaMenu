package server;

import beans.Order;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import layouts.OrdersController;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KitchenServer extends Service {
    private OrdersController ordersController;

    public KitchenServer(OrdersController controller){
        super();
        ordersController = controller;
    }

    @Override
    protected Task createTask() {
        /*System.out.println("Tasking");
        try {
            ServerSocket ss = new ServerSocket(30190);
            Executor executor = Executors.newFixedThreadPool(4);
            for(;;){
                executor.execute(new OrdersServer(ss.accept(), ordersController));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    ServerSocket ss = new ServerSocket(30197);
                    Executor executor = Executors.newFixedThreadPool(4);
                    while(true){
                        System.out.println("In loop");
                        executor.execute(new OrdersServer(ss.accept(), ordersController));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    class OrdersServer extends Task{
        Socket client;
        OrdersController controller;

        public OrdersServer(Socket client, OrdersController controller) {
            super();
            this.client = client;
            this.controller = controller;
        }

        @Override
        protected Object call() throws Exception {
            try{
                ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
                OrderListener orderListener = (OrderListener)controller;
                orderListener.onReceiveOrder((Order)oin.readObject());
                System.out.println(((Order)oin.readObject()).getSpecifications());
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public interface OrderListener {
        void onReceiveOrder(Order o);
    }
}
