package com.example.factviewer.utils

enum class WorkMode {
    Online,
    Offline
}

object Settings {
    var currentWorkMode = WorkMode.Online
    var factsAmount = 30
}