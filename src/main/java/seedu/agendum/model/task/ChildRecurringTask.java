package seedu.agendum.model.task;

import java.time.LocalDateTime;
import java.util.Optional;

public class ChildRecurringTask extends RecurringTask {

    private RecurringTask parent;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    
    private boolean isLatestChild;

    public ChildRecurringTask(RecurringTask parent) {
        super(parent);
        System.out.println("Being called by parent!");
        this.parent = parent;
        this.startDateTime = parent.getStartDateTime().orElse(null);
        this.endDateTime = parent.getEndDateTime().orElse(null);
    }
    
    public void setLatestChild() {
        isLatestChild = true;
    }
    
    public void unsetLatestChild() {
        isLatestChild = false;
    }
    
    @Override
    public RecurringTask getParent() {
        return this.parent;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public Optional<LocalDateTime> getStartDateTime() {
        return Optional.ofNullable(startDateTime);
    }

    @Override
    public Optional<LocalDateTime> getEndDateTime() {
        return Optional.ofNullable(endDateTime);
    }
    
    @Override
    public boolean isChild() {
        return true;
    }
    @Override
    public boolean isLatestChild() {
        return isLatestChild;
    }
}
