package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;

public class RedoCommandTest extends TaskManagerGuiTest {

    TestTask[] expectedList = td.getTypicalTasks();

    public void redo() {
        redoAdd();

        redoDelete();

        redoClear();
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
        assertRedoSuccess(expectedList);
    }

    /**
     * Tries to redo a delete command
     */
    @Test
    public void redoDelete() {
        int targetIndex = 1;
        commandBox.runCommand(DeleteCommand.COMMAND_WORD + " " + targetIndex);
        commandBox.runCommand(UndoCommand.COMMAND_WORD);
        expectedList = TestUtil.removeTaskFromList(expectedList, targetIndex);
        assertRedoSuccess(expectedList);
    }

    /**
     * Tries to redo a clear command
     */
    @Test
    public void redoClear() {
        commandBox.runCommand("mark 1");
        commandBox.runCommand("mark 2");
        commandBox.runCommand(ClearCommand.COMMAND_WORD + " done");
        commandBox.runCommand(UndoCommand.COMMAND_WORD);

        expectedList = TestUtil.removeTaskFromList(expectedList, 1);
        expectedList = TestUtil.removeTaskFromList(expectedList, 1);
        assertRedoSuccess(expectedList);
    }

    /**
     * Runs redo command and checks whether the current list matches the expected list
     * @param currentList list before redo command is carried out
     * @param expectedList list after redo command is carried out
     */
    private void assertRedoSuccess(TestTask[] expectedList) {
        commandBox.runCommand(RedoCommand.COMMAND_WORD);
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(String.format(RedoCommand.MESSAGE_SUCCESS));
    }
}
