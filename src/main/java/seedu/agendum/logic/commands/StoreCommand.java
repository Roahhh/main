package seedu.agendum.logic.commands;

import seedu.agendum.commons.core.Config;
import seedu.agendum.commons.exceptions.FileDeletionException;
import seedu.agendum.commons.util.FileUtil;
import seedu.agendum.commons.util.StringUtil;

/**
 * Allow the user to specify a folder as the data storage location
 */
public class StoreCommand extends Command {
    
    public static final String COMMAND_WORD = "store";
    public static final String MESSAGE_SUCCESS = "New save location: %1$s";
    public static final String MESSAGE_LOCATION_INVALID = "The specified location is invalid.";
    public static final String MESSAGE_FAIL_DELETE_FILE = "Failed to delete previous data file.";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Specify a save location. \n"
            + "Parameters: FILE_PATH\n" 
            + "Example: " + COMMAND_WORD 
            + "C:/agendum";
    private String newSaveLocation;

    public StoreCommand(String location) {
        newSaveLocation = location.trim();
    }

    @Override
    public CommandResult execute() {
        assert newSaveLocation != null;
        
        if(newSaveLocation.equalsIgnoreCase("default")) {
            newSaveLocation = Config.DEFAULT_SAVE_LOCATION;
        } else if(!isNewSaveLocationValid()) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(MESSAGE_LOCATION_INVALID);
        }

        try {
            model.changeSaveLocation(newSaveLocation);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newSaveLocation));
        } catch (FileDeletionException e) {
            return new CommandResult(String.format(MESSAGE_FAIL_DELETE_FILE));
        }
    }
    
    private boolean isNewSaveLocationValid() {
        boolean stringValid = StringUtil.isValidFilePath(newSaveLocation);
        boolean locationValid = FileUtil.isPathWriteable(newSaveLocation);
        
        return stringValid && locationValid;
    }
    
}
