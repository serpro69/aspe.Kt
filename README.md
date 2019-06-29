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

### Adding a dependency
**With gradle**  
Add bintray repository:  
```groovy
repositories {
    maven("https://dl.bintray.com/serpro69/maven")
}
```  

Add dependency:  
```groovy
dependencies {
    implementation("io.github.serpro69:aspe.Kt-core:0.1")
}
```  

**With maven**  
Add bintray repository:  
```xml
<repositories>
    <repository>
        <id>serpro69-maven</id>
        <url>https://dl.bintray.com/serpro69/maven/</url>
        <layout>default</layout>
        <releases>
            <enabled>true</enabled>
        </releases>
    </repository>
</repositories>
```  

Add dependency:  
```xml
<dependencies>
    <!--Add dependency-->
    <dependency>
        <groupId>io.github.serpro69</groupId>
        <artifactId>aspe.Kt-core</artifactId>
        <version>0.1</version>
    </dependency>
</dependencies>
```  

**Downloading a jar**  
The jar and pom files can also be found at this [link](https://dl.bintray.com/serpro69/maven/io/github/serpro69/aspe.Kt-core/)

### Enabling load-time weaving
To enable load-time weaving attach aspectjweaver to the JVM as java agent.
Here are a few examples to get you started:  

**With gradle kotlin-dsl**  
```kotlin
val agent: Configuration by configurations.creating

dependencies {
    // ...
    agent("org.aspectj:aspectjweaver:1.9.4")
}

val test by tasks.getting(Test::class) {
    doFirst {
        jvmArgs("-javaagent:${agent.singleFile}")
    }
    // ...
}
```

**With maven**  
```xml
<project>
    <build>
        <plugins>
            <!--...-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.version}</version>
                <configuration>
                    <!--Add aspectjweaver agent through maven surefire plugin config-->
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>
                    <!--...-->
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <!--...-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.2</version>
            <scope>test</scope>
        </dependency>
        <!--...-->
    </dependencies>
</project>
```

**From command line**  
```
$ java -javaagent:"/path/to/aspectjweaver.jar" -jar application.jar
```


## Available annotations
### @Loggable
Use this annotation to log function/method invocation and return details.  
**TODO**: 
- configuration
- example outputs


## Sample project
**TODO**


## Deploy
**Running tests:**
```
$ ./gradlew clean test
```

**Publishing to bintray repo:**
```
$ ./gradlew clean test :core:bintrayUpload -PbintrayUser=ABC -PbintrayApi=XYZ
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
