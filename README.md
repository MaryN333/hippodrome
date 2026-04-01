# Hippodrome Simulation. Testing and Logging

## Project Description

The project started with an existing codebase that is a console-based simulation of a horse race (hippodrome). The program creates a list of horses, simulates a race
by repeatedly calling the `move()` method on each horse, and finally determines the winner based on the distance
covered.

The primary goal of this project was to add **unit testing** using JUnit 5 and Mockito, as well as
implement **logging** using Log4j 2 with file rotation and automatic cleanup.

---

## Technologies

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Maven | Build automation and dependency management |
| JUnit 5 | Unit testing framework |
| Mockito | Mocking framework for static method testing |
| Log4j 2 | Logging framework with rolling file appender |

---

## Project Structure
```
hippodrome/
├── src/
│   ├── main/
│   │  ├── java/
│   │  │   └── cz/wz/marysidy/hippodrome/
│   │  │      ├── Main.java
│   │  │      ├── Horse.java
│   │  │      └── Hippodrome.java
│   │  └── resources/
│   │       └── log4j2.xml
│   └── test/
│       └── java/
│           └── cz/wz/marysidy/hippodrome/
│              ├── HorseTest.java
│              ├── HippodromeTest.java
│              └── MainTest.java
├── logs/
│   └── hippodrome.log (generated at runtime)
├── pom.xml
└── README.md
```

---

## How to Run

### Build the project
```bash
mvn clean compile
```
### Run the application
```bash
mvn exec:java -Dexec.mainClass="cz.wz.marysidy.hippodrome.Main"
```
Or run Main.java directly from your IDE.

## Testing
## Run all tests
```bash
mvn test
```

## Test Coverage

### Horse Class
- Constructor validation (null name, blank name, negative speed, negative distance)
- Getters (`getName`, `getSpeed`, `getDistance`)
- `move()` method with mocked static `getRandomDouble()`

### Hippodrome Class
- Constructor validation (null list, empty list)
- `getHorses()` returns unmodifiable list with correct order
- `move()` calls `move()` on all horses (verified with mocks)
- `getWinner()` returns horse with maximum distance

### Main Class
- Timeout test (disabled by default, runs in ~22 seconds)

## Logging
Logs are written using Log4j 2 with the following configuration:
### Log File Location
- Directory: `logs/` at project root
- Current log file: `logs/hippodrome.log`

### Log Rotation
- Daily rotation: `hippodrome.YYYY-MM-DD.log`
- New `hippodrome.log` created each day
- Files older than 7 days are automatically deleted

### Log Format
```text
2026-04-01 09:50:32,040 DEBUG cz.wz.marysidy.hippodrome.Horse     : Creating Horse, name [Bucephalus], speed [2.4]
```
### Log Levels Used
| Level | Usage |
|-------|-------|
| `ERROR` | Validation failures (null, empty, negative values) |
| `INFO` | Race start and finish events |
| `DEBUG` | Object creation details (Horse, Hippodrome) |

### Example Log Output
```text
2026-04-01 09:50:32,043 DEBUG cz.wz.marysidy.hippodrome.Horse     : Creating Horse, name [Cherry], speed [3.0]
2026-04-01 09:50:32,043 DEBUG cz.wz.marysidy.hippodrome.Hippodrome: Hippodrome created, horses count [7]
2026-04-01 09:50:32,043 INFO  cz.wz.marysidy.hippodrome.Main      : Race start. Participants: 7
2026-04-01 09:50:32,658 INFO  cz.wz.marysidy.hippodrome.Main      : Race finished. Winner: Lobster
```
---
## Original Project Assignment
## Project Description

The project started with an existing codebase that simulates a horse race (hippodrome). The goal is to extend it by adding:
- **Unit testing** for all classes using JUnit 5 and Mockito
- **Logging** with Log4j 2, including file rotation and 7-day retention

## Test Requirements
Each requirement below should be implemented as a separate test method. Method names should be concise yet descriptive enough to understand what is being tested.

## Test Requirements
Each requirement below should be implemented as a separate test method. Method names should be concise yet descriptive enough to understand what is being tested.
### 1. Horse Class

#### Constructor
- Verify that passing `null` as the first parameter throws `IllegalArgumentException`.
- Verify that when passing `null` as the first parameter, the exception message is `"Name cannot be null."`.
- Verify that passing an empty string or a string containing only whitespace (space, tab, etc.) as the first parameter throws `IllegalArgumentException`.  
  *Use a parameterized test to cover different whitespace variations.*
