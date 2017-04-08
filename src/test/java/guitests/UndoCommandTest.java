package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;

public class UndoCommandTest extends TaskManagerGuiTest {

    private TestTask[] expectedList = td.getTypicalTasks();

    /**
     * Tries to undo an add command
     */
    @Test
    public void undoAdd() {
        TestTask taskToAdd = td.alice;
        commandBox.runCommand(taskToAdd.getAddCommand());
        assertUndoSuccess(expectedList);
    }

    private void assertUndoSuccess(TestTask[] expectedList) {
        commandBox.runCommand(UndoCommand.COMMAND_WORD);
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(String.format(UndoCommand.MESSAGE_SUCCESS));

    }

}

