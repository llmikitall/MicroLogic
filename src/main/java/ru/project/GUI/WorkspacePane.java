package ru.project.GUI;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class WorkspacePane extends Pane {
    private List<GElement> elements;

    public WorkspacePane(){
        this.elements = new ArrayList<>();

        setupWorkspace();
    }

    private void setupWorkspace(){
        this.setStyle("-fx-background-color: #246923;");
        this.setMinSize(600, 400);


    }


}
