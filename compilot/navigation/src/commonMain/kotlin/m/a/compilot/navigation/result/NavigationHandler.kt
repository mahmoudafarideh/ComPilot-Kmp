package m.a.compilot.navigation.result


/**
 * This interface provides methods for setting various types of data to be returned as navigation results.
 */
interface NavigationHandler {

    /**
     * Sets a string value associated with the specified key.
     *
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    fun setString(key: String, value: String)

    /**
     * Sets a key for the navigation result.
     *
     * @param key The key to be set.
     */
    fun setKey(key: String)

    /**
     * Sets a boolean value associated with the specified key.
     *
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    fun setBoolean(key: String, value: Boolean)

    /**
     * Sets an integer value associated with the specified key.
     *
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    fun setInt(key: String, value: Int)
}
