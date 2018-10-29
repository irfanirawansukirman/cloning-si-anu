package id.gits.gitsmvvmkotlin.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
@Entity(tableName = "localMessages")
data class LocalMessages(
        @ColumnInfo(name = "asBCC")
        var asBCC: Boolean? = false,
        @ColumnInfo(name = "asCC")
        var asCC: Boolean? = false,
        @ColumnInfo(name = "asMain")
        var asMain: Boolean? = false,
        @ColumnInfo(name = "attachmentCount")
        var attachmentCount: Int? = 0,
        @ColumnInfo(name = "attachmentTypes")
        var attachmentTypes: String? = null,
        @ColumnInfo(name = "attachments")
        var attachments: String? = null,
        @ColumnInfo(name = "attributeID")
        var attributeID: String? = null,
        @ColumnInfo(name = "availabilityStatus")
        var availabilityStatus: Int? = 0,
        @ColumnInfo(name = "categories")
        var categories: String? = null,
        @ColumnInfo(name = "content")
        var content: String? = null,
        @ColumnInfo(name = "contentPreview")
        var contentPreview: String? = null,
        @ColumnInfo(name = "contentType")
        var contentType: String? = null,
        @ColumnInfo(name = "createdAt")
        var createdAt: String? = null,
        @ColumnInfo(name = "defaultLabels")
        var defaultLabels: String? = null,
        @ColumnInfo(name = "deliveryStatus")
        var deliveryStatus: Int? = 0,
        @ColumnInfo(name = "editedAt")
        var editedAt: String? = null,
        @ColumnInfo(name = "extra")
        var extra: String? = null,
        @ColumnInfo(name = "firstReadAt")
        var firstReadAt: String? = null,
        @ColumnInfo(name = "hash")
        var hash: String? = null,
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "inReplyTo")
        var inReplyTo: String? = null,
        @ColumnInfo(name = "isArchived")
        var isArchived: Boolean? = false,
        @ColumnInfo(name = "isGroup")
        var isGroup: Boolean? = false,
        @ColumnInfo(name = "isImportant")
        var isImportant: Boolean? = false,
        @ColumnInfo(name = "isTrashed")
        var isTrashed: Boolean? = false,
        @ColumnInfo(name = "lastInteractionAt")
        var lastInteractionAt: String? = null,
        @ColumnInfo(name = "lastReadAt")
        var lastReadAt: String? = null,
        @ColumnInfo(name = "messageClass")
        var messageClass: Int? = 0,
        @ColumnInfo(name = "messageType")
        var messageType: Int? = 0,
        @ColumnInfo(name = "metaFields")
        var metaFields: String? = null,
        @ColumnInfo(name = "participantHash")
        var participantHash: String? = null,
        @ColumnInfo(name = "readByRecipientAt")
        var readByRecipientAt: String? = null,
        @ColumnInfo(name = "readerList")
        var readerList: String? = null,
        @ColumnInfo(name = "receivedAt")
        var receivedAt: String? = null,
        @ColumnInfo(name = "recipientEmail")
        var recipientEmail: String? = null,
        @ColumnInfo(name = "recipientID")
        var recipientID: String? = null,
        @ColumnInfo(name = "recipients")
        var recipients: String? = null,
        @ColumnInfo(name = "recipientsAsBCC")
        var recipientsAsBCC: String? = null,
        @ColumnInfo(name = "recipientsAsCC")
        var recipientsAsCC: String? = null,
        @ColumnInfo(name = "sender")
        var sender: String? = null,
        @ColumnInfo(name = "senderEmail")
        var senderEmail: String? = null,
        @ColumnInfo(name = "sentAt")
        var sentAt: String? = null,
        @ColumnInfo(name = "subject")
        var subject: String? = null,
        @ColumnInfo(name = "subjectPreview")
        var subjectPreview: String? = null,
        @ColumnInfo(name = "tags")
        var tags: String? = null,
        @ColumnInfo(name = "threadID")
        var threadID: String? = null,
        @ColumnInfo(name = "userID")
        var userID: String? = null
)

