package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.webtekproject.smarhealthconsultancy.Model_Classes.*

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME, null,
    DB_VERSION
) {
    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "UsedDB"

        private val TABLE_DOCTOR = "Doctor"

        private var KEY_Dr_ID = "Dr_ID"
        private val KEY_Dr_Name = "Dr_Name"
        private val KEY_Dr_Current_Status = "Dr_Current_Status"
        private val KEY_Dr_Speciality = "Dr_Speciality"
        private val KEY_Dr_Qualification = "Dr_Qualification"
        private val KEY_Dr_Contact = "Dr_Contact"
        private val KEY_Dr_Pass = "Dr_Pass"


        private val TABLE_HOSPITAL = "Hospital"

        private var KEY_Hosp_ID = "Hosp_ID"
        private val KEY_Hosp_Name = "Hosp_Name"
        private val KEY_Hosp_Location = "Hosp_Location"
        private val KEY_Available_Beds = "Available_Beds"
        private val KEY_Booked_Beds = "Booked_Beds"
        private val KEY_Available_OT = "Available_OT"
        private val KEY_Booked_OT = "Booked_OT"
        private val KEY_Hosp_Contact = "Hosp_Contact"
        private val KEY_Hosp_Pass = "Hosp_Pass"


        private val TABLE_CLINIC = "Clinic"

        private var KEY_Clinic_ID = "Clinic_ID"
        private val KEY_Clinic_Name = "Clinic_Name"
        private val KEY_Clinic_Location = "Clinic_Location"
        private val KEY_Clinic_Contact = "Clinic_Contact"
        private val KEY_Clinic_Pass = "Clinic_Pass"


        private val TABLE_PHARMACY = "Pharmacy"

        private var KEY_Pharma_ID = "Pharma_ID"
        private val KEY_Pharma_Name = "Pharma_Name"
        private val KEY_Pharma_Location = "Pharma_Location"
        private val KEY_Pharma_Contact = "Pharma_Contact"
        private val KEY_Pharma_Pass = "Pharma_Pass"


        private val TABLE_DOC_HOSP = "Doc_Hosp_Availability"
        private val TABLE_DOC_CLINIC = "Doc_Clinic_Availability"


        private val TABLE_HOSP_ROOM = "Hosp_Room"

        private var KEY_Room_ID = "Room_ID"
        private val KEY_Room_Type = "Room_Type"
        private val KEY_Beds_Booked = "Beds_Booked"
        private val KEY_Beds_Available = "Beds_Available"


        private val TABLE_HOSP_BED = "Hosp_Bed"

        private var KEY_Bed_ID = "Bed_ID"


        private val TABLE_MEDS = "Medication"

        private var KEY_Med_ID = "Med_ID"
        private val KEY_Med_Name = "Med_Name"
        private val KEY_Med_Brand = "Med_Brand"
        private val KEY_Med_Use = "Med_Use"


        private val TABLE_AVAIL_MEDS = "Availability_Meds"

        private val KEY_Med_Quant = "Med_Quant"
        private val KEY_Price_10 = "Price_10"


        private val TABLE_PATIENT = "Patient"

        private var KEY_Patient_ID = "Patient_ID"
        private val KEY_Patient_Name = "Patient_Name"
        private val KEY_Patient_Address = "Patient_Address"
        private val KEY_Patient_Contact = "Patient_Contact"
        private val KEY_Patient_Pass = "Patient_Pass"


        private val TABLE_DOC_APPOINT = "Dr_Appointment"

        private var KEY_App_Id = "App_Id"
        private val KEY_Start_at = "Start_at"


        private val TABLE_CLINIC_APPOINT = "Clinic_Appointment"


        private val TABLE_BOOK_HOSP = "Book_Hosp_Stay"

        private val KEY_Hosp_Stay_Start = "Hosp_Stay_Start"
        private val KEY_Hosp_Stay_End = "Hosp_Stay_End"


        private val TABLE_BOOK_SURGERY = "Hosp_Surgery"

        private val KEY_OT_Booked = "OT_Booked"
        private val KEY_OT_Start = "OT_Start"
        private val KEY_OT_End = "OT_End"


        private val TABLE_PRESC = "Prescription"

        private var KEY_Presc_ID = "Presc_ID"
        private val KEY_Date = "Date"
        private val KEY_Nxt_Appoint = "Nxt_Appoint"


        private val TABLE_MED_PRESC = "Med_Prescribed"

        private val KEY_Dose = "Dose"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //To change body of created functions use File | Settings | File Templates.

        //creating Table Doctor
        val CREATE_TABLE_DOCTOR = ("CREATE TABLE " + TABLE_DOCTOR + " (" +
                KEY_Dr_ID + " TEXT PRIMARY KEY, " + KEY_Dr_Name + " TEXT," + KEY_Dr_Current_Status + " INTEGER, " + KEY_Dr_Speciality + " TEXT, " + KEY_Dr_Qualification + " TEXT," + KEY_Dr_Contact + " INTEGER UNIQUE, " + KEY_Dr_Pass + " TEXT " + ")")

        db?.execSQL(CREATE_TABLE_DOCTOR)


        //creating Table Hospital
        val CREATE_TABLE_HOSPITAL =
            ("CREATE TABLE " + TABLE_HOSPITAL + " (" + KEY_Hosp_ID + " TEXT PRIMARY KEY, " + KEY_Hosp_Name + " TEXT," + KEY_Hosp_Location + " TEXT, " + KEY_Available_Beds + " INTEGER, " + KEY_Booked_Beds + " INTEGER," + KEY_Available_OT + " INTEGER, " + KEY_Booked_OT + " INTEGER, " + KEY_Hosp_Contact + " INTEGER," + KEY_Hosp_Pass + " TEXT " + ")")

        db?.execSQL(CREATE_TABLE_HOSPITAL)


        //creating Table Clinic
        val CREATE_TABLE_CLINIC = ("CREATE TABLE " + TABLE_CLINIC + " (" +
                KEY_Clinic_ID + " TEXT PRIMARY KEY, " + KEY_Clinic_Name + " TEXT," + KEY_Clinic_Location + " TEXT, " + KEY_Clinic_Contact + " INTEGER UNIQUE, " + KEY_Clinic_Pass + " TEXT  " + ")")

        db?.execSQL(CREATE_TABLE_CLINIC)


        //creating Table Pharmacy

        val CREATE_TABLE_PHARMACY = ("CREATE TABLE " + TABLE_PHARMACY + " ("
                + KEY_Pharma_ID + " TEXT PRIMARY KEY, " + KEY_Pharma_Name + " TEXT," + KEY_Pharma_Location + " TEXT, " + KEY_Pharma_Contact + " INTEGER UNIQUE, " + KEY_Pharma_Pass + " TEXT " + ")")

        db?.execSQL(CREATE_TABLE_PHARMACY)


        //creating Table Doc_Hosp_Availability

        val CREATE_TABLE_DOC_HOSP = ("CREATE TABLE " + TABLE_DOC_HOSP + " ("
                + KEY_Dr_ID + " TEXT, " + KEY_Hosp_ID + " TEXT," +
                "FOREIGN KEY (" + KEY_Dr_ID + ") REFERENCES " + TABLE_DOCTOR + "(" + KEY_Dr_ID + ")," +
                "FOREIGN KEY (" + KEY_Hosp_ID + ") REFERENCES " + TABLE_HOSPITAL + "(" + KEY_Hosp_ID + "))")

        db?.execSQL(CREATE_TABLE_DOC_HOSP)


        //creating Table Doc_Clinic_Availability

        val CREATE_TABLE_DOC_CLINIC = ("CREATE TABLE " + TABLE_DOC_CLINIC + " ("
                + KEY_Dr_ID + " TEXT, " + KEY_Clinic_ID + " TEXT," +
                "FOREIGN KEY (" + KEY_Dr_ID + ") REFERENCES " + TABLE_DOCTOR + "(" + KEY_Dr_ID + ")," +
                "FOREIGN KEY (" + KEY_Clinic_ID + ") REFERENCES " + TABLE_CLINIC + "(" + KEY_Clinic_ID + "))")

        db?.execSQL(CREATE_TABLE_DOC_CLINIC)


        //creating Table Hosp_Room

        val CREATE_TABLE_HOSP_ROOM = ("CREATE TABLE " + TABLE_HOSP_ROOM + " ("
                + KEY_Room_ID + " TEXT PRIMARY KEY, " + KEY_Hosp_ID + " TEXT," + KEY_Room_Type + " TEXT," + KEY_Beds_Booked + " INTEGER, " + KEY_Beds_Available + " INTEGER, " +
                "FOREIGN KEY (" + KEY_Hosp_ID + ") REFERENCES " + TABLE_HOSPITAL + "(" + KEY_Hosp_ID + "))")

        db?.execSQL(CREATE_TABLE_HOSP_ROOM)


        //creating Table Hosp_Bed

        val CREATE_TABLE_HOSP_BED = ("CREATE TABLE " + TABLE_HOSP_BED + " ("
                + KEY_Bed_ID + " TEXT PRIMARY KEY, " + KEY_Room_ID + " TEXT," +
                "FOREIGN KEY (" + KEY_Room_ID + ") REFERENCES " + TABLE_HOSPITAL + "(" + KEY_Room_ID + "))")

        db?.execSQL(CREATE_TABLE_HOSP_BED)

/*
       //creating Table Medication

        val CREATE_TABLE_MEDS = ("CREATE TABLE " + TABLE_MEDS + " ("
                + KEY_Med_ID + " TEXT PRIMARY KEY, " + KEY_Med_Name + " TEXT," + KEY_Med_Brand + " TEXT, "  + KEY_Med_Use + " TEXT" + ")" )

        db?.execSQL(CREATE_TABLE_MEDS)


        //creating Table Availability_Meds

        val CREATE_TABLE_AVAIL_MEDS = ("CREATE TABLE " + TABLE_AVAIL_MEDS + " ("
                + KEY_Pharma_ID + " TEXT, " + KEY_Med_ID + " TEXT,"  + KEY_Med_Quant + " INTEGER," + KEY_Price_10 + " REAL, "  +
                "FOREIGN KEY ("+ KEY_Pharma_ID + ") REFERENCES " + TABLE_PHARMACY +"(" + KEY_Pharma_ID + "),"+
                "FOREIGN KEY ("+ KEY_Med_ID + ") REFERENCES " + TABLE_MEDS +"(" + KEY_Med_ID + ")," + ")" )

        db?.execSQL(CREATE_TABLE_AVAIL_MEDS)*/


        //creating Table Patient

        val CREATE_TABLE_PATIENT = ("CREATE TABLE " + TABLE_PATIENT + " ("
                + KEY_Patient_ID + " TEXT PRIMARY KEY, " + KEY_Patient_Name + " TEXT," + KEY_Patient_Address + " TEXT, "  + KEY_Patient_Contact + " INTEGER UNIQUE, " + KEY_Patient_Pass+ " TEXT " + ")" )

        db?.execSQL(CREATE_TABLE_PATIENT)


        //creating Table Dr_Appointment

        val CREATE_TABLE_DOC_APPOINT = ("CREATE TABLE " + TABLE_DOC_APPOINT + " ("
                + KEY_App_Id + " TEXT PRIMARY KEY, " + KEY_Patient_ID + " TEXT,"  + KEY_Dr_ID + " TEXT," + KEY_Start_at + " TEXT, " + KEY_Clinic_ID + " TEXT,"  + KEY_Hosp_ID + " TEXT,"  +
                "FOREIGN KEY ("+ KEY_Patient_ID + ") REFERENCES " + TABLE_PATIENT +"(" + KEY_Patient_ID + "),"+
                "FOREIGN KEY ("+ KEY_Clinic_ID + ") REFERENCES " + TABLE_CLINIC +"(" + KEY_Clinic_ID + ")," +
                "FOREIGN KEY ("+ KEY_Dr_ID + ") REFERENCES " + TABLE_DOCTOR +"(" + KEY_Dr_ID + ")," +
                "FOREIGN KEY ("+ KEY_Hosp_ID + ") REFERENCES " + TABLE_HOSPITAL +"(" + KEY_Hosp_ID + ")" +  ")" )

        db?.execSQL(CREATE_TABLE_DOC_APPOINT)

/*
        //creating Table Clinic_Appointment

        val CREATE_TABLE_CLINIC_APPOINT = ("CREATE TABLE " + TABLE_CLINIC_APPOINT + " ("
                + KEY_App_Id + " TEXT, " + KEY_Patient_ID + " TEXT,"  + KEY_Dr_ID + " TEXT," + KEY_Start_at + " TEXT, " + KEY_Clinic_ID + " TEXT,"  +
                "FOREIGN KEY ("+ KEY_App_Id + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_App_Id + "),"+
                "FOREIGN KEY ("+ KEY_Start_at + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Start_at + "),"+
                "FOREIGN KEY ("+ KEY_Patient_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Patient_ID + "),"+
                "FOREIGN KEY ("+ KEY_Clinic_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Clinic_ID + ")," +
                "FOREIGN KEY ("+ KEY_Dr_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Dr_ID + ")," +  ")" )

        db?.execSQL(CREATE_TABLE_CLINIC_APPOINT)


        //creating Table Book_Hosp_Stay

        val CREATE_TABLE_BOOK_HOSP = ("CREATE TABLE " + TABLE_BOOK_HOSP + " ("
                + KEY_App_Id + " TEXT, " + KEY_Patient_ID + " TEXT,"  + KEY_Dr_ID + " TEXT," + KEY_Hosp_ID + " TEXT,"  +KEY_Hosp_Stay_Start + " TEXT, " + KEY_Hosp_Stay_End + " TEXT,"  +  KEY_Bed_ID + " TEXT, "+ KEY_Room_ID + " TEXT,"  +
                "FOREIGN KEY ("+ KEY_Room_ID + ") REFERENCES " + TABLE_HOSP_ROOM +"(" + KEY_Room_ID + ")," +
                "FOREIGN KEY ("+ KEY_App_Id + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_App_Id + "),"+
                "FOREIGN KEY ("+ KEY_Patient_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Patient_ID + ")," +
                "FOREIGN KEY ("+ KEY_Hosp_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Hosp_ID + ")," +
                "FOREIGN KEY ("+ KEY_Dr_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Dr_ID + ")," +
                "FOREIGN KEY ("+ KEY_Bed_ID + ") REFERENCES " + TABLE_HOSP_BED +"(" + KEY_Bed_ID +"))" )

        db?.execSQL(CREATE_TABLE_BOOK_HOSP)


        //creating Table Hosp_Surgery

        val CREATE_TABLE_BOOK_SURGERY = ("CREATE TABLE " + TABLE_BOOK_SURGERY + " ("
                + KEY_App_Id + " TEXT, " + KEY_Patient_ID + " TEXT,"  + KEY_Dr_ID + " TEXT," + KEY_Hosp_ID + " TEXT,"  +KEY_OT_Booked + " TEXT, " + KEY_OT_Start + " TEXT,"  +  KEY_OT_End + " TEXT, " +
                "FOREIGN KEY ("+ KEY_App_Id + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_App_Id + "),"+
                "FOREIGN KEY ("+ KEY_Patient_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Patient_ID + ")," +
                "FOREIGN KEY ("+ KEY_OT_Booked + ") REFERENCES " + TABLE_HOSP_ROOM +"(" + KEY_Room_ID + ")," +
                "FOREIGN KEY ("+ KEY_Hosp_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Hosp_ID + ")," +
                "FOREIGN KEY ("+ KEY_Dr_ID + ") REFERENCES " + TABLE_DOC_APPOINT +"(" + KEY_Dr_ID + "))")

        db?.execSQL(CREATE_TABLE_BOOK_SURGERY)


        //creating Table Prescription

        val CREATE_TABLE_PRESC = ("CREATE TABLE " + TABLE_PRESC + " ("
                + KEY_Presc_ID + " TEXT PRIMARY KEY, " + KEY_Patient_ID + " TEXT," + KEY_Dr_ID + " TEXT,"  + KEY_Date + " TEXT," + KEY_Nxt_Appoint + " TEXT, "  +
                "FOREIGN KEY ("+ KEY_Patient_ID + ") REFERENCES " + TABLE_PATIENT +"(" + KEY_Patient_ID + ")," +
                "FOREIGN KEY ("+ KEY_Dr_ID + ") REFERENCES " + TABLE_DOCTOR +"(" + KEY_Dr_ID + "))")

        db?.execSQL(CREATE_TABLE_PRESC)


        //creating Table Med_Prescribed

        val CREATE_TABLE_MED_PRESC = ("CREATE TABLE " + TABLE_MED_PRESC + " ("
                + KEY_Presc_ID + " TEXT PRIMARY KEY, " + KEY_Med_ID + " TEXT," + KEY_Dose + " TEXT, "  +
                "FOREIGN KEY ("+ KEY_Presc_ID + ") REFERENCES " + TABLE_PRESC +"(" + KEY_Presc_ID + ")," +
                "FOREIGN KEY ("+ KEY_Med_ID + ") REFERENCES " + TABLE_MEDS +"(" + KEY_Med_ID + "))")

        db?.execSQL(CREATE_TABLE_MED_PRESC)*/

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //To change body of created functions use File | Settings | File Templates.

        db!!.execSQL(
            "DROP TABLE IF EXISTS " + TABLE_DOCTOR + TABLE_HOSPITAL + TABLE_AVAIL_MEDS + TABLE_BOOK_HOSP + TABLE_BOOK_SURGERY + TABLE_CLINIC + TABLE_CLINIC_APPOINT + TABLE_DOC_APPOINT + TABLE_DOC_CLINIC + TABLE_DOC_HOSP + TABLE_HOSP_BED + TABLE_HOSP_ROOM + TABLE_MEDS + TABLE_MED_PRESC + TABLE_PATIENT + TABLE_PHARMACY + TABLE_PRESC
        )

        onCreate(db)

    }


    //Adding Tables


    //Working on Doctor's Table

    fun addDoctor(doc: Doctor_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Dr_ID, doc.Dr_ID)
        contentValues.put(KEY_Dr_Name, doc.Dr_Name)
        contentValues.put(KEY_Dr_Speciality, doc.Dr_Speciality)
        contentValues.put(KEY_Dr_Current_Status, doc.Dr_Current_Status)
        contentValues.put(KEY_Dr_Qualification, doc.Dr_Qualification)
        contentValues.put(KEY_Dr_Contact, doc.Dr_Contact)
        contentValues.put(KEY_Dr_Pass, doc.Dr_Pass)


        val success = db.insert(TABLE_DOCTOR, null, contentValues)
        db.close()
        return success
    }


    //Working on Hospital's Table

    fun addHospital(hosp: Hospital_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Hosp_ID, hosp.Hosp_ID)
        contentValues.put(KEY_Hosp_Name, hosp.Hosp_Name)
        contentValues.put(KEY_Hosp_Location, hosp.Hosp_Location)
        contentValues.put(KEY_Available_Beds, hosp.Available_Beds)
        contentValues.put(KEY_Booked_Beds, hosp.Booked_Beds)
        contentValues.put(KEY_Available_OT, hosp.Available_OT)
        contentValues.put(KEY_Booked_OT, hosp.Booked_OT)
        contentValues.put(KEY_Hosp_Contact, hosp.Hosp_Contact)
        contentValues.put(KEY_Hosp_Pass, hosp.Hosp_Pass)


        val success = db.insert(TABLE_HOSPITAL, null, contentValues)
        db.close()
        return success
    }


    //Working on Clinic's Table

    fun addClinic(clinic: Clinic_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Clinic_ID, clinic.Clinic_ID)
        contentValues.put(KEY_Clinic_Name, clinic.Clinic_Name)
        contentValues.put(KEY_Clinic_Location, clinic.Clinic_Location)
        contentValues.put(KEY_Clinic_Contact, clinic.Clinic_Contact)
        contentValues.put(KEY_Clinic_Pass, clinic.Clinic_Pass)

        val success = db.insert(TABLE_CLINIC, null, contentValues)
        db.close()
        return success
    }


    //Working on Pharmacy's Table

    fun addPharmacy(pharma: Pharmacy_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Pharma_ID, pharma.Pharma_ID)
        contentValues.put(KEY_Pharma_Name, pharma.Pharma_Name)
        contentValues.put(KEY_Pharma_Location, pharma.Pharma_Location)
        contentValues.put(KEY_Pharma_Contact, pharma.Pharma_Contact)
        contentValues.put(KEY_Pharma_Pass, pharma.Pharma_Pass)

        val success = db.insert(TABLE_PHARMACY, null, contentValues)
        db.close()
        return success
    }


    //Working on Doc_Hosp Link

    fun addDoc_Hosp(dh: Doc_Hosp_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Dr_ID, dh.Dr_ID)
        contentValues.put(KEY_Hosp_ID, dh.Hosp_ID)

        val success = db.insert(TABLE_DOC_HOSP, null, contentValues)
        db.close()
        return success
    }


    //Working on Doc_Clinic Link

    fun addDoc_Clinic(dc: Doc_Clinic_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Dr_ID, dc.Dr_ID)
        contentValues.put(KEY_Clinic_ID, dc.Clinic_ID)

        val success = db.insert(TABLE_DOC_CLINIC,
            null, contentValues)
        db.close()
        return success
    }


    //Working on Hospital Room's Table

    fun addHosp_Room(hr: Hosp_Room_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Room_ID, hr.Room_ID)
        contentValues.put(KEY_Hosp_ID, hr.Hosp_ID)
        contentValues.put(KEY_Room_Type, hr.Room_Type)
        contentValues.put(KEY_Beds_Booked, hr.Beds_Booked)
        contentValues.put(KEY_Beds_Available, hr.Beds_Available)

        val success = db.insert(TABLE_HOSP_ROOM, null, contentValues)
        db.close()
        return success
    }


    //Working on Hospital Bed's Table

    fun addHosp_Bed(hb: Hosp_Bed_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Bed_ID, hb.Bed_ID)
        contentValues.put(KEY_Room_ID, hb.Room_ID)

        val success = db.insert(TABLE_HOSP_BED, null, contentValues)
        db.close()
        return success
    }

