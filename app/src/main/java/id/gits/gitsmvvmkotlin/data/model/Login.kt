package id.gits.gitsmvvmkotlin.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
data class Login(
        @SerializedName("token")
        val token: String? = "",
        @SerializedName("user")
        val user: User? = User()
)

data class User(
        @SerializedName("activeEmail")
        val activeEmail: String? = "",
        @SerializedName("avatarPathLarge")
        val avatarPathLarge: String? = "",
        @SerializedName("avatarPathMedium")
        val avatarPathMedium: String? = "",
        @SerializedName("avatarPathSmall")
        val avatarPathSmall: String? = "",
        @SerializedName("countryDialCode")
        val countryDialCode: CountryDialCode? = CountryDialCode(),
        @SerializedName("firstName")
        val firstName: String? = "",
        @SerializedName("fullName")
        val fullName: String? = "",
        @SerializedName("hasUsablePassword")
        val hasUsablePassword: Boolean? = false,
        @SerializedName("hash")
        val hash: String? = "",
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("lastName")
        val lastName: String? = "",
        @SerializedName("phoneNumber")
        val phoneNumber: String? = "",
        @SerializedName("status")
        val status: Int? = 0,
        @SerializedName("username")
        val username: String? = ""
)

data class CountryDialCode(
        @SerializedName("code")
        val code: String? = "",
        @SerializedName("dialCode")
        val dialCode: String? = "",
        @SerializedName("name")
        val name: String? = ""
)

@Entity(tableName = "userlogin")
data class UserLogin(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "token")
        var token: String? = null,
        @ColumnInfo(name = "user")
        var user: String? = null
) : Serializable
