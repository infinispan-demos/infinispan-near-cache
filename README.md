# Run infinispan server

docker run -it -p 11222:11222 jboss/infinispan-server:9.4.0.Final

# Load Data
This project is a simple spring-boot app that connects to a Remote Cache and loads a list of data.
The data is stored in a cache called `contributors` of type `Integer`/`Contributor`.
A 'Contributor' has an int 'code' and a 'String' name.

```bash
cd server
mvn spring-boot:run
```

# Reader
This project is a simple spring-boot app that randomly call's `get` to display a `Contributor` during a 10.000 calls loop.

```bash
cd reader
mvn spring-boot:run
```

# Reader with near caching
Exactly the same code as the Reader but activating near caching through configuration 

```bash
cd reader-with-near-cache
mvn spring-boot:run
```
