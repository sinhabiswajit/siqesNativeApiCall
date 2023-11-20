# siqesNativeApiCall
A simple native android application showing a list of quotes from an API service using the MVVM architecture model

The codebase demonstrates the use of Room Database for offline access of the list of quotes as well. Once the application runs using the internet access to call the api, the application will immediately store the quotes in the SQLite using the ROOM service. Next time, even if there's no internet access the application will show the quotes from its offline storage.
