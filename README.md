## Lightning

[![Build status](https://api.travis-ci.org/automatictester/lightning.svg?branch=master)](https://travis-ci.org/automatictester/lightning)
[![Coverage Status](https://coveralls.io/repos/automatictester/lightning/badge.svg?branch=master)](https://coveralls.io/r/automatictester/lightning?branch=master)

## Current version

Current version: [0.7.0](https://github.com/automatictester/lightning/releases/download/0.7.0/lightning-0.7.0.jar)

## Introduction

Lightning's mission is to enable JMeter performance testing integration with Continuous Integration infrastructure. Lightning can instantly provide CI server with simple and meaningful information whether to pass or fail the build - with no human involvement needed. Check our [wiki pages](https://github.com/automatictester/lightning/wiki) for more information.

## Why Lightning?

There are tools which can help you run JMeter tests as part of CI build, and tools which generate performance charts for execution results. Lightning goes one step further, letting you automatically pass or fail the build in your CI server.

## Philosophy

- Keep technology stack as close to JMeter as possible
- Be continuous integration server-independent and operating system-independent: Lightning should not be designed to run in particular environment only, but can offer extra features in certain environments
- Using Lightning shouldn't require coding skills, as JMeter doesn't require that neither
- Release changes frequently
- Do not provide bugfixes and support for old versions

## Design assumptions

- JMeter result files produced in CI environment should be small enough to be processed by Lightning and stored in memory without hacks

## Issues and feature requests

Issues and feature requests should be raised [here](https://github.com/automatictester/lightning/issues).

## Roadmap

Check milestones assigned to [issues](https://github.com/automatictester/lightning/issues) to get an idea what can be implemented when.
