# Run infinispan server

```bash 
docker run -it -p 11222:11222 jboss/infinispan-server:9.4.0.Final
```
(Please note that try to run infinispan server version >9.x.x *might not* be compibled with this example. )

# Build first
Please build and install first before run,  because there are some common module dependency needs to be installed in your local maven repository.

```bash
mvn clean install
```


# Load Data
This project is a simple spring-boot app that connects to a Remote Cache and loads a list of data.
The data is stored in a cache called `contributors` of type `Integer`/`Contributor`.
A 'Contributor' has an int 'code' and a 'String' name.

```bash
cd writer
mvn spring-boot:run
```

# Reader

The reader project contains 5 submodules.

- **Common**: the application code. This project is a simple spring-boot app that randomly call's `get` to display a `Contributor` during a 10.000 calls loop.
There is no main method, this is the code the other readers will be using.

- **Reader No Near Cache** Adds the main method needed, but there is no near caching
```bash
cd reader-no-near-cache
mvn spring-boot:run
```

- **Reader Near Code** Showcases the configuration you need to activate near caching by code
```bash
cd reader-near-code
mvn spring-boot:run
```
- **Reader Near Hotrod** Showcases the configuration you need to activate near caching using the hotrod-client.properties
```bash
cd reader-near-hotrod
mvn spring-boot:run
```

- **Reader Near Spring** Showcases the configuration you need to activate near caching using the application.properties
```bash
cd reader-near-spring
mvn spring-boot:run
```

# Infinispan Spring-Boot starter
This project is built using the [Infinispan Spring-Boot Starter](https://github.com/infinispan/infinispan-spring-boot)
