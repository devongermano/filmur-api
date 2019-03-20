package io.kotin.api.utility

import org.springframework.beans.BeanUtils


fun copyNonNullProperties(src: Any, target: Any) {
    BeanUtils.copyProperties(src, target, *getNullPropertyNames(src))
}