# 📱 Old Phone Pad Decoder

## 📌 Problem Statement
This project simulates the behavior of a classic mobile phone keypad (like Nokia 3310), where numeric inputs are converted into text using multi-tap input logic.

Example:
- Input: `33#` → Output: `E`
- Input: `4433555 555666#` → Output: `HELLO`

---

## 🧠 Approach
- Implemented multi-tap keypad decoding logic
- Used clean architecture (service + constants + exception)
- Handled special characters:
  - `#` → End of input
  - `*` → Backspace
  - ` ` → Pause
  - `0` → Space

---

## 🏗️ Project Structure
```
src/main/java/com/pushpak/oldphonepad/
 ├── service/
 ├── constants/
 └── exception/

src/test/java/com/pushpak/oldphonepad/
 ├── tests/
 ├── listeners/
 └── utils/
```

---

## ▶️ How to Run

### Run tests
```
mvn clean test
```

### Run using TestNG XML
```
mvn test
```

---

## 🧪 Testing
- Framework: TestNG
- Data-driven testing using DataProvider
- Extent Reports for reporting

---

## 📊 Sample Input/Output

| Input | Output |
|------|--------|
| 33# | E |
| 4433555 555666# | HELLO |
| 2*# | "" |

---

## 🚀 Features
- Production-ready code
- Modular design
- Comprehensive test coverage
- HTML reporting via Extent Reports

---

## 🤖 AI Usage
AI tools (ChatGPT) were used for:
- Code refinement
- Test coverage enhancement
- Documentation support

Core logic and implementation were independently designed.
