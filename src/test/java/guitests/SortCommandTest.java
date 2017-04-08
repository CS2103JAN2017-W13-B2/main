package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.testutil.TestTask;

// TODO: reduce GUI tests by transferring some tests to be covered by lower level tests.
public class SortCommandTest extends TaskManagerGuiTest {

    // The list of tasks in the task list panel is expected to match this list.
    // This list is updated with every successful call to assertMarkSuccess().
    TestTask[] testTasksList = td.getTypicalTasks();

    @Test
    public void sortByDeadline() {

        //sort the list according to deadline
        assertSortSuccess("deadline", testTasksList);

        //mark the first in the list from Done to Undone
        testTasksList = td.getUnsortedTasks();
        assertSortSuccess("name", testTasksList);

    }

    @Test
    public void sort_invalidParameter_failure() {
        commandBox.runCommand("sort 8 Bobby");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    /**
     * Checks whether the edited task has the correct updated details.
     *
     * @param filteredTaskListIndex index of task to edit in filtered list
     * @param taskManagerIndex index of task to edit in the address book.
     *      Must refer to the same task as {@code filteredTaskListIndex}
     * @param detailsToMark details to edit the task with as input to the edit command
     * @param editedTask the expected task after editing the task's details
     */
    private void assertSortSuccess(String sortParameter, final TestTask[] currentList) {

        commandBox.runCommand("sort " + sortParameter);

        TestTask[] expectedList = null;

        if (sortParameter.equals("deadline")) {
            expectedList = td.getSortedByDeadlineTasks();
        } else if (sortParameter.equals("name")) {
            expectedList = td.getTypicalTasks();
        }

        assertTrue(taskListPanel.isListMatching(expectedList));
        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_SUCCESS + " by " + sortParameter + "."));

    }
}
