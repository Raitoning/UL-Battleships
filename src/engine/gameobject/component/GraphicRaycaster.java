package engine.gameobject.component;

import engine.gameobject.GameObject;
import engine.input.Input;

/**
 * @author Raitoning
 * @version 2018.12.11
 * @since 2018.11.14
 */
public class GraphicRaycaster implements Component {

    private GameObject gameObject;

    public GraphicRaycaster(GameObject gameObject) {

        this.gameObject = gameObject;
        Input.getMouseInput().addListener(this);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public void raycasted(int cameraIndex) {
        gameObject.onRaycast(cameraIndex);
    }

    @Override
    public void destroy() {

        Input.getMouseInput().removeListener(this);
        gameObject = null;
    }
}
