//@@author A0143504R
package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;

/**
 * Finds by keywords or deadline.
 * Lists all tasks in task manager whose name, description or tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 * Lists all tasks in address book whose deadline coincides with specified deadline
 * Tasks whose time period contains specified deadline will be listed.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks either by keyword or deadline.\n"
            + "Tasks whose names, description or tags contain any of the specified keywords (case-sensitive) "
            + "are displayed as a list with index numbers.\n"
            + "Tasks who deadline or time period includes specified deadline "
            + "are displayed as a list with index numbers.\n"
            + "Parameters: [KEYWORDS]|[dl/DEADINE]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " dl/today\n";

    private final Set<String> keywords;
    private final Deadline deadline;

    public FindCommand(String keywords, String deadline)
            throws IllegalValueException {
        final Set<String> keywordSet = new HashSet<>();
        if (!"".equals(keywords)) {
            StringTokenizer st = new StringTokenizer(keywords, " ");
            while (st.hasMoreTokens()) {
                keywordSet.add(st.nextToken());
            }
        }
        this.keywords = keywordSet;
        this.deadline = createDeadline(deadline);
    }

    @Override
    public CommandResult execute() {
        if (!keywords.isEmpty()) {
            model.updateFilteredTaskListByKeywords(keywords);
        } else if (deadline != null) {
            model.updateFilteredTaskListByDate(deadline);
        }
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

    //@@author A0143504R-reused
    public Deadline createDeadline(String deadline) throws IllegalValueException {
        return (deadline == null ? new Deadline() : new Deadline(deadline));
    }
    //@@author

}
