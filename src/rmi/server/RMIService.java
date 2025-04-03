package rmi.server;

import rmi.common.RemoteService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author jyzxc
 */
public class RMIService extends UnicastRemoteObject implements RemoteService {
    private static final long serialVersionUID = 1L;

    protected RMIService() throws RemoteException {
        super();
    }

    @Override
    public String run() throws RemoteException {
        System.out.println("RMI Service Started");
        return "running";
    }
}
