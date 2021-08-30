# Run infinispan server

```bash
docker run -p 11222:11222 -e USER="admin" -e PASS="password" infinispan/server:12.1.7.Final
```
(Please note that try to run infinispan server version >13.x.x *might not* be compibled with this example.)

# Build first
Please build and install first before run,  because there are some common module dependency needs to be installed in your local maven repository.

```bash
mvn clean install
```

# Load Data
This project is a simple spring-boot app that connects to a Remote Cache and loads a list of data.
The data is stored in a cache called `default` of type `Integer`/`Contributor`.
A 'Contributor' has an int 'code' and a 'String' name.

```bash
mvn spring-boot:run -pl writer
```

# Reader

The reader project contains 5 submodules.

- **Common**: the application code. This project is a simple spring-boot app that randomly call's `get` to display a `Contributor` during a 10.000 calls loop.
There is no main method, this is the code the other readers will be using.

- **Reader No Near Cache** Adds the main method needed, but there is no near caching
```bash
mvn spring-boot:run -pl reader/reader-no-near-cache
```

- **Reader Near Code** Showcases the configuration you need to activate near caching by code
```bash
mvn spring-boot:run -pl reader/reader-near-code
```

- **Reader Near Hotrod** Showcases the configuration you need to activate near caching using the hotrod-client.properties
```bash
mvn spring-boot:run -pl reader/reader-near-hotrod
```

- **Reader Near Spring** Showcases the configuration you need to activate near caching using the application.properties
```bash
mvn spring-boot:run -pl reader/reader-near-spring
```

# Infinispan Spring-Boot starter
This project is built using the [Infinispan Spring-Boot Starter](https://github.com/infinispan/infinispan/tree/main/spring/spring-boot)
