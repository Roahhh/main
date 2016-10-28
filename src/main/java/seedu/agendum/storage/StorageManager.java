package seedu.agendum.storage;

import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.agendum.commons.core.ComponentManager;
import seedu.agendum.commons.core.Config;
import seedu.agendum.commons.core.LogsCenter;
import seedu.agendum.commons.events.model.LoadDataRequestEvent;
import seedu.agendum.commons.events.logic.CommandLibraryChangedEvent;
import seedu.agendum.commons.events.model.ChangeSaveLocationRequestEvent;
import seedu.agendum.commons.events.model.ToDoListChangedEvent;
import seedu.agendum.commons.events.storage.DataLoadingExceptionEvent;
import seedu.agendum.commons.events.storage.DataSavingExceptionEvent;
import seedu.agendum.commons.events.storage.LoadDataCompleteEvent;
import seedu.agendum.commons.exceptions.DataConversionException;
import seedu.agendum.commons.util.ConfigUtil;
import seedu.agendum.commons.util.StringUtil;
import seedu.agendum.model.ReadOnlyToDoList;
import seedu.agendum.model.UserPrefs;

/**
 * Manages storage of ToDoList data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ToDoListStorage toDoListStorage;
    private UserPrefsStorage userPrefsStorage;
    private CommandLibraryStorage commandLibraryStorage;
    private Config config;

    public StorageManager(ToDoListStorage toDoListStorage, CommandLibraryStorage commandLibraryStorage,
            UserPrefsStorage userPrefsStorage, Config config) {
        super();
        this.toDoListStorage = toDoListStorage;
        this.commandLibraryStorage = commandLibraryStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.config = config;
    }

    public StorageManager(String toDoListFilePath, String commandLibraryFilePath,
            String userPrefsFilePath, Config config) {
        this(new XmlToDoListStorage(toDoListFilePath), new JsonCommandLibraryStorage(commandLibraryFilePath),
                new JsonUserPrefsStorage(userPrefsFilePath), config);
    }
    
    // ================ CommandLibrary methods ==============================

    @Override
    public Optional<Hashtable<String, String>> readCommandLibraryTable()
            throws DataConversionException, IOException {
        return commandLibraryStorage.readCommandLibraryTable();
    }

    @Override
    public void saveCommandLibraryTable(Hashtable<String, String> table) throws IOException {
        commandLibraryStorage.saveCommandLibraryTable(table);
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ ToDoList methods ==============================

    @Override
    public String getToDoListFilePath() {
        return toDoListStorage.getToDoListFilePath();
    }

    @Override
    public Optional<ReadOnlyToDoList> readToDoList() throws DataConversionException {
        return readToDoList(toDoListStorage.getToDoListFilePath());
    }

    @Override
    public Optional<ReadOnlyToDoList> readToDoList(String filePath) throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        return toDoListStorage.readToDoList(filePath);
    }

    @Override
    public void saveToDoList(ReadOnlyToDoList toDoList) throws IOException {
        saveToDoList(toDoList, toDoListStorage.getToDoListFilePath());
    }

    @Override
    public void saveToDoList(ReadOnlyToDoList toDoList, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        toDoListStorage.saveToDoList(toDoList, filePath);
    }
    
    //@@author A0148095X
    @Override
    public void setToDoListFilePath(String filePath){
        assert StringUtil.isValidPathToFile(filePath);
        toDoListStorage.setToDoListFilePath(filePath);
        logger.info("Setting todo list file path to: " + filePath);
    }
    
    private void saveConfigFile() {
        try {
            ConfigUtil.saveConfig(config, Config.DEFAULT_CONFIG_FILE);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }        
    }

    //@@author
    @Override
    @Subscribe
    public void handleToDoListChangedEvent(ToDoListChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveToDoList(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
    
    @Override
    @Subscribe
    public void handleCommandLibraryChangedEvent(CommandLibraryChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Command library changed, saving to file"));
        try {
            saveCommandLibraryTable(event.aliasTable);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
    
    //@@author A0148095X
    @Override
    @Subscribe
    public void handleChangeSaveLocationRequestEvent(ChangeSaveLocationRequestEvent event) {
        String location = event.location;
        
        setToDoListFilePath(location);
        config.setToDoListFilePath(location);
        saveConfigFile();
        
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
    }    

    @Override
    @Subscribe
    public void handleLoadDataRequestEvent(LoadDataRequestEvent event) {        
        setToDoListFilePath(event.loadLocation);
        
        Optional<ReadOnlyToDoList> toDoListOptional;
        ReadOnlyToDoList loadedData = null;
        try {
            toDoListOptional = readToDoList();
            loadedData = toDoListOptional.get();
            logger.info("Loading successful - " + LogsCenter.getEventHandlingLogMessage(event));
            raise(new LoadDataCompleteEvent(loadedData));
        } catch (DataConversionException dce) {
            logger.warning("Loading unsuccessful - Data file not in the correct format. ");
            raise(new DataLoadingExceptionEvent(dce));
        } catch (NoSuchElementException nse) {
            logger.warning("Loading unsuccessful - File does not exist.");
            raise(new DataLoadingExceptionEvent(nse));            
        }
    }
}
