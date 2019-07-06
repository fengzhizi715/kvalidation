package com.safframework.kvalidation.property

/**
 * Created by tony on 2019-07-06.
 */
open class ValidationProcessItem<T>(val specName: String = "", val fieldNames: List<String>)

class ValidationSpec<T>(specName: String = "",
                        fieldNames: List<String>,
                        val assertionFun: T.() -> Boolean) : ValidationProcessItem<T>(specName, fieldNames) {

    private var msgFun: ((T) -> String)? = null

    fun showMessage(target: T): String {
        val f = msgFun
        return f?.invoke(target) ?: "validation failed"
    }

    fun isValid(target: T): Boolean = assertionFun.invoke(target)


    fun errorMessage(msgFun: T.() -> String) {
        this.msgFun = msgFun
    }
}