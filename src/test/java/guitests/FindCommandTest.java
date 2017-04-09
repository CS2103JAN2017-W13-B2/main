package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.testutil.TestTask;

public class FindCommandTest extends TaskManagerGuiTest {

    @Test
    public void find_nonEmptyList() {
        assertFindResult("find Mark"); // no results
        assertFindResult("find Meier", td.benson, td.daniel); // multiple results

        //find after deleting one result
        commandBox.runCommand("delete 1");
    public void find_byKeywords() {
        assertFindResult("find Meier", td.benson, td.daniel); // find by name, multiple results
        assertFindResult("find friends", td.alice, td.benson); // find by tags, multiple results
        assertFindResult("find find", td.alice, td.benson, td.fiona); // find by description, multiple results

      // find after adding one task
        commandBox.runCommand(td.benson.getAddCommand());
        assertFindResult("find Meier", td.benson, td.daniel, td.benson);

      //find after deleting one result
        commandBox.runCommand("delete 3");
        assertFindResult("find Meier", td.benson, td.daniel);

        //find after editing one task
        int targetIndex = 2;
        String detailsToEdit = "Bobby";
        commandBox.runCommand(EditCommand.COMMAND_WORD + " " + targetIndex + " " + detailsToEdit);
        assertFindResult("find Meier", td.daniel);
    }

    @Test
    @Test
    public void find_emptyList() {
        commandBox.runCommand("clear");
        assertFindResult("find Jean"); // no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("findgeorge");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertFindResult(String command, TestTask... expectedHits) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
