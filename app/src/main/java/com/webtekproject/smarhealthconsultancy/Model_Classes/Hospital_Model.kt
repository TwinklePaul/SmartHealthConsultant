package com.webtekproject.smarhealthconsultancy.Model_Classes

class Hospital_Model(
    var Hosp_ID: String,
    val Hosp_Name: String,
    val Hosp_Location: String,
    val Available_Beds: Int,
    val Booked_Beds: Int,
    val Available_OT: Int,
    val Booked_OT: Int,
    val Hosp_Contact: Int,
    val Hosp_Pass: String
)