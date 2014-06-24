package shell;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class HelpCommand implements Command {
  
    private static final Joiner joiner = Joiner.on('\n');

    @Override
    public boolean eval(Shell shell, String args) throws IOException {
        List<String> cmds = Lists.newArrayList(shell.getCommands().keySet());
        Collections.sort(cmds);

        shell.println("The following commands are available:");
        shell.println(joiner.join(cmds));
        return true;
    }

}
