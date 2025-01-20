# tamk-ohsyte-2025
TAMK / Tietotekniikka / Ohjelmoinnin syventävät tekniikat, kevät 2025

## Java versions

* Marco Behler, [Java Versions and Features](https://www.marcobehler.com/guides/a-guide-to-java-versions-and-features)

## Random number generation

* [Generating Random Numbers Between 1 and 100 in Java](https://dev.to/meenakshi052003/generating-random-numbers-between-1-and-100-in-java-i3b)

## JShell

* [JShell Tutorial](https://cr.openjdk.org/~rfield/tutorial/JShellTutorial.html)

## Visual Studio Code

If you don't want/need to see the names of
method arguments etc. in the editor, then do this:

With a Java source code file open, select "Java"
in the bottom right of the status bar, then
"Configure 'Java' language settings", search for
"editor.inlayHints.enabled", set to "off".

This should result in the following in your `settings.json` file:

    {
        "[java]": {
            "editor.inlayHints.enabled": "off",
        }
    }
