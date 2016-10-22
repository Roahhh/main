package seedu.agendum.model.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import seedu.agendum.logic.parser.DateTimeParser;

public class RecurringTask extends Task {

    private String period;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    
    private ArrayList<ChildRecurringTask> children = new ArrayList<ChildRecurringTask>();
    
    public RecurringTask(Name name, Optional<LocalDateTime> startDateTime, 
            Optional<LocalDateTime> endDateTime, String period) {
        super(name, startDateTime, endDateTime);
        this.startDateTime = startDateTime.orElse(null);
        this.endDateTime = endDateTime.orElse(null);
        this.period = period;
    }
    
    public RecurringTask(Name name, Optional<LocalDateTime> endDateTime, String period) {
        super(name, endDateTime);
        this.startDateTime = null;
        this.endDateTime = endDateTime.orElse(null);
        this.period = period;
    }
    
    public RecurringTask(RecurringTask self) {
        super(self);
    }
    
    public void setChild(ChildRecurringTask child) {
        child.setLatestChild();
        if(this.children.size() >= 1) {
            this.children.get(this.children.size() - 1).unsetLatestChild();
        }
        this.children.add(child);
        this.setNextDateTime();
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
        if(this.children.size() >= 2) {
            this.children.get(this.children.size() - 2).setLatestChild();
        }
        this.children.remove(this.children.size() - 1);
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
        ChildRecurringTask child = new ChildRecurringTask(this);
        return child;
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
    
    @Override
    public boolean isLatestChild() {
        return false;
    }
    
    @Override
    public String getPeriod() {
        return period;
    }
}
