package org.me2you.itroll

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform