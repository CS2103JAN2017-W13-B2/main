package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.ShowTaskGroupEvent;
import seedu.address.model.task.ReadOnlyTask;

/**
 * Panel containing a group of tasks.
 */
public class TaskGroupPanel extends UiPart<Region> {

    private final Logger logger = LogsCenter.getLogger(TaskGroupPanel.class);
    private static final String FXML = "TaskGroupPanel.fxml";

    @FXML
    private TitledPane titledPane;

    @FXML
    private VBox taskGroupView;

    private String title;

    public TaskGroupPanel(String title, ObservableList<ReadOnlyTask> taskList, List<Integer> taskIndexList) {
        super(FXML);
        setTitle(title, taskList.size());
        setExpandingListener();
        createTaskGroupView(taskList, taskIndexList);
        closeTitlePane();
        registerAsAnEventHandler(this);
    }

    public void closeTitlePane() {
        titledPane.setExpanded(false);
    }

    public void openTitlePane() {
        titledPane.setExpanded(true);
    }

    public void setTitle(String title, int taskCount) {
        this.title = title;
        titledPane.setText(title + " (" + taskCount + ")");
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * Instantiate TaskCard objects and add them to taskListView.
     */
    private void createTaskGroupView(ObservableList<ReadOnlyTask> taskList, List<Integer> taskIndexList) {
        taskGroupView.getChildren().clear();
        int index = 0;
        for (ReadOnlyTask task : taskList) {
            TaskCard taskCard = new TaskCard(task, taskIndexList.get(index) + 1);
            taskGroupView.getChildren().add(taskCard.getRoot());
            index++;
        }
    }

    private void setExpandingListener() {
        titledPane.expandedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue && newValue != oldValue) {
                EventsCenter.getInstance().post(new ShowTaskGroupEvent(getTitle()));
            }
        });
    }

    @Subscribe
    private void handleShowTaskGroupEvent(ShowTaskGroupEvent event) {
        if (!getTitle().equals(event.title) && titledPane.isExpanded()) {
            closeTitlePane();
        }
    }

}
