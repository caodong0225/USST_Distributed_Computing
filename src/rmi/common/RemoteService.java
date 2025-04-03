package rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author jyzxc
 */
public interface RemoteService extends Remote {
    String run() throws RemoteException;
}
