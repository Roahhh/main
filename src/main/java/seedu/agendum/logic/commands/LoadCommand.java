package seedu.agendum.logic.commands;

import seedu.agendum.commons.core.Config;
import seedu.agendum.commons.util.FileUtil;
import seedu.agendum.commons.util.StringUtil;

/**
 * Allow the user to specify a folder as the data storage location
 */
public class LoadCommand extends Command {
    
    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_SUCCESS = "Data successfully loaded from:: %1$s";
    public static final String MESSAGE_LOCATION_INVALID = "The specified file path is invalid.";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Specify a file to load from. \n"
            + "Parameters: FILE_PATH\n" 
            + "Example: " + COMMAND_WORD 
            + "agendum/todolist.xml";
    private String loadLocation;

    public LoadCommand(String location) {
        loadLocation = location.trim();
    }

    @Override
    public CommandResult execute() {
        assert loadLocation != null;

        if(!isLoadLocationValid()) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(MESSAGE_LOCATION_INVALID);
        }

        model.loadFromLocation(loadLocation);
        return new CommandResult(String.format(MESSAGE_SUCCESS, loadLocation));
    }
    
    private boolean isLoadLocationValid() {
       return StringUtil.isValidFilePath(loadLocation);
    }
    
}
