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
