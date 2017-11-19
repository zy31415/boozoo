package io.github.zy31415.boozoo.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AddLabelBox extends VBox {

    private static Logger logger = LogManager.getLogger();

    private VBox labelBox;

    public AddLabelBox() {
        super();

        setId("tags");

        Label tagsLabel =  new Label("Tag");
        getChildren().add(tagsLabel);

        labelBox = new VBox();
        getChildren().add(labelBox);

        labelBox.getChildren().add(createTagInput());

        Button addButton = new Button("+");
        addButton.setOnAction((ActionEvent event) -> labelBox.getChildren().add(createTagInput()));

        getChildren().add(addButton);
    }

    private HBox createTagInput() {
        HBox textInput = new HBox();

        TextField tagTextField = new TextField();
        Button removeButton = new Button("-");
        removeButton.setOnAction((ActionEvent event) -> labelBox.getChildren().remove(textInput));
        textInput.getChildren().addAll(tagTextField, removeButton);

        return textInput;
    }

    public List<String> getTagNames() {
        List<String> tags = new ArrayList<>();

        for (Node tagInput : labelBox.getChildren()) {
            HBox input = (HBox) tagInput;

            TextField tagTextField = (TextField) input.getChildren().get(0);

            // TODO: A lot more formatting you can do ...
            tags.add(tagTextField.getText().trim().toLowerCase());
        }
        return tags;
    }
}