/*
    //Working on Med's Table

    fun addMeds (med: Med_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Med_ID, med.Med_ID)
        contentValues.put(KEY_Med_Name, med.Med_Name)
        contentValues.put(KEY_Med_Brand, med.Med_Brand)
        contentValues.put(KEY_Med_Use, med.Med_Use)

        val success = db.insert(TABLE_MEDS, null, contentValues)
        db.close()
        return success
    }


    //Working on Med Availability's Table

    fun addAvail_Med (med_avail: Avail_Med_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Med_ID, med_avail.Med_ID)
        contentValues.put(KEY_Pharma_ID, med_avail.Pharma_ID)
        contentValues.put(KEY_Med_Quant, med_avail.Med_Quant)
        contentValues.put(KEY_Price_10, med_avail.Price_10)

        val success = db.insert(TABLE_AVAIL_MEDS, null, contentValues)
        db.close()
        return success
    }
*/

    //Working on Patient's Table

    fun addPatient (pat: Patient_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Patient_ID, pat.Patient_ID)
        contentValues.put(KEY_Patient_Name, pat.Patient_Name)
        contentValues.put(KEY_Patient_Address, pat.Patient_Address)
        contentValues.put(KEY_Patient_Contact, pat.Patient_Contact)
        contentValues.put(KEY_Patient_Pass,  pat.Patient_Pass)

        val success = db.insert(TABLE_PATIENT, null, contentValues)
        db.close()
        return success
    }


    //Working on Doc_Appointment's Table

    fun addDoc_App (doc_app: Dr_App_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_Id, doc_app.App_Id)
        contentValues.put(KEY_Patient_ID, doc_app.Patient_ID)
        contentValues.put(KEY_Dr_ID, doc_app.Dr_ID)
        contentValues.put(KEY_Start_at, doc_app.Start_at)
        contentValues.put(KEY_Clinic_ID, doc_app.Clinic_ID)
        contentValues.put(KEY_Hosp_ID, doc_app.Hosp_ID)

        val success = db.insert(TABLE_DOC_APPOINT, null, contentValues)
        db.close()
        return success
    }

