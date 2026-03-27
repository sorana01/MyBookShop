# MyBookShop

A desktop application for managing and purchasing books, built with Java and JavaFX. It supports three user roles — Client, Manager, and Courier — each with a dedicated interface and workflow.

---

## Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Data Storage](#data-storage)
- [Order Lifecycle](#order-lifecycle)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Build & Run](#build--run)
- [Running Tests](#running-tests)
- [Screenshots](#screenshots)

---

## About

MyBookShop is a role-based book shop desktop application. Users register with one of three roles and are routed to their respective dashboards. The application persists all data locally as JSON files in the user's home directory.

---

## Features

### Authentication
- User registration with full profile (username, password, full name, address, phone number, email)
- Role selection at registration: **Client**, **Manager**, or **Courier**
- Login with username and password validation

### Manager
- Add new books (title, author, category, price, quantity)
- Edit existing book details
- Delete books from the catalogue
- View all orders placed by clients
- Accept or reject pending orders (accepted orders become visible to couriers)

### Client
- Browse all books currently in stock
- Add books to cart with desired quantity
- View and manage cart contents
- Place orders
- View personal order history and statuses

### Courier
- View all manager-accepted orders
- Mark orders as delivered (status transitions from `ACCEPTED` to `DELIVERED`)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 18 |
| UI Framework | JavaFX 18 |
| Data Binding | Jackson Databind 2.13.4.1 |
| Build Tool | Maven |
| Testing | JUnit 5, TestFX, AssertJ |
| Code Coverage | JaCoCo |
| Utilities | Apache Commons IO |

---

## Project Structure

```
src/
└── main/java/com/example/book_shop/
    ├── controllers/          # JavaFX controllers for each screen
    │   ├── LogInController
    │   ├── RegisterController
    │   ├── ManagerController
    │   ├── ClientController
    │   ├── CourierSeeOrdersController
    │   ├── AddBooksController
    │   ├── EditBooksController
    │   ├── DeleteBooksController
    │   ├── AddToCartController
    │   ├── SeeCartController
    │   ├── PlaceOrderController
    │   ├── ClientSeeOrdersController
    │   ├── ManagerSeeOrdersController
    │   ├── AcceptOrRejectController
    │   └── DeliverOrderController
    ├── model/                # Domain models
    │   ├── Book              # title, author, category, price, quantity
    │   ├── Order             # list of books, user, status, order number
    │   └── User              # username, password, role, full name, address, phone, email
    ├── services/             # Business logic and file I/O
    │   ├── FileSystemService # Resolves paths under ~/.book-shop/
    │   ├── ClientBookService
    │   └── CourierBookService
    └── exceptions/           # Custom application exceptions
```

---

## Data Storage

All data is persisted as JSON files in a dedicated application folder created in the user's home directory:

```
~/.book-shop/
├── users.json
├── books.json
└── orders.json
```

This folder is created automatically on first launch if it does not exist.

---

## Order Lifecycle

```
Client places order
       │
       ▼
  [PENDING]  ◄── Visible to Manager
       │
  Manager reviews
       │
  ┌────┴────┐
  │         │
[ACCEPTED] [REJECTED]
  │
  ▼
Courier picks up
  │
  ▼
[DELIVERED]
```

---

## Getting Started

### Prerequisites

- **Java 17+** (compiled with Java 17 source/target; tested with Java 18)
- **Maven 3.6+**

### Build & Run

```bash
# Clone the repository
git clone https://github.com/sorana01/MyBookShop.git
cd MyBookShop

# Build and run
mvn clean javafx:run

# Or build a shaded (fat) JAR and run it
mvn clean package
java -jar target/MyBookShop-1.0-SNAPSHOT-shaded.jar
```

---

## Running Tests

```bash
mvn test
```

To generate a code coverage report with JaCoCo:

```bash
mvn clean package
# Report is generated at target/site/jacoco/index.html
```

---

## Screenshots

![image](https://user-images.githubusercontent.com/74464853/221370204-ae403d85-58b0-4f85-aa87-6a34f5675139.png)
![image](https://user-images.githubusercontent.com/74464853/221370223-f84d57ef-1456-497f-8d13-4eb7ddacc1b8.png)

* Manager page
---
![image](https://user-images.githubusercontent.com/74464853/221419581-6308e099-cbc9-4572-9b48-12e11ca83561.png)

* Client page
---
![image](https://user-images.githubusercontent.com/74464853/221419607-37cf9bb3-cb7e-44ba-b3a2-42cbb0b13b17.png)

* Courier page
---
![image](https://user-images.githubusercontent.com/74464853/221419629-376d7d6e-5652-404a-a7ed-7a39223687a2.png)
