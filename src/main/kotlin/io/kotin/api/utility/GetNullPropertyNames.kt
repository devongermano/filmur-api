package io.kotin.api.utility

import org.springframework.beans.BeanWrapperImpl
import java.util.HashSet

fun getNullPropertyNames(source: Any): Array<String> {
    val src = BeanWrapperImpl(source)
    val pds = src.propertyDescriptors

    val emptyNames = HashSet<String>()
    for (pd in pds) {
        val srcValue = src.getPropertyValue(pd.name)
        if (srcValue == null) emptyNames.add(pd.name)
    }
    val result = arrayOfNulls<String>(emptyNames.size)
    return emptyNames.toTypedArray()
}