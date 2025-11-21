package ru.project.GUI;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import ru.project.Core.LogElement;

import java.util.List;

public class GElement extends Group {
    private LogElement logElement;
    private List<LogElement> inputs;
    private LogElement output;

    private boolean isDraggable = true;
    private double dragStartX, dragStartY;
    private double elementStartX, elementStartY;

    public GElement(){

        setupDragHandlers();
    }

    private void setupDragHandlers(){
        this.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && isDraggable){
                startDrag(event);
                event.consume();
            }
        });
        this.setOnMouseDragged(event -> {
            if(isDraggable){
                handleDrag(event);
                event.consume();
            }
        });

        this.setOnMouseReleased(event -> {
            if(isDraggable){
                endDrag(event);
                event.consume();
            }
        });
    }

    private void startDrag(MouseEvent mouseEvent){
        dragStartX = mouseEvent.getSceneX();
        dragStartY = mouseEvent.getSceneY();

        elementStartX = this.getLayoutX();
        elementStartY = this.getLayoutY();
        this.setOpacity(0.8);
    }

    private void handleDrag(MouseEvent mouseEvent){
        double deltaX = mouseEvent.getSceneX() - dragStartX;
        double deltaY = mouseEvent.getSceneY() - dragStartY;

        this.setLayoutX(elementStartX + deltaX);
        this.setLayoutY(elementStartY + deltaY);

        updateConnectedWires();
    }

    private void endDrag(MouseEvent mouseEvent){
        this.setOpacity(1.0);
        snapToGrid();
    }

    protected void updateConnectedWires(){
        //Обновление проводов
    }

    protected void snapToGrid(){
        int gridSize = 15;
        double x = Math.round(this.getLayoutX()/gridSize) * gridSize;
        double y = Math.round(this.getLayoutY()/gridSize) * gridSize;

        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setDraggable(boolean isDraggable){
        this.isDraggable = isDraggable;
    }

    public boolean getDraggable(){
        return isDraggable;
    }

}