data class Messages(
    @SerializedName("asBCC")
    var asBCC: Boolean? = false,
    @SerializedName("asCC")
    var asCC: Boolean? = false,
    @SerializedName("asMain")
    var asMain: Boolean? = false,
    @SerializedName("attachmentCount")
    var attachmentCount: Int? = 0,
    @SerializedName("attachmentTypes")
    var attachmentTypes: AttachmentTypes? = AttachmentTypes(),
    @SerializedName("attachments")
    var attachments: List<Any?>? = listOf(),
    @SerializedName("attributeID")
    var attributeID: String? = "",
    @SerializedName("availabilityStatus")
    var availabilityStatus: Int? = 0,
    @SerializedName("categories")
    var categories: List<Any?>? = listOf(),
    @SerializedName("content")
    var content: String? = "",
    @SerializedName("contentPreview")
    var contentPreview: String? = "",
    @SerializedName("contentType")
    var contentType: String? = "",
    @SerializedName("createdAt")
    var createdAt: String? = "",
    @SerializedName("defaultLabels")
    var defaultLabels: List<String?>? = listOf(),
    @SerializedName("deliveryStatus")
    var deliveryStatus: Int? = 0,
    @SerializedName("editedAt")
    var editedAt: Any? = Any(),
    @SerializedName("extra")
    var extra: Extra? = Extra(),
    @SerializedName("firstReadAt")
    var firstReadAt: String? = "",
    @SerializedName("hash")
    var hash: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("inReplyTo")
    var inReplyTo: String? = "",
    @SerializedName("isArchived")
    var isArchived: Boolean? = false,
    @SerializedName("isGroup")
    var isGroup: Boolean? = false,
    @SerializedName("isImportant")
    var isImportant: Boolean? = false,
    @SerializedName("isTrashed")
    var isTrashed: Boolean? = false,
    @SerializedName("lastInteractionAt")
    var lastInteractionAt: Any? = Any(),
    @SerializedName("lastReadAt")
    var lastReadAt: String? = "",
    @SerializedName("messageClass")
    var messageClass: Int? = 0,
    @SerializedName("messageType")
    var messageType: Int? = 0,
    @SerializedName("metaFields")
    var metaFields: MetaFields? = MetaFields(),
    @SerializedName("participantHash")
    var participantHash: String? = "",
    @SerializedName("readByRecipientAt")
    var readByRecipientAt: Any? = Any(),
    @SerializedName("readerList")
    var readerList: List<Any?>? = listOf(),
    @SerializedName("receivedAt")
    var receivedAt: String? = "",
    @SerializedName("recipientEmail")
    var recipientEmail: String? = "",
    @SerializedName("recipientID")
    var recipientID: String? = "",
    @SerializedName("recipients")
    var recipients: List<Recipient?>? = listOf(),
    @SerializedName("recipientsAsBCC")
    var recipientsAsBCC: List<Any?>? = listOf(),
    @SerializedName("recipientsAsCC")
    var recipientsAsCC: List<Any?>? = listOf(),
    @SerializedName("sender")
    var sender: Sender? = Sender(),
    @SerializedName("senderEmail")
    var senderEmail: String? = "",
    @SerializedName("sentAt")
    var sentAt: String? = "",
    @SerializedName("subject")
    var subject: String? = "",
    @SerializedName("subjectPreview")
    var subjectPreview: String? = "",
    @SerializedName("tags")
    var tags: List<Any?>? = listOf(),
    @SerializedName("threadID")
    var threadID: String? = "",
    @SerializedName("userID")
    var userID: String? = ""
)

data class Sender(
    @SerializedName("activeEmail")
    var activeEmail: String? = "",
    @SerializedName("avatarPathLarge")
    var avatarPathLarge: String? = "",
    @SerializedName("avatarPathMedium")
    var avatarPathMedium: String? = "",
    @SerializedName("avatarPathSmall")
    var avatarPathSmall: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("fullName")
    var fullName: String? = "",
    @SerializedName("hash")
    var hash: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("lastName")
    var lastName: String? = ""
)

data class MetaFields(
    @SerializedName("draftType")
    var draftType: String? = "",
    @SerializedName("hash")
    var hash: String? = "",
    @SerializedName("id")
    var id: String? = ""
)

data class Recipient(
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("fullName")
    var fullName: String? = "",
    @SerializedName("hash")
    var hash: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("phoneNumber")
    var phoneNumber: String? = ""
)

data class AttachmentTypes(
    @SerializedName("isArchive")
    var isArchive: Boolean? = false,
    @SerializedName("isAudio")
    var isAudio: Boolean? = false,
    @SerializedName("isDoc")
    var isDoc: Boolean? = false,
    @SerializedName("isFile")
    var isFile: Boolean? = false,
    @SerializedName("isImage")
    var isImage: Boolean? = false,
    @SerializedName("isPDF")
    var isPDF: Boolean? = false,
    @SerializedName("isPlainText")
    var isPlainText: Boolean? = false,
    @SerializedName("isPresentation")
    var isPresentation: Boolean? = false,
    @SerializedName("isSpreadsheet")
    var isSpreadsheet: Boolean? = false,
    @SerializedName("isVideo")
    var isVideo: Boolean? = false
)

data class Extra(
    @SerializedName("data")
    var data: Any? = Any()
)