package com.safframework.kvalidation.rxjava3

import com.safframework.kvalidation.Validator
import io.reactivex.rxjava3.core.*

/**
 *
 * @FileName:
 *          com.safframework.kvalidation.rxjava3.RxValidator
 * @author: Tony Shen
 * @date: 2022/2/19 3:54 下午
 * @version: V1.0 <描述当前版本功能>
 */
class RxValidator<T>(private val data: T) : Validator<T>() {

    fun toObservable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Observable.create<T> {
            it.onNext(data)
        }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)})
            }

    fun toFlowable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Flowable.create<T> ({
            it.onNext(data)
        }, BackpressureStrategy.BUFFER)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }

    fun toSingle(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Single.create<T> {
            it.onSuccess(data)
        }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }

    fun toMaybe(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Maybe.create<T> {
            it.onSuccess(data)
        }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }
}