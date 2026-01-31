package ru.project.GUI;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import ru.project.Application;
import ru.project.Core.LogElement;

import java.util.ArrayList;
import java.util.List;

public class GElement extends Group {
    private LogElement logElement;
    private List<Connector> inputs = new ArrayList<>();
    private List<Connector> outputs = new ArrayList<>();

    private boolean isDraggable = true;
    private double dragStartX, dragStartY;
    private double elementStartX, elementStartY;

    public GElement(){
        setupDragHandlers();
    }




    protected void setupConnectorHandlers(WireManager wireManager){

        for(Connector output : outputs){
            output.setOnMousePressed(event -> {
                if(!output.isConnected()){
                    wireManager.startWireCreation(output);
                    event.consume();
                }
            });

            output.setOnMouseDragged(event -> {
                if(wireManager.isCreatingWire()){
                    wireManager.updateTempWire(event.getSceneX(), event.getSceneY());
                    event.consume();
                }
            });
        }

        for(Connector input : inputs){
            input.setOnMousePressed(event -> {
                if(!input.isConnected()){
                    wireManager.startWireCreation(input);
                    event.consume();
                }
            });

            input.setOnMouseDragged(event -> {
                if(wireManager.isCreatingWire()){
                    wireManager.updateTempWire(event.getSceneX(), event.getSceneY());
                    event.consume();
                }
            });
        }
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

        this.setLayoutX(((mouseEvent.getSceneX()>=0) ? elementStartX + deltaX : 0));
        this.setLayoutY(((mouseEvent.getSceneY()>=0) ? elementStartY + deltaY : 0));

        updateConnectedWires();
    }

    private void endDrag(MouseEvent mouseEvent){
        this.setOpacity(1.0);
        snapToGrid();

        updateConnectedWires();

    }

    protected void updateConnectedWires(){
        for(Connector output: outputs){
            if(output.getWire() != null)
                output.getWire().updatePosition();
        }
        for(Connector input: inputs){
            if(input.getWire() != null)
                input.getWire().updatePosition();
        }
    }

    protected void snapToGrid(){
        int gridSize = 15;
        double x = Math.round(((this.getLayoutX()>=0) ? this.getLayoutX() : 0)/gridSize) * gridSize;
        double y = Math.round(((this.getLayoutY()>=0) ? this.getLayoutY() : 0)/gridSize) * gridSize;
        System.out.println(x + " " + y);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setDraggable(boolean isDraggable){
        this.isDraggable = isDraggable;
    }
    public boolean getDraggable(){
        return isDraggable;
    }
    public List<Connector> getInputs(){
        return inputs;
    }
    public List<Connector> getOutputs(){
        return outputs;
    }

    public void setLogElement (LogElement logElement){
        this.logElement = logElement;
    }

    public LogElement getLogElement(){
        return logElement;
    }

}
