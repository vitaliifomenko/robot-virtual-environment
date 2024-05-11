# robot-virtual-environment

The virtual environment for a robot where it can be controlled by a provided script.

## Requirements

Java 21

## Running Application

The application can be run from the command line or IDE.

### Linux

```bash
./gradlew bootRun
```

### Windows

```bash
gradlew.bat bootRun
```

### After startup, the application will be available via the URL:

```bash
http://localhost:8080/robot
```

### Script example

```bash
POSITION 1 3 EAST
FORWARD 3
WAIT
TURNAROUND
FORWARD 1
RIGHT
FORWARD 2
```