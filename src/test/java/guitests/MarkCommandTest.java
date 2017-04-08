//@@author A0138377U
package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.MarkCommand.MESSAGE_MARK_TASK_SUCCESS;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.testutil.TestTask;

// TODO: reduce GUI tests by transferring some tests to be covered by lower level tests.
public class MarkCommandTest extends TaskManagerGuiTest {



    @Test
    public void mark() {
        // The list of tasks in the task list panel is expected to match this list.
        // This list is updated with every successful call to assertMarkSuccess().
        TestTask[] expectedTasksList = td.getTypicalTasks();

        //mark the first in the list from Undone to Done
        int targetIndex = 1;
        assertMarkSuccess(targetIndex, expectedTasksList);

        //mark the first in the list from Done to Undone
        expectedTasksList[targetIndex - 1].setStatus("Done");
        assertMarkSuccess(targetIndex, expectedTasksList);

        //mark the last in the list from Undone to Done
        targetIndex = expectedTasksList.length;
        assertMarkSuccess(targetIndex, expectedTasksList);

        //mark the last in the list from Done to Undone
        expectedTasksList[targetIndex - 1].setStatus("Done");
        assertMarkSuccess(targetIndex, expectedTasksList);

        //mark from the middle of the list from Undone to Done
        targetIndex = expectedTasksList.length / 2;
        assertMarkSuccess(targetIndex, expectedTasksList);

        //mark from the middle of the list from Done to Undone
        expectedTasksList[targetIndex - 1].setStatus("Done");
        assertMarkSuccess(targetIndex, expectedTasksList);

        //invalid index
        commandBox.runCommand("mark " + expectedTasksList.length + 1);
        assertResultMessage("The task index provided is invalid");

    }

    @Test
    public void markMissingTaskIndexFailure() {
        commandBox.runCommand("mark Bobby");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void markInvalidTaskIndexFailure() {
        commandBox.runCommand("mark 8 Bobby");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
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
    private void assertMarkSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToMark = new TestTask(currentList[targetIndexOneIndexed - 1]); // -1 as array uses zero indexing
        //TestTask[] expectedRemainder = TestUtil.removeTaskFromList(currentList, targetIndexOneIndexed);

        commandBox.runCommand("mark " + targetIndexOneIndexed);

        // confirm the new card contains the right data
        TaskCardHandle markedCard = taskListPanel.getTaskCardWithID(targetIndexOneIndexed);
        assertMatching(taskToMark, markedCard);

        //confirm the list now contains all previous tasks except the deleted task
        assertTrue(markedCard.getStatus().equals("Done")  ?
                !taskToMark.getStatus().status.equals("Done") :
                    taskToMark.getStatus().status.equals("Done"));

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_MARK_TASK_SUCCESS, markedCard.getAsText()));
    }
}
