# mustech

[![Build Status](https://travis-ci.org/matobet/mustech.svg?branch=master)](https://travis-ci.org/matobet/mustech)

Project for PV243 JBoss

# Testing

When writing test we differentiate between regular JUnit tests and Arquillian integration tests.
The integration test class name should end in "IntegrationTest" and those tests will be skipped in 
regular run. To enable integration tests see Running Integration Tests.

## Running Unit Tests

    mvn test

## Running Integration Tests

    mvn test -Parq-wildfly-embedded

or if you have already running instance of Wildfly

    mvn test -Parq-wildfly-remote
