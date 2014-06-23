package shell;

import java.io.IOException;

public class ExitCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) throws IOException {
        return false;
    }

}
