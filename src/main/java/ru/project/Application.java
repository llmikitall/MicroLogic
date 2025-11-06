package ru.project;

import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;

public class Application extends SimpleApplication {

    @Override
    public void simpleInitApp(){

        Spatial model = assetManager.loadModel("/Models/logic.obj");
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.White);
        model.setMaterial(material);
        model.setLocalScale(0.1f);
        model.rotate(0, 0, FastMath.DEG_TO_RAD * 90);
        rootNode.attachChild(model);

        /*
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3).normalizeLocal());
        rootNode.addLight(sun);


         */
    }

    public Application(){
        super(new FlyCamAppState(), new StatsAppState());
    }

    public static void main(String[] args){
        Application application = new Application();

        AppSettings settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("MicroLogic");


        application.setSettings(settings);
        application.start();
    }


}
