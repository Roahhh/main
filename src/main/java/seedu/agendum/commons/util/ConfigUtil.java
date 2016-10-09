package seedu.agendum.commons.util;

import seedu.agendum.commons.core.Config;
import seedu.agendum.commons.core.LogsCenter;
import seedu.agendum.commons.exceptions.DataConversionException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    private static final Logger logger = LogsCenter.getLogger(ConfigUtil.class);

    /**
     * Returns the Config object from the given file or {@code Optional.empty()} object if the file is not found.
     *   If any values are missing from the file, default values will be used, as long as the file is a valid json file.
     * @param configFilePath cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static Optional<Config> readConfig(String configFilePath) throws DataConversionException {

        assert configFilePath != null;

        File configFile = new File(configFilePath);

        if (!configFile.exists()) {
            logger.info("Config file "  + configFile + " not found");
            return Optional.empty();
        }

        Config config;

        try {
            config = FileUtil.deserializeObjectFromJsonFile(configFile, Config.class);
        } catch (IOException e) {
            logger.warning("Error reading from config file " + configFile + ": " + e);
            throw new DataConversionException(e);
        }

        return Optional.of(config);
    }

    /**
     * Saves the Config object to the specified file.
     *   Overwrites existing file if it exists, creates a new file if it doesn't.
     * @param config cannot be null
     * @param configFilePath cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static void saveConfig(Config config, String configFilePath) throws IOException {
        assert config != null;
        assert configFilePath != null;

        FileUtil.serializeObjectToJsonFile(new File(configFilePath), config);
    }
    
    /**
     * Load the current Config file, change ToDoList file path then saves
     *   Overwrites existing file.
     * @param newToDoListFilePath cannot be null
     * @param configFilePath cannot be null
     * @throws IOException if there was an error during writing to Config file
     */
    public static void saveToDoListFilePathInConfig(String configFilePath, String newToDoListFilePath) throws IOException {
        assert configFilePath != null;
        assert !configFilePath.isEmpty();
        
        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePath);
            Config config = configOptional.orElse(new Config());            
            
            config.setToDoListFilePath(newToDoListFilePath);
            ConfigUtil.saveConfig(config, configFilePath);
            
            logger.info("ToDoList file path has changed to: " + newToDoListFilePath);
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePath + " was not in the correct format. " +
                    "ToDoList file path was not changed in Config.");
        }
    }
}
