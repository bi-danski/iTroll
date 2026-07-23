package org.me2you.tvtroll

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform