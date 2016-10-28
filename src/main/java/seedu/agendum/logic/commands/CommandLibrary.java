package seedu.agendum.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import seedu.agendum.commons.core.EventsCenter;
import seedu.agendum.commons.events.logic.CommandLibraryChangedEvent;

import com.google.common.eventbus.Subscribe;

/**
 * Manage and store the various alias keys and values
 */
public class CommandLibrary {

    private List<String> allCommandWords = new ArrayList<String>(Arrays.asList(AddCommand.COMMAND_WORD,
            AliasCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD, LoadCommand.COMMAND_WORD, MarkCommand.COMMAND_WORD,
            RenameCommand.COMMAND_WORD, ScheduleCommand.COMMAND_WORD, StoreCommand.COMMAND_WORD,
            UnaliasCommand.COMMAND_WORD, UndoCommand.COMMAND_WORD, UnmarkCommand.COMMAND_WORD));

    // Hashtable with key as user-defined aliases,
    // value as Agendum's reserved command keywords
    private Hashtable<String, String> aliasTable = new Hashtable<String, String>();

    public CommandLibrary() {
    }

    public void loadCommandTable(Hashtable<String, String> aliasTable) {
        this.aliasTable = aliasTable;
    }

    /**
     * Returns true if key is already an alias to a command keyword
     */
    public boolean isExistingAliasKey(String key) {
        assert key != null;
        assert key.equals(key.toLowerCase());

        return aliasTable.containsKey(key);
    }

    /**
     * Precondition: key is an existing alias. Returns the reserved command
     * keyword that is aliased by key
     */
    public String getAliasedValue(String key) {
        assert isExistingAliasKey(key);

        return aliasTable.get(key);
    }

    /**
     * Returns true if value is a reserved command keyword
     */
    public boolean isReservedCommandKeyword(String value) {
        assert value != null;
        assert value.equals(value.toLowerCase());

        return allCommandWords.contains(value);
    }

    /**
     * Precondition: key is a new unique alias and not a command keyword; value
     * is a reserved command keyword. Saves the new alias relationship (key can
     * be used in place of value)
     */
    public void addNewAlias(String key, String value) {
        assert !isExistingAliasKey(key);
        assert !isReservedCommandKeyword(key);
        assert isReservedCommandKeyword(value);

        aliasTable.put(key, value);

        indicateCommandLibraryChanged(key + " aliased");
    }

    /**
     * Precondition: key is aliased to a command keyword Destroy the alias
     * relationship (key cannot be used in place of value)
     */
    public void removeExistingAlias(String key) {
        assert isExistingAliasKey(key);

        aliasTable.remove(key);

        indicateCommandLibraryChanged(key + " unaliased");
    }

    private void indicateCommandLibraryChanged(String keyChanged) {
        EventsCenter eventCenter = EventsCenter.getInstance();
        eventCenter.post(new CommandLibraryChangedEvent(keyChanged, aliasTable));
    }

}
