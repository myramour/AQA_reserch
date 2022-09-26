# Automation_Mariya_Kacemba

Start command **mvn versions:use-latest-versions** to update dependencies

[INFO] --- versions-maven-plugin:2.12.0:use-latest-versions (default-cli) @ Automation_Mariya_Kacemba ---
[INFO] Major version changes allowed


**mvn clean test** (запускает только те классы, названия которых содержат "Test")
Results :
Tests run: 19, Failures: 5, Errors: 0, Skipped: 4

**mvn -Dtest=Lesson7_1 test** (запуск класса с тестами)
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.414 sec

**mvn -Dtest=Lesson7_1,Lesson7_2 test** (запуск двух классов с тестами)
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.975 sec

**mvn -Dtest=Lesson5_2#test2 test** (запуск определенного теста из класса)
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.935 sec

**mvn -Dtest=Lesson5_2#test2+test3 test** (запуск двух определенных тестов из класса)
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.798 s - in lesson5.Lesson5_2

**mvn clean test -DsuiteXmlFile="Lesson_10"** (запуск определенного свита)
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.796 s - in TestSuite
