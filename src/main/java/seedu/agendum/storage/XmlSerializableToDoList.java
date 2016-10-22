package seedu.agendum.storage;

import seedu.agendum.commons.exceptions.IllegalValueException;
import seedu.agendum.model.task.ReadOnlyTask;
import seedu.agendum.model.task.RecurringTask;
import seedu.agendum.model.task.Task;
import seedu.agendum.model.task.UniqueTaskList;
import seedu.agendum.model.ReadOnlyToDoList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable ToDoList that is serializable to XML format
 */
@XmlRootElement(name = "todolist")
public class XmlSerializableToDoList implements ReadOnlyToDoList {

    
    @XmlElement
    private List<XmlAdaptedTask> tasks;

    {
        tasks = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableToDoList() {}

    /**
     * Conversion
     */
    public XmlSerializableToDoList(ReadOnlyToDoList src) {
        tasks.addAll(src.getTaskList().stream().map(XmlAdaptedTask::new).collect(Collectors.toList()));
    }
    
    

    @Override
    public UniqueTaskList getUniqueTaskList() {
        UniqueTaskList lists = new UniqueTaskList();
        List<XmlAdaptedTask> childrenWatingList = new ArrayList<XmlAdaptedTask>();
        try {
            for (XmlAdaptedTask p : tasks) {
                if (p.isRecurring() && p.isChild()) {
                    childrenWatingList.add(p);
                } else if (p.isRecurring()) {
                    lists.add(p.toRecurringTaskModelType());
                } else {
                    lists.add(p.toTaskModelType());
                }
            }
            for (XmlAdaptedTask p : childrenWatingList) {
                lists.add(p.toChildRecurringTaskModelType(getParent(p, lists)));
            }
        } catch (IllegalValueException e) {
            // TODO: better error handling
        }
        return lists;
    }

    @Override
    public List<ReadOnlyTask> getTaskList() {
//        return tasks.stream().map(p -> {
//            try {
//                return p.toTaskModelType();
//            } catch (IllegalValueException e) {
//                e.printStackTrace();
//                //TODO: better error handling
//                return null;
//            }
//        }).collect(Collectors.toCollection(ArrayList::new));
        List<ReadOnlyTask> tasks = new ArrayList<ReadOnlyTask>();
        Iterator itr =  getUniqueTaskList().iterator();
        while(itr.hasNext()) {
            tasks.add((ReadOnlyTask)itr.next());
        }
        return tasks;
    }
    
    public RecurringTask getParent(XmlAdaptedTask child, UniqueTaskList lists) {
        String parentName = child.getParentName();
        for(Task task : lists) {
            if(!task.isChild() && task.getName().fullName.equals(parentName) && task.isRecurring()) {
                return (RecurringTask)task;
            }
        }
        return null;
    }

}
