package seedu.agendum.commons.events.storage;

import seedu.agendum.commons.events.BaseEvent;

/**
 * Indicates an exception during a file saving
 */
public class DataLoadingExceptionEvent extends BaseEvent {

    public Exception exception;

    public DataLoadingExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString(){
        return exception.toString();
    }

}
