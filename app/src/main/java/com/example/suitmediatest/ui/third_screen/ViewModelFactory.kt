package com.example.suitmediatest.ui.third_screen

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediatest.data.UserRepository
import java.lang.reflect.InvocationTargetException

class ViewModelFactory(private val repository: UserRepository?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(UserRepository::class.java).newInstance(repository)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }

    companion object {
        fun createFactory(activity: Activity, application: Application): ViewModelFactory {
            val context = activity.applicationContext
                ?: throw IllegalStateException("Not yet attached to Application")

            return ViewModelFactory(UserRepository.getInstance(application))
        }
    }
}