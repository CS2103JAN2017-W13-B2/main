# A0143504R-reused
###### \java\seedu\address\logic\commands\FindCommand.java
``` java
    public Deadline createDeadline(String deadline) throws IllegalValueException {
        return (deadline == null ? new Deadline() : new Deadline(deadline));
    }
```
