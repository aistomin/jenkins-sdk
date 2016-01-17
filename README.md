[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)

# jenkins-sdk

## Purpose

This project is objective oriented Java SDK for 
[Jenkins XML API](https://wiki.jenkins-ci.org/display/JENKINS/Remote+access+API) 
that is going to help developers to manipulate Jenkins instance using it's API.
This SDK can be useful for developers who creates CI scripts or other environment
software that needs to read information of Jenkins, trigger builds etc.

## How to contribute?

Fork the repository, make changes, submit a pull request.
We promise to review your changes same day and apply to
the `master` branch, if they look correct.

Please run Maven (3.1 or higher!) build before submitting a pull request:

```
$ mvn clean install -Pqulice
```

or

```
$ mvn clean install package
```
