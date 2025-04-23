# 🧩 Dynamic Form Builder in Jetpack Compose

This project demonstrates how to build a **dynamic form system** using Jetpack Compose, driven by **JSON configurations** and built with **clean, testable architecture (SOLID)** principles.

## 📌 Features

- 🔧 Dynamic form fields from JSON
- ✅ Rule-based validation (e.g. required, match passwords)
- 🔒 Password field with toggle visibility
- 🌍 Error message localization using `strings.xml`
- 🧪 Testable business logic (no context in use cases)
- 🧱 Clean architecture using ViewModel + UseCase + Repository

---

## 🚀 Getting Started

### 1. Clone the repo
```bash
git clone https://github.com/yourusername/jetpack-compose-dynamic-form.git
cd jetpack-compose-dynamic-form
```

### 2. Open in Android Studio
Ensure your Kotlin version matches the one in `libs.versions.toml` (e.g., `1.9.10`).

### 3. Run the app
Run the `MainActivity` as your entry point.

---

## 🔨 Tech Stack

- **Jetpack Compose** – UI
- **Kotlinx Serialization** – JSON parsing
- **Hilt** – Dependency Injection
- **StateFlow + MVI** – ViewModel architecture

---

## 🧱 Architecture

```text
View (Jetpack Compose)
│
├── ViewModel (MVI reducer, state holder)
│   └── UseCase (pure validation logic)
│       └── JSON Repository (form config, toggles)
```

---

## 📝 JSON Sample
```json
{
  "section": [
    {
      "key": "edtUserName",
      "type": "editText",
      "hint": "User Name",
      "rules": { "required": true },
      "errorText": "error_username_required"
    },
    {
      "key": "edtUserPassword",
      "type": "password",
      "hint": "Password",
      "rules": { "required": true },
      "errorText": "error_password_required"
    }
  ]
}
```

---

## 🌍 Localized Error Messages
Define `strings.xml` like this:

```xml
<string name="error_username_required">Username is required</string>
<string name="error_password_required">Password is required</string>
```

In your UI, we resolve string IDs using:
```kotlin
val resId = context.resources.getIdentifier(errorId, "string", context.packageName)
val message = context.getString(resId)
```

---

## 📦 Modules

| Module | Responsibility |
|--------|----------------|
| `FormViewModel.kt` | Handles state and intent processing |
| `ValidateFormUseCase.kt` | Validates values based on JSON rules |
| `FeatureToggleRepository.kt` | Mocks feature toggles from backend |
| `DynamicFormScreen.kt` | Renders form UI dynamically |

---

## 🧪 Testing Strategy
- Unit test `ValidateFormUseCase` with fake field data
- Assert that rules (like `required` and `match`) produce correct string IDs
- No `Context` needed for test execution

---

## 📖 License
MIT

---

## 🙋‍♀️ Contributions
PRs welcome! Suggest enhancements for dropdowns, multi-step forms, or remote configs.

---

Built with ❤️ using Jetpack Compose by [@indexer](https://github.com/indexer)

