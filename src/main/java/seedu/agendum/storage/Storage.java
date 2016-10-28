package seedu.agendum.storage;

import seedu.agendum.commons.events.model.LoadDataRequestEvent;
import seedu.agendum.commons.events.logic.CommandLibraryChangedEvent;
import seedu.agendum.commons.events.model.ChangeSaveLocationRequestEvent;
import seedu.agendum.commons.events.model.ToDoListChangedEvent;
import seedu.agendum.commons.events.storage.DataSavingExceptionEvent;
import seedu.agendum.commons.exceptions.DataConversionException;
import seedu.agendum.model.ReadOnlyToDoList;
import seedu.agendum.model.UserPrefs;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends ToDoListStorage, UserPrefsStorage, CommandLibraryStorage {

    @Override
    Optional<Hashtable<String, String>> readCommandLibraryTable()
            throws DataConversionException, IOException;

    @Override
    void saveCommandLibraryTable(Hashtable<String, String> table) throws IOException;

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getToDoListFilePath();
    
    @Override
    public void setToDoListFilePath(String filePath);

    @Override
    Optional<ReadOnlyToDoList> readToDoList() throws DataConversionException, IOException;

    @Override
    void saveToDoList(ReadOnlyToDoList toDoList) throws IOException;

    /**
     * Saves the current version of the To Do List to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleToDoListChangedEvent(ToDoListChangedEvent event);

    /**
     * Saves the current version of the Command Library to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleCommandLibraryChangedEvent(CommandLibraryChangedEvent event);

    /** Loads todo list data from the file **/
    public void handleLoadDataRequestEvent(LoadDataRequestEvent event);
    
    /** Sets the save location **/
    public void handleChangeSaveLocationRequestEvent(ChangeSaveLocationRequestEvent event);
}
