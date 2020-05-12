package com.safframework.kvalidation.rx

import com.safframework.kvalidation.Validator
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tony on 2019-07-03.
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
        Flowable.just(data)
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