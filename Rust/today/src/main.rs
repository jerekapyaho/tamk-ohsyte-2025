// This program uses the `chrono` crate (dependency in `Cargo.toml`)
// Identifiers from external crates need to be mentioned with `use`
use chrono;
use chrono::{
    NaiveDate,  // like java.time.LocalDate
    Datelike,
    DateTime,
    Utc
};

// Rust struct is a bit like class in Java,
// but methods come from traits (a bit like Java interfaces).
// This struct implements the `Debug` trait (needed for debug printing).
// Visibility of fields can be controlled with `pub`.
#[derive(Debug)]
struct Event {
    date: NaiveDate,
    description: String
}

fn main() {
    // `let` defines an immutable variable
    // Type definition is usually not needed,
    // because the type can be inferred.
    let now = chrono::Utc::now();
    
    // `println!` is a macro (all macro names end with `!`)
    // curly braces {} are placeholders for values to print
    println!("Current date and time = {}\n", now);

    // `let mut` defines a mutable variable.
    // `Vec` is a generic type from the standard library.
    // It represents a vector, kind of like `java.util.ArrayList`.
    // Here it contains instances of our `Event` struct.
    let mut events = Vec::<Event>::new();

    // Struct instances can be created on the fly.
    // Here is one, added ("pushed") to the end of the vector.
    events.push(Event {
        date: NaiveDate::from_ymd_opt(2001, 4, 7).unwrap(),
        description: "NASA launches the 2001 Mars Odyssey orbiter".to_string()
    });

    events.push(Event {
        date: NaiveDate::from_ymd_opt(1969, 4, 7).unwrap(),
        description: "The Internet's symbolic birth date: Publication of RFC 1".to_string()
    });

    events.push(Event {
        date: NaiveDate::from_ymd_opt(1964, 4, 7).unwrap(),
        description: "IBM announces the System/360".to_string()
    });

    events.push(Event {
        date: NaiveDate::from_ymd_opt(1993, 4, 8).unwrap(),
        description: "The Space Shuttle Discovery is launched on mission STS-56".to_string()
    });

    events.push(Event {
        date: NaiveDate::from_ymd_opt(1973, 4, 6).unwrap(),
        description: "Launch of Pioneer 11 spacecraft".to_string()
    });

    // The `NaiveDate` constructor `from_ymd_opt` returns
    // an `Option<NaiveDate>` type, because it could fail. Rust does not
    // have exceptions, so we need to handle the failure here.
    // Since we know we put in the right values, we just "unwrap"
    // the value inside the optional type.

    // Rust has two string types: a slice or `&str` is a reference,
    // while `String` is owned. String literals need to be turned
    // into `String` values with `to_string`.

    // The vector can be iterated with a for loop.
    // This iteration consumes the vector, so it can't
    // be used after the loop! (See commented-out statement below)
    for event in events {
        if is_same_day(&event.date, &now) {
            println!("Debug printout: {:?}", event);
            println!("Field printout: {}: {}\n", event.date, event.description);
        }
    }

    // Uncomment this and recompile to see
    // the Rust borrow checker in action!
    //println!("Number of events: {}", events.len());
}

// Function to return `true` if the day and month match,
// `false` otherwise. Parameters are passed as references.
// The last expression in the function body is the return value.
fn is_same_day(event_date: &NaiveDate, date: &DateTime<Utc>) -> bool {
    event_date.day() == date.day() &&
    event_date.month() == date.month()
}
