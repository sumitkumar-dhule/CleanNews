# News Application

## Get Breaking News from India.
1. Get latest news from India on Home tab of application
2. See detailed news by selecting the News article from Home or SavedNews tab.
3. Bookmark favorite content to read later, access it from SavedNews tab.

## Technical Specification
This is a multi-module project with modularization by feature.
User gets News feeds from remote API "https://newsapi.org" and can cache it locally using ROOM.
Details of News article can be viewed in WebView.

Architecture: MVVM

Libraries: Retrofit, GSON, ROOM, DaggerHilt, Glide, Navigation