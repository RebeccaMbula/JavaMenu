package socketComm;

import beans.Order;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OrdersComm {

    public static void sendOrder(Order order) {
        try {
            Socket socket = new Socket("localhost", 30197);
            ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
            oOut.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
