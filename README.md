# README.MD
This is a project for an account service in REST guideline. It should be able to create, access, find accounts and to be able to transfer money between them.

## Content
This proyect has one branch: technical-test. It is build with Spring-boot with five modules:
* technitaltest-api: it contains the data which are in the request and the response body
* technicaltest-model: it contains the data transfer object
* technicatest-repository: it contains the classes which perform action in the database. These classes have the @Repository annotation
* technicaltest-service: it contains the classes which performs some service, such as execute business-logic, perform calculation. These classes have the @Service annotation
* technicaltest-web: it contains the classes which are the Spring Controller and the main class. These classes have the @RestController annotation. This Controller has the following methods:
  * createAccount: returns the state of the action, the url is http://localhost:8080/account/create and you need to send a json object in the body request like than: {"name" : <accountName>, "currency" : <currency>, "balance" : <balance>, "treasury" : <isTreasury>}
  * searchAccount: returns the account to search, the url is http://localhost:8080/account/search/{accountName}, the {accountName} is the account to search
  * transferAmount: returns the state of the action, the url is http://localhost:8080/account/transfer and you need to send a json object in the body request like than: {"amount" : <amount>, "sourceAccount" : <sourceAccountName>, "destinyAccount" : <destinyAccountName>}
  * getAccounts: returns the data of all accounts, the url is http://localhost:8080/account/get
  * addBalance: returns the state of the action, the url is http://localhost:8080/account/add and you need to send a json object in the body request like than: {"name" : <accountName>, "currency" : <currency>, "balance" : <balance>, "treasury" : <isTreasury>}

## How to clone
You can clone the repository wherever you want.
```bash
git clone https://github.com/alsanesc/technical-test/dotfiles.git
```

## Installation
To run this proyect just type and execute
```bash
mvn clean install
```
Run TechnicalTestApplication.java like as application
