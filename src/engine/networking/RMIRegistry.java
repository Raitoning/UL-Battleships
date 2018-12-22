package engine.networking;

import java.rmi.Remote;

/**
 * <h1>RMIRegistry</h1>
 * This interface represents an RMI registry.
 * <p>
 * This interface must be implemented by every class you want to make a
 * registry of for RMI networking.
 * </p>
 *
 * <b>Note:</b> This interface is marked as deprecated because it's implementation is not finshed and shouldn't work at all. Use it as your own risks.
 *
 * @author Raitoning
 * @version 2018.12.05
 * @since 2018.12.05
 * @deprecated
 */

@Deprecated
public interface RMIRegistry extends Remote {
}
