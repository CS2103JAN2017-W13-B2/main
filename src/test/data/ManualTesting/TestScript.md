# Docket - Test Script

* In the same folder location of Docket.jar, create a folder named data.
* Rename the sample data file from SampleData.xml to taskmanager.xml
* Put sample data file, taskmanager.xml into the new data\ folder.
* Double click Docket.jar to start the application with sample data.
* Docket will create six sample tasks if the data file is not detected.
 
## Keyboard hotkeys

When the focus is on the text box, the following key presses will result in appropriate actions in Docket.

Key press: `alt` + `1`  
Outcome: Expand the “Done” category and collapse the “Unfinished”.

Key press: `alt` + `2`  
Outcome: Expand the “Unfinished” category and collapse the “Done”.

Key press: `alt` + `3`  
Outcome: At this point, nothing will happen.

Key press: `alt` + `4`  
Outcome: At this point, nothing will happen

Key press: `alt` + `5`  
Outcome: At this point, nothing will happen

Key press: `Page Down`  
Outcome: Scrolls down the list of tasks.

Key press: `Page Up`  
Outcome: Scrolls up the list of tasks.

Key press: `Up`, `Down`
Outcome: Show previously typed command. Since no command has been entered so far, nothing will happen.


## Help
This command will load the User Guide for Docket. 

`help`

## Find and List
Find will filter the list of tasks for the parameter specified.
List will remove the filter and list all tasks.

Command: `find important`
Outcome: Four tasks with the specific word `important` in the name, description or tag will be listed.

Command: `list`
Outcome: All tasks will be listed.

Command: `find dl/today`
Outcome: There will be at least one task with name “test task long time period”. More than one will appear based on when this command is executed. 

Command: `list`
Outcome: All tasks will be listed.

Command: `find party`
Outcome: All tasks with the specific word `party` in the name or description will be listed. Which is one task.

Command: `list`
Outcome: All tasks will be listed.

## View

Command: `view`
Outcome: This there should not be any changes as the view of Docket is already at the default view. Which is two categories “Done” and “Unfinished” with tasks marked as done under “Done” and tasks which are not done under “Unfinished”.

Command: `view all`
Outcome: Only one category of task should be on the view of Docket called “All” showing all tasks, done and unfinished.

Command: `view calendar
Outcome: Five categories of tasks should be on the view of Docket called “Floating”, “Overdue”, “Today”, “Tomorrow” and “Future”. Tasks should be sorted according to their deadlines to five of the categories. 

At this point, you can test the Keyboard hotkeys `alt` + `1` to `alt` + `5`.

Command: `view today tomorrow`
Outcome: Two category of tasks should be on the view of Docket called “Today” and “Tomorrow” with tasks with deadlines due/starting today and tomorrow.

Command: `view floating overdue`
Outcome: Two category of tasks should be on the view of Docket called “Floating” and “Overdue” with tasks with no deadlines and deadlines ended before the command is executed.

Command: `view`
Outcome: This there should not be any changes as the view of Docket is already at the default view. Which is two categories “Done” and “Unfinished” with tasks marked as done under “Done” and tasks which are not done under “Unfinished”. This is to proceed to the next command.

## Add

Command: `add test task 1`
Outcome: Adds a task with name “test task 1” with no description, no deadline and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.

Command: `add test task 2 ds/this is a description`
Outcome: Adds a task with name “test task 2” with description “this is a description” but no deadline and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.

Command: `add test task 3 dl/today`
Outcome: Adds a task with name “test task 3” with deadline the day this command is executed but no description  and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.

Command: `add test task 4 dl/tomorrow`
Outcome: Adds a task with name “test task 4” with deadline the day after this command is executed but no description  and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.

Command: `add test task 5 dl/next week`
Outcome: Adds a task with name “test task 5” with deadline the week after this command is executed but no description  and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.


Command: `add test task 6 dl/next year`
Outcome: Adds a task with name “test task 6” with deadline the year after this command is executed but no description  and no tags to the list of tasks. The list of task should be scrolled all the way down to the bottom of the list. The next available index number will be assigned to it. Task status will be set automatically.


## Edit
Command: `edit 6 dl/next tuesday`
Outcome: Changes the deadline of task with index number 6 to next Tuesday. If task status is currently done, it will remain as done, otherwise, it will be updated according to the new deadline. Task description, title and tags will not be affected. The task list will not scroll or expand itself after this command is executed.

Command: `edit 6 ds/new description`
Outcome: Changes the description of task with index number 6. Task deadline, title and tags will not be affected. The task list will not scroll or expand itself after this command is executed.

Command: `edit 6 t/tag1 t/tag2 t/tag3`
Outcome: Changes the tags of task with index number 6, the old tag list will be replaced with the new one. Task description, title and deadline will not be affected. The task list will not scroll or expand itself after this command is executed.

Command: `edit 6 t/`
Outcome: Clears the tag list of task with index number 6. Task description, title and deadline will not be affected. The task list will not scroll or expand itself after this command is executed.


## Delete

Command: `delete 1`
Outcome: Removes the task with index number 1 from the list of tasks.

## Undo

Command: `undo`
Outcome: undo the previous command which made changes to the data storage file. If the commands were entered in sequence, the task that was deleted with `delete 1` will be added back to the list.

## Redo

Command: `redo`
Outcome: redo the changes made by the `undo` command. If used in sequence, will once again remove the task with index number 1.

## Mark

Command: `mark 1`
Outcome: Changes the Status of the task with index number 1 to “Done”. The task should be moved to the “Done” category in the view of the Docket.

Command: `mark 1`
Outcome: Changes the Status of the task with index number 1 back to the original status. The task should be moved to the “Unfinished” category in the view of the Docket.

## Clear

Command: `clear done`
Outcome: Deletes all tasks with the Status “Done” from the list.

Command: `undo`
Outcome: Returns all the tasks deleted by the previous command for further testing.

Command: `clear all`
Outcome: Deletes all tasks from the list.

Command: `undo`
Outcome: Returns all the tasks deleted by the previous command for further testing.

## Sort

Command: `sort name`
Outcome: Sorts the list currently on the view of Docket according to the name in alphabetical order. The tasks stay within their category.

Command: `sort deadline`
Outcome: Sorts the list currently on the view of Docket according to the deadline in chronological order with tasks with no deadline at the top follow by the tasks with the deadlines furthest in the future. The tasks with deadline furthest into the past will be at the bottom.

## Set Storage

FOLDER_PATH = folder_the_jar_file_is_in + \data
Command: `set-storage <FOLDER_PATH>`
Outcome: The current Docket application closes and a new Docket with a NEW data storage file “task_manager.xml” created in the input FOLDER_PATH since the filename is not entered. 

FOLDER_PATH = folder_the_jar_file_is_in + \data\taskmanager.xml
Command: `set-storage <FOLDER_PATH>`
Outcome: The current Docket application closes and a new Docket with the sample list of task provided.


## Exit

Command: `exit`
Outcome: Closes Docket
