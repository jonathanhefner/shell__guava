import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.Iterables;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shell {

    public static void main(String[] args) throws Exception {
        Shell shell = new Shell();

        shell.getCommands().put("exit", new ExitCommand());
        shell.getCommands().put("echo", new EchoCommand());
        shell.getCommands().put("pwd", new PwdCommand());

        shell.repl();
    }

    private final static Splitter INPUT_SPLITTER = Splitter.on(CharMatcher.WHITESPACE).limit(2).omitEmptyStrings();


    private final Map<String, Command> commands = new HashMap<>();
    private File currentWorkingDir = new File(StandardSystemProperty.USER_DIR.value());
    private String prompt = "> ";


    public void repl() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(getPrompt());
            System.out.flush();
        } while (scanner.hasNextLine() && eval(scanner.nextLine()));
    }


    public boolean eval(String input) {
        String[] parts = Iterables.toArray(INPUT_SPLITTER.split(input), String.class);

        if (parts.length >= 1) {
            Command command = commands.get(parts[0]);
            if (command == null) {
                System.out.println("ERROR: unrecognized command");
                return true;
            } else {
                return command.eval(this, parts.length >= 2 ? parts[1] : "");
            }
        } else {
            return true;
        }
    }


    public Map<String, Command> getCommands() {
        return commands;
    }

    public File getCurrentWorkingDir() {
        return currentWorkingDir;
    }

    public void setCurrentWorkingDir(File currentWorkingDir) {
        this.currentWorkingDir = currentWorkingDir;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

}
