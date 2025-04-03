package rmi.client;

import rmi.common.RemoteService;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * @author jyzxc
 */
public class RMIClient {
    private static final String host = "localhost";
    private static final int port = 1099;
    private static final String serviceUrl = "rmi://" + host + ":" + port;
    private static final String serviceName = "RMIService";
    public static void main(String[] args) throws RemoteException {
        try {
            RemoteService service = (RemoteService) Naming.lookup(serviceUrl + "/" + serviceName);
            String result = service.run();
            System.out.println("Result from server: " + result);
        } catch (Exception e) {
            System.err.println("Error connecting to RMI server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
