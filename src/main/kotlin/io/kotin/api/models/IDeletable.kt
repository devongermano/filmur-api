package io.kotin.api.models

import java.util.*

interface IDeletable {
    val deleted: Boolean;
    val dateDeleted: Date?;
}