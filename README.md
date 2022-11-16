<p align="left">
  <a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
  <a href="#"><img alt="Languages-Kotlin" src="https://flat.badgen.net/badge/Language/Kotlin?icon=https://raw.githubusercontent.com/binaryshrey/Awesome-Android-Open-Source-Projects/master/assets/Kotlin_Logo_icon_white.svg&color=f18e33"/></a>
</p>

# RickAndMorty
This an Android Application that displays all Rick & Morty Characters and their details.

## Approach ##
### Architecture 
This app has a MultiModule, MVVM architecture in place to allow the App to **Scale**, improve Quality and Robustness and Allow the App to Scale. This also makes the App to Scale

<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/CleanArchitecture.jpeg" width="500"/>
This App uses Clean Architecture to ensure:

- Separation of **concern**
- Drive UI from Data Model
- Make functionality easily changeable or droppable 
- Make Code easier to read
- Make use of good practices and Jetpack libraries(Architecture components)

#### Layers
<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/googleclen.png" width="500"/>

**Domain Layer**

- Sits between the UI and Data layer
- Used in this project to encapsulate business logic
- Enables use-cases to be reused in multiple view model
- Defines the repository interface that drives the main functionality

*Data Layer**

- Contains the implementation of business logic(Repository Implementation)
- Gets data from the remote data source
- Cache's remote data to Local Room Database

**Presentation/UI layer**

- This layer is the layer that displays data to the user screen
- Contains view models that are lifecycle friendly and takes code away from our Activity/UI components
- Defines our architecture which is MVVM (Model View View-Model)
- Contains our states that handle logic like loading

## Libraries used

- **Retrofit** - Android Network Client, Used to consume API from RickAndMorty API
- **AirBnB Epoxy** - Epoxy is an Android library for building complex screens in a RecyclerView. Models are automatically generated from custom views or databinding layouts via annotation processing. These models are then used in an EpoxyController to declare what items to show in the RecyclerView.
- **Coroutines** - Used to execute code asynchronously
- **Material Design + XML** - Used to write the UI of the App
- **Material Design** - Give the App a theme and generally improve UI of the App
- **Coil Image** - Image Loading library
- **Lifecycle library** - Majorly to define the ViewModels of the app
- **Navigation** - To navigate to different screens of the App


## Screenshots
<img src="https://user-images.githubusercontent.com/47518452/198730172-3180ea78-e214-4def-9b74-bfc008dc8e66.jpg" width="240" height="510">.<img src="https://user-images.githubusercontent.com/47518452/198730442-369c60e7-e057-454f-90ba-95f9463c8d4f.jpg" width="240" height="510">
<img src="https://user-images.githubusercontent.com/47518452/198730578-3aab5b35-cdf2-4f2a-8c20-f1caabb70b1c.jpg" width="240" height="510">






