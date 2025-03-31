# Changes in 11/v1

* `Category` is implemented as a Java record
* Update `Category::parse`
* Added method `isAddSupported` to `EventProvider`, and implementations
to the providers
* Added `IdentityFilter` to make filtering more streamlined
* Removed unnecessary `EventManager` methods
* Updated `EventManager` to use a hashmap to store the event providers
* `EventManager::getEventProviderIdentifiers` returns `Set` instead of `List`
* Added `EventManager::getEvents(EventFilter filter)` (also to provider implementations)
* Removed `providers/web` directory (web provider stuff will be in `providers`)
* SQLiteEventProvider now creates the database and tables if they do not exist
* Create config directory if it doesn't exist
* Replace println calls with proper logging (using `java.util.logging`)

## Preparing the app

See [Compile and build applications with IntelliJ IDEA](https://www.jetbrains.com/help/idea/compiling-applications.html)

## Running under Linux

Copy the JAR file to a directory, for example $HOME/bin.

Define an alias in `.bashrc`:

    alias today='java -jar $HOME/bin/Today.jar listevents $@'

Use the alias in the shell or in your `.bashrc`:

    today

That's it!
