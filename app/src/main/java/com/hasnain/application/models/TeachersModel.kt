package com.hasnain.application.models

data class TeachersModel(
    val tid: String? = null,
    val tname: String? = null,
    val username: String? = null,
    val password: String? = null,
    val tsubject: String? = null,
    val tsalary: String? = null,
    val tgender: String? = null,
    var attendanceRecords: MutableMap<String, String> = mutableMapOf()
)
