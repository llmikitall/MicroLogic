package ru.project;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.project.GUI.GAndElement;
import ru.project.GUI.WorkspacePane;

public class Application extends javafx.application.Application {
    public static void getLaunch(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        WorkspacePane workspacePane = new WorkspacePane();
        root.setCenter(workspacePane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("MicroLogic");
        primaryStage.setScene(scene);

        primaryStage.show();

        test(workspacePane);
    }

    public void test(WorkspacePane workspacePane){
        GAndElement and1 = new GAndElement(50, 50);
        GAndElement and2 = new GAndElement(100, 100);
        GAndElement and3 = new GAndElement(50, 50);
        GAndElement and4 = new GAndElement(100, 100);
        workspacePane.getChildren().addAll(and1, and2, and3, and4);
    }
}