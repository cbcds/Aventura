package com.cbcds.aventura.core.navigation

interface Screen {

    val name: String
}

// TODO: remove
class EmptyScreen(override val name: String = "empty") : Screen