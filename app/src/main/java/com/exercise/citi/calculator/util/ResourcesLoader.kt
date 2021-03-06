package com.exercise.citi.calculator.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.*
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesLoader @Inject constructor(@ApplicationContext private val context: Context) {

    private val resources: Resources
        get() = context.resources

    fun getString(@StringRes resource: Int): String {
        return try {
            context.getString(resource)
        } catch (exception: Resources.NotFoundException) {
            Timber.e("Resource not found")
            ""
        }

    }

    fun getString(@StringRes resource: Int, vararg formatArgs: String): String {
        return try {
            context.getString(resource, *formatArgs)
        } catch (exception: Resources.NotFoundException) {
            Timber.e("Resource not found")
            ""
        }
    }

    fun openRawResource(@RawRes resId: Int): InputStream {
        return resources.openRawResource(resId)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return context.getDrawable(resId)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getColor(@ColorRes resId: Int): Int {
        return context.getColor(resId)
    }

    /**
     * Get resource ID by resource name.
     *
     * @param resName Name of the resource.
     * @return Resource ID or 0 if resource was not found.
     */
    fun getResourceIdByName(resName: String): Int {
        return resources.getIdentifier(resName, null, context.packageName)
    }
}