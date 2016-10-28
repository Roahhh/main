package seedu.agendum.storage;

import seedu.agendum.commons.exceptions.DataConversionException;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.agendum.logic.commands.CommandLibrary}.
 */
public interface CommandLibraryStorage {

    /**
     * Returns hash table of aliased commands from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Hashtable<String, String>> readCommandTable()
            throws DataConversionException, IOException;

    /**
     * Saves the table {{@link seedu.agendum.logic.commands.CommandLibrary} to the storage.
     * @param table cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCommandTable(Hashtable<String, String> table) throws IOException;

}
