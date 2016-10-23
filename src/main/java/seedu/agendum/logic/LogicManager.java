package seedu.agendum.logic;

import javafx.collections.ObservableList;
import seedu.agendum.commons.core.ComponentManager;
import seedu.agendum.commons.core.LogsCenter;
import seedu.agendum.logic.commands.Command;
import seedu.agendum.logic.commands.CommandResult;
import seedu.agendum.logic.parser.Parser;
import seedu.agendum.model.Model;
import seedu.agendum.model.task.ReadOnlyTask;
import seedu.agendum.storage.Storage;

import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;
    private final CommandLibrary commandLibrary;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
        this.commandLibrary = CommandLibrary.getInstance();
    }

    @Override
    public CommandResult execute(String commandText) {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        command.setData(model, commandLibrary);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    /**
     * Requires implementation here
     */
    @Override
    public ObservableList<ReadOnlyTask> getCompletedTaskList() {
        return null;
    }
}
