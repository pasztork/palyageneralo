package com.pasztork.data

/**
 * Generic Size class, can be used with any descendant of Number.
 */
data class Size<T : Number>(val x: T, val y: T)