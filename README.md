# JuiceTracker 🥤

JuiceTracker is a simple Android application built to help you keep track of your favorite juices. Log details like name, description, color, and rating, all stored locally on your device.

## ✨ Features

* **Add New Juices:** Easily add new juice entries using a convenient bottom sheet dialog. Record the name, description, color, and your personal rating.
* **View Juice List:** See all your saved juices displayed clearly on the home screen.
* **Juice Details:** Each list item shows the juice's color, name, description, and rating.
* **Edit Entries:** Tap on any juice in the list to open the bottom sheet again, pre-filled with its details, allowing you to make edits.
* **Delete Juices:** Remove juices you no longer want to track with a simple delete button on each list item.
* **Local Storage:** Uses Room database for efficient and persistent local data storage.
* **Modern UI:** Built with Jetpack Compose, Android's modern UI toolkit.
* **View Interoperability:** Demonstrates integrating traditional Android Views (Spinner for color selection, RatingBar for rating input) within a Compose UI.
* **Ad Integration:** Includes an Ad Banner for demo from AdMob SDK.

## 📸 Screenshots

| Home Screen                                    | Add/Edit Juice Screen                              |
| :--------------------------------------------: | :------------------------------------------------: |
| ![Home Screen](placeholder_home_screen.png)    | ![Add/Edit Screen](placeholder_add_edit_screen.png)|
| *Displays the list of saved juices.* | *Bottom sheet for adding or editing juice details.*|

## 💻 Tech Stack & Concepts

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Database:** Room Persistence Library
* **Architecture:** MVVM
* **UI Components:** Material Design Components
* **View Interoperability:** Using `AndroidView` composable to embed XML Views - Spinner, RatingBar, and AdView within Compose.
* **Asynchronous Programming:** Kotlin Coroutines
* **Dependency Injection:** Manual DI)
* **Ads:** Google AdMob
