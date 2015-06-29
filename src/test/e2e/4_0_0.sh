#!/bin/bash

java \
    -jar target/lightning*.jar \
    -xml src/test/resources/xml/4_0_0.xml \
    -csv src/test/resources/csv/10_transactions.csv

OUT=$?
if [ $OUT -eq 0 ];then
    echo "Exit code as expected - test passed"
    exit 0
else
    echo "Unexpected exit code - test failed"
    exit 1
fi