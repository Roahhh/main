package seedu.agendum.model.task;

import java.time.LocalDateTime;
import java.util.Optional;

public class childRecurringTask extends RecurringTask{

    private RecurringTask parent;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public childRecurringTask(RecurringTask parent) {
        super(parent);
        System.out.println("Being called by parent!");
        this.parent = parent;
        this.startDateTime = parent.getStartDateTime().orElse(null);
        this.endDateTime = parent.getEndDateTime().orElse(null);
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
}
