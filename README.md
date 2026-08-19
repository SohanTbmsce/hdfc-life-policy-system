# HDFC Life Policy Claims Console

A simple Java console application developed to manage HDFC Life insurance policies and handle policy claims.

The main purpose of this project is not just to build a working application, but also to understand how different Java concepts and software design principles can be applied together in a small real-world style project.

The project covers Java Collections, SOLID principles, Design Patterns, custom exception handling and basic file handling.

---

## About the Project

This application maintains a set of HDFC Life insurance policies and provides functionality to:

- Store and retrieve policies
- Look up policies using their policy number
- Maintain unique customer names
- Display policies in sorted order
- Calculate premiums based on policy type
- Create and process insurance claims
- Order claims based on their urgency
- Notify registered observers when a claim status changes
- Handle invalid policy numbers and claim amounts
- Maintain an audit log for filed claims

The application is implemented using plain Java without Spring or any other framework.

---

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections Framework
- SOLID Principles
- Design Patterns
- Custom Exceptions
- File Handling
- Git & GitHub

No Java Streams are used in this project as required by the assignment.

---

## Project Structure

```text
hdfc-life-policy-system/
│
├── src/
│   └── com/
│       └── hdfc/
│
│           ├── Main.java
│           │
│           ├── model/
│           │   ├── Policy.java
│           │   ├── TermLifePolicy.java
│           │   ├── UlipPolicy.java
│           │   ├── EndowmentPolicy.java
│           │   ├── Claim.java
│           │   └── Urgency.java
│           │
│           ├── store/
│           │   └── PolicyStore.java
│           │
│           ├── config/
│           │   └── AppConfig.java
│           │
│           ├── factory/
│           │   └── PolicyFactory.java
│           │
│           ├── strategy/
│           │   ├── PremiumStrategy.java
│           │   ├── TermPremiumStrategy.java
│           │   ├── UlipPremiumStrategy.java
│           │   ├── EndowmentPremiumStrategy.java
│           │   └── PremiumCalculator.java
│           │
│           ├── observer/
│           │   ├── ClaimObserver.java
│           │   ├── ClaimEventPublisher.java
│           │   ├── InAppNotifier.java
│           │   └── BranchLetterNotifier.java
│           │
│           ├── service/
│           │   ├── ClaimService.java
│           │   └── AuditLogger.java
│           │
│           └── exception/
│               ├── PolicyServiceException.java
│               ├── PolicyNotFoundException.java
│               ├── InvalidClaimException.java
│               └── UnknownPolicyTypeException.java
│
├── README.md
└── .gitignore
