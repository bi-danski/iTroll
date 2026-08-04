package org.me2you.itroll.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform