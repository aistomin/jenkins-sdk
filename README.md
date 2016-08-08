# JENKINS-SDK
[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)
[![Dependencies](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk)

See: [Documentation](DOCS.md) and [Examples](https://github.com/aistomin/jenkins-sdk-examples) for more details.

## Purpose
This project is object oriented Java SDK for 
[Jenkins XML API](https://wiki.jenkins-ci.org/display/JENKINS/Remote+access+API) 
that is going to help developers to manipulate Jenkins instance using it's API.
This SDK can be useful for developers who creates CI scripts or other environment
software that needs to read information from Jenkins, trigger builds etc. Java 7
or higher is required. 

## Add Maven Dependency
Add the following configuration to your project's `pom.xml`
```maven
<dependencies>
    <!-- other dependencies are there -->
    <dependency>
        <groupId>com.github.aistomin</groupId>
        <artifactId>jenkins-sdk</artifactId>
        <version>0.2</version>
    </dependency>
    <!-- other dependencies are there -->
</dependencies>
```

## Quick start
Using classes from `com.github.aistomin.jenkins` and `com.github.aistomin.jenkins.real` you can 
manipulate with existing Jenkins installation. For example, reading builds information:
```java
public class Main {
    public static void main(String[] args) throws Exception {
        Jenkins jenkins = new RealJenkins(
            "<YOUR JENKINS URL>",
            new UsernamePasswordCredentials("<USERNAME>", "<PASSWORD>")
        );
        Job job = jenkins.jobs().findByName("<JOB NAME>").next();
        System.out.println("----------------- BUILDS -----------------");
        final Iterator<Build> iterator = job.builds().iterator();
        while (iterator.hasNext()) {
            Build build =  iterator.next();
            System.out.println(build.number());
        }
        System.out.println("----------------------------------------");
    }
}
```

More details and code examples you may find in 
[documentation](DOCS.md) or [examples project](https://github.com/aistomin/jenkins-sdk-examples).

Every API object contains some methods for getting information. Also it has 
`.xml()` method. This method allows you to get plain object's XML which allows
you to decorate our objects in a way you like.

We provide a number of stub classes which are going to help you with testing 
your code which is using our project. Using classes from 
`com.github.aistomin.jenkins.fake` you can create stubs/fakes of Jenkins 
installation.

## Have you found a bug?
We've tested our code using Jenkins ver. 1.642.2. We're not robots and bugs are
possible :) Don't hesitate to [create and issue](https://github.com/aistomin/jenkins-sdk/issues/new). 
It will help to make our project better. Thanks in advance.

## How to contribute?

Do you want to help us with this project? Please: fork the repository, make 
changes, submit a pull request. We promise to review your changes same day and
apply to the `master` branch, if they look correct.

Please run Maven (3.1 or higher!) build before submitting a pull request:

```
$ mvn clean install -Pqulice
```

Be aware: unfortunately our Jenkins instance for integrations tests is not
perfect. Sometimes it's slow, sometimes it's even not available. We're trying to
do our best to solve this problem, but for now we haven't got success with 
that :(
Thanks in advance for your help.

## Jenkins Instance for Integration tests

We have running instance at https://cisdk-istomin.rhcloud.com Unfortunately,
sometimes this instance goes to "Idle" status and needs to be restarted. But,
at least, we have something to test with. Feel free to create an issue if 
Jenkins is not available.