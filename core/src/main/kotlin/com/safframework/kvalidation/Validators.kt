package com.safframework.kvalidation

import com.safframework.kvalidation.property.PropertyValidator
import com.safframework.kvalidation.rx.RxValidator

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

fun <T> defineRxValidator(data: T, block: RxValidator<T>.() -> Unit): RxValidator<T> {
    val v = RxValidator<T>(data)
    block.invoke(v)
    return v
}

fun <T> T.rxValidator(block: RxValidator<T>.() -> Unit): RxValidator<T> {
    val v = RxValidator<T>(this)
    block.invoke(v)
    return v
}

fun <T> definePropertyValidator(block: PropertyValidator<T>.() -> Unit): PropertyValidator<T> {
    val v = PropertyValidator<T>()
    block.invoke(v)
    return v
}