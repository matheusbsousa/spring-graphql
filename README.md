# Boat Rental Service API

## Objective
The main goal of this project is to check the eligibility of a pet to the boat rent service. The system will register the pet and owner information 
so the users can verify if the pet is allowed to board the boat.

## Architecture

### Versions
- Java JDK 21 -> Java Virtual Machine responsible for project execution
- Kotlin 1.9 -> Programming language used for development
- Gradle 8.5 -> Dependency management tool
- JUnit 5 -> Unit testing framework
- Mockito -> Unit test mocking framework
- Docker Compose -> Container execution tool
- GraphQL -> Query language for APIs
- Liquibase -> Database version control tool
- Git -> Version control system
- Makefile -> Command execution tool

### Database 
The project uses a PostgreSQL database to store the data. The database is managed by the Liquibase tool, 
which is responsible for creating the tables and versioning the database. 

### API

The project uses the GraphQL API to manage the data. 
The API is responsible for receiving the requests and returning the responses.

#### GraphQL
For a better experience, you should access the API using the GraphQL playground interface.

---
## Installation

It is necessary install the docker and docker compose plugin to run the project. To run the project from an IDE, you also need
to install the Java JDK 21 .

### Java

### Linux
```bash 
  sudo apt-get install openjdk-21-jdk
```

### MacOS
```bash
  brew install openjdk@21
```

### Windows
```bash
  choco install openjdk-21-jdk
```

> [Configure Java PATH](https://www.geeksforgeeks.org/how-to-set-java-path-in-windows-and-linux/)

### Docker
Please follow the official documentation the detail installation process 

> [How to install Docker](https://docs.docker.com/engine/install/)

> [How to install Docker Compose](https://docs.docker.com/compose/install/)

### Makefile

Makefile files are used to map commands

> [Makefile Docs - by Matheus B. Sousa](https://matheusbsousa.notion.site/Makefile-5a79d2dfcef4403aa2922c6b11a30fe0?pvs=4)

### Linux
```bash
sudo apt-get install make
```

### MacOS
```bash
brew install make
```

### Windows
```bash
choco install make
```

## Execution

To execute the project, run the command bellow on your terminal. 
This will start the project build and the system will be executed based on the installation.

The project has two types of profiles, each one for a different scenario. 
The command will execute the project with dev profile instantiating the api and database containers. 

```bash
make run-dev
```

### Profiles 

Local -> Used for local development and testing. This profile runs the database 
migrations everytime the project is started. *It does not keep the data after the project is stopped.*

Dev -> Used for the final version of the system. This profile runs the database migrations only once, 
when the project is started for the first time. *It keeps the data after the project is stopped.*


