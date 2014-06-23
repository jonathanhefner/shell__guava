package shell;

import java.io.IOException;

public class PwdCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) throws IOException {
        shell.println(shell.getWorkingDir().getAbsolutePath());
        return true;
    }

}
