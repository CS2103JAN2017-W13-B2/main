package seedu.address.ui;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.ReadOnlyTask;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    private static final String TAG_LABEL_CSS = "-fx-text-fill: %s; -fx-background-color: %s;";

    private static final HashMap<String, UiColor> tagColorMap = new HashMap<String, UiColor>();

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label description;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().toString());
        id.setText(Integer.toString(displayedIndex) + ") ");
        deadline.setText(task.getDeadline().toString());
        description.setText(task.getDescription().toString());
        status.setText(task.getStatus().toString());
        initTags(task);
    }


    //@@author A0144885R
    private void initTags(ReadOnlyTask task) {
        for (Tag tag : task.getTags()) {
            // Get color of this tag or randomly create a new one
            UiColor tagColor = (tagColorMap.containsKey(tag.tagName) ?
                                tagColorMap.get(tag.tagName) :
                                UiColor.getRandomColor());
            UiColor textColor = UiColor.WHITE;
            // Update HashMap
            tagColorMap.put(tag.tagName, tagColor);

            // Create tag label
            Label tagLabel = new Label(tag.tagName);
            String css = String.format(TAG_LABEL_CSS, textColor.toString(), tagColor.toString());
            tagLabel.setStyle(css);

            tags.getChildren().add(tagLabel);
        }
    }
}
