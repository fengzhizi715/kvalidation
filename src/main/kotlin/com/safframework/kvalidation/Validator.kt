package com.safframework.kvalidation

/**
 * Created by tony on 2019-07-03.
 */
open class Validator<T> : LinkedHashSet<ValidateRule<T>>() {

    fun validate(data: T,
                      onSuccess: (() -> Unit)? = null,
                      onError: ((String) -> Unit)? = null): Boolean {

        if (data == null) {
            return false
        }

        forEach {
            if (!it.validate(data)) {
                onError?.invoke(it.errorMessage())
                return false
            }
        }

        onSuccess?.invoke()
        return true
    }

    infix fun addRule(rule: ValidateRule<T>): Validator<T> {
        add(rule)
        return this
    }
}
