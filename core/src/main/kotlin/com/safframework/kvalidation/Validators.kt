package com.safframework.kvalidation

import com.safframework.kvalidation.property.PropertyValidator

/**
 * Created by tony on 2019-07-04.
 */
fun <T> defineValidator(block: Validator<T>.() -> Unit): Validator<T> {
    val v = Validator<T>()
    block.invoke(v)
    return v
}

fun <T> T.validate(block: Validator<T>.() -> Unit): Boolean {
    val v = Validator<T>()
    block.invoke(v)
    return v.validate(this)
}

fun <T> definePropertyValidator(block: PropertyValidator<T>.() -> Unit): PropertyValidator<T> {
    val v = PropertyValidator<T>()
    block.invoke(v)
    return v
}