# Experiment 6 Jenkins with Maven

A Java 21 application compiled, tested and packaged by a Jenkins Freestyle job on Ubuntu EC2.

## Build

```sh
mvn --batch-mode clean package
java -cp target/classes HelloWorld
```

The Maven build runs a JUnit test of the application's printed greeting and creates `target/jenkins-lab-1.0-SNAPSHOT.jar`.

## Jenkins job

- Source: this Git repository, branch `*/main`.
- Build: Invoke top-level Maven targets, `--batch-mode clean package`.
- Build trigger: Poll SCM, `* * * * *` (checks for a new Git commit every minute).
- Post-build actions: publish `target/surefire-reports/*.xml` as JUnit results and archive `target/*.jar`.
- An additional shell step runs `java -cp target/classes HelloWorld` to show the application output.

Polling needs no public webhook endpoint. After the first build establishes a baseline, a new push to main is detected and built automatically.

The source uses Maven's standard `src/main/java` and `src/test/java` layout. Java 21 replaces the handout's outdated Java 11 Jenkins prerequisite.
