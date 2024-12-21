package com.example.wansati
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val bio: String,
    val birthday: String,
    val location: String,
    val profession: String,
    val education: String,
    val profilePictureUri: String?
)

