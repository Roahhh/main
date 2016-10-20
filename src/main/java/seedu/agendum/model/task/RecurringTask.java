package seedu.agendum.model.task;

import java.time.LocalDateTime;
import java.util.Optional;
import seedu.agendum.logic.parser.DateTimeParser;

public class RecurringTask extends Task {

    private String period;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    
    private ChildRecurringTask child = null;
    
    public RecurringTask(Name name, Optional<LocalDateTime> startDateTime, 
            Optional<LocalDateTime> endDateTime, String period) {
        super(name, startDateTime, endDateTime);
        this.startDateTime = startDateTime.orElse(null);
        this.endDateTime = endDateTime.orElse(null);
        this.period = period;
        System.out.println("period: " + period);
    }
    
    public RecurringTask(Name name, Optional<LocalDateTime> endDateTime, String period) {
        super(name, endDateTime);
        this.startDateTime = null;
        this.endDateTime = endDateTime.orElse(null);
        this.period = period;
        System.out.println("period: " + period);
    }
    
    public RecurringTask(RecurringTask task) {
        super(task);
    }
    
    public void setNextDateTime() {
        if(this.startDateTime != null) {
            this.startDateTime = DateTimeParser.parseString(period + " from " + this.startDateTime.toString()).get();
        } 
        this.endDateTime = DateTimeParser.parseString(period + " from " + this.endDateTime.toString()).get();
    }
    
    public void setPreviousDateTime() {
        if(this.startDateTime != null) {
            this.startDateTime = DateTimeParser.parseString(period + " before " + this.startDateTime.toString()).get();
        } 
        this.endDateTime = DateTimeParser.parseString(period + " before " + this.endDateTime.toString()).get();
    }
    
    private ChildRecurringTask setChild() {
        this.child = new ChildRecurringTask(this);
        return this.child;
    }
    
    public void deleteChild() {
        this.child = null;
    }
    
    @Override
    public Optional<LocalDateTime> getStartDateTime() {
        return Optional.ofNullable(this.startDateTime);
    }

    @Override
    public Optional<LocalDateTime> getEndDateTime() {
        return Optional.ofNullable(this.endDateTime);
    }

    @Override
    public ChildRecurringTask getChild() {
        System.out.println("exeuted in recurringTask class");
        if(this.child == null) {
            System.out.println("child is null");
            this.setChild();
            this.setNextDateTime();
        } else {
            this.setPreviousDateTime();
        }
        return this.child;
    }
    
    @Override
    public RecurringTask getParent() {
        return null;
    }
    
    @Override
    public boolean isRecurring() {
        return true;
    }
    
    @Override
    public boolean isCompleted() {
        return false;
    }
    
    @Override
    public void markAsCompleted() {
        return;
    }
}
