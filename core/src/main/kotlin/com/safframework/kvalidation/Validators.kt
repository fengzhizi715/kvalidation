package com.safframework.kvalidation

import com.safframework.kvalidation.rx.RxValidator

/**
 * Created by tony on 2019-07-04.
 */
fun <T> defineValidator(block: Validator<T>.() -> Unit): Validator<T> {
    val v = Validator<T>()
    block.invoke(v)
    return v
}

fun <T> defineRxValidator(data: T, block: RxValidator<T>.() -> Unit): RxValidator<T> {
    val v = RxValidator<T>(data)
    block.invoke(v)
    return v
}