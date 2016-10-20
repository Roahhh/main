package seedu.agendum.commons.events.storage;

import seedu.agendum.commons.events.BaseEvent;
import seedu.agendum.model.ReadOnlyToDoList;

/** Indicates the ToDoList in the model has changed*/
public class LoadDataCompleteEvent extends BaseEvent {

    public final ReadOnlyToDoList data;

    public LoadDataCompleteEvent(ReadOnlyToDoList data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "Todo list data load completed. Task list size: " + data.getTaskList().size();
    }
}
