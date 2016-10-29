//@@author A0133367E
package seedu.agendum.commons.events.logic;

import seedu.agendum.commons.events.BaseEvent;

import java.util.*;

/**
 * Indicate the (alias) command library in the logic has changed
 */
public class CommandLibraryChangedEvent extends BaseEvent {

    public final String aliasedKeyChanged;
    public final Hashtable<String, String> aliasTable;

    public CommandLibraryChangedEvent(String aliasedKeyChanged, Hashtable<String, String> aliasTable) {
        this.aliasedKeyChanged = aliasedKeyChanged;
        this.aliasTable = aliasTable;
    }

    @Override
    public String toString() {
        return aliasedKeyChanged;
    }
}
