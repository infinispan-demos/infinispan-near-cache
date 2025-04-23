# Infinispan Example Project

## Start Infinispan Server

Run the Infinispan server using Docker or Podman:

```bash
docker run -it -p 11222:11222 -e USER="admin" -e PASS="password" quay.io/infinispan/server:latest

podman run -it -p 11222:11222 -e USER="admin" -e PASS="password" quay.io/infinispan/server:latest
```

## Build the Project First

Before running anything, build and install the project. This ensures that all required modules are available in your local Maven repository:

```bash
./mvnw clean install
```

---

## Load Sample Data

This project is a Spring Boot app that connects to a remote cache and loads sample data.

- Cache name: `default`
- Data type: `Integer` key / `Contributor` value
- Each `Contributor` has:
    - `int code`
    - `String name`

To run the writer module:

```bash
./mvnw spring-boot:run -pl writer
```

---

## Reader Modules

The reader project includes several submodules for different near caching setups.

### 1. Common
- Shared code used by all reader modules.
- Randomly calls `get` in a loop of 10,000 iterations to retrieve `Contributor` objects.
- No `main` method (not directly runnable).

---

### 2. Reader Without Near Cache

Run this to test without near caching:

```bash
./mvnw spring-boot:run -pl reader/reader-no-near-cache
```

---

### 3. Reader With Near Cache (Code Configuration)

Near caching is enabled directly in the code:

```bash
./mvnw spring-boot:run -pl reader/reader-near-code
```

---

### 4. Reader With Near Cache (Spring Config)

Near caching is configured in `application.properties`:

```bash
./mvnw spring-boot:run -pl reader/reader-near-spring
```

---

## Infinispan Spring Boot Starter

This project uses the [Infinispan Spring Boot Starter](https://github.com/infinispan/infinispan/tree/main/spring).

