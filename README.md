#Planotius 
####test framework tool

This framework will provide common features that other frameworks provides, but in a friendly way to use.
The purpose is to write few lines of code to get automation running!

####Frameworks available:

* Selenium
* Junit
* RestAPI testing
* BDD with cucumber

####Features available:

* Easy to use with the Page Object Pattern;
* Smart FindBy elements: you only have to provide one identifier, the framework search by any means;
* Makes your code clean and maintanable;
* You can provide all your test data in csv files;
* Maven compiled and maven provided;

####Latest improvements:

* Now it is possible to hint a locator when using @ElementDiscover:
```
    @ElementDiscover(value = "submit_button", locator = "id") 
```
* Cucumber libraries already included, only start creating .features and steps files!

#### Install

Do a git clone of this repository.

Change to the development branch

```
git branch development
```

Perform a maven install:

```
mvn clean install
```



Include as maven dependency on your project (pom.xml)

```
<dependencies>
…
        <dependency>
            <groupId>com.lenovo</groupId>
            <artifactId>testFramework</artifactId>
            <version>2.0.0-SNAPSHOT</version>
        </dependency>
…
</dependencies>
```

Or add the jar as usual...
