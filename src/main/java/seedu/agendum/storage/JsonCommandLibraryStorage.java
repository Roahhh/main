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
 * A class to access the (alias) command table stored in the hard disk as a json file
 */
public class JsonCommandLibraryStorage implements CommandLibraryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCommandLibraryStorage.class);

    private String filePath;

    public JsonCommandLibraryStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<Hashtable<String, String>> readCommandLibraryTable() throws DataConversionException, IOException {
        return readCommandLibraryTable(filePath);
    }

    @Override
    public void saveCommandLibraryTable(Hashtable<String, String> table) throws IOException {
        saveCommandLibraryTable(table, filePath);
    }

    /**
     * Similar to {@link #readCommandLibraryTable()}
     * @param commandLibraryFilePath location of the command library data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<Hashtable<String, String>> readCommandLibraryTable(String commandLibraryFilePath)
            throws DataConversionException {
        assert commandLibraryFilePath != null;

        File commandLibraryFile = new File(commandLibraryFilePath);

        if (!commandLibraryFile.exists()) {
            logger.info("Commands file: "  + commandLibraryFile + " not found");
            return Optional.empty();
        }

        Hashtable<String, String> table = new Hashtable<String, String>();

        try {
            table = FileUtil.deserializeObjectFromJsonFile(commandLibraryFile, table.getClass());
        } catch (IOException e) {
            logger.warning("Error reading from command library file " + commandLibraryFile + ": " + e);
            throw new DataConversionException(e); 
        }

        return Optional.of(table);
    }

    /**
     * Similar to {@link #saveCommandTable(Hashtable<String, String> table)}
     * @param commandLibraryFilePath location of the command library data. Cannot be null.
     */
    public void saveCommandLibraryTable(Hashtable<String, String> table, String commandLibraryFilePath)
            throws IOException {
        assert table != null;
        assert commandLibraryFilePath != null;

        FileUtil.serializeObjectToJsonFile(new File(commandLibraryFilePath), table);
    }
}
