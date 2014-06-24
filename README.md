# Shell

A console app mimicking a rudimentary shell.  Written to explore 
features of Google's [Guava](https://code.google.com/p/guava-libraries/).


## Prerequisites

* Java 1.7
* [Gradle](http://www.gradle.org/)


## Usage

```bash
cd /path/to/project

# Run via Gradle (NOTE: "TERM=dumb" suppresses Gradle's log noise)
TERM=dumb gradle run

# OR Compile and run
gradle distJar
java -jar build/libs/shell.jar
```

Type `help` to see a list of available commands.
