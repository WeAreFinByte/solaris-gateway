# Architecture of solaris-gateway

## Acquiring the token

As of now, when the solaris-gateway application starts an executor is scheduled in order to get the access token to the service API.
This executor tries to get the token and after a successful attempt it adds the token to the token pool for further usage.

<img src="img/6.png" width="720">