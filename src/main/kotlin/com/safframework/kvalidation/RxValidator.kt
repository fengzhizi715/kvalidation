package com.safframework.kvalidation

import io.reactivex.Observable

/**
 * Created by tony on 2019-07-03.
 */
class RxValidator<T>(private val data: T) : Validator<T>() {

    fun toObservable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) = Observable.create<T> {
        it.onNext(data)
    }.map {
        validate(it, onSuccess = {
            success?.invoke()
        }, onError = { message ->
            error?.invoke(message)
        })
    }
}