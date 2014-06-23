package shell;

import java.io.IOException;

public class EchoCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) throws IOException {
        shell.println(args);
        return true;
    }

}
