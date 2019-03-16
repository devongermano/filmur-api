package io.kotin.api.interfaces

import java.util.*

interface IDeletable {
    val deleted: Boolean;
    val dateDeleted: Date?;
}