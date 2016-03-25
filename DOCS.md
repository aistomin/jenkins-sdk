# JENKINS-SDK DOCUMENTATION
[![Build Status](https://travis-ci.org/aistomin/jenkins-sdk.svg?branch=master)](https://travis-ci.org/aistomin/jenkins-sdk)
[![Dependencies](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56b7e8d2f6e506003159ac3c)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/jenkins-sdk)

## Table of Contents
* [Jenkins](#jenkins)  
* [Jenkins Users](#jenkins-users)  
    * [Iterate through Users](#iterate-through-users)
    * [Users Search](#users-search) 
    * [Read User Details](#read-user-details)
* [Jenkins Jobs](#jenkins-jobs)  
    * [Iterate through Jobs](#iterate-through-jobs)
    * [Jobs Search](#jobs-search) 
    * [Read Job Details](#read-job-details) 

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
We provide ```RealUsers``` and ```RealUser``` classes to read information about
Jenkins users.
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
Iterator<User> users = jenkins.users().iterator();
while (users.hasNext()) {
    User user = users.next();
    System.out.println("user.fullName() = " + user.fullName());
    System.out.println("user.username() = " + user.username());
    System.out.println("user.email() = " + user.email());
    System.out.println("user.url() = " + user.url());
    System.out.println("user.description() = " + user.description());
}
```
## Jenkins Jobs 
We provide ```RealJobs``` and ```RealJob``` classes to read information about
Jenkins users.
### Iterate through Jobs
Using ```jenkins``` instance of class ```RealJenkins``` you may iterate through
jobs:
```
Jobs jobs = jenkins.jobs();
System.out.println("----------------- JOBS -----------------");
Iterator<Job> iterator = jobs.iterator();
while (iterator.hasNext()) {
    Job job =  iterator.next();
    System.out.println(job.name());
}
System.out.println("----------------------------------------");
```
### Jobs Search
Using ```jenkins``` instance of class ```RealJenkins``` you may search for
jobs:
```
Iterator<Job> found = jenkins.jobs().findByName("<JOB TO SEARCH>");
System.out.println("----------------- FOUND JOBS -----------------");
while (found.hasNext()) {
    Job job =  found.next();
    System.out.println(job.name());
}
System.out.println("----------------------------------------------");
```
### Read Job Details
Using ```jenkins``` instance of class ```RealJenkins``` you may read particular
job details:
```
Iterator<Job> jobs = jenkins.jobs().iterator();
while (jobs.hasNext()) {
    System.out.println("job.details().displayName() = " + job.details().displayName());
    System.out.println("job.details().description() = " + job.details().description());
    System.out.println("job.details().buildable() = " + job.details().buildable());
    System.out.println("job.url() = " + job.url());
}
```