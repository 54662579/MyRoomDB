# MyRoomDB
This project aims to show the implementation of Room Database.

## Getting Started
### 1. Create an android project with empty activity
### 2. Add dependendies to your build gradle 
In build gradle ((Module:app)

    def room_version = "1.1.1"
    implementation "android.arch.persistence.room:runtime:$room_version"
    annotationProcessor "android.arch.persistence.room:compiler:$rootProject.roomVersion"
    androidTestImplementation "android.arch.persistence.room:testing:$rootProject.roomVersion"

    def lifecycle_version = "1.1.1"
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"
    annotationProcessor "android.arch.lifecycle:compiler:$rootProject.archLifecycleVersion"
--------------------------------
In build gradle (Project: myroomdbdemo)

    ext {
    roomVersion = '1.1.1'
    archLifecycleVersion = '1.1.1'
    }
--------------------------------
### 3. Create an entity class with annotation @Entity(tableName="Your Table Name")

<img src="https://github.com/54662579/MyRoomDB/blob/master/image/entity.PNG" />

### 4. Create a Data Access Object (DAO) interface for CRUD operations
<img src="" />

### 5. Create a abstract Room Database class that extends RoomDatabase.

### 6. Create a Database repository that acts as a manager for multiple data sources if applicable

### 7. Create a View Model that communicates between the user interfaces (UI) and the data repository. 
