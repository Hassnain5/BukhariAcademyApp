package com.hasnain.application

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Enable Firebase disk persistence for offline capabilities
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
