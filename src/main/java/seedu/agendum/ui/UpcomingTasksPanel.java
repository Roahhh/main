package seedu.agendum.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.agendum.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.agendum.model.task.ReadOnlyTask;
import seedu.agendum.commons.core.LogsCenter;

/**
 * Panel contains the list of upcoming tasks
 */
public class UpcomingTasksPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(UpcomingTasksPanel.class);
    private static final String FXML = "UpcomingTasksPanel.fxml";
    private AnchorPane panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyTask> upcomingTasksListView;

    public UpcomingTasksPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (AnchorPane) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static UpcomingTasksPanel load(Stage primaryStage, AnchorPane UpcomingTasksPlaceholder,
            ObservableList<ReadOnlyTask> taskList) {
        UpcomingTasksPanel upcomingTasksPanel = UiPartLoader.loadUiPart(primaryStage, UpcomingTasksPlaceholder, new UpcomingTasksPanel());
        upcomingTasksPanel.configure(taskList);
        return upcomingTasksPanel;
    }

    private void configure(ObservableList<ReadOnlyTask> upcomingTasks) {
        setConnections(upcomingTasks);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyTask> upcomingTasks) {
        upcomingTasksListView.setItems(upcomingTasks);
        upcomingTasksListView.setCellFactory(listView -> new upcomingTasksListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        upcomingTasksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in task list panel changed to : '" + newValue + "'");
                raise(new TaskPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            upcomingTasksListView.scrollTo(index);
            upcomingTasksListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class upcomingTasksListViewCell extends ListCell<ReadOnlyTask> {

        public upcomingTasksListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(UpcomingTaskCard.load(task, getIndex() + 1).getLayout());
            }
        }
    }

}
