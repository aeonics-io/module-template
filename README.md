# module-template
This repository contains the Aeonics module template structure

Please refer to the Aeonics SDK documentation for detailed javadoc and other content.

## Dependencies

You need the Aeonics SDK to be available, this is a small single jar file to add to your project.
There are no other dependencies, no `mvn`, no `gradle`, no `npm` of any sort is required.

## How to build

Use the regular `javac` command, or any custom tool of your choice.

```
javac -d ./bin -p . --module-source-path ./src --module template
```

Then package your module as a jar file using `jar`, or any custom tool of your choice.

```
jar -c --file=./template.jar -C ./bin/template .
```

Then encrypt and digitally sign your jar file on the Aeonics portal.
You can then deploy your module on your Aeonics instance.