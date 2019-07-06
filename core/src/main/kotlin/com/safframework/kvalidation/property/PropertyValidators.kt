package com.safframework.kvalidation.property

/**
 * Created by tony on 2019-07-06.
 */
open class ValidationProcessItem<T>(val specName: String = "", val fieldNames: List<String>)

open class ValidationSpec<T>(specName: String = "",
                             fieldNames: List<String>,
                             val validateFunction: T.() -> Boolean) : ValidationProcessItem<T>(specName, fieldNames) {

    private var messageFunction: ((T) -> String)? = null

    fun errorMessage(messageFunction: T.() -> String) {
        this.messageFunction = messageFunction
    }

    fun showMessage(target: T) = messageFunction?.invoke(target) ?: "validation failed"

    fun isValid(target: T): Boolean = validateFunction(target)
}

class PropertyValidator<T> (

    private val validationProcessItems: MutableList<ValidationProcessItem<T>> = mutableListOf(),
    private val fieldNames: List<String> = emptyList()) {

    fun mustBe(specName: String = "", validateFunction: T.() -> Boolean): ValidationSpec<T> {

        val spec = ValidationSpec(specName = specName, validateFunction = validateFunction, fieldNames = fieldNames)
        validationProcessItems.add(spec)
        return spec
    }

    fun fieldName(fieldName: String, block: PropertyValidator<T>.() -> Unit) {
        val fieldValidator = PropertyValidator(validationProcessItems, listOf(fieldName))
        block.invoke(fieldValidator)
    }

    fun fieldNames(vararg fieldNames: String, block: PropertyValidator<T>.() -> Unit) {
        val fieldValidator = PropertyValidator(validationProcessItems, fieldNames.toList())
        block.invoke(fieldValidator)
    }

    private fun execValidate(target: T, validateAll: Boolean = false): List<ValidationError> {

        val errors = mutableListOf<ValidationError>()

        validationProcessItems.forEach {

            when(it) {
                is ValidationSpec<T> -> {

                    if (!it.validateFunction(target)) {

                        val error = ValidationError(
                            specName = it.specName,
                            fieldNames = it.fieldNames,
                            errorMessage = it.showMessage(target)
                        )
                        errors.add(error)

                        if (!validateAll) return errors
                    }

                }
            }
        }

        return errors
    }

    fun validateAll(target: T) = ValidationErrors(execValidate(target = target, validateAll = true))

    fun validateUntilFirst(target: T) = ValidationErrors(execValidate(target = target))

    fun isValid(target: T) = !validateUntilFirst(target).hasErrors()
}