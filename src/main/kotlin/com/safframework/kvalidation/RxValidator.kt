package com.safframework.kvalidation

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tony on 2019-07-03.
 */
class RxValidator<T>(private val data: T) : Validator<T>() {

    fun toObservable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Observable.just(data)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)
                })
            }

    fun toFlowable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Flowable.just(data)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)
                })
            }

    fun toSingle(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Single.just(data)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)
                })
            }

    fun toMaybe(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Maybe.just(data)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)
                })
            }
}