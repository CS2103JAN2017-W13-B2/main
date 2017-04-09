//@@author A0144885R
package seedu.address.commons.events.ui;

import java.util.List;

import seedu.address.commons.events.BaseEvent;

/**
 * Indicates a request to change current view
 */
public class ChangeViewRequestEvent extends BaseEvent {

    public final List<String> viewGroups;

    public ChangeViewRequestEvent(List<String> viewGroups) {
        this.viewGroups = viewGroups;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
