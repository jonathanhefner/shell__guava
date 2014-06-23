package shell;

import com.google.common.base.Optional;

import java.io.File;
import java.io.IOException;

public class CdCommand implements Command {

    @Override
    public boolean eval(Shell shell, String args) throws IOException {
        Optional<File> dir = shell.checkFile(args, FileCheck.EXISTS, FileCheck.IS_DIR, FileCheck.CAN_READ);

        if (dir.isPresent()) {
            shell.setWorkingDir(dir.get());
        }

        return true;
    }

}
