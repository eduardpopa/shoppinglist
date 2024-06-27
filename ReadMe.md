# BINFO-CEP FCE-14: Semester Project

This project is an android shopping list application.

- ## Functionalities

  The goal is create and manage a persistant shopping list. The application provide the following functionalities:

  1. "New Item" - press this button to create new shopping list item.
  2. "Swipe LEFT" - swipe left to delete the sopping list item.
  3. "Swipe RIGHT" - swipe right and edit the item.
  4. "Long press" - long press to activate reordering.
  5. "Short press" - short press to toggle item.
  6. "Top Menu > Reset" - press to reset the list
  7. "Help" - press to see the application help.

- ## Structure

The application use activities stored under `./src/main/java/uni/eduard/popa/shoppinglist/activities` folder, the main activity `ListItemsActivity` shows
the list of items into a 'RecyclerView'. The actions are executed in backround by using `AsyncTaskLoader` stored under `./src/main/java/uni/eduard/popa/shoppinglist/tasks`
folder and the response is forwared to `Callbacks` stored on `./src/main/java/uni/eduard/popa/shoppinglist/callbacks`.
Data is stored in a local `RoomDatabase` file `shopping-list.db`.

## Run the application

The application was created with Android Studio Iguana | 2023.2.1

### Build and deploy

- Android Studio > Run
