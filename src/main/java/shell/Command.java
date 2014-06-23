package shell;

import java.io.IOException;

public interface Command {
    boolean eval(Shell shell, String args) throws IOException;
}
