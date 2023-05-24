@echo off
::javac *.java
javac -d ./bin/ ./src/solver/*.java
::java -cp bin/ solver/main %1 %2