# Getting Started

Exploring OpenID Connect Protocol for security implementation. The project consists of the following components:-

* Resource Server: Server that exposes secured endpoints that require a valid bearer token
* Web Application: Standard web application that delegates authentication to identify provider.
* Single Page Application: Standalone html/js pages that use PKCE for integration with identify provider (no client secret required)

### Identity Provider
For this project, Okta has been used but any identify provider will work. Testing using Keyclock was successful.

### Postman Collections
* Keycloak: https://www.getpostman.com/collections/9ee00dd8a881c3ee518c
* Okta: https://www.getpostman.com/collections/64ab58bf295277a7e929