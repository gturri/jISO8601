```
 _____________________________________
/ This repo has moved to                \\
\\ https://codeberg.org/gturri/jISO8601/ /
  -------------------------------------
         \   ^__^
          \  (oo)\_______
             (__)\       )\/\\
                 ||----w |
                 ||     ||
```

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


Each type of ISO8601 dates are supported (calendar, ordinal and week dates, basic and extended formats)
as well as each format of hour and timezone.

Installation
============
Using it with maven
-------------------
This package is available on maven central. To use it, you just need to add the following lines in your pom.xml:

```xml
<dependency>
    <groupId>fr.turri</groupId>
    <artifactId>jISO8601</artifactId>
    <version>0.2</version>
</dependency>
```

Out of scope (for now)
=====================
Recurring time interval and Periods aren't supported. Feel free to open feature requests.

Build status
============
Status: ![Status](https://travis-ci.org/gturri/jISO8601.svg?branch=master)
