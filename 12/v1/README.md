# State machine in Java

This version of Today has a new event provider,
called `TextFileEventProvider`. It reads events from
a text file with the following format:

    date
    description
    category
    (blank line)
    date
    description
    category
    (blank line)

etc. There should be no blank line after the last category
of the last event.

`TextFileEventProvider` reads lines from the specified file
one at a time, and keeps track of what is is expecting to see
next. This is done using a _state machine_.

The state machine is implemented using a Java enumeration type
or `enum`, called `ReadingState`. Enums in Java can implement
interfaces, and here each state implements a method called
`nextState`, which returns the next expected state. The state
machine starts at the state `DATE`, and expects to transition
to the `DESCRIPTION` state. From there it goes to `CATEGORY`, 
where it expects a `BLANK` state next.

The lines are read using a `java.io.BufferedReader`, which wraps
a `java.io.FileReader`. This is known as the _Decorator_ design
pattern. At the end of the file, the `readLine` method returns
`null`, and the state machine goes to the `DONE` state.

## The `EventProvider::isAddSupported` method

The EventProvider interface has gained a new method called
`isAddSupported`. This method has a default implementation 
that returns `false`, and any event provider that wants to
support adding events should override it to return `true`.

## `EventFactory::makeEvent` supports `RuleBasedEvent`

The static `makeEvent` method of `EventFactory` has been
extended to support creating rule-based events (see the
abstract class `Rule` and the concrete `Event` subclass
`RuleBasedEvent` for details). Currently the only supported
rule type is `VerbalRule`. This is identified by a date
string that starts with a letter (other possibilities are
a dash for `MonthDay` and a digit for `LocalDate`).
