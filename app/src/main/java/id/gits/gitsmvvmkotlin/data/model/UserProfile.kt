package id.gits.gitsmvvmkotlin.data.model
import com.google.gson.annotations.SerializedName


/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
data class UserProfile(
    @SerializedName("agendaInvitationStatus")
    val agendaInvitationStatus: Int? = 0,
    @SerializedName("alternatePhone")
    val alternatePhone: String? = "",
    @SerializedName("anniversary")
    val anniversary: String? = "",
    @SerializedName("avatar")
    val avatar: Avatar? = Avatar(),
    @SerializedName("birthday")
    val birthday: String? = "",
    @SerializedName("companyPhone")
    val companyPhone: String? = "",
    @SerializedName("department")
    val department: String? = "",
    @SerializedName("dialCode")
    val dialCode: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("email2")
    val email2: String? = "",
    @SerializedName("email2Title")
    val email2Title: String? = "",
    @SerializedName("email3")
    val email3: String? = "",
    @SerializedName("email3Title")
    val email3Title: String? = "",
    @SerializedName("emailTitle")
    val emailTitle: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("followUpTopic")
    val followUpTopic: String? = "",
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("gender")
    val gender: Int? = 0,
    @SerializedName("hasAccount")
    val hasAccount: Boolean? = false,
    @SerializedName("homeAddress")
    val homeAddress: String? = "",
    @SerializedName("homeCity")
    val homeCity: String? = "",
    @SerializedName("homeCountry")
    val homeCountry: String? = "",
    @SerializedName("homePhone")
    val homePhone: String? = "",
    @SerializedName("homeProvince")
    val homeProvince: String? = "",
    @SerializedName("homeZIP")
    val homeZIP: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("inMyContact")
    val inMyContact: Boolean? = false,
    @SerializedName("isVIP")
    val isVIP: Boolean? = false,
    @SerializedName("jobTitle")
    val jobTitle: String? = "",
    @SerializedName("kidsName")
    val kidsName: List<Any?>? = listOf(),
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("linkedUserID")
    val linkedUserID: String? = "",
    @SerializedName("managersName")
    val managersName: String? = "",
    @SerializedName("middleName")
    val middleName: String? = "",
    @SerializedName("mobilePhone")
    val mobilePhone: String? = "",
    @SerializedName("notes")
    val notes: List<Any?>? = listOf(),
    @SerializedName("office")
    val office: String? = "",
    @SerializedName("phoneNumber")
    val phoneNumber: String? = "",
    @SerializedName("profession")
    val profession: String? = "",
    @SerializedName("sentInvitation")
    val sentInvitation: Boolean? = false,
    @SerializedName("spouseName")
    val spouseName: String? = "",
    @SerializedName("spouseType")
    val spouseType: Int? = 0,
    @SerializedName("suffix")
    val suffix: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("topicsToAvoid")
    val topicsToAvoid: String? = "",
    @SerializedName("topicsToMention")
    val topicsToMention: String? = "",
    @SerializedName("type")
    val type: Int? = 0,
    @SerializedName("workAddress")
    val workAddress: String? = "",
    @SerializedName("workCity")
    val workCity: String? = "",
    @SerializedName("workCountry")
    val workCountry: String? = "",
    @SerializedName("workPhone")
    val workPhone: String? = "",
    @SerializedName("workProvince")
    val workProvince: String? = "",
    @SerializedName("workZIP")
    val workZIP: String? = ""
)

data class Avatar(
    @SerializedName("avatarPathLarge")
    val avatarPathLarge: String? = "",
    @SerializedName("avatarPathMedium")
    val avatarPathMedium: String? = "",
    @SerializedName("avatarPathSmall")
    val avatarPathSmall: String? = ""
)