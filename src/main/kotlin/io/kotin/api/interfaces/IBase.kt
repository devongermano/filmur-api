package io.kotin.api.interfaces

import org.bson.types.ObjectId
import java.util.*

interface IBase {
    val id: String?
    val dateCreated: Date
    val dateModified: Date
}