# AccessibleNavigationSwitchDemo
Sample project to demonstrate the status switch of flutter AcccessibleNavigation when app is queried by appium 

## Setting up your machine
### Prerequisites before running the script for Appium setup 
* Set `JAVA_HOME` as an environment variable

* Set `ANDROID_HOME` as an environment variable - pointing to the directory where Android SDK should be setup

* Execute the following scripts to setup your Mac [setup_mac.sh](setup_mac.sh) or Ubuntu [setup_linux.sh](setup_linux.sh) machine automatically 
> The above script will install all dependencies required for implementing / running tests on Android devices. To do the setup for iOS devices, run `appium-doctor` and see the list of dependencies that are missing, and install the same.

> You may be prompted for password or confirmations along the way 

## Running the tests
### Prerequisites:
* Have devices connected / emulators started.

### Tests
This project includes the following tests implemented for Android & iOS devices:

#### Android
* [AppiumAccessibilitySwitchTest.java](src/test/java/com/eot/sample/android/AppiumAccessibilitySwitchTest.java) - run the demo
