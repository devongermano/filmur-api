package io.kotin.api.models

// Really I made this for a future migration if Filmur gets too big, and we
// don't want to pay for a 3rd part authentication provider. Should let us know where
// the user's PII is located. Is based off Auth0's identity property...^^^
data class Identity(val user_id: String, val provider: String)