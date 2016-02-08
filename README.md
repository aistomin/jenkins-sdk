[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)
[![Dependencies](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c)

# JENKINS-SDK

## Purpose

This project is objective oriented Java SDK for 
[Jenkins XML API](https://wiki.jenkins-ci.org/display/JENKINS/Remote+access+API) 
that is going to help developers to manipulate Jenkins instance using it's API.
This SDK can be useful for developers who creates CI scripts or other environment
software that needs to read information from Jenkins, trigger builds etc.

Using classes from `org.rising.jenkins` and `org.rising.jenkins.real` you can 
manipulate with existing Jenkins installation. For example:
```java
public class Main {
    public static void main(String[] args) throws Exception {
        Jenkins jenkins = new RealJenkins(
            "<Jenkins URL>",
            new UsernamePasswordCredentials("<USERNAME>", "<PASSWORD>")
        );
        Iterator<User> users = jenkins.users().iterator();
        while (users.hasNext()) {
            User user = users.next();
            System.out.println(
                "username: " + user.username() + ", email: " + user.email()
            );
        }
    }
}
```

Using classes from `org.rising.jenkins.fake` you have possibility to create
stubs/fakes of Jenkins installation.

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

## Jenkins Instance for Integration tests

We have running instance at https://cisdk-istomin.rhcloud.com Unfortunately,
sometimes this instance goes to "Idle" status and needs to be restarted. But,
at least, we have something to test with. Feel free to create an issue if 
Jenkins is not available.