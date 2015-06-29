#!/bin/bash

java \
    -jar target/lightning*.jar \
    -xml src/test/resources/xml/1_1_1.xml \
    -csv src/test/resources/csv/10_transactions.csv

OUT=$?
if [ $OUT -eq 2 ];then
    echo "Exit code as expected - test passed"
    exit 0
else
    echo "Unexpected exit code - test failed"
    exit 1
fi