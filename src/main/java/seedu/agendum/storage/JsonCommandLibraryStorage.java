package seedu.agendum.storage;

import seedu.agendum.commons.core.LogsCenter;
import seedu.agendum.commons.exceptions.DataConversionException;
import seedu.agendum.commons.util.FileUtil;
import seedu.agendum.model.UserPrefs;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A class to access the alias command table stored in the hard disk as a json file
 */
public class JsonCommandLibraryStorage implements CommandLibraryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCommandLibraryStorage.class);

    private String filePath;

    public JsonCommandLibraryStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<Hashtable<String, String>> readCommandTable() throws DataConversionException, IOException {
        return readCommandTable(filePath);
    }

    @Override
    public void saveCommandTable(Hashtable<String, String> table) throws IOException {
        saveCommandTable(table, filePath);
    }

    /**
     * Similar to {@link #readCommandTable()}
     * @param commandLibraryFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<Hashtable<String, String>> readCommandTable(String commandLibraryFilePath)
            throws DataConversionException {
        assert commandLibraryFilePath != null;

        File commandsFile = new File(commandLibraryFilePath);

        if (!commandsFile.exists()) {
            logger.info("Commands file: "  + commandsFile + " not found");
            return Optional.empty();
        }

        Hashtable<String, String> table = new Hashtable<String, String>();

        try {
            table = FileUtil.deserializeObjectFromJsonFile(commandsFile, table.getClass());
        } catch (IOException e) {
            logger.warning("Error reading from commands file " + commandsFile + ": " + e);
            throw new DataConversionException(e); 
        }

        return Optional.of(table);
    }

    /**
     * Similar to {@link #saveCommandTable(Hashtable<String, String> table)}
     * @param commandLibraryFilePath location of the data. Cannot be null.
     */
    public void saveCommandTable(Hashtable<String, String> table, String commandLibraryFilePath)
            throws IOException {
        assert table != null;
        assert commandLibraryFilePath != null;

        FileUtil.serializeObjectToJsonFile(new File(commandLibraryFilePath), table);
    }
}
