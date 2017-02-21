# go-to-prague

A simple test of sorting journeys by price and a wiki with a test plan for testing a page on travelling site
### Technologies
In order to run the test in this repository, you need to have Java and Maven installed.
### Run
Open a console and clone the repostitory
```
git clone git@github.com:ninna/go-to-prague.git
```
Change directory into the root directory of the project, where you see the pom.xml file
```
cd go-to-prague
```
Run the test
```
mvn test
```
In the console, you will see the output from the test.
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running goeurolab.search.sort.SortJourneyByPriceTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.85 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
The test reports are generated in target/surefire-reports
```
less target/surefire-reports/TEST-goeurolab.search.sort.SortJourneyByPriceTest.xml
less target/surefire-reports/goeurolab.search.sort.SortJourneyByPriceTest.txt
```
