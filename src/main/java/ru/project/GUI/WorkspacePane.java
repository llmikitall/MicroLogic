package ru.project.GUI;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class WorkspacePane extends Pane {
    private List<GElement> elements;
    private List<Connector> connectors = new ArrayList<>();
    private List<Wire> wires = new ArrayList<>();
    private List<GElement> endElements = new ArrayList<>();
    private final WireManager wireManager;

    public WorkspacePane(){
        this.elements = new ArrayList<>();
        this.wireManager = new WireManager(this);
        setupGlobalEventHandlers();
        setupWorkspace();
    }

    private void setupWorkspace(){
        this.setStyle("-fx-background-color: #246923;");
        this.setMinSize(600, 400);


    }
    public void addElement(GElement element) {
        elements.add(element);
        this.getChildren().add(element);
    }

    private void setupGlobalEventHandlers(){

        this.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY){
                for(Wire wire: wires){
                    if(wire.isDeleted())
                        this.getChildren().remove(wire.getVisual());
                }
            }
        });

        this.setOnMouseMoved(event -> {
            if(wireManager.isCreatingWire()){
                wireManager.updateTempWire(event.getSceneX(), event.getSceneY());
            }
        });

        this.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ESCAPE && wireManager.isCreatingWire()){
                wireManager.cancelWireCreation();
            }
        });

        this.setOnMouseReleased(event -> {
            if(wireManager.isCreatingWire()){
                Connector target = findConnectorAt(event.getSceneX(), event.getSceneY());

                if(target != null) {
                    wireManager.finishWireCreating(target);
                }
                else
                    wireManager.cancelWireCreation();
            }
        });


        this.setFocusTraversable(true);

    }



    private Connector findConnectorAt(double x, double y){
        for(Connector connector: connectors){
            Point2D point = connector.sceneToLocal(x, y);
            if(connector.contains(point))
                return connector;
        }
        return null;
    }

    public void simulation(){
        for(GElement gElement: endElements){
            gElement.getLogElement().simulation();
        }
        for(Wire wire: wires){
            wire.startUpdateColor();
        }
    }
    public void stopSimulation(){
        for(Wire wire: wires){
            wire.stopUpdateColor();
        }
    }

    public void addWire(Wire wire){
        wires.add(wire);
        this.getChildren().add(wire.getVisual());
        wire.getVisual().toBack();
    }

    public void addElement(GElement element){
        elements.add(element);
        connectors.addAll(element.getInputs());
        connectors.addAll(element.getOutputs());
    }

    public void addEndElement(GElement element){
        endElements.add(element);
    }

    public WireManager getWireManager(){
        return wireManager;
    }

    public void updateAllWires(){
        for(Wire wire : wires){
            wire.updatePosition();
        }
    }


}
