# RAPID CYCLING

## How to run it

### Requirements

- [Java 21](https://adoptium.net/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Bun](https://bun.sh/)

### 1. Configure the environment

Copy `.env.example` to `.env` and fill in the values

### 2. Start the database

```bash
docker compose up -d
```

This starts MySQL (port `3307`) and Redis (port `6379`).

### 3. Install dependencies and compile the CSS

```bash
bun install
bun run tailwind
```

Leave this terminal open: it watches the templates and rebuilds the CSS on every change.

### 4. Start the application

In a new terminal:

```bash
./mvnw spring-boot:run
```

On Windows (PowerShell / CMD): `.\mvnw.cmd spring-boot:run`

### 5. Open it

Go to [http://localhost:8443](http://localhost:8443)

To stop the database: `docker compose down`

## URL

## Demo credentials

## Technology choices

## Current limitations 

## Next steps