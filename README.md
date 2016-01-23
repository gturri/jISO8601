Overview
========
jISO8601 is yet another library made to parse dates in Java. It may still be useful since:

* It's trivial to use
* It's lightweight
* It's compatible with Android
* It can parse any date compatible with the norm


Getting started
===============

There are only two public methods:

    import fr.turri.jiso8601.*;
    ...
    Calendar cal = Iso8601Deserializer.toCalendar("1985-03-04");
    Date date = Iso8601Deserializer.toDate("1985-03-04T12:34:56Z");


Each type of ISO8601 dates are supported (calendar, ordinal and week dates, basic and extended format)
as weel as each format of hour and timezone.

Installation
============
Using it with maven
-------------------
This package will soon be available on maven central. For now it needs to be build from source.
For instance, on Ubuntu:

    git clone https://github.com/gturri/jiso8601
    cd jiso8601
    sudo apt-get install maven
    mvn install

Then, in your pom.xml, add

```xml
<dependency>
    <groupId>fr.turri</groupId>
    <artifactId>jISO8601</artifactId>
    <version>0.1</version>
</dependency>
```

Out of scope (for now)
=====================
Recurring time interval and Periods aren't supported. Feel free to open feature requests.
