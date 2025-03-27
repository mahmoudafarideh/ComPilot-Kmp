package m.a.compilot.navigation.result

import androidx.core.bundle.Bundle

class ComposeResult(val savedStateHandle: Bundle) {

    fun getBoolean(key: String): Boolean? {
        return when {
            savedStateHandle.containsKey(key) -> savedStateHandle.getBoolean(key)
            else -> null
        }
    }

    fun getString(key: String): String? {
        return when {
            savedStateHandle.containsKey(key) -> savedStateHandle.getString(key)
            else -> null
        }
    }

    fun containsKey(key: String): Boolean {
        return savedStateHandle.containsKey(key)
    }

    fun getInt(key: String): Int {
        return savedStateHandle.getInt(key)
    }

    fun getDouble(key: String): Double {
        return savedStateHandle.getDouble(key)
    }

    fun getFloat(key: String): Float {
        return savedStateHandle.getFloat(key)
    }
}