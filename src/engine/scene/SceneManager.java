package engine.scene;

import java.util.ArrayList;

public class SceneManager {

    private static SceneManager instance;

    private ArrayList<Scene> scenes;
    private Scene activeScene;

    private SceneManager() {

        scenes = new ArrayList<>();
        activeScene = null;
    }

    public static SceneManager getInstance() {

        if (instance == null) {

            instance = new SceneManager();
        }

        return instance;
    }

    public void update() {

        if (activeScene != null) {

            activeScene.update();
        }
    }

    public void loadScene(String name) {

        if (activeScene != null) {

            activeScene.unload();
        }

        for (Scene scene : scenes) {

            if (scene.getName().equals(name)) {

                scene.load();
                activeScene = scene;
            }
        }
    }

    public void unloadActiveScene() {

        activeScene.unload();
        System.gc();
    }

    public void addScene(Scene scene) {

        scenes.add(scene);

        if (activeScene == null) {

            activeScene = scene;
            loadScene(scene.getName());
        }
    }

    public Scene getActiveScene() {

        return activeScene;
    }
}
