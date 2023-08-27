package com.giftech.terbit.data.source.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.giftech.terbit.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val userFlow: Flow<User> = dataStore.data
        .map { preferences ->
            User(
                nama = preferences[PreferencesKeys.NAMA] ?: "",
                tinggi = preferences[PreferencesKeys.TINGGI] ?: 170,
                berat = preferences[PreferencesKeys.BERAT] ?: 70,
                tglLahir = preferences[PreferencesKeys.TGL_LAHIR] ?: "",
                isMale = preferences[PreferencesKeys.IS_MALE] ?: true
            )
        }

    suspend fun updateUser(user: User) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.NAMA] = user.nama
            preferences[PreferencesKeys.TINGGI] = user.tinggi
            preferences[PreferencesKeys.BERAT] = user.berat
            preferences[PreferencesKeys.TGL_LAHIR] = user.tglLahir
            preferences[PreferencesKeys.IS_MALE] = user.isMale
        }
    }

    private object PreferencesKeys {
        val NAMA = stringPreferencesKey("nama")
        val TINGGI = intPreferencesKey("tinggi")
        val BERAT = intPreferencesKey("berat")
        val TGL_LAHIR = stringPreferencesKey("tgl_lahir")
        val IS_MALE = booleanPreferencesKey("is_male")
    }
}