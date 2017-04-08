//@@author A0138377U
package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClearCommandTest extends TaskManagerGuiTest {

    @Test
    public void clear() {

        //verify a non-empty list can be cleared
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
        assertClearCommandSuccess("all");

        //verify other commands can work after a clear command
        commandBox.runCommand(td.hoon.getAddCommand());
        assertTrue(taskListPanel.isListMatching(td.hoon));
        commandBox.runCommand("delete 1");
        assertListSize(0);

        //verify clear command works when the list is empty
        assertClearCommandSuccess("all");
    }

    @Test
    public void clearDone() {

        //prepare test
        commandBox.runCommand("mark 1");
        commandBox.runCommand("mark 2");
        commandBox.runCommand("mark 3");

        //verify done tasks can be cleared
        assertClearCommandSuccess("done");
        assertListSize(4);

    }

    private void assertClearCommandSuccess(String parameter) {
        commandBox.runCommand("clear " + parameter);
        if ("all".equals(parameter)) {
            assertListSize(0);
        } else {
            assertListSize(4);
        }
        assertResultMessage("Task Manager has been cleared!");
    }
}
