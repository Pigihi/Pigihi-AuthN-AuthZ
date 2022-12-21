# Authentication and Authorization Service


This service is used to authenticate and authorize a user.

Two types of authentication methods that are considered to be currently included in this microservice are:

- Username/Password Authentication
- SSO (OpenID) \[Later Stage\]

JWT is used for user authorization.

Each time the JWT is used for authorization, the expiry is checked and if not expired, the roles (or permissions) are included in the 'X-Permission-Header' and sent to the intended microservice. If the user is disabled, then the API gateway refrains from forwarding the request to the microservice.

Each microservice also checks for the 'X-Permission-Header' and only processes the request if it has sufficient roles (or permissions).
