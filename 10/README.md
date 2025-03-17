# Using an SQLite database with Java

Java has JDBC (Java DataBase Connectivity) which can be used to 
store and retrieve data to and from a compatible SQL database.

The easiest SQL database to work with is SQLite. It needs no server,
since the database is all in one file. The [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) driver is required.

## Creating the database

Use the SQLite command line interface to create the database file
and the tables:

    % sqlite3 events.sqlite3

This creates a new database with the given filename. To create the tables,
enter the SQL statements in create_tables.sql or use the SQLite shell:

    sqlite> .read create_tables.sql

To create the categories, use the SQL statements in the file insert_categories.sql.

The 10,000 fictional tech events are in the file fake10k.csv. Each of them
has the category ID 1, with the primary name as "test" and the secondary name
as "fake", so that they can easily be filtered out.

The event table in the database uses an automatically incremented primary key,
but those are not found in the CSV file. That is why we use a temporary table
to read in the events from the CSV file. The temporary table is created like
this:

    sqlite> create table temp_event (event_date DATE, event_description TEXT, category_id INTEGER);
 
To store the fake tech events into the temporary table, insert the contents
of the file:

    sqlite> .import --csv fake10k.csv temp_event

This will read the file fake10k.csv and insert the contents of the rows into
the temporary table. For more information, 
see the SQLite shell [documentation](https://www.sqlite.org/cli.html#importing_files_as_csv_or_other_formats).

Then insert the events from the temporary table to the actual event table:

    sqlite> insert into event(event_date, event_description, category_id) select * from temp_event;

Finally, clean up:

    sqlite> drop table temp_event;

## Installing the sqlite-jdbc driver

Insert the following dependency into your Maven pom.xml file:

    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.49.1.0</version>
    </dependency>

Synchronize the project if necessary.

## Connecting to the database

TBD

## Retrieving data from the database

TBD
