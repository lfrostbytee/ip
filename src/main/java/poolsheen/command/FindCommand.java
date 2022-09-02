package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a Find Command that when executed by the Poolsheen program
 * prints out all the available tasks that matches the keyword.
 */
public class FindCommand extends Command {
    public FindCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.size() != 1) {
            throw new PoolsheenException(String.join(" ", rest),
                    "find", "The find command only accepts one word");
        } else {
            TaskList matchedTasksArray = tl.find(rest.get(0));
            return ui.displayList(matchedTasksArray);
        }
    }
}
