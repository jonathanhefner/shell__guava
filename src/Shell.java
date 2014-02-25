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
    private File workingDir = new File(StandardSystemProperty.USER_DIR.value());
    private String prompt = "> ";


    public void repl() {
        Scanner scanner = new Scanner(System.in);
        do {
            this.print(getPrompt());
        } while (scanner.hasNextLine() && this.eval(scanner.nextLine()));
    }


    public boolean eval(String input) {
        String[] parts = Iterables.toArray(INPUT_SPLITTER.split(input), String.class);

        if (parts.length >= 1) {
            Command command = commands.get(parts[0]);
            if (command == null) {
                this.error("unrecognized command");
                return true;
            } else {
                return command.eval(this, parts.length >= 2 ? parts[1] : "");
            }
        } else {
            return true;
        }
    }


    public void print(String s) {
        System.out.print(s);
        System.out.flush();
    }


    public void println(String s) {
        System.out.println(s);
    }


    public void error(String s) {
        this.print("ERROR: ");
        this.println(s);
    }


    public Map<String, Command> getCommands() {
        return commands;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

}
