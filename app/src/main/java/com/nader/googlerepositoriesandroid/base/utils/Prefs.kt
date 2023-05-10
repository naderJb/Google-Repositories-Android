package com.nader.googlerepositoriesandroid.base.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.nader.googlerepositoriesandroid.base.extensions.safe

class Prefs(context: Context) {
    companion object {
        private var sp: SharedPreferences? = null
        private var masterKey: MasterKey? = null

        private fun getInstance(context: Context): SharedPreferences {
            synchronized(this) {
                masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                sp = EncryptedSharedPreferences.create(
                    context,
                    "secret_shared_prefs",
                    masterKey!!,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                return sp!!
            }
        }

        private const val THEME = "theme"
    }

    init {
        getInstance(context)
    }
    fun getTheme(): Int = sp?.getInt(THEME, AppCompatDelegate.MODE_NIGHT_YES).safe(AppCompatDelegate.MODE_NIGHT_YES)

    fun setTheme(theme: Int) {
        sp?.edit()?.putInt(THEME, theme)?.apply()
    }

}