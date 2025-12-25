package ru.project.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Connector extends Circle {
    private GElement element;
    private boolean input;
    private int pinIndex;
    private Wire wire = null;
    private boolean connected = false;

    public Connector(GElement element, boolean input, int pinIndex, double x, double y){
        super(x, y, 5);
        this.element = element;
        this.input = input;
        this.pinIndex = pinIndex;

        setupAppearance();
        setupEventHandlers();
    }

    private void setupAppearance(){
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1.5);
    }

    private void setupEventHandlers(){
        this.setOnMouseEntered(event ->{
            if(!connected){
                this.setFill(Color.LIGHTGREEN);
            }
        });

        this.setOnMouseExited(event -> {
            if(!connected){
                this.setFill(Color.WHITE);
            }
        });
    }

    public GElement getElement(){
        return element;
    }

    public boolean isInput(){
        return input;
    }
    public int getPinIndex(){
        return pinIndex;
    }
    public boolean isConnected(){
        return connected;
    }

    public void setElement(GElement element){
        this.element = element;
    }

    public void setConnected(boolean connected){
        this.connected = connected;
        this.setFill(connected ? Color.LIGHTBLUE : Color.WHITE);
    }

    public Wire getWire() {
        return wire;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }
}
