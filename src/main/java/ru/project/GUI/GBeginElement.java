package ru.project.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ru.project.Core.LStart;

public class GBeginElement extends GElement{
    private Rectangle body;
    private Text text;

    public GBeginElement(WireManager wireManager){
        super();
        createVisual();
        setupConnectorHandlers(wireManager);
    }

    public GBeginElement(double x, double y, WireManager wireManager){
        super();
        createVisual();
        setLogElement(new LStart());
        setupConnectorHandlers(wireManager);

        setPosition(x, y);
    }

    private void createVisual(){

        body = new Rectangle(50, 30);
        body.setStroke(Color.WHITE);

        Connector output = new Connector(this, false, 0, 50, 15);
        this.getOutputs().add(output);

        text = new Text(15, 20, "Start");
        text.setStroke(Color.WHITE);

        this.getChildren().addAll(body, output, text);
    }


    public void setPosition(double x, double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}
