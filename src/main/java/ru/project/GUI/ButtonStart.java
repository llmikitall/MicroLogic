package ru.project.GUI;

import javafx.scene.control.Button;

public class ButtonStart extends Button {

    private boolean state = false;
    private final WorkspacePane workspacePane;

    public ButtonStart(WorkspacePane workspacePane){
        this.setStyle("-fx-background-color: black;" +
                "-fx-border-width: 2px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: white;" +
                "-fx-font-color: white;");
        this.setText("Запуск");
        this.workspacePane = workspacePane;
        setupHandler();
    }

    private void setupHandler(){
        this.setOnMouseClicked(mouseEvent -> {
            if(state){
                this.workspacePane.stopSimulation();
                this.setText("Запуск");
                state = false;
            }
            else{
                this.workspacePane.simulation();
                this.setText("Стоп");
                state = true;
            }
        });
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
