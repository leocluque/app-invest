package com.example.network.local

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.Directory.PACKAGE_NAME

object PreferencesHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    const val packageName = PACKAGE_NAME
    private const val SHARED_PREFERENCES_NAME = "$PACKAGE_NAME.SHARED_PREFERENCES"

    private const val PREF_SESSION_COOKIE = "$SHARED_PREFERENCES_NAME.PREF_SESSION_COOKIE"
    private const val PREF_BASIC_AUTH = "$SHARED_PREFERENCES_NAME.PREF_BASIC_AUTH"

    var sessionCookie: String
        get() = sharedPreferences.getString(PREF_SESSION_COOKIE, "").toString()
        set(value) = sharedPreferences.edit().putString(PREF_SESSION_COOKIE, value).apply()

    var basicAuth: String
        get() = sharedPreferences.getString(PREF_BASIC_AUTH, "").toString()
        set(value) = sharedPreferences.edit().putString(PREF_BASIC_AUTH, value).apply()

    fun clearBasicAuth() {
        sharedPreferences.edit().putString(PREF_BASIC_AUTH, "").apply()
    }

    fun clearSharedPref() {
        sharedPreferences.edit().clear().apply()
    }

}
