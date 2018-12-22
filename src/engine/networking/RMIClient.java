package engine.networking;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <h1>RMIClient</h1>
 * This class represents an RMI registry client.
 * <p>
 * From this class, you can connect to an existing RMI registry.
 * </p>
 *
 * <b>Note:</b> This class is marked as deprecated because it's implementation is not finshed and shouldn't work at all. Use it as your own risks.
 *
 * @author Raitoning
 * @version 2018.12.05
 * @since 2018.12.05
 * @deprecated
 */

@Deprecated
public class RMIClient {

    public RMIClient(String registryName) throws
            RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(7777);

        System.out.println("RMI registry binding...");

        String[] registries = registry.list();

        for (String reg : registries) {

            System.out.println(reg);
        }

        RMIRegistry remoteRegistry = (RMIRegistry) registry.lookup(registryName);
    }
}
