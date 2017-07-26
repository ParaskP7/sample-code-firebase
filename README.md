# Sample Code: Firebase
This repository contains sample code. Its purpose being, to quickly demonstrate Android and software development in general, clean code, best practices, testing and all those other must know goodies.

The below listed skills are the main focus:

1. Architectural Pattern
    1. [Mosby MVP](https://github.com/sockeqwe/mosby) ```(Helper MVP library)```
2. Android Support
    1. [Constraint Layout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) ```(A ViewGroup which allows you to position and size widgets in a flexible way)```
    2. [Recycler View](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) ```(A flexible view for providing a limited window into a large data set)```
    3. [Card View](https://developer.android.com/reference/android/support/v7/widget/CardView.html) ```(A FrameLayout with a rounded corner background and shadow)```
    4. [Multi Dex](https://developer.android.com/reference/android/support/multidex/MultiDex.html) ```(Patches the application context class loader in order to load classes from more than one dex file)```
3. Firebase
    1. [Firebase Database](https://firebase.google.com/docs/database/) ```(Store and sync data with our NoSQL cloud database. Data is synced across all clients in realtime, and remains available when your app goes offline)```
4. Libraries
    1. [Dagger](https://github.com/google/dagger) ```(A fast dependency injector for Android and Java)```
    2. [Butter Knife](https://github.com/JakeWharton/butterknife) ```(Bind Android views and callbacks to fields and methods)```
    3. [RxJava](https://github.com/ReactiveX/RxJava) ```(RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM)```
    4. [Realm](https://github.com/realm/realm-java) ```(Realm is a mobile database: a replacement for SQLite & ORMs)```
    5. [Timber](https://github.com/JakeWharton/timber) ```(A logger with a small, extensible API which provides utility on top of Android's normal Log class)```
    6. [Lottie](https://github.com/airbnb/lottie-android) ```(A library that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile)```
    7. [Joba](https://github.com/dlew/joda-time-android) ```(A version of Joda-Time built with Android in mind)```
5. Code Quality
    1. [Android Lint](https://developer.android.com/studio/write/lint.html) ```(The lint tool checks your Android project source files for potential bugs and optimization improvements for correctness, security, performance, usability, accessibility, and internationalization)```
    2. [Checkstyle](https://github.com/checkstyle/checkstyle) ```(Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. By default it supports the Google Java Style Guide and Sun Code Conventions, but is highly configurable. It can be invoked with an ANT task and a command line program.)```
    3. [PMD](https://pmd.github.io/) ```(PMD is a source code analyzer)```
    4. [Findbugs](http://findbugs.sourceforge.net/) ```(A program which uses static analysis to look for bugs in Java code)```
    5. [Jacoco](https://github.com/jacoco/jacoco) ```(JaCoCo - Java Code Coverage Library)```
6. Tests
    1. [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) ```(Espresso to write concise, beautiful, and reliable Android UI tests)```
    2. [JUnit](https://github.com/junit-team/junit4) ```(A programmer-oriented testing framework for Java)```

# Setup
    
In order to build and experiment with this project you first have to create (or get) a google-services json file that will allow you to connect to the Firebase instance of your choice.  

```
add google-services.json to app module
```
    
# Usage
Use the below command to build the project in order to install it on an Android device for demonstration:

```
gradlew clean build -x check
```

Open an emulator or connect a physical device to experiment with the sample app, use the below command, which first uninstalls and then installs the sample app:

```
gradlew uninstallDebug | gradlew installDebug
```

Or faster yet and targeting a specific device (in our case an emulator)!

```
adb -s emulator-5554 uninstall com.hubrickchallenge.android | adb -s emulator-5554 install app\build\outputs\apk\app-debug.apk
```

![alt tag](https://github.com/ParaskP7/sample-code-firebase/blob/master/demo.jpg)

Use this command in order to run the static code analysis for the project:

```
gradlew check -x test
```

Or even run the below commands to run a specific code quality tool in isolation:

```
gradlew lint
gradlew checkstyle
gradlew pmd
gradlew findbugs
```

Run the project unit tests using this command, Jacoco is included:

```
gradlew test
gradlew testDebugUnitTestCoverage
```

Open an emulator or connect a physical device to run the instrumentation tests using this command:

```
gradlew connectedAndroidTest
```

# PS
I also hope this project will help others to quick understand and grasp all the listed technologies...

**ENJOY YOU**
