
Demo application to illustrate how we can use multiple datasources in Spring Data JPA application.

# Prepare environment
Start docker-compose environment with PostgreSQL and MySQL databases.
```bash
repo-root# docker-compose up 
```

# Run
```bash
./gradlew clean bootRun
```

# Testing

in file `src/test/resources/content.rest` you can find samples of requests which can be used for testing & experiments.
