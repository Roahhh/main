package guitests;

import org.junit.Test;

import seedu.agendum.commons.core.Config;
import seedu.agendum.logic.commands.StoreCommand;

public class StoreCommandTest extends ToDoListGuiTest {

    @Test
    public void store() {
        String testLocation = "data/test.xml";
        //save to a valid directory
        commandBox.runCommand("store " + testLocation);
        assertResultMessage(String.format(StoreCommand.MESSAGE_SUCCESS, testLocation));

        //save to default directory
        commandBox.runCommand("store default");
        assertResultMessage(String.format(StoreCommand.MESSAGE_LOCATION_DEFAULT, Config.DEFAULT_SAVE_LOCATION));
        
        //invalid directory
        commandBox.runCommand("store test/.xml");
        assertResultMessage(StoreCommand.MESSAGE_LOCATION_INVALID);
    }
}
