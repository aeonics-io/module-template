# module-template
This repository contains the Aeonics module template structure

Please refer to the Aeonics SDK documentation for detailed javadoc and other content.

## Dependencies

You need the Aeonics SDK to be available, this is a small single jar file to add to your project.
There are no other dependencies, no `mvn`, no `gradle`, no `npm` of any sort is required.

## How to build

Use the regular `javac` command, or any custom tool of your choice.

```
javac -d ./bin -p . --module-source-path ./src --module my_module
```

Then package your module as a jar file using `jar`, or any custom tool of your choice.

```
jar -c --file=./my_module.jar -C ./bin/my_module .
```

Then encrypt and digitally sign your jar file on the Aeonics portal, manually via the web interface or directly throught the REST API.

```
$ curl -X POST https://portal.aeonics.io/api/portal/generate/module \
	-u user:pass \
	-F "cert_id=42" \
	-F "jar=@/path/to/jar.jar" > /path/to/module.module
```

You can then deploy your module on your Aeonics instance, manually via the web interface or directly throught the REST API.

```
$ curl -X POST http://localhost/api/admin/module/upsert \
	-u admin:pass \
	-F "archive=@/path/to/module.module"
```
