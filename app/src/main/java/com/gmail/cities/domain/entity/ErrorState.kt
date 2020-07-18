package com.gmail.cities.domain.entity

/**
 * A mapping from ErrorCode class. The set of params
 * supported by this class is purposefully restricted to the ones that can
 * be used on UI.
 * @property code  the code of error
 * @property message  a detailed message of the error
 */
class ErrorState(val code: Int, val message: String)
