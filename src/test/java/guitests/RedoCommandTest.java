package guitests;

import org.junit.Test;

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

}
