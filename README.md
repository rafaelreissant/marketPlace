# MarketPlace API

A RESTful API for a Customer-to-Customer (C2C) marketplace, developed with Java and Spring Boot.

The application allows users to publish products, browse available listings, and make offers on products published by other users.

The project focuses on backend development, business logic implementation, data persistence, and authentication and authorization using the Spring ecosystem.

## Features

- User registration and management
- Product creation and management
- Public product browsing
- Offer creation and management
- User authentication
- Request validation
- Data persistence using Spring Data JPA

## Technologies

| Technology | Purpose |
|---|---|
| Java | Main programming language |
| Spring Boot | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Data persistence |
| Spring Security | Authentication and authorization |
| Hibernate | ORM |
| Jakarta Validation | Input validation |
| H2 Database | Development and testing database |
| Maven | Dependency and build management |
| Lombok | Boilerplate code reduction |
| JUnit | Automated testing |

## Domain Model

The application is built around three main entities:

### User

Represents a registered marketplace user.

- UUID
- Name
- Email
- Password

A user can publish multiple products and make multiple offers.

### Product

Represents a product published by a user.

- UUID
- Name
- Description
- Price
- Created At
- Updated At
- Seller (User)

Each product belongs to a registered user and can receive multiple offers.

### Offer

Represents an offer made by a user on a product.

- UUID
- Offered Price
- Created At
- Updated At
- Buyer (User)
- Product

Each offer belongs to a user and references a product.

## Security

Spring Security is used to protect application resources.

The security configuration distinguishes between public and authenticated operations.

- Public product browsing.
- User registration.
- Authentication for protected operations.
- Authorization for restricted resources.

## Getting Started

### Prerequisites

- Java
- Maven

### Clone the repository

```bash
git clone https://github.com/rafaelreissant/marketPlace.git
```

### Navigate to the application directory

```bash
cd marketPlace
```

### Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## Testing

The project includes automated tests covering different application components, including persistence, validation, and business logic.

Run the test suite with:

```bash
mvn test
```

## Future Improvements

- Advanced product filtering and pagination.
- Improved offer lifecycle management.
- Additional business rules for marketplace transactions.
- Expanded authorization and ownership validation.
- Integration and security testing.

## Author

**Rafael Reis**

Java | Spring Boot | Backend Development

[GitHub](https://github.com/rafaelreissant)
