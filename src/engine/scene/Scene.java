package engine.scene;

import engine.gameobject.GameObject;

import java.util.ArrayList;

public class Scene {

    private static int numberOfScenes = 0;
    protected String name;
    protected ArrayList<GameObject> gameObjects;
    protected boolean isLoaded = false;
    private int id;

    public Scene() {

        gameObjects = new ArrayList<>();
        id = numberOfScenes;
        numberOfScenes++;
    }

    public Scene(String name) {

        this();
        this.name = name;
    }

    protected void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    protected void removeGameObject(GameObject gameObject) {

        gameObjects.remove(gameObject);
    }

    public String getName() {

        return name;
    }

    public void update() {

        if (isLoaded) {

            for (GameObject gameObject : gameObjects) {

                gameObject.update();
            }
        }
    }

    public int getId() {

        return id;
    }

    public int numberOfGameObjects() {

        return gameObjects.size();
    }

    public GameObject getGameObjectByName(String name) {

        for (GameObject gameObject : gameObjects) {

            if (gameObject.getName().equals(name)) {

                return gameObject;
            }
        }

        return null;
    }

    public void unload() {

        isLoaded = false;

        for (GameObject gameObject : gameObjects) {

            gameObject.destroy();
        }

        gameObjects.clear();
        gameObjects = null;
    }

    public void load() {}
}
