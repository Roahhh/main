package seedu.agendum.logic.commands;

import java.io.IOException;

import seedu.agendum.commons.core.Config;
import seedu.agendum.commons.util.ConfigUtil;

/**
 * Allow the user to specify a folder as the data storage location
 */
public class StoreCommand extends Command {

    public static final String COMMAND_WORD = "store";
    public static final String MESSAGE_SUCCESS = "New data storage location: %1$s";
    public static final String MESSAGE_LOCATION_DOES_NOT_EXIT = "The specified location does not exit.";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Specify a data storage location. \n"
            + "Parameters: FILE_PATH\n" 
            + "Example: " + COMMAND_WORD 
            + "C:/agendum";
    private final String newToDoListFilePath;

    public StoreCommand(String location) {
        newToDoListFilePath = location;
    }

    @Override
    public CommandResult execute() {
        assert newToDoListFilePath != null;
        
        try {
            ConfigUtil.saveToDoListFilePathInConfig(Config.DEFAULT_CONFIG_FILE, newToDoListFilePath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newToDoListFilePath));
        } catch (IOException e) {
            return new CommandResult(MESSAGE_LOCATION_DOES_NOT_EXIT);
        }
    }
}
