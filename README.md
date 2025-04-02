# 🏠 IBI Home Test

I wasn’t able to fully complete the assignment regarding showing favorite products with Room due to some onClick issues you may have noticed.  
Hope you enjoy reviewing the work I did manage to complete. (but it does save to the db :) )

---

## 🚀 Project Architecture

The app uses **two separate activities**:
- `LoginActivity` for handling all authentication and biometric logic
- `MainActivity` for displaying the app content with bottom navigation

This was done intentionally for **security purposes** (avoiding unauthorized access to the main app via back navigation) and to maintain **separation of concerns** between the login flow and the rest of the application.

---

## ✨ Features

- **Login with biometric support** (Fingerprint)
- **Bottom navigation** with 3 sections:
  - **Products List**
  - **Favorites**
  - **Settings**
- **Products List** loaded from [DummyJSON API](https://dummyjson.com/)
- **Product Details** page with image, title, description, and price
- **Favorites toggle** using local Room database (in progress)
- **Dark/Light mode toggle** in settings

---

## 🧠 Tech Stack

- **Kotlin**
- **MVVM** Architecture
- **Hilt** for Dependency Injection
- **Retrofit** for network calls
- **Room** for local database
- **Jetpack Navigation** and **ViewModel**
- **Lottie** animations
- **BiometricPrompt API**
- **Material Design 3**

---

## 🛠 How to Run

1. Clone this repo
2. Open with Android Studio (Giraffe+ recommended)
3. Build & run on an emulator or physical device (API 26+)
4. Login using:
   - Username: `user`
   - Password: `password`
   - Or use biometric (if enrolled)

---

## 👋 Author

Developed by **Netanel Amar**  
📧 [NetanelCA2@gmail.com](mailto:NetanelCA2@gmail.com)

---
