package com.jolybell.jolybellunofficial.сommon

import android.os.Bundle
import java.io.Serializable

class VersionHelper {
    companion object {
        fun <T: Serializable> Bundle.getSerializableVersion(key: String, clas: Class<T>): T?{
            return if (android.os.Build.VERSION.SDK_INT == 33){
                getSerializable(key, clas)
            }else{
                clas.cast(getSerializable(key))
            }
        }
    }
}