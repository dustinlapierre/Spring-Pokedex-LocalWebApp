# Pokemon Local Webapp API

This program can be ran from the command line using “java -jar pokedex.jar” and requires Java 11 or higher.

The web client should automatically open in your browser on startup, but if for some reason this is not the case you can access it at http://localhost:8080. This client allows for query parameters to be entered into a simple form and sent via HTTP GET. This is simply for convenience purposes; however, the API can also be accessed using any HTTP client. Currently the only valid endpoints are the numbers 1-5, corresponding to a given record in the internal database. If no endpoint is supplied then all records will be returned.

Example: http://localhost:8080/api?id=123&endpoint=3
