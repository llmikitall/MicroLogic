package ru.project.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GAndElement extends GElement {
    private Rectangle body;
    private Circle inputFirst;
    private Circle inputSecond;
    private Circle output;
    private Text text;

    public GAndElement(){
        createVisual();
    }

    public GAndElement(double x, double y){
        super();
        createVisual();
        setPosition(x, y);
    }

    private void createVisual(){
        body = new Rectangle(50, 30);
        body.setStroke(Color.WHITE);

        inputFirst = new Circle(0, 10, 5);
        inputFirst.setStroke(Color.WHITE);

        inputSecond = new Circle(0, 20, 5);
        inputSecond.setStroke(Color.WHITE);

        output = new Circle(50, 15, 5);
        output.setStroke(Color.WHITE);

        text = new Text(15, 20, "AND");
        text.setStroke(Color.WHITE);

        //inputFirst.setCenterX(0);
        //inputSecond.setCenterX(0);
        //output.setCenterX(50);

        this.getChildren().addAll(body, inputFirst, inputSecond, output, text);
    }

    private void setupEventHandlers(){
        this.setOnMouseDragged(event -> {

        });
    }

    public void setPosition(double x, double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
    }


}
