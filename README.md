# README

## Overview

**Aldebaran MVP Main Unit**
This repository offers a RESTful API designed for the generation and validation of one-time use QR codes. 
Users can utilize this API to create and verify unique QR codes, enabling seamless integration of QR code functionality into various applications and services.

## API ENDPOINTS (Read swagger documentation for details)

- **BASE ENDPOINT: /qrcode**
  - By configuration, all endpoints stars with **/qrcode**

- **POST: /generate/{validUntilInHours}**
  - Generates a dynamic QR code based on a positive integer input representing the desired lifetime of the code in hours from its creation until a specified time. 
- **GET: /validate/{ulid}**
  - Validates expiration time and ensures the one-time use functionality of QR codes.

## PRE-REQUIREMENTS:
|Software|Download Link|
|----|----|
|GIT|[GIT - Download Link](https://git-scm.com/downloads)|
|Java 21|[Correto - Download Link](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)|
|Gradle|[Gradle - Download Link](https://gradle.org/install/)|
|Docker|[Docker Desktop - Download Link](https://www.docker.com/products/docker-desktop/)|

## PRE-RUN REST API:

1. Create a folder of your choice
2. Open a command promp
3. Navigate to the folder you created in the first step
4. Execute: `git clone https://github.com/ivanyavila/qrcodeapi.git`

## RUN REST API:

1. Navigate to `your_folder\qrcodeapi\src\main\resources`
2. Create a local.yml file with the next format:

      ```
      database:
        type: postgresql
        hostname: localhost
        port: 5432
        name: qrcodeapi
        username: <user>
        password: <pass>
      ```

4. Execute: `docker compose up -d`
5. Navigate back to `your_folder\qrcodeapi\`
6. Execute: `./gradlew bootRun`

## STOP REST API:

1. Go to the same command promp where REST API is running
2. Press "Ctrl + c" to stop the REST API, and press "Y" and "enter" (or "S" according your SO languaje)
3. Navigate to `your_folder\qrcodeapi\src\main\resources`
4. Execute: `docker compose down`

## SWAGGER LOCATION:
Once the REST API is 
- http://localhost:8080/qrcode/swagger-ui/index.html
