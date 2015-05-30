# mustech

[![Build Status](https://travis-ci.org/matobet/mustech.svg?branch=master)](https://travis-ci.org/matobet/mustech)

Project for PV243 JBoss

## Description
Built-in users:

    login/password:
    admin@admin.cz/admin
    user@user.cz/user

## Prerequisites

* maven
* wildfly 8.2.0+

## Installation

### standalone.xml

    <security-domain name="MustechDomain" cache-type="default">
        <authentication>
            <login-module code="Database" flag="required">
                <module-option name="multi-threaded" value="true"/>
                <module-option name="unauthenticatedIdentity" value="guest"/>
                <module-option name="dsJndiName" value="java:jboss/datasources/ExampleDS"/>
                <module-option name="principalsQuery" value="SELECT password FROM USERS WHERE email=?"/>
                <module-option name="rolesQuery" value="SELECT role, 'Roles' FROM USERS WHERE email=?"/>
                <module-option name="password-stacking" value="useFirstPass"/>
                <module-option name="hashAlgorithm" value="MD5"/>
                <module-option name="hashEncoding" value="hex"/>
                <module-option name="hashCharset" value="UTF-8"/>
                </login-module>
        </authentication>
    </security-domain>

### deployment

    mvn clean install
    wildfly:deploy

## Testing

When writing test we differentiate between regular JUnit tests and Arquillian integration tests.
The integration test class name should end in "IntegrationTest" and those tests will be skipped in 
regular run. To enable integration tests see Running Integration Tests.

### Running Unit Tests

    mvn test

### Running Integration Tests

    mvn test -Parq-wildfly-embedded

or if you have already running instance of Wildfly

    mvn test -Parq-wildfly-remote
