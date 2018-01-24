# Mock Currency Converter API

This service provides a mock currency conversion service that can be called via standard HTTP API, e.g., a Spring RestTemplate.

It is ready to be deployed onto Heroku using this command line:

```bash
java -Dserver.port=$PORT $JAVA_OPTS -jar target/currency-converter-0.0.1-SNAPSHOT.jar
```