package guitests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import seedu.agendum.commons.exceptions.FileDeletionException;
import seedu.agendum.commons.util.FileUtil;
import seedu.agendum.logic.commands.LoadCommand;

public class LoadCommandTest extends ToDoListGuiTest {

    private String command;
    
    @Override
    public void setup() throws Exception{
        super.setup();
        command = LoadCommand.COMMAND_WORD + " ";
    }
    
    @Test
    public void load_pathValid() throws IOException, FileDeletionException {
        String fileThatExists = "data/todolist.xml";
        String fileThatDoesNotExist = "data/DoesNotExist.xml";
        String fileInWrongFormat = "data/WrongFormat.xml";

        // load from an existing file
        commandBox.runCommand(command + fileThatExists);
        assertResultMessage(String.format(LoadCommand.MESSAGE_SUCCESS, fileThatExists));   
        
        // load from a non-existing file
        commandBox.runCommand(command + fileThatDoesNotExist);
        assertResultMessage(String.format(LoadCommand.MESSAGE_FILE_DOES_NOT_EXIST, fileThatDoesNotExist));
        
        // file in wrong format
        FileUtil.createFile(new File(fileInWrongFormat)); // create empty file
        commandBox.runCommand(command + fileInWrongFormat);
        assertResultMessage(String.format(LoadCommand.MESSAGE_FILE_WRONG_FORMAT, fileInWrongFormat));
        FileUtil.deleteFile(fileInWrongFormat); // cleanup
    }

    @Test
    public void load_pathInvalid(){
        String missingFileType = "test/invalid";
        String missingFileName = "test/.bad";
        
        // invalid file type
        commandBox.runCommand(command + missingFileType);
        assertResultMessage(String.format(LoadCommand.MESSAGE_PATH_INVALID, missingFileType));
        
        // invalid file name
        commandBox.runCommand(command + missingFileName);
        assertResultMessage(String.format(LoadCommand.MESSAGE_PATH_INVALID, missingFileName));
    }
}
