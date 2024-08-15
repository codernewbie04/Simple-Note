# Simple Note App
**Simple Note** is an Android application that allows users to create, edit, delete, and view notes. The app uses Room for local storage and follows the MVVM architecture.

## Features

-   **Create Note**: Users can add new notes with a title and description.
-   **Edit Note**: Users can update the content of existing notes.
-   **Delete Note**:  Users can remove notes from the list by swipe right or left.
-   **Undo Delete**: Allows users to undo the deletion of a note within a short time frame.
-   **View Notes**: A list of all saved notes is displayed on the main screen.
-   **Empty State**: Displays a message when no notes are available.

## Technologies Used

-   **Kotlin**: Programming language for Android development.
-   **Room**: Android's SQLite Object Mapping library.
-   **MVVM Architecture**: Model-View-ViewModel pattern for separating the UI logic from business logic.
-   **LiveData**: Observes data and updates the UI accordingly.
-   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
-   **Data Binding**: Binds UI components in your layouts to data sources in your app using a declarative format.


## Project Structure

- **`data`**: Contains data models and the Room database setup.
  - **`model/Note.kt`**: Defines the `Note` data class, which represents a note entity in the database.
  - **`repository/NoteRepository.kt`**: Handles data operations, such as inserting, updating, and deleting notes.
  - **`database/NoteDao.kt`**: Data Access Object (DAO) interface for performing database operations.
  - **`database/SimpleNoteDatabase.kt`**: Abstract class that provides an instance of the database.

- **`ui`**: Contains the user interface components.
  - **`MainActivity.kt`**: The main activity displaying the list of notes.
  - **`ManipulateNoteActivity.kt`**: Activity for adding or editing notes.
  - **`adapter/NoteAdapter.kt`**: RecyclerView adapter for displaying notes in a list.
  - **`base/BaseRecyclerViewAdapter.kt`**: A reusable base class for RecyclerView adapters.
  - **`base/BaseActivity.kt`**: A reusable base class for Activity.

- **`viewmodel`**: Contains the ViewModel classes.
  - **`NoteViewModel.kt`**: ViewModel that manages the note data and business logic for the UI.

- **`utils`**: Contains utility classes and functions.
  - **`ExtensionsFunction.kt`**: Utility functions, such as formatting timestamps.

## How to Build and Run

1.  **Clone the repository**: 
    `git clone https://github.com/yourusername/simple-note.git` 
    
3.  **Open in Android Studio**:
    -   Open the project in Android Studio.
    
4.  **Build and Run**:
    
    -   Build the project using Android Studio.
    -   Run the app on an emulator or a physical device.
5.  **Install**:
	-   You can also download it directly [here](https://github.com/codernewbie04/Simple-Note/releases/download/release/app-debug.apk)

## Screenshots


<table>
  <tr>
	  <td><img src='https://github.com/codernewbie04/Simple-Note/blob/develop/assets/list.jpg?raw=true'></td>
	   <td><img src='https://github.com/codernewbie04/Simple-Note/blob/develop/assets/add.jpg?raw=true'></td>
	   <td><img src='https://github.com/codernewbie04/Simple-Note/blob/develop/assets/empty.jpg?raw=true'></td>
  </tr>
</table>