/*
    //Working on Doc_Clinic's Table

    fun addClinc_App (clinic_app: Clinic_App_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_Id, clinic_app.App_Id)
        contentValues.put(KEY_Patient_ID, clinic_app.Patient_ID)
        contentValues.put(KEY_Dr_ID, clinic_app.Dr_ID)
        contentValues.put(KEY_Start_at, clinic_app.Start_at)
        contentValues.put(KEY_Clinic_ID, clinic_app.Clinic_ID)

        val success = db.insert(TABLE_CLINIC_APPOINT, null, contentValues)
        db.close()
        return success
    }


    //Working on Hosp_Stay's Table

    fun addHosp_Stay (hosp_stay: Hosp_Stay_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_Id, hosp_stay.App_Id)
        contentValues.put(KEY_Patient_ID, hosp_stay.Patient_ID)
        contentValues.put(KEY_Dr_ID, hosp_stay.Dr_ID)
        contentValues.put(KEY_Hosp_ID, hosp_stay.Hosp_ID)
        contentValues.put(KEY_Hosp_Stay_Start, hosp_stay.Hosp_Stay_Start)
        contentValues.put(KEY_Hosp_Stay_End, hosp_stay.Hosp_Stay_End)
        contentValues.put(KEY_Room_ID, hosp_stay.Room_ID)
        contentValues.put(KEY_Bed_ID, hosp_stay.Bed_ID)

        val success = db.insert(TABLE_BOOK_HOSP, null, contentValues)
        db.close()
        return success
    }


    //Working on Hosp_Surgery's Table

    fun addHosp_Surg (hosp_surg: Hosp_Surgery_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_Id, hosp_surg.App_Id)
        contentValues.put(KEY_Patient_ID, hosp_surg.Patient_ID)
        contentValues.put(KEY_Dr_ID, hosp_surg.Dr_ID)
        contentValues.put(KEY_Hosp_ID, hosp_surg.Hosp_ID)
        contentValues.put(KEY_OT_Start, hosp_surg.OT_Start)
        contentValues.put(KEY_OT_End, hosp_surg.OT_End)
        contentValues.put(KEY_OT_Booked, hosp_surg.OT_Booked)

        val success = db.insert(TABLE_BOOK_SURGERY, null, contentValues)
        db.close()
        return success
    }


    //Working on Prescription's Table

    fun addPresc (presc: Prescription_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Presc_ID, presc.Presc_ID)
        contentValues.put(KEY_Patient_ID, presc.Patient_ID)
        contentValues.put(KEY_Dr_ID, presc.Dr_ID)
        contentValues.put(KEY_Date, presc.Date)
        contentValues.put(KEY_Nxt_Appoint, presc.Nxt_Appoint)

        val success = db.insert(TABLE_PRESC, null, contentValues)
        db.close()
        return success
    }


    //Working on Med Prescriotion's Table

    fun addMed_Presc (med_presc: Med_Presc_Model) : Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_Presc_ID, med_presc.Presc_ID)
        contentValues.put(KEY_Med_ID, med_presc.Med_ID)
        contentValues.put(KEY_Dose, med_presc.Dose)

        val success = db.insert(TABLE_MED_PRESC, null, contentValues)
        db.close()
        return success
    }

*/

    //Reading Tables

    //Reading Doctor's Table Data

    fun viewDoctors(): List<Doctor_Model> {

        val Doc_List: ArrayList<Doctor_Model> = ArrayList<Doctor_Model>()
        val selectQuery = "SELECT * FROM $TABLE_DOCTOR"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Dr_ID: String
        var Dr_Name: String
        var Dr_Speciality: String
        var Dr_Current_Status: Int
        var Dr_Qualification: String
        var Dr_Contact: Int
        var Dr_Pass: String

        if (cursor.moveToFirst()) {
            do {
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Dr_Name = cursor.getString(cursor.getColumnIndex("Dr_Name"))
                Dr_Speciality = cursor.getString(cursor.getColumnIndex("Dr_Speciality"))
                Dr_Current_Status = cursor.getInt(cursor.getColumnIndex("Dr_Current_Status"))
                Dr_Qualification = cursor.getString(cursor.getColumnIndex("Dr_Qualification"))
                Dr_Contact = cursor.getInt(cursor.getColumnIndex("Dr_Contact"))
                Dr_Pass = cursor.getString(cursor.getColumnIndex("Dr_Pass"))

                val doc = Doctor_Model(
                    Dr_ID,
                    Dr_Name,
                    Dr_Current_Status,
                    Dr_Speciality,
                    Dr_Qualification,
                    Dr_Contact,
                    Dr_Pass
                )
                Doc_List.add(doc)
            } while (cursor.moveToNext())
        }

        return Doc_List
    }


    //Reading Hospital's Table Data

    fun viewHospital(): List<Hospital_Model> {

        val Hosp_List: ArrayList<Hospital_Model> = ArrayList<Hospital_Model>()
        val selectQuery = "SELECT * FROM $TABLE_HOSPITAL "
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Hosp_ID: String
        var Hosp_Name: String
        var Hosp_Location: String
        var Available_Beds: Int
        var Booked_Beds: Int
        var Available_OT: Int
        var Booked_OT: Int
        var Hosp_Contact: Int
        var Hosp_Pass: String

        if (cursor.moveToFirst()) {
            do {
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))
                Hosp_Name = cursor.getString(cursor.getColumnIndex("Hosp_Name"))
                Hosp_Location = cursor.getString(cursor.getColumnIndex("Hosp_Location"))
                Available_Beds = cursor.getInt(cursor.getColumnIndex("Available_Beds"))
                Booked_Beds = cursor.getInt(cursor.getColumnIndex("Booked_Beds"))
                Available_OT = cursor.getInt(cursor.getColumnIndex("Available_OT"))
                Booked_OT = cursor.getInt(cursor.getColumnIndex("Booked_OT"))
                Hosp_Contact = cursor.getInt(cursor.getColumnIndex("Hosp_Contact"))
                Hosp_Pass = cursor.getString(cursor.getColumnIndex("Hosp_Pass"))

                val hosp = Hospital_Model(
                    Hosp_ID,
                    Hosp_Name,
                    Hosp_Location,
                    Available_Beds,
                    Booked_Beds,
                    Available_OT,
                    Booked_OT,
                    Hosp_Contact,
                    Hosp_Pass
                )
                Hosp_List.add(hosp)
            } while (cursor.moveToNext())
        }
        db.close()
        return Hosp_List

    }

    //Reading Clinic's Table Data

    fun viewClinic(): List<Clinic_Model> {

        val Clinic_List: ArrayList<Clinic_Model> = ArrayList<Clinic_Model>()
        val selectQuery = "SELECT * FROM $TABLE_CLINIC"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Clinic_ID: String
        var Clinic_Name: String
        var Clinic_Location: String
        var Clinic_Contact: Int
        var Clinic_Pass: String

        if (cursor.moveToFirst()) {
            do {
                Clinic_ID = cursor.getString(cursor.getColumnIndex("Clinic_ID"))
                Clinic_Name = cursor.getString(cursor.getColumnIndex("Clinic_Name"))
                Clinic_Location = cursor.getString(cursor.getColumnIndex("Clinic_Location"))
                Clinic_Contact = cursor.getInt(cursor.getColumnIndex("Clinic_Contact"))
                Clinic_Pass = cursor.getString(cursor.getColumnIndex("Clinic_Pass"))

                val clinic = Clinic_Model(Clinic_ID, Clinic_Name, Clinic_Location, Clinic_Contact, Clinic_Pass)
                Clinic_List.add(clinic)
            } while (cursor.moveToNext())
        }
        db.close()
        return Clinic_List
    }


    //Reading Pharmacy's Table Data

    fun viewPharmacy(): List<Pharmacy_Model> {

        val Pharma_List: ArrayList<Pharmacy_Model> = ArrayList<Pharmacy_Model>()
        val selectQuery = "SELECT * FROM $TABLE_PHARMACY"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Pharma_ID: String
        var Pharma_Name: String
        var Pharma_Location: String
        var Pharma_Contact: Int
        var Pharma_Pass: String

        if (cursor.moveToFirst()) {
            do {
                Pharma_ID = cursor.getString(cursor.getColumnIndex("Pharma_ID"))
                Pharma_Name = cursor.getString(cursor.getColumnIndex("Pharma_Name"))
                Pharma_Location = cursor.getString(cursor.getColumnIndex("Pharma_Location"))
                Pharma_Contact = cursor.getInt(cursor.getColumnIndex("Pharma_Contact"))
                Pharma_Pass = cursor.getString(cursor.getColumnIndex("Pharma_Pass"))

                val pharma = Pharmacy_Model(Pharma_ID, Pharma_Name, Pharma_Location, Pharma_Contact, Pharma_Pass)
                Pharma_List.add(pharma)
            } while (cursor.moveToNext())
        }

        return Pharma_List
    }


    //Reading Doc_Hosp Link Data

    fun viewDoc_Hosp(): List<Doc_Hosp_Model> {

        val Dr_Hosp_List: ArrayList<Doc_Hosp_Model> = ArrayList<Doc_Hosp_Model>()
        val selectQuery = "SELECT * FROM $TABLE_DOC_HOSP"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Dr_ID: String
        var Hosp_ID: String

        if (cursor.moveToFirst()) {
            do {
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))

                val Dr_Hosp = Doc_Hosp_Model(Dr_ID, Hosp_ID)
                Dr_Hosp_List.add(Dr_Hosp)
            } while (cursor.moveToNext())
        }
        db.close()
        return Dr_Hosp_List
    }


    //Reading Doc_Clinic Link Data

    fun viewDoc_Clinic(): List<Doc_Clinic_Model> {

        val Dr_Clinic_List: ArrayList<Doc_Clinic_Model> = ArrayList<Doc_Clinic_Model>()
        val selectQuery = "SELECT * FROM $TABLE_DOC_CLINIC"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Dr_ID: String
        var Clinic_ID: String

        if (cursor.moveToFirst()) {
            do {
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Clinic_ID = cursor.getString(cursor.getColumnIndex("Clinic_ID"))

                val Dr_Clinic = Doc_Clinic_Model(Dr_ID, Clinic_ID)
                Dr_Clinic_List.add(Dr_Clinic)
            } while (cursor.moveToNext())
        }
        db.close()
        return Dr_Clinic_List
    }


    //Reading on Hospital Room's Table Data

    fun viewHosp_Room(): List<Hosp_Room_Model> {

        val Hosp_Room_List: ArrayList<Hosp_Room_Model> = ArrayList<Hosp_Room_Model>()
        val selectQuery = "SELECT * FROM $TABLE_HOSP_ROOM"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Room_ID: String
        var Hosp_ID: String
        var Room_Type: String
        var Beds_Booked: Int
        var Beds_Available: Int

        if (cursor.moveToFirst()) {
            do {
                Room_ID = cursor.getString(cursor.getColumnIndex("Room_ID"))
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))
                Room_Type = cursor.getString(cursor.getColumnIndex("Room_Type"))
                Beds_Booked = cursor.getInt(cursor.getColumnIndex("Beds_Booked"))
                Beds_Available = cursor.getInt(cursor.getColumnIndex("Beds_Available"))


                val Hosp_Room = Hosp_Room_Model(Room_ID, Hosp_ID, Room_Type, Beds_Booked, Beds_Available)
                Hosp_Room_List.add(Hosp_Room)
            } while (cursor.moveToNext())
        }
        db.close()
        return Hosp_Room_List
    }


    //Reading Hospital Bed's Table Data

    fun viewHosp_Bed(): List<Hosp_Bed_Model> {

        val Bed_List: ArrayList<Hosp_Bed_Model> = ArrayList<Hosp_Bed_Model>()
        val selectQuery = "SELECT * FROM $TABLE_DOC_CLINIC"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Bed_ID: String
        var Room_ID: String

        if (cursor.moveToFirst()) {
            do {
                Bed_ID = cursor.getString(cursor.getColumnIndex("Bed_ID"))
                Room_ID = cursor.getString(cursor.getColumnIndex("Room_ID"))

                val Beds = Hosp_Bed_Model(Bed_ID, Room_ID)
                Bed_List.add(Beds)
            } while (cursor.moveToNext())
        }

        return Bed_List
    }

