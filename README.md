# lein-maven-plugin
Execute leiningen tasks from within maven. Uses leiningen as a library, no custom downloads.

[![Clojars Project](https://img.shields.io/clojars/v/ingesolvoll/lein-maven-plugin.svg)](https://clojars.org/ingesolvoll/lein-maven-plugin)

## Usage

The plugin only has one goal, `run`. Its configuration, `<command>`, is the leiningen command line arguments.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>ingesolvoll</groupId>
            <artifactId>lein-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
        
            <executions>
                <execution>
                    <id>compile-clojurescript</id>
                    <goals>
                        <goal>run</goal>
                    </goals>
                    <configuration>
                        <command>cljsbuild once min</command>
                    </configuration>
                    <phase>generate-resources</phase>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Limitations

The plugin implementation is simplistic for now, so there are some limitations. Those are:
* No `do`. You can only use single lein plugins with args, like in the example. 
* No `clean`. You need to configure maven to do that.

Upcoming versions will hopefully remove these limitations.

## Clojars

The plugin is hosted on clojars, so don't forget your repository definition!

```xml
<repositories>
    <repository>
        <id>clojars</id>
        <url>https://clojars.org/repo/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <releases>
            <enabled>true</enabled>
        </releases>
    </repository>
</repositories>
```