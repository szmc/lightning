#!/bin/bash

echo -e 'Run all Integration Tests\n'

mvn clean compile assembly:single

./src/test/scripts/console/output/report_10_0.sh
./src/test/scripts/console/output/verify_1_1_1.sh
./src/test/scripts/console/output/verify_3_0_0.sh
./src/test/scripts/exit/code/1_1_1.sh
./src/test/scripts/exit/code/3_0_0.sh