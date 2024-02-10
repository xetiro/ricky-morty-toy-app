# ricky-morty-toy-app
An android app toy project based on the ricky and morty api. Just for fun!

https://rickandmortyapi.com/

First version will be to display a list of episodes.

# App Architecture
Single activity app implemented following the MVVM pattern and uniderectional data flow. 

View (Activity/Fragment) -> ViewModel -> Repository -> API

Also based on Google's guide to app architecture, the app is split in two horizontal layers:

UI Layer -> View and ViewModels
Data Layer -> Repository and Data models

Each screen is an individual feature.

https://developer.android.com/topic/architecture

# Compose UI
All UI elements are developed in Jetpack Compose.

# Test Driven Development
This project is developed in TDD: Write the desirable test first, create the code as you need to make the test pass.

The main targets of unit tests are the Repositories and the ViewModels.