/*
    //Reading Med's Table Data

    fun viewMeds () : List <Med_Model> {

        val Meds_List : ArrayList <Med_Model> = ArrayList < Med_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_MEDS"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var Med_ID : String
        var Med_Name : String
        var Med_Brand : String
        var Med_Use : String

        if (cursor.moveToFirst()) {
            do {
                Med_ID = cursor.getString(cursor.getColumnIndex("Med_ID"))
                Med_Name = cursor.getString(cursor.getColumnIndex("Med_Name"))
                Med_Brand = cursor.getString(cursor.getColumnIndex("Med_Brand"))
                Med_Use = cursor.getString(cursor.getColumnIndex("Med_Use"))

                val meds = Med_Model (Med_ID, Med_Name, Med_Brand, Med_Use)
                Meds_List.add(meds)
            }
            while (cursor.moveToNext())
        }

        return Meds_List
    }


    //Reading Med Availability's Table Data

    fun viewAvail_Med  () : List <Avail_Med_Model> {

        val AvailMeds_List : ArrayList <Avail_Med_Model> = ArrayList < Avail_Med_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_AVAIL_MEDS"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var Med_ID : String
        var Pharma_ID : String
        var Med_Quant : Int
        var Price_10 : Double

        if (cursor.moveToFirst()) {
            do {
                Med_ID = cursor.getString(cursor.getColumnIndex("Med_ID"))
                Pharma_ID = cursor.getString(cursor.getColumnIndex("Pharma_ID"))
                Med_Quant = cursor.getInt(cursor.getColumnIndex("Med_Quant"))
                Price_10 = cursor.getDouble(cursor.getColumnIndex("Price_10"))

                val meds = Avail_Med_Model (Pharma_ID, Med_ID, Med_Quant, Price_10)
                AvailMeds_List.add(meds)
            }
            while (cursor.moveToNext())
        }

        return AvailMeds_List
    }
*/

    //Reading Patient's Table Data

    fun viewPatient () : List <Patient_Model> {

        val Patient_List : ArrayList <Patient_Model> = ArrayList < Patient_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_PATIENT"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var Patient_ID : String
        var Patient_Name : String
        var Patient_Address : String
        var Patient_Contact : Int
        var Patient_Pass : String

        if (cursor.moveToFirst()) {
            do {
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Patient_Name = cursor.getString(cursor.getColumnIndex("Patient_Name"))
                Patient_Address = cursor.getString(cursor.getColumnIndex("Patient_Address"))
                Patient_Contact = cursor.getInt(cursor.getColumnIndex("Patient_Contact"))
                Patient_Pass = cursor.getString(cursor.getColumnIndex("Patient_Pass"))

                val patients = Patient_Model (Patient_ID, Patient_Name, Patient_Address, Patient_Contact, Patient_Pass)
                Patient_List.add(patients)
            }
            while (cursor.moveToNext())
        }

        return Patient_List
    }


    //Reading Doc_Appointment's Table Data

    fun viewDoc_App  () : List <Dr_App_Model> {

        val dr_app_List : ArrayList <Dr_App_Model> = ArrayList < Dr_App_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_DOC_APPOINT"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var App_Id : String
        var Patient_ID : String
        var Dr_ID : String
        var Start_at : String
        var Clinic_ID : String
        var Hosp_ID : String

        if (cursor.moveToFirst()) {
            do {
                App_Id = cursor.getString(cursor.getColumnIndex("App_Id"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Start_at = cursor.getString(cursor.getColumnIndex("Start_at"))
                Clinic_ID = cursor.getString(cursor.getColumnIndex("Clinic_ID"))
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))

                val dr_app = Dr_App_Model (App_Id, Patient_ID, Dr_ID, Start_at, Clinic_ID, Hosp_ID)
                dr_app_List.add(dr_app)
            }
            while (cursor.moveToNext())
        }

        return dr_app_List
    }

