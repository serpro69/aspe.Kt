### aspe.Kt
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Issues Badge](https://img.shields.io/github/issues/serpro69/aspe.Kt.svg)](https://github.com/serpro69/aspe.Kt/issues)
[![Licence Badge](https://img.shields.io/github/license/serpro69/aspe.Kt.svg)](LICENCE.md)


# Disclaimer
<b>
This project is under development and is mainly used to gain better knowledge of AOP and AspectJ
as well as investigating into possibilities of using AspectJ with Kotlin.  
At this stage this project should not be used in production.
</b>


## ToC
- [About](#about)
- [Usage](#usage)  
  - [@Loggable](#loggable)
- [Contributing](#contributing)
- [Licence](#licence)


## About
A collection of annotation-driven AspectJ utilities written in Kotlin.


## Usage

### @Loggable
Use this annotation to log function/method invocation and return details.  

To use the annotation add the following plugin and dependencies to the `pom.xml`:

```xml
<project>
<!--...-->
    <build>
        <plugins>
            <!--...-->
            <plugin>
                <groupId>com.jcabi</groupId>
                <artifactId>jcabi-maven-plugin</artifactId>
                <version>0.14.1</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>ajc</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <!--...-->
        </plugins>
    </build>

    <dependencies>
        <!--...-->
        <dependency>
            <groupId>io.github.serpro69</groupId>
            <artifactId>aspe.Kt-core</artifactId>
            <version>0.1</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>1.9.4</version>
            <scope>runtime</scope>
        </dependency>
        <!--...-->
    </dependencies>
</project>
```

## Sample project
See [pom.xml](it/pom.xml) and [sources](it/src) directory in [integration-test](it) module for sample pom setup and usage examples.

## Deploy
To run tests use: 
```
$ ./mvnw clean test
```

To install locally use: 
```
$ ./mvnw clean install
```


## Contributing
Feel free to submit a [pull request](https://github.com/serpro69/aspe.Kt/compare) 
and/or open a [new issue](https://github.com/serpro69/aspe.Kt/issues/new)
if you would like to contribute.


## Thanks
Many thanks to these awesome tools that help us in creating open-source software:  

[![Intellij IDEA](https://cloud.google.com/tools/images/icon_IntelliJIDEA.png)](http://www.jetbrains.com/idea) 
[![YourKit Java profiler](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/features/)


## Licence
This code is free to use under the terms of the MIT licence.
See [LICENCE.md](LICENCE.md).
