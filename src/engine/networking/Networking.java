package engine.networking;

/**
 * <h1>Networking</h1>
 * This class manages the networking capability of the game engine.
 * <p>
 * From this class, you can create a server and a client to create a
 * networked multiplayer game.
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
public class Networking {

    private Networking instance;

    /**
     * Returns the instance of the Networking singleton, or creates a new
     * one and returns it.
     *
     * @return The instance of the Networking singleton, or creates a new
     * one and returns it.
     */
    public Networking getInstance() {

        if (instance == null) {

            instance = new Networking();
        }

        return instance;
    }
}
