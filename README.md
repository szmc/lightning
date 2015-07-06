## Lightning

[![Build status](https://api.travis-ci.org/automatictester/lightning.svg?branch=master)](https://travis-ci.org/automatictester/lightning)
[![Coverage Status](https://coveralls.io/repos/automatictester/lightning/badge.svg?branch=master)](https://coveralls.io/r/automatictester/lightning?branch=master)

## Current version

Current version: [0.2.0](https://github.com/automatictester/lightning/releases/download/0.2.0/lightning-0.2.0.jar)

## Introduction

Lightning's mission is to enable JMeter performance testing integration with Continuous Integration infrastructure. Lightning can instantly provide CI server with simple and meaningful information whether to pass or fail the build - with no human involvement needed.

## Why Lightning?

There are tools which can help you run JMeter tests as part of CI build, and tools which generate performance charts for execution results. Lightning goes one step further, letting you automatically pass or fail the build in your CI server.

## Philosophy

- Keep technology stack as close to JMeter as possible
- Be CI server- and OS- independent
- Using Lightning shouldn't require coding skills, as JMeter doesn't require that neither

## Design assumptions

- JMeter result files produced in CI environment should be small enought to be processed by Lightning and stored in memory without hacks

## Usage example

Lightning requires 2 sources of input data: XML config file and JMeter CSV output. XML file contains definition of tests, which will be executed to determine if execution should be marked as passed or failed, based on analysis of JMeter CSV output.
Types of tests which you can define in Lightning XML file are described on [wiki](https://github.com/automatictester/lightning/wiki/Test-Types).
 
- Lightning XML config file, e.g.:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<testSet xmlns="https://github.com/automatictester/lightning"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://github.com/automatictester/lightning https://github.com/automatictester/lightning/releases/download/0.2.0/lightning-0.2.0.xsd">
    <avgRespTimeTest>
        <testName>Test #1</testName>
        <description>Verify average login times</description>
        <transactionName>Login</transactionName>
        <maxAvgRespTime>4000</maxAvgRespTime>
    </avgRespTimeTest>
    <respTimeStdDevTest>
        <testName>Test #2</testName>
        <description>Verify standard deviation</description>
        <transactionName>Search</transactionName>
        <maxRespTimeStdDev>500</maxRespTimeStdDev>
    </respTimeStdDevTest>
    <passedTransactionsTest>
        <testName>Test #3</testName>
        <description>Verify number of passed tests</description>
        <transactionName>Login</transactionName>
        <allowedNumberOfFailedTransactions>0</allowedNumberOfFailedTransactions>
    </passedTransactionsTest>
</testSet>
```

- JMeter CSV output file, e.g.:

```
timeStamp,elapsed,label,responseCode,threadName,dataType,success,bytes,Latency
1434291247743,3514,Login,200,Thread Group 1-2,,true,444013,0
1434291247541,3780,Login,200,Thread Group 1-1,,true,444236,0
1434291247949,3474,Login,200,Thread Group 1-3,,true,444041,0
1434291248160,3448,Login,200,Thread Group 1-4,,true,444712,0
1434291248359,3700,Login,200,Thread Group 1-5,,true,444054,0
1434291251330,10769,Search,200,Thread Group 1-1,,true,1912363,0
1434291251624,10626,Search,200,Thread Group 1-4,,true,1912352,0
1434291251436,11086,Search,200,Thread Group 1-3,,true,1912321,0
1434291251272,11250,Search,200,Thread Group 1-2,,true,1912264,0
1434291252072,11221,Search,200,Thread Group 1-5,,true,1912175,0
```

To run Lightning:

`java -jar lightning-<version>.jar -xml=path/to/xml/file -csv=path/to/csv/file`

## FAQ

Q: Which Java version is required to run Lightning?

A: Java 7.

Q: Does Lightning support JMeter results in XML format?

A: No, only CSV format is supported.

Q: Which data JMeter must save to CSV result file?

A: First line should include CSV header with column names. Following 3 columns must be included: label, elapsed, success. All other columns may be present, but will be ignored by Lightning.

Q: Is Lightning XML config file associated with schema definition?

A: Yes, XML is associated with XSD.

Q: Can we omit XML schema validation?

A: Yes, but this is discouraged. XSD is here to help you ensure your config file is well formed to avoid problems at runtime. Don't disable the validation unless there is a bug which you are trying to work around, or you really know what you are doing.

Q: How to omit XML schema validation?

A: Add this command line parameter when running Lightning: `-skipSchemaValidation=true`

Q: How CI server can know if test execution was successful or not? Is it required to parse Lightning output?

A: CI servers make decision on build result based on its last process exit code. Lightning exits with code 0 if none of the defined tests failed, and non-zero (greater or equal 1) if any of the tests failed. Exit code is equal to number of failed tests. If for some reason you prefer to parse Lightning output, you can do that as well.

## Feature requests

To add a feature, open a pull request or log an [issue](https://github.com/automatictester/lightning/issues).

## Raising issues

To raise an issue, go to [issues](https://github.com/automatictester/lightning/issues). Do not raise defects via email or elsewhere.

## Roadmap

In next release (0.3.0) following features **will** be made available:

- Add Response time nth percentile test
- Get console help with `-h` or `--help` switch - [#1](https://github.com/automatictester/lightning/issues/1)
- Include test execution time in console output - [#11](https://github.com/automatictester/lightning/issues/11)
- Add option to run PassedTransactionsTest against all transaction names - [#4](https://github.com/automatictester/lightning/issues/4)
- Fix problem with XML schema, which required tests to be defined in particular order

Also some of the features listed in [issues](https://github.com/automatictester/lightning/issues) **may** be made available.
