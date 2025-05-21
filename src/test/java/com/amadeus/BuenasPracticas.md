# Best Practices for Automation Testing

## 📌 Key Concepts

- **DRY Principle (Don't Repeat Yourself)**  
  Reuse methods instead of repeating the same code in multiple places. Centralize common actions in a `BasePage` and call them when needed.

- **Code Quality**  
  Your code must be:
  - Robust  
  - Readable  
  - Reusable

  Ensure that it is:
  - Well-structured  
  - Commented  
  - Easy to understand for any team member

- **Script Timing**  
  As the project grows, scripting becomes faster due to reuse of:
  - Pages  
  - Locators  
  - Methods  

- **Maintainability**  
  A robust and well-organized framework reduces the cost and time of maintenance.

- **ROI (Return on Investment)**  
  Good automation leads to measurable returns over time, despite initial high costs.

---

## 🧪 Test Runners

- Choose a **Test Runner** that supports **parallel execution** for faster testing.

### Popular Runners:
- **TestNG**
  - Supports multithreading
  - Uses annotations like `@DataProvider`, `@Factory`
- **JUnit**
  - Offers Watchers, Categories, Matchers, Assumptions, Rules
- **Cucumber (BDD)**
  - Parallel execution with similar capabilities

> ⚠️ Avoid switching runners mid-project unless necessary—refactoring takes time.

---

## 🔄 Data Driven Testing (DDT)

- Use the **same test case** to run multiple **data sets**.
- Use `@DataProvider` or load data from:
  - `.properties` files  
  - `.csv` files  
  - Databases  

### Tips:
- Always parameterize your test methods.
- Avoid using iterators inside test cases—prefer `@DataProvider`.

---

## 🔍 Locators - Best Practices

- **Preferred Order**:
  1. ID (must be unique)
  2. Name
  3. CSS Selector
  4. XPath (only if absolutely necessary)

### XPath vs CSS
- CSS is **faster and cleaner**
- XPath is more **flexible** (e.g., accessing parent elements or text)

> ❌ Avoid absolute locators like `/html/body/div/...`  
> ✅ Prefer short, relative locators: `div.classname > span`

### On Mobile:
- **iOS**: Prefer `Predicate`, `Class Chain`  
- **Android**: Use `UISelector`

> Always test your locators to ensure uniqueness.

---

## 🧱 Page Object Pattern (POP)

- Separate test logic from UI interactions.
- Each page should represent only its own elements and actions.

### Good Practices:
- **Respect structure**: Don’t call elements or methods from other pages.
- **Encapsulation**:
  - Locators → Private  
  - Page methods → Public/Protected as needed
- **Class Structure**:
  1. Class declaration  
  2. Locators  
  3. Constructor  
  4. Interaction methods  

- **Naming Conventions**:
  - Classes: `CamelCase` starting with uppercase
  - Methods and variables: `camelCase`, no underscores or hyphens

### Avoid:
- Calling **assertions** inside Page Classes—keep them in test layer.
- Calling `driver` directly in tests—use page methods.
- Exposing `WebElement` or `MobileElement` outside of Page Classes.

> Users of your framework should only know **what** a test is doing, not **how**.

---

## ✅ Final Recommendations

- Keep your test code **clean, modular, and scalable**.
- Focus on **readability** and **maintainability**.
- Follow **consistent naming and structural patterns** across the entire test suite.
- Use your automation framework as a **tool for business value**, ensuring it provides long-term benefits.

