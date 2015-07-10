#!/bin/bash

mkdir -p src/test/resources/results/actual/

EXPECTED_RESULT="src/test/resources/results/expected/help.txt"
ACTUAL_RESULT="src/test/resources/results/actual/help.txt"

java \
    -jar target/lightning*.jar \
    -h \
    > $ACTUAL_RESULT

DIFF_OUTPUT=`diff $EXPECTED_RESULT $ACTUAL_RESULT`
OUT=$?

echo "CONSOLE HELP OUTPUT TEST"
if [ $OUT -eq 0 ];then
    echo "HELP OUTPUT AS EXPECTED"
    echo "TEST PASSED"
    exit 0
else
    echo "INCORRECT CONSOLE HELP OUTPUT - DIFF:"
    echo $DIFF_OUTPUT
    echo "TEST FAILED"
    exit 1
fi