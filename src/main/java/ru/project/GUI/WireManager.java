package ru.project.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class WireManager {

    private WorkspacePane workspacePane;

    private Connector draggingFrom = null;

    private Line tempWire = null;

    public WireManager(WorkspacePane workspacePane){
        this.workspacePane = workspacePane;
    }

    public WorkspacePane getWorkspacePane(){
        return workspacePane;
    }

    public void startWireCreation(Connector source){
        this.draggingFrom = source;
        tempWire = new Line();

        tempWire.setStroke(Color.BLUE);
        tempWire.setStrokeWidth(4.0);
        tempWire.getStrokeDashArray().addAll(5d, 5d);
        double startX = getAbsoluteX(source);
        double startY = getAbsoluteY(source);
        tempWire.setStartX(startX);
        tempWire.setStartY(startY);
        tempWire.setEndX(startX);
        tempWire.setEndY(startY);

        workspacePane.getChildren().add(tempWire);
    }

    public void updateTempWire(double sceneX, double sceneY){
        if(tempWire != null){
            tempWire.setEndX(sceneX);
            tempWire.setEndY(sceneY);
        }
    }

    public void finishWireCreating(Connector target){
        if(draggingFrom == null || target == null){
            return;
        }

        if(!isValidConnection(draggingFrom, target)){
            cancelWireCreation();
            return;
        }
        Wire wire = new Wire(draggingFrom, target);
        draggingFrom.setWire(wire);
        target.setWire(wire);
        workspacePane.addWire(wire);
        if(draggingFrom.isInput()){
            draggingFrom.getElement().getLogElement().getInput().add(target.getElement().getLogElement());
            target.getElement().getLogElement().setOutput(draggingFrom.getElement().getLogElement());
        }
        else{
            draggingFrom.getElement().getLogElement().setOutput(target.getElement().getLogElement());
            target.getElement().getLogElement().getInput().add(draggingFrom.getElement().getLogElement());
        }

        cancelWireCreation();
    }

    public void cancelWireCreation(){
        if(tempWire != null){
            workspacePane.getChildren().remove(tempWire);
            tempWire = null;
        }
        draggingFrom = null;
    }
    private boolean isValidConnection(Connector from, Connector to){
        if(from == to) return false;
        if(from.isInput() == to.isInput()) return false;
        return !to.isConnected();
    }

    private double getAbsoluteX(Connector connector){
        return connector.getCenterX() + connector.getElement().getLayoutX();
    }

    private double getAbsoluteY(Connector connector){
        return connector.getCenterY() + connector.getElement().getLayoutY();
    }

    public boolean isCreatingWire(){
        return draggingFrom != null;
    }
}
