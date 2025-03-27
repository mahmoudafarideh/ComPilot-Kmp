package m.a.compilot.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform