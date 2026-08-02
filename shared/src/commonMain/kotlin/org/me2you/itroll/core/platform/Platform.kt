package org.me2you.itroll.core.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform