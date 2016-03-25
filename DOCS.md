# JENKINS-SDK DOCUMENTATION
[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)
[![Dependencies](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk)

## Table of Contents
{:toc}

## Jenkins
You can create Jenkins object:
```
Jenkins jenkins = new RealJenkins(
    "<YOUR JENKINS URL>",
    new UsernamePasswordCredentials("<USERNAME>", "<PASSWORD>")
);
```
After Jenkins object was created you can use it for getting information. For
example, you can get Jenkin's verson: ```jenkins.version()```.

## Jenkins Users 
### Iterate through Users
Using ```jenkins``` instance of class ```RealJenkins``` you may iterate through
users:
```
Users users = jenkins.users();
System.out.println("----------------- USERS -----------------");
final Iterator<User> iterator = users.iterator();
while (iterator.hasNext()) {
    User user =  iterator.next();
    System.out.println(user.fullName());
}
System.out.println("----------------------------------------");
```
### Users Search
Using ```jenkins``` instance of class ```RealJenkins``` you may search for
users:
```
Iterator<User> found = jenkins.users()
    .findByUsername("<USERNAME>");
System.out.println("----------------- FOUND USERS -----------------");
while (found.hasNext()) {
    User user =  found.next();
    System.out.println(user.fullName());
}
System.out.println("----------------------------------------------");
```
Currently we have three methods that allow to search for users:
```Users.findByUsername()```, ```Users.findByEmail()``` and 
```Users.findByFullName()```
### Read User Details
Using ```jenkins``` instance of class ```RealJenkins``` you may read particular
user details:
```
final Iterator<User> users = jenkins.users().iterator();
while (users.hasNext()) {
    User user = users.next();
    System.out.println("user.fullName() = " + user.fullName());
    System.out.println("user.username() = " + user.username());
    System.out.println("user.email() = " + user.email());
    System.out.println("user.url() = " + user.url());
    System.out.println("user.description() = " + user.description());
}
```