# Selenium Test Automation Framework with Jenkins CI/CD

A modular and maintainable Selenium test automation framework built using Java, Maven, TestNG, and integrated with GitHub and Jenkins for continuous integration and delivery. Generates rich HTML reports using ExtentReports.

## 🚀 Features

- 🔹 Page Object Model (POM) Design Pattern
- 🔹 TestNG test suites and annotations
- 🔹 Maven for build management
- 🔹 ExtentReports for detailed HTML reporting
- 🔹 Jenkins integration for CI/CD
- 🔹 Public GitHub repo for source control
- 🔹 Easily scalable for more test modules

## 🛠️ Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Git & GitHub
- Jenkins (WAR-based)
- ExtentReports

## 📁 Project Structure
Selenium_Framework/
│
├── src/
│ └── test/
│ ├── java/
│ │ ├── pages/ # Page classes (POM)
│ │ ├── tests/ # TestNG test classes
│ │ └── utils/ # Utility classes
│
├── testSuites/
│ └── testng.xml # TestNG suite file
│
├── reports/
│ └── extent-report.html # Generated report
│
├── pom.xml # Maven dependencies & plugins
└── README.md

## 🧪 How to Run Tests Locally

1. Clone the repo:
   git clone https://github.com/Chitra1504/Selenium_Framework.git
   cd Selenium_Framework
2. Run tests using Maven:
  mvn clean test -DsuiteXmlFile=testSuites/testng.xml
3. View the test report:
  Navigate to /reports/extent-report.html and open it in a browser.

🔄 Jenkins Integration
Configured Jenkins to pull code from this public GitHub repo.

Builds triggered automatically on code push.

Post-build action publishes the extent-report.html via HTML Publisher plugin.

📊 Reports
Generated using ExtentReports.

Includes detailed logs, screenshots (optional), and pass/fail summaries.

