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
under `degug` unless you specify `--release`.

You can run the program with

    cargo run

You can also copy the executable from the `target` subdirectory
to some other directory that is in your `PATH`.

## External crates required

The `Cargo.toml` file lists the dependencies.
