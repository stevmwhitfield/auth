# Auth

This is a simple authentication API microservice that uses JWT-based authentication with asymmetric key encryption. It allows users to request a token and access protected routes.

## Table of Contents

- [Quick Start](#quick-start)
- [Endpoints](#endpoints)
- [Future Improvements](#future-improvements)

## Quick Start

1. Create a `certs` directory under `auth-service/src/main/resources`.
2. Generate a `private-key.pem` and `public-key.pem` in the `certs` directory.

```bash
openssl genrsa -out private-key.pem 2048

openssl rsa -in private-key.pem -pubout -out public-key.pem
```

3. Run the application.

## Endpoints

### GET /

Returns:

```
Authorization Service
```

### GET /hello

Greets the user (requires authentication).

Returns:

```
Hello <user-name>
```

### POST /api/auth/token

Grants a token when provided with basic authentication credentials.

Returns:

```
<access-token>
```

## Future Improvements

The following are some ideas for future improvements, in no particular order:

- Use response entities with JSON instead of strings
- Add support for email registration with confirmation
- Add support for JWT refresh tokens
- Add support for multiple user roles
- Add support for Docker deployments
- Add GET /user to retrieve details about the logged in user
- Add POST /logout to revoke the user's refresh token
