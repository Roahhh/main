package seedu.agendum.logic.commands;

import seedu.agendum.commons.util.StringUtil;
import seedu.agendum.commons.util.XmlUtil;

/**
 * Allow the user to specify a folder as the data storage location
 */
public class LoadCommand extends Command {
    
    public static final String COMMAND_WORD = "load";
    public static final String COMMAND_FORMAT = "load <location>";
    public static final String COMMAND_DESCRIPTION = "loads task list from specified location";
    
    public static final String MESSAGE_SUCCESS = "Data successfully loaded from: %1$s";
    public static final String MESSAGE_PATH_INVALID = "The specified path to file is invalid: %1$s";
    public static final String MESSAGE_FILE_WRONG_FORMAT = "The specified file is in the wrong format: %1$s";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Specify a file to load from. \n"
            + "Parameters: PATH_TO_FILE\n" 
            + "Example: " + COMMAND_WORD 
            + "agendum/todolist.xml";
    
    private String loadLocation;

    public LoadCommand(String location) {
        loadLocation = location.trim();
    }

    @Override
    public CommandResult execute() {
        assert loadLocation != null;

        if(!isLocationValid()) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(String.format(MESSAGE_PATH_INVALID, loadLocation));
        }
        
        if(!isFileCorrectFormat()) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(String.format(MESSAGE_FILE_WRONG_FORMAT, loadLocation));            
        }

        model.loadFromLocation(loadLocation);
        return new CommandResult(String.format(MESSAGE_SUCCESS, loadLocation));
    }
    
    private boolean isFileCorrectFormat() {
        return XmlUtil.isFileCorrectFormat(loadLocation);
    }

    private boolean isLocationValid() {
        return StringUtil.isValidPathToFile(loadLocation);
    }

    @Override
    public String getName() {
        return COMMAND_WORD;
    }
        
    @Override
    public String getFormat() {
        return COMMAND_FORMAT;
    }
        
    @Override
    public String getDescription() {
        return COMMAND_DESCRIPTION;
    }
    

}
