package com.safframework.kvalidation.property

/**
 * Created by tony on 2019-07-06.
 */
data class ValidationError(val specName:String,val errorMessage: String, val fieldNames: List<String>)

data class ValidationErrors(val errors: List<ValidationError>) {

    fun fieldErrors(vararg fieldNames: String) = errors.filter { it.fieldNames.containsAll(fieldNames.toList()) }

    fun singleFieldErrors(fieldName:String)= errors.filter { it.fieldNames.size ==1 && it.fieldNames.contains(fieldName)}

    fun showAllMessages()= errors.map{it.toString()}

    fun hasErrors() = errors.isNotEmpty()
}