package com.safframework.kvalidation.rxjava3.extension

import com.safframework.kvalidation.rxjava3.RxValidator


/**
 *
 * @FileName:
 *          com.safframework.kvalidation.rxjava3.extension.`Validators+Extension`
 * @author: Tony Shen
 * @date: 2022/2/19 3:38 下午
 * @version: V1.0 <描述当前版本功能>
 */
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