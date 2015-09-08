# gradle-slick-plugin

*gradle plugin to generate [Slick](http://slick.typesafe.com) classes from Database schema*

[![Build Status](https://travis-ci.org/chauhraj/gradle-slick-plugin.svg?branch=master)](https://travis-ci.org/chauhraj/gradle-slick-plugin)

## Basic Usage

It uses the [Slicks Code Generator](http://slick.typesafe.com/doc/3.0.2/code-generation.html) to generate the scala classes from the table name.
One needs to define the following block to access the Database schema.

```
slick {
    pkg = 'com.github.chauhraj.samples.sakila'
    slickDriver = 'slick.driver.MySQLDriver'
    jdbcDriver = 'com.mysql.jdbc.Driver'
    jdbcUrl = 'jdbc:mysql://localhost/sakila'
    user = 'xxxx'
    password = 'yyyyyyyyy'
}
```

## Limitations

1. Does not support selective class generation i.e it will generate the classes for all the tables in the Database.
2. It is yet to be released in any central maven repository or bintray.