- Verify that when passing an empty or whitespace-only string as the first parameter, the exception message is `"Name cannot be blank."`.
- Verify that passing a negative number as the second parameter throws `IllegalArgumentException`.
- Verify that when passing a negative speed, the exception message is `"Speed cannot be negative."`.
- Verify that passing a negative number as the third parameter throws `IllegalArgumentException`.
- Verify that when passing a negative distance, the exception message is `"Distance cannot be negative."`.

#### `getName()` Method
- Verify that the method returns the string passed as the first parameter to the constructor.

#### `getSpeed()` Method
- Verify that the method returns the number passed as the second parameter to the constructor.

#### `getDistance()` Method
- Verify that the method returns the number passed as the third parameter to the constructor.
- Verify that the method returns `0` when the object is created using the constructor with two parameters.

#### `move()` Method
- Verify that the method calls `getRandomDouble(0.2, 0.9)`.  
  *Use `MockedStatic` and its `verify` method.*
- Verify that the method assigns the distance according to the formula:  
  `distance + speed * getRandomDouble(0.2, 0.9)`.  
  *Mock `getRandomDouble` to return specific values and use a parameterized test.*
---

### 2. Hippodrome Class
#### Constructor
- Verify that passing `null` to the constructor throws `IllegalArgumentException`.
- Verify that when passing `null`, the exception message is `"Horses cannot be null."`.
- Verify that passing an empty list to the constructor throws `IllegalArgumentException`.
- Verify that when passing an empty list, the exception message is `"Horses cannot be empty."`.

#### `getHorses()` Method
- Verify that the method returns a list containing the same objects in the same order as the list passed to the constructor.  
  *Create a `Hippodrome` object with a list of 30 different horses.*

#### `move()` Method
- Verify that the method calls `move()` on all horses.  
  *Pass a list of 50 mock horses to the constructor and use `verify`.*

#### `getWinner()` Method
- Verify that the method returns the horse with the largest `distance` value.
---

### 3. Main Class
#### `main()` Method
- Verify that the method executes within 22 seconds.  
  *Use the `@Timeout` annotation. Disable this test with `@Disabled` so it doesn't run during normal test execution, but can be run manually when needed.*
---

## Logging Requirements
### 1. Main Class
- After creating the hippodrome object, add a log entry:  
  `2025-05-31 17:05:26,152 INFO Main: Race start. Participants: 7`

- After displaying the winner information, add a log entry:  
  `2025-05-31 17:05:46,963 INFO Main: Race finished. Winner: Cherry`

### 2. Hippodrome Class
- If `null` is passed to the constructor, before throwing the exception, add a log entry:  
  `2025-05-31 17:29:30,029 ERROR Hippodrome: Horses list is null`

- If an empty list is passed to the constructor, before throwing the exception, add a log entry:  
  `2025-05-31 17:30:41,074 ERROR Hippodrome: Horses list is empty`

- At the end of the constructor, add a log entry:  
  `2022-05-31 17:05:26,152 DEBUG Hippodrome: Hippodrome created, horses [7]`

### 3. Horse Class
- If `null` is passed as the name parameter, before throwing the exception, add a log entry:  
  `2025-05-31 17:34:59,483 ERROR Horse: Name is null`

- If an empty or whitespace string is passed as the name, before throwing the exception, add a log entry:  
  `2025-05-31 17:36:44,196 ERROR Horse: Name is blank`

- If a negative speed is passed, before throwing the exception, add a log entry:  
  `2025-05-31 17:40:27,267 ERROR Horse: Speed is negative`

- If a negative distance is passed, before throwing the exception, add a log entry:  
  `2025-05-31 17:41:21,938 ERROR Horse: Distance is negative`

- At the end of the constructor, add a log entry:  
  `2025-05-31 17:15:25,842 DEBUG Horse: Creating Horse, name [Lobster], speed [2.8]`

---

### Log File Configuration
Logs must be written to a file named `hippodrome.log` located in the `logs` folder at the root of the project.

- **Daily rotation:** Each day, the file should be renamed following the pattern `hippodrome.YYYY-MM-DD.log` (e.g., `hippodrome.2021-12-31.log`), and a new `hippodrome.log` file should be created.

- **Retention:** Log files older than 7 days must be automatically deleted.

- **Implementation:** Use the `RollingFile` appender with appropriate configuration.