package com.hasnain.application.models

data class TeacherAttendenceModel(
    var date: String? = null,
    var isPresent: String? = null
) {
    // No-argument constructor required for Firebase
    constructor() : this(null, null)
}
