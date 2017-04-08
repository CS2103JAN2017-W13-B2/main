package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;

public class RedoCommandTest extends TaskManagerGuiTest {

    TestTask[] expectedList = td.getTypicalTasks();
    TestTask[] currentList = expectedList;


    public void redo() {
        redoAdd();
    }

    /**
     * Tries to redo an add command
     */
    @Test
    public void redoAdd() {
        TestTask taskToAdd = td.alice;
        commandBox.runCommand(taskToAdd.getAddCommand());
        commandBox.runCommand(UndoCommand.COMMAND_WORD);
        expectedList = TestUtil.addTasksToList(expectedList, taskToAdd);
        assertRedoSuccess(currentList, expectedList);
    }

    /**
     * Runs redo command and checks whether the current list matches the expected list
     * @param currentList list before redo command is carried out
     * @param expectedList list after redo command is carried out
     */
    private void assertRedoSuccess(TestTask[] currentList, TestTask[] expectedList) {
        commandBox.runCommand(RedoCommand.COMMAND_WORD);
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(String.format(RedoCommand.MESSAGE_SUCCESS));
    }
}
