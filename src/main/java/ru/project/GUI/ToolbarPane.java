package ru.project.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ToolbarPane extends HBox {

    private WorkspacePane workspacePane;

    public ToolbarPane(WorkspacePane workspacePane) {
        this.workspacePane = workspacePane;
        setupToolbar();
    }

    private void setupToolbar() {
        this.setStyle("-fx-background-color: #333; -fx-padding: 10; -fx-spacing: 10;");

        // Кнопка для создания AND элемента
        Button addAndButton = new Button("Добавить AND");
        addAndButton.setOnAction(event -> {
            GAndElement andElement = new GAndElement();
            // Можно задать начальные координаты или разместить по центру
            workspacePane.getChildren().add(andElement);
        });

        // Кнопка для создания других элементов (пример для будущих элементов)
        Button addOrButton = new Button("Добавить OR");
        addOrButton.setOnAction(event -> {
            // Здесь можно будет добавить логику для OR элемента
            System.out.println("Добавление OR элемента (в разработке)");
        });

        Button addNotButton = new Button("Добавить NOT");
        addNotButton.setOnAction(event -> {
            // Здесь можно будет добавить логику для NOT элемента
            System.out.println("Добавление NOT элемента (в разработке)");
        });

        this.getChildren().addAll(addAndButton, addOrButton, addNotButton);
    }
}
