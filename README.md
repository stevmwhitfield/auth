# Auth

This is a simple authentication API that uses JWT-based authentication with asymmetric key encryption. It allows users to request a token and access protected routes.

## Table of Contents

- [Quick Start](#quick-start)
- [Endpoints](#endpoints)
- [Future Improvements](#future-improvements)

## Quick Start

1. Download the project.
2. Use maven to clean and build the project.
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

### POST /token

Grants a token when provided with basic authentication credentials.

Body:

```json
{
  "email": "email@example.com",
  "password": "secret"
}
```

Returns:

```json
{
  "accessToken": "jwt-token",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

## Future Improvements

The following are some ideas for future improvements, in no particular order:

- Add support for email registration with confirmation
- Add support for JWT refresh tokens
- Add support for multiple user roles
- Add support for Docker deployments
- Add GET /user to retrieve details about the logged in user
- Add POST /logout to revoke the user's refresh token
