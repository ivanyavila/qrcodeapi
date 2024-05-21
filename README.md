# README

## Overview

**Aldebaran MVP Main Unit**
This repository offers a RESTful API designed for the generation and validation of one-time-use QR codes. Users can utilize this API to create and verify unique QR codes, enabling seamless integration of QR code functionality into various applications and services.

## API ENDPOINTS (Read Swagger documentation for details)

- **BASE ENDPOINT: /qrcode**
  - By configuration, all endpoints start with **/qrcode**

- **POST: /generate/{validUntilInHours}**
  - Generates a dynamic QR code based on a positive integer input representing the desired lifetime of the code in hours from its creation until a specified time.
  
- **GET: /validate/{ulid}**
  - Validates the expiration time and ensures the one-time use functionality of QR codes.

## PRE-REQUIREMENTS:
| Software | Download Link |
| ---- | ---- |
| GIT | [GIT - Download Link](https://git-scm.com/downloads) |
| Java 21 | [Corretto - Download Link](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html) |
| Gradle | [Gradle - Download Link](https://gradle.org/install/) |
| Docker | [Docker Desktop - Download Link](https://www.docker.com/products/docker-desktop/) |

## PRE-RUN REST API:

1. Create a folder of your choice
2. Open a command prompt
3. Navigate to the folder you created in the first step
4. Execute: `git clone https://github.com/ivanyavila/qrcodeapi.git`

## RUN REST API:

1. Navigate to `your_folder\qrcodeapi\src\main\resources`
2. **Create a local.yml** file with the following format:

      ```
      database:
        type: postgresql
        hostname: localhost
        port: 5432
        name: <db name>
        username: <user>
        password: <pass>
      ```

4. **Create a local.env** file with the following format:

      ```
        POSTGRESDB_DATABASE=<db name>
        POSTGRESDB_USER=<user>
        POSTGRESDB_PASSWORD=<pass>
        POSTGRESDB_PORT=5432
      ```

5. Execute: `docker compose --env-file .\local.env up`
6. Navigate back to `your_folder\qrcodeapi\`
7. Execute: `./gradlew bootRun`

## STOP REST API:

1. Go to the same command prompt where the REST API is running
2. Press "Ctrl + c" to stop the REST API, and press "Y" and "enter" (or "S" according to your OS language)
3. Navigate to `your_folder\qrcodeapi\src\main\resources`
4. Execute: `docker compose --env-file .\local.env down`

## SWAGGER LOCATION:
Once the REST API is running, access Swagger at:
- http://localhost:8080/qrcode/swagger-ui/index.html