/*
    //Reading Doc_Clinic's Table Data

    fun viewClinc_App  () : List <Clinic_App_Model> {

        val clinic_app_List : ArrayList <Clinic_App_Model> = ArrayList < Clinic_App_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_DOC_APPOINT"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var App_Id : String
        var Patient_ID : String
        var Dr_ID : String
        var Start_at : String
        var Clinic_ID : String

        if (cursor.moveToFirst()) {
            do {
                App_Id = cursor.getString(cursor.getColumnIndex("App_Id"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Start_at = cursor.getString(cursor.getColumnIndex("Start_at"))
                Clinic_ID = cursor.getString(cursor.getColumnIndex("Clinic_ID"))

                val clinic_app = Clinic_App_Model (App_Id, Patient_ID, Dr_ID, Start_at, Clinic_ID)
                clinic_app_List.add(clinic_app)
            }
            while (cursor.moveToNext())
        }

        return clinic_app_List
    }


    //Reading Hosp_Stay's Table Data

    fun viewHosp_Stay () : List <Hosp_Stay_Model> {

        val hosp_stay_List : ArrayList <Hosp_Stay_Model> = ArrayList < Hosp_Stay_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_BOOK_HOSP"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var App_Id : String
        var Patient_ID : String
        var Dr_ID : String
        var Hosp_ID : String
        var Hosp_Stay_Start : String
        var Hosp_Stay_End : String
        var Room_ID : String
        var Bed_ID : String

        if (cursor.moveToFirst()) {
            do {
                App_Id = cursor.getString(cursor.getColumnIndex("App_Id"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))
                Hosp_Stay_Start = cursor.getString(cursor.getColumnIndex("Hosp_Stay_Start"))
                Hosp_Stay_End = cursor.getString(cursor.getColumnIndex("Hosp_Stay_End"))
                Room_ID = cursor.getString(cursor.getColumnIndex("Room_ID"))
                Bed_ID = cursor.getString(cursor.getColumnIndex("Bed_ID"))

                val hosp_stay = Hosp_Stay_Model (App_Id, Patient_ID, Dr_ID, Hosp_Stay_Start, Hosp_ID, Hosp_Stay_End, Room_ID, Bed_ID)
                hosp_stay_List.add(hosp_stay)
            }
            while (cursor.moveToNext())
        }

        return hosp_stay_List
    }


    //Reading Hosp_Surgery's Table Data

    fun viewHosp_Surg () : List <Hosp_Surgery_Model> {

        val hosp_surgery_List : ArrayList <Hosp_Surgery_Model> = ArrayList < Hosp_Surgery_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_BOOK_SURGERY"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var App_Id : String
        var Patient_ID : String
        var Dr_ID : String
        var Hosp_ID : String
        var OT_Start : String
        var OT_End : String
        var OT_Booked : String

        if (cursor.moveToFirst()) {
            do {
                App_Id = cursor.getString(cursor.getColumnIndex("App_Id"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Hosp_ID = cursor.getString(cursor.getColumnIndex("Hosp_ID"))
                OT_Start = cursor.getString(cursor.getColumnIndex("OT_Start"))
                OT_End = cursor.getString(cursor.getColumnIndex("OT_End"))
                OT_Booked = cursor.getString(cursor.getColumnIndex("OT_Booked"))

                val hosp_surg = Hosp_Surgery_Model (App_Id, Patient_ID, Dr_ID, Hosp_ID, OT_Booked, OT_Start, OT_End)
                hosp_surgery_List.add(hosp_surg)
            }
            while (cursor.moveToNext())
        }

        return hosp_surgery_List
    }


    //Reading Prescription's Table Data

    fun viewPresc () : List <Prescription_Model> {

        val presc_List : ArrayList <Prescription_Model> = ArrayList < Prescription_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_PRESC"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var Presc_ID : String
        var Patient_ID : String
        var Dr_ID : String
        var Date : String
        var Nxt_Appoint : String

        if (cursor.moveToFirst()) {
            do {
                Presc_ID = cursor.getString(cursor.getColumnIndex("Presc_ID"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Date = cursor.getString(cursor.getColumnIndex("Date"))
                Nxt_Appoint = cursor.getString(cursor.getColumnIndex("Nxt_Appoint"))

                val presc = Prescription_Model (Presc_ID, Patient_ID, Dr_ID, Date, Nxt_Appoint)
                presc_List.add(presc)
            }
            while (cursor.moveToNext())
        }

        return presc_List
    }


    //Reading Med Prescriotion's Table Data

    fun viewMed_Presc  () : List <Med_Presc_Model> {

        val presc_List : ArrayList <Med_Presc_Model> = ArrayList < Med_Presc_Model > ()
        val selectQuery = "SELECT * FROM $TABLE_MED_PRESC"
        val db = this.readableDatabase
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery (selectQuery, null)
        }
        catch (e: SQLException){
            db. execSQL (selectQuery)
            return ArrayList()
        }

        var Presc_ID : String
        var Med_ID : String
        var Dose : String

        if (cursor.moveToFirst()) {
            do {
                Presc_ID = cursor.getString(cursor.getColumnIndex("Presc_ID"))
                Med_ID = cursor.getString(cursor.getColumnIndex("Med_ID"))
                Dose = cursor.getString(cursor.getColumnIndex("Dose"))

                val presc = Med_Presc_Model (Presc_ID, Med_ID, Dose)
                presc_List.add(presc)
            }
            while (cursor.moveToNext())
        }

        return presc_List
    }

*/

}