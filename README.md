#Planotius 
####test framework tool

This framework will provide common features that other frameworks provides, but in a friendly way to use.
Only minor coding is needed on the test codes! 

####Frameworks available:

* Selenium
* Junit

####Features available:

* Easy to use with the Page Object Pattern;
* Smart FindBy elements: you only have to provide one identifier, the framework search by any means;
* Makes your code clean and maintanable;
* You can provide all your test data in csv files;
* Maven compiled and maven provided;

####Latest improvements:

* Now it is possible to hint a locator when using @ElementDiscover:
```
@ElementDiscover(value = "submit_button", locator = Locator.ID) 
```


#### Install

Do a git clone of this repository.

Change to the planotius branch

```
git branch planotius
```

Perform a maven install:

```
mvn clean install
```



Maven dependency (pom.xml)

```
<dependencies>
…
        <dependency>
            <groupId>com.lenovo</groupId>
            <artifactId>testFramework</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
…
</dependencies>
```

