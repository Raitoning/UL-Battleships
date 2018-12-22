package engine.networking;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <h1>RMIServer</h1>
 * This class represents an RMI registry server.
 * <p>
 * From this class, you can create a registry for clients to connect to.
 * </p>
 *
 * <b>Note:</b> This class is marked as deprecated because it's implementation is not finshed and shouldn't work at all. Use it as your own risks.
 *
 * @author Raitoning
 * @version 2018.12.12-tailored-wastelands
 * @since 2018.12.05
 * @deprecated
 */

@Deprecated
public class RMIServer {

    private Registry registry;
    private String registryName;

    /**
     * @param registryName String The name to use for the RMIRegistry.
     * @param rmiRegistry  RMIRegistry The RMIRegistry to publish.
     * @throws RemoteException       Throws an RemoteException if the remote isn't an RMIRegistry.
     * @throws AlreadyBoundException Throws an AlreadyBoundException if the server is already bound to the target RMIRegistry.
     * @deprecated
     */
    public RMIServer(String registryName, RMIRegistry rmiRegistry) throws RemoteException,
            AlreadyBoundException {

        this.registryName = registryName;

        System.out.println("Constructing server implementation...");

        System.out.println("Binding server implementation to registry...");
        registry = LocateRegistry.createRegistry(7777);

        registry.bind(registryName, rmiRegistry);

        System.out.println("Waiting for invocations from clients...");
    }

    /**
     * @throws RemoteException   Throws a RemoteException if the remote isn't an RMIRegistry.
     * @throws NotBoundException Throws an AlreadyBoundException if the server is already bound to the target RMIRegistry.
     * @deprecated
     */
    public void closeServer() throws RemoteException, NotBoundException {

        registry.unbind(registryName);
    }
}
