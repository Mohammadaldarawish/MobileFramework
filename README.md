# 📱 Mobile Automation Framework

This is a reusable and scalable **Mobile Automation Framework** built using **Appium**, **TestNG**, **Java**, **Maven**, and the **Page Object Model (POM)** design pattern. It’s designed to support automated testing for any mobile app (native or hybrid) with minimal configuration changes.

---

## 🚀 Features

- 🔧 Built using **Appium** with **TestNG** and **Java**
- 📦 Modular architecture using **Page Object Model**
- 🌍 Supports cross-platform (Android/iOS) with minimal config changes
- 🧪 Easy test execution using Maven commands
- 💬 Well-structured test logs and reports
- 🔁 Easily extendable for different mobile applications

---

## 🧰 Tech Stack

- Java  
- Appium  
- TestNG  
- Maven  
- Page Object Model (POM)  
- Git

---

## 📁 Project Structure

MobileFramework/ ├── src/ │ ├── main/ │ │ └── java/ │ │ ├── base/ # Base classes (e.g., BaseTest) │ │ ├── pages/ # Page Object classes │ │ └── utils/ # Utilities (e.g., config, helpers) │ └── test/ │ └── java/ │ └── tests/ # Test classes ├── testng.xml # TestNG configuration ├── pom.xml # Maven build file └── README.md


---

## ⚙️ Prerequisites

- Java 11 or later  
- Maven  
- Appium Server installed & running  
- Android/iOS emulator or real device configured  
- Node.js (for Appium)  
- Git

---

## 🔄 Setup & Installation

1. **Clone the repository**:
   git clone https://github.com/Mohammadaldarawish/MobileFramework.git
   cd MobileFramework

2. Install dependencies: Maven handles dependencies via pom.xml.

3. Start Appium server: "Appuim"
4. Configure your emulator/device in the config file.

▶️ Running Tests
Use Maven to run your tests:
mvn clean test

Reporting
Test execution results are logged via TestNG listeners and standard Maven output.

Customize for Your App
To reuse this framework for another mobile app:

Replace the locators in pages/ with those of your app.
Update configurations for appPackage, appActivity, deviceName, etc.




