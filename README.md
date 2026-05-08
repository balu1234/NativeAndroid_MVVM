# MVVM Android App

A modern Android application demonstrating MVVM (Model-View-ViewModel) architecture with Jetpack Compose, Room database, and Retrofit networking.

## 🚀 Features

### Core Functionality
- **Students Management**: Local Room database with full CRUD operations
- **Todos Management**: API-based task management with Retrofit
- **Employees Management**: Dual implementation (Local Room + Remote API)
- **Custom Dialogs**: Reusable dialog module for user interactions
- **Tab Navigation**: ViewPager2 with TabLayout for seamless navigation

### Architecture Components
- **MVVM Pattern**: Clean separation of concerns
- **Room Database**: Local data persistence with entities, DAOs, and repositories
- **Retrofit**: Network layer for API communication
- **Jetpack Compose**: Modern declarative UI framework
- **LiveData**: Reactive data streams between ViewModels and Views
- **DataBinding**: Traditional XML layouts with binding support

### Technical Stack
- **Kotlin**: Modern programming language
- **Android SDK**: Targeting API 24-35 (Android 7.0+)
- **Material Design 3**: Latest Material Design components
- **Coroutines**: Asynchronous programming support
- **Hilt/Dagger**: Dependency injection (if needed)

## 📱 Modules

### App Module
Main application module containing:
- Activities and Fragments
- ViewModels and Repositories
- Database entities and DAOs
- Network services and models

### Dialog Module (Reusable)
Custom dialog library located in `/dialog/`:
- **Package**: `com.example.customdialog.ui.common.dialog`
- **Components**: `CustomDialog`, `DialogManager`, `WelcomeDialog`, `InfoDialog`, `FeatureDialog`
- **Features**: Material Design 3, customizable, state management
- **Usage**: Can be imported into any Android project

## 🏗️ Build & Deployment

### Development
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew testDebugUnitTest

# Install debug APK
./gradlew installDebug
```

### CI/CD Pipeline
Automated GitHub Actions workflow:
- **Triggers**: Push, Pull Request, Release creation
- **Build Matrix**: Ubuntu latest with JDK 17
- **Testing**: Unit tests, lint checks
- **Artifacts**: Debug/Release APKs, test results, lint reports
- **Releases**: Automatic GitHub releases with signed APK/AAB

### Release Signing
- **Keystore**: `mvvmapp-release.keystore`
- **Configuration**: Release builds are automatically signed
- **Distribution**: Signed APKs ready for Play Store deployment

## 📁 Project Structure

```
MVVMAPP/
├── app/                          # Main application module
│   ├── src/main/
│   │   ├── java/com/example/mvvmapp/
│   │   │   ├── data/
│   │   │   │   ├── local/
│   │   │   │   │   ├── entity/
│   │   │   │   ├── dao/
│   │   │   │   └── db/
│   │   │   ├── remote/
│   │   │   │   ├── model/
│   │   │   │   ├── api/
│   │   │   │   └── network/
│   │   │   └── repository/
│   │   ├── ui/
│   │   │   ├── student/
│   │   │   ├── todo/
│   │   │   └── employee/
│   │   └── res/
│   ├── build.gradle.kts
│   └── mvvmapp-release.keystore
├── dialog/                        # Reusable dialog module
│   ├── src/main/java/com/example/customdialog/ui/common/dialog/
│   └── build.gradle.kts
├── .github/workflows/
│   └── ci-cd.yml              # GitHub Actions workflow
├── settings.gradle.kts
└── README.md
```

## 🧪 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 17 or later
- Android SDK API 24+

### Installation
1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle files
4. Run `./gradlew assembleDebug` to build
5. Install on device or emulator

## 🔧 Configuration

### Gradle Properties
Key configurations in `app/build.gradle.kts`:
- **Application ID**: `com.example.mvvmapp`
- **Min SDK**: 24
- **Target SDK**: 35
- **Version**: 1.0
- **Build Types**: Debug and Release with signing

### Database
- **Name**: `student_db`
- **Version**: 2 (with migration support)
- **Entities**: Student, Employee
- **APIs**: JSONPlaceholder, Dummy REST API

## 📱 Screens

### Students Tab
- Local Room database management
- Add, view, delete students
- DataBinding with RecyclerView

### Todos Tab
- Remote API integration
- Fetch and display tasks
- Error handling and loading states

### Employees (Local) Tab
- Room database for employees
- Full CRUD operations
- Jetpack Compose UI

### Employees (API) Tab
- Network integration with dummy API
- Real-time data fetching
- Error handling and retry logic

## 🎨 UI/UX

### Design System
- Material Design 3 components
- Consistent blue color scheme
- Responsive layouts
- Custom dialog overlays

### Custom Dialogs
- Welcome dialogs on tab open
- Material Design 3 styling
- Emoji icons and animations
- Reusable across all screens

## 🧪 Testing

### Unit Tests
- ViewModel testing
- Repository testing
- DAO testing

### CI/CD Testing
- Automated unit test execution
- Lint analysis
- Build verification

## 📦 Deployment

### Release Process
1. Code pushed to `main` branch
2. GitHub Actions builds release APK
3. APK is signed with keystore
4. GitHub release is created
5. Artifacts are uploaded to release

### Distribution Files
- `app-release.apk`: Signed APK for direct distribution
- `app-release.aab`: Android App Bundle for Play Store

## 🤝 Contributing

1. Fork the repository
2. Create feature branch
3. Make changes with proper MVVM architecture
4. Add tests for new functionality
5. Submit pull request

## 📄 License

This project is for educational purposes demonstrating Android development best practices.

## 🔗 Dependencies

### Core Libraries
- AndroidX Core & Lifecycle
- Room Database
- Retrofit & Gson
- Jetpack Compose & Material 3
- Coroutines

### Development Tools
- Gradle build system
- Kapt annotation processing
- JUnit testing framework
- AndroidX Test libraries

---

**Built with ❤️ using modern Android development practices**
