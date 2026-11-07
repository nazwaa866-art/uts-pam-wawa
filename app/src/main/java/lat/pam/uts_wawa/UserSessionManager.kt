package lat.pam.uts_wawa

import android.content.Context
import android.content.SharedPreferences

object UserSessionManager {
    private const val PREF_NAME = "kopi_nyemil_session"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_FULL_NAME = "full_name"
    private const val KEY_ADDRESS = "address"
    private const val KEY_PATOKAN = "patokan"
    private const val KEY_SELECTED_FOOD = "selected_food"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserName(context: Context, name: String) {
        val cleanName = if (name.isNotBlank()) name else "Nazwa"
        getPrefs(context).edit()
            .putString(KEY_USER_NAME, cleanName)
            .putString(KEY_FULL_NAME, cleanName)
            .apply()
    }

    fun getUserName(context: Context): String {
        return getPrefs(context).getString(KEY_USER_NAME, "Nazwa") ?: "Nazwa"
    }

    fun saveAddress(context: Context, fullName: String, address: String, patokan: String) {
        val cleanName = if (fullName.isNotBlank()) fullName else getUserName(context)
        getPrefs(context).edit()
            .putString(KEY_USER_NAME, cleanName)
            .putString(KEY_FULL_NAME, cleanName)
            .putString(KEY_ADDRESS, address)
            .putString(KEY_PATOKAN, patokan)
            .apply()
    }

    fun getFullName(context: Context): String {
        return getPrefs(context).getString(KEY_FULL_NAME, getUserName(context)) ?: getUserName(context)
    }

    fun getAddress(context: Context): String {
        return getPrefs(context).getString(KEY_ADDRESS, "") ?: ""
    }

    fun getPatokan(context: Context): String {
        return getPrefs(context).getString(KEY_PATOKAN, "") ?: ""
    }

    fun setSelectedFood(context: Context, foodName: String) {
        getPrefs(context).edit().putString(KEY_SELECTED_FOOD, foodName).apply()
    }

    fun getSelectedFood(context: Context): String {
        return getPrefs(context).getString(KEY_SELECTED_FOOD, "Kopi Susu Gula Aren") ?: "Kopi Susu Gula Aren"
    }
}