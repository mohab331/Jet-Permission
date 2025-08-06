# 📱 Jet Permission – Jetpack Compose CameraX App & Permission POC

A modern Android app built using **Jetpack Compose** that demonstrates:

- 🔐 Camera permission handling at runtime
- 📷 Live preview and photo capture with **CameraX**
- ✅ Clean architecture using **ViewModel**, **StateFlow**
- 💉 Dependency injection with **Hilt**
- 🎨 Material 3 UI with enhanced user experience

---

## 🚀 Features

- 📸 **Camera Preview**: Live camera feed using `PreviewView` inside Compose via `AndroidView`
- 🔄 **Permission Handling**: Runtime camera permission via Accompanist Permissions
- 💾 **Photo Capture**: Save image to app-specific directory using `ImageCapture`
- 🧠 **ViewModel + StateFlow**: Manage permission and photo URI state reactively
- 🧼 **Modern UI**: Material 3 `Card`, `ElevatedButton`, and proper UX states for permission flows

---

## 🧱 Tech Stack

| Technology     | Purpose                                      |
|----------------|----------------------------------------------|
| Jetpack Compose| UI Framework                                 |
| CameraX        | Camera preview & image capture               |
| Accompanist    | Permissions handling in Compose              |
| ViewModel      | UI logic & lifecycle-safe state management   |
| StateFlow      | Reactive data streams to the UI              |
| Hilt           | Dependency Injection                         |
| Material 3     | Modern, adaptive design                      |

---
