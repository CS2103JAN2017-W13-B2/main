//@@author A0143504R
package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.testutil.TestTask;

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

    /**
     * Tries to undo a delete command
     */
    @Test
    public void undoDelete() {
        int targetIndex = 1;
        commandBox.runCommand(DeleteCommand.COMMAND_WORD + " " + targetIndex);

        assertUndoSuccess(expectedList);
    }

    /**
     * Tries to undo a clear command
     */
    @Test
    public void undoClear() {
        commandBox.runCommand("mark 1");
        commandBox.runCommand("mark 2");
        commandBox.runCommand(ClearCommand.COMMAND_WORD + " done");

        expectedList[0].setStatus("done");
        expectedList[0].setStatus("done");
        assertUndoSuccess(expectedList);
    }

    /**
     * Tries to undo a mark command
     */
    @Test
    public void undoMark() {
        int targetIndex = 1;
        commandBox.runCommand(MarkCommand.COMMAND_WORD + " " + 1);

        expectedList[targetIndex - 1].setStatus("Done");
        assertUndoSuccess(expectedList);
    }

    /**
     * Tries to undo an edit command
     */
    @Test
    public void undoEdit() {
        int targetIndex = 1;
        String detailsToEdit = "Bobby";
        commandBox.runCommand(EditCommand.COMMAND_WORD + " " + targetIndex + " " + detailsToEdit);

        assertUndoSuccess(expectedList);
    }

    /**
     * Tries to undo a command with an invalid command word
     */
    @Test
    public void undoInvalidCommand() {
        commandBox.runCommand("undoo");
        assertResultMessage(String.format(Messages.MESSAGE_UNKNOWN_COMMAND));
    }

    /**
     * Tries to undo a command without any previous commands
     */
    @Test
    public void undoWithoutPreviousCommand() {
        commandBox.runCommand(UndoCommand.COMMAND_WORD);
        assertResultMessage(String.format(UndoCommand.MESSAGE_NO_CHANGE));
    }

    /**
     * Runs undo command and checks whether the current list matches the expected list
     * @param expectedList list after undo command is carried out
     */
    private void assertUndoSuccess(TestTask[] expectedList) {
        commandBox.runCommand(UndoCommand.COMMAND_WORD);
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(String.format(UndoCommand.MESSAGE_SUCCESS));

    }
}

