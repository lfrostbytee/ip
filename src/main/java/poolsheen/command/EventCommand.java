package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.Event;

/**
 * Represents an EventCommand which when executed will cause the Poolsheen program to
 * add one Event task for Poolsheen to remember.
 */
public class EventCommand extends Command {
    public EventCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "event", "Events need a description and time");
        }

        if (!rest.contains("/at")) {
            throw new PoolsheenException(String.join(" ", rest), "event", "event commands need an '/at'");
        }

        String descE = String.join(" ", rest.subList(0, rest.indexOf("/at")));
        String timeE = String.join(" ", rest.subList(rest.indexOf("/at") + 1, rest.size()));
        boolean isEmptyDesc = descE.length() == 0;
        boolean isEmptyTime = timeE.length() == 0;

        if (isEmptyDesc || isEmptyTime) {
            throw new PoolsheenException(String.join(" ", rest),
                    "event", "event commands must specify a description and time");
        }

        Event e = new Event(descE, false, timeE);
        tl.add(e);
        return ui.say("Poolsheen now remembers: " + descE);
    }
}
