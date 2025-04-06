# Today in Rust

## Steps to recreate

* Install the Rust tools using `rustup`. See [Getting Started](https://www.rust-lang.org/learn/get-started) for details.
* Change to a suitable directory and create the project
with `cargo new today`.
* Change to the newly created `today` directory to edit and run.
The source code is in the `src` subdirectory.

## Building and running the program

You can use Cargo to build the program:

    cargo build

The executable will end up in the `target` subdirectory,
under `debug` unless you specify `--release`.

You can run the program with

    cargo run

You can also copy the executable from the `target` subdirectory
to some other directory that is in your `PATH`.

## External crates required

The `Cargo.toml` file lists the dependencies. They are called "crates".

Rust has a fairly small standard library, so various external crates
are used as semi-official solutions to common problems.

The [`chrono`](https://crates.io/crates/chrono) crate deals with dates and times.

## Parsing events from CSV

Next stop would be to parse the event CSV file like in the 
Java version. For one way to do it, see [Rudiments of Data Wrangling in Rust](https://dev.solita.fi/2021/09/03/rudiments-of-data-wrangling-in-rust.html). The most important crate you need is `csv`, as mentioned in the
post.

For the Today app you also need the category, which would be another
struct, something like this:

    struct Category {
        primary: String,
        secondary: String
    }

You would need to implement category string parsing (from "primary/secondary" to a `Category` value).

You can use the `chrono` crate to parse the ISO 8601 date strings
into `NaiveDate` values.

## Modeling different kinds of events

Rust does not have classes or inheritance, but instead uses traits 
and enumerated types for data modeling.

The Rust enumerated type can be what is known as a "sum type", often
found in functional languages. The different kinds of events of the 
Java version could be defined in Rust like this:

    enum Event {
        Singular { date: NaiveDate, description: String, category: Category },
        Annual { date: MonthDay, description: String, category: Category },
    }

The `MonthDay` struct needs to be defined since there isn't one 
like `java.time.MonthDay` in the `chrono` crate:

    struct MonthDay {
        month: u32,
        day: u32,
    }

... and so on.
