package seedu.agendum.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.agendum.model.task.ReadOnlyTask;

//@@author A0148031R
/**
 * Panel contains the list of other tasks
 */
public class OtherTasksPanel extends TasksPanel {
    private static final String FXML = "OtherTasksPanel.fxml";

    @FXML
    private ListView<ReadOnlyTask> otherTasksListView;

    public OtherTasksPanel() {
        super();
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    protected void setConnections(ObservableList<ReadOnlyTask> otherTasks) {
        otherTasksListView.setItems(otherTasks);
        otherTasksListView.setCellFactory(listView -> new OtherTasksListViewCell());
    }
    
    protected ListView<ReadOnlyTask> getListView() {
        return otherTasksListView;
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            otherTasksListView.scrollTo(index);
            otherTasksListView.getSelectionModel().clearAndSelect(index);
        });
    }
    
    class OtherTasksListViewCell extends ListCell<ReadOnlyTask> {
        public OtherTasksListViewCell() {}

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(TaskCard.load(task).getLayout());
            }
        }
    }
}
