# Mock Currency Converter API

This service provides a mock currency conversion service that can be deployed locally or on a service such as Heroku and called via standard HTTP API mechanisms, e.g., a Spring RestTemplate.

## Run Locally

The default port for this application is 8081, as specified in the [application.properties](src/main/resources/application.properties) file.

Then change the URL in your to point to your local instance with the correct port, e.g.:

```java
private static final String CURRENCY_CONVERSION_URL = "http://localhost:8081/convert?from={from}&to={to}&amount={amount}";
```

### Changing Server Port

You can change the port that it runs on by either passing a new value as a parameter (`-Dserver.port=9999`), or by changing the application.properties file, e.g.:

```
server.port=9999
```

## Run on Heroku
It is ready to be deployed onto Heroku using this command line:

```bash
java -Dserver.port=$PORT $JAVA_OPTS -jar target/currency-converter-0.0.1-SNAPSHOT.jar
```