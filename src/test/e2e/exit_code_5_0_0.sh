#!/bin/bash

mkdir -p src/test/resources/results/actual/

java \
    -jar target/lightning*.jar \
    -xml src/test/resources/xml/5_0_0.xml \
    -csv src/test/resources/csv/10_transactions.csv \
    > src/test/resources/results/actual/5_0_0.txt
OUT=$?

echo "EXIT CODE TEST"
if [ $OUT -eq 0 ];then
    echo "EXIT CODE = $OUT"
    echo "TEST PASSED"
    exit 0
else
    echo "EXIT CODE = $OUT"
    echo "TEST FAILED"
    exit 1
fi