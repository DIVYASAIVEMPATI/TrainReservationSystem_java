# KSDK Train Ticket Reservation System

This is a console-based Train Ticket Reservation System implemented in Java. It provides a basic simulation of real-world train ticket booking, including both Admin and User functionalities.

## 🎯 Features

### Admin Features
- Secure login for Admin
- Add new trains with stops and bhogies
- Set seat availability and base pricing
- View seat availability and train stops

### User Features
- Sign up and login
- Book seats by selecting train and bhogie type
- View ticket price before booking
- View available seats
- Simulate real-time train movement

## 💻 Technologies Used
- Java (Core)
- Multithreading (for train animation)
- ANSI escape codes (for colorful CLI output)

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher

### Compilation
```bash
javac src/TrainReservationSystemd1.java
```

### Execution
```bash
java -cp src TrainReservationSystemd1
```

> Note: This is a demo project; payment and authentication are simulated.
> ANSI escape codes may not render correctly on Windows without enabling virtual terminal processing.

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
