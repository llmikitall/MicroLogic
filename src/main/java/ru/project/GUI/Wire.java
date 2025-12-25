package ru.project.GUI;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Wire {
    private Line visual;
    private Connector start;
    private boolean deleted = false;
    private Connector end;

    public Wire(Connector start, Connector end){
        this.start = start;
        this.end = end;
        this.visual = createVisual();

        start.setConnected(true);
        end.setConnected(true);
        setupHandler();
    }

    private Line createVisual(){
        double startX = getAbsoluteX(start);
        double startY = getAbsoluteY(start);

        double endX = getAbsoluteX(end);
        double endY = getAbsoluteY(end);

        Line line = new Line(startX, startY, endX, endY);

        line.setStroke(Color.BLACK);
        line.setStrokeWidth(4.0);
        line.getStrokeDashArray().clear();

        return line;
    }

    public void startUpdateColor(){
        if(start.getElement().getLogElement().isState()){
            visual.setStroke(Color.YELLOWGREEN);
        }
    }

    public void stopUpdateColor(){
        visual.setStroke(Color.BLACK);
    }

    private double getAbsoluteX(Connector connector){
        return connector.getCenterX() + connector.getElement().getLayoutX();
    }

    private double getAbsoluteY(Connector connector){
        return connector.getCenterY() + connector.getElement().getLayoutY();
    }

    public void updatePosition(){
        int gridSize = 15;

        visual.setStartX(getAbsoluteX(start));
        visual.setStartY(getAbsoluteY(start));

        visual.setEndX(getAbsoluteX(end));
        visual.setEndY(getAbsoluteY(end));
    }

    private void setupHandler(){
        this.visual.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY)
                deleteWire();
        });
    }

    private void deleteWire(){
        if(this.start.isInput()){
            this.start.getElement().getLogElement().getInput().remove(this.end.getElement().getLogElement());
            this.end.getElement().getLogElement().setOutput(null);
        }
        else{
            this.end.getElement().getLogElement().getInput().remove(this.start.getElement().getLogElement());
            this.start.getElement().getLogElement().setOutput(null);
        }

        this.start.setConnected(false);
        this.start.setWire(null);

        this.end.setConnected(false);
        this.end.setWire(null);

        this.setDeleted(true);
    }

    public Line getVisual(){
        return visual;
    }

    public Connector getStart(){
        return start;
    }

    public Connector getEnd(){
        return end;
    }

    public boolean isConnectedTo(GElement element){
        return start.getElement() == element || end.getElement() == element;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
