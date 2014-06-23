package shell;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.io.File;

public enum FileCheck implements Predicate<File> {

    EXISTS("invalid path") {
        @Override
        public boolean apply(File file) {
            return file.exists();
        }
    },

    IS_DIR("invalid directory") {
        @Override
        public boolean apply(File file) {
            return file.isDirectory();
        }
    },

    IS_FILE("invalid file") {
        @Override
        public boolean apply(File file) {
            return file.isFile();
        }
    },

    CAN_READ("insufficient read permissions") {
        @Override
        public boolean apply(File file) {
            return file.canRead();
        }
    },

    CAN_WRITE("insufficient write permissions") {
        @Override
        public boolean apply(File file) {
            return file.canWrite();
        }
    };


    private final String errorMessage;

    FileCheck(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    /* would be nice as a static util method in com.google.common.base.Predicates */
    public static Optional<FileCheck> firstFalse(File file, FileCheck... checks) {
        for (FileCheck c : checks) {
            if (!c.apply(file)) {
                return Optional.of(c);
            }
        }
        return Optional.absent();
    }

}
