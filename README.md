# gradle-slick-plugin

*gradle plugin to generate [Slick](http://slick.typesafe.com) classes from Database schema*

[![Build Status](https://travis-ci.org/chauhraj/gradle-slick-plugin.svg?branch=master)](https://travis-ci.org/chauhraj/gradle-slick-plugin)

## Basic Usage

It uses the [Slicks Code Generator](http://slick.typesafe.com/doc/3.0.2/code-generation.html) to generate the scala classes from the table name.
One needs to define the following block to access the Database schema.

```java
slick {
    basePackage = 'com.github.chauhraj.samples'
    outputFolder = 'build/generated-sources/scala'
    mysql {
        host = "localhost"
        port = "3306"
        user = 'xxxx'
        password = 'yyyyyyyyy'
        schema {
          name = 'sakila'
        }
    }
}

```

> Note that the Database can have multiple schemas. For each schema, there is one block.
> In the future, it might be supported to exclude certain tables from being generated.


## Limitations

1. Does not support selective class generation i.e it will generate the classes for all the tables in the Database.
2. It is yet to be released in any central maven repository or bintray.
3. Currently, only supports MySQL database.
