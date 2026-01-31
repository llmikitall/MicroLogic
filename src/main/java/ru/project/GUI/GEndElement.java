package ru.project.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ru.project.Core.LEnd;

public class GEndElement extends GElement {
    private Rectangle body;
    private Text text;

    public GEndElement(WireManager wireManager){
        super();
        createVisual();
        setupConnectorHandlers(wireManager);
    }

    public GEndElement(double x, double y, WireManager wireManager){
        super();
        createVisual();
        setLogElement(new LEnd());
        setupConnectorHandlers(wireManager);
        setPosition(x, y);
    }



    private void createVisual(){
        Connector input1 = new Connector(this, true, 0, 0, 15);
        this.getInputs().add(input1);

        body = new Rectangle(50, 30);
        body.setStroke(Color.WHITE);

        text = new Text(15, 20, "End");
        text.setStroke(Color.WHITE);

        this.getChildren().addAll(body, input1, text);
    }


    public void setPosition(double x, double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}
