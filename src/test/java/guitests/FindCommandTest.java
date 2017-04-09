package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.testutil.TestTask;

public class FindCommandTest extends TaskManagerGuiTest {

  //@@author A0143504R
    @Test
    public void findByKeywords() {
        assertFindResult(FindCommand.COMMAND_WORD + " Meier", td.benson, td.daniel); // find by name, multiple results
        assertFindResult(FindCommand.COMMAND_WORD + " friends", td.alice, td.benson); // find by tags, multiple results
        // find by description, multiple results
        assertFindResult(FindCommand.COMMAND_WORD + " find", td.alice, td.benson, td.fiona);

      // find after adding one task
        commandBox.runCommand(td.benson.getAddCommand());
        assertFindResult(FindCommand.COMMAND_WORD + " Meier", td.benson, td.daniel, td.benson);

      //find after deleting one result
        commandBox.runCommand("delete 3");
        assertFindResult(FindCommand.COMMAND_WORD + " Meier", td.benson, td.daniel);

        //find after editing one task
        int targetIndex = 2;
        String detailsToEdit = "Bobby";
        commandBox.runCommand(EditCommand.COMMAND_WORD + " " + targetIndex + " " + detailsToEdit);
        assertFindResult(FindCommand.COMMAND_WORD + " Meier", td.daniel);
    }

    @Test
    public void findByDeadline() {
        assertFindResult(FindCommand.COMMAND_WORD + " dl/10/10/2010", td.daniel);
        assertFindResult(FindCommand.COMMAND_WORD + " dl/11-11-2011", td.carl);
        assertFindResult(FindCommand.COMMAND_WORD + " dl/27 feb 2025", td.benson);
    }

  //@@author
    @Test
    public void find_emptyList() {
        commandBox.runCommand("clear");
        assertFindResult(FindCommand.COMMAND_WORD + " Jean"); // no results
    }

    @Test
    public void find_unknownCommand_fail() {
        commandBox.runCommand(FindCommand.COMMAND_WORD + "george");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    //@@author A0143504R
    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand(FindCommand.COMMAND_WORD + " Meier dl/10/10/2010");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
    //@@author

    private void assertFindResult(String command, TestTask... expectedHits) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
