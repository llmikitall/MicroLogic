package ru.project;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.project.GUI.*;

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
        WireManager wireManager = workspacePane.getWireManager();
        ButtonStart button = new ButtonStart(workspacePane);
        workspacePane.getChildren().add(button);

        GAndElement and1 = new GAndElement(50, 50, wireManager);
        workspacePane.addElement(and1);
        GAndElement and2 = new GAndElement(100, 100, wireManager);
        workspacePane.addElement(and2);
        GAndElement and3 = new GAndElement(50, 50, wireManager);
        workspacePane.addElement(and3);
        GAndElement and4 = new GAndElement(100, 100, wireManager);
        workspacePane.addElement(and4);

        GEndElement end = new GEndElement(200, 200, wireManager);
        workspacePane.addElement(end);
        workspacePane.addEndElement(end);

        GBeginElement begin1 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin1);

        GBeginElement begin2 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin2);

        GBeginElement begin3 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin3);

        GBeginElement begin4 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin4);

        GBeginElement begin5 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin5);

        GBeginElement begin6 = new GBeginElement(200, 200, wireManager);
        workspacePane.addElement(begin6);

        workspacePane.getChildren().addAll(and1, and2, and3, and4, end, begin1, begin2, begin4, begin6, begin5, begin3);


    }
}