package rmi.server;

import rmi.common.RemoteService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author jyzxc
 */
public class RMIServer {
    private static final String host = "localhost";
    private static final int port = 1099;
    private static final String serviceUrl = "rmi://" + host + ":" + port + "/RMIService";
    public static void main(String[] args) throws RemoteException {
        RemoteService r = new RMIService();
        try {
            LocateRegistry.createRegistry(port);
            Naming.rebind(serviceUrl, r);
            System.out.println("RMI Server started at " + serviceUrl);
        } catch (Exception e) {
            System.err.println("Error starting RMI server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
