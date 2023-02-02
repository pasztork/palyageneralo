package com.pasztork.image

/**
 * Generic wrapper for Array class.
 */
interface PixelArray<T> {
    /**
     * Checks if two instances are the same.
     */
    fun matches(pixelArray: PixelArray<T>): Boolean

    /**
     * Creates array from this class.
     */
    fun toArray(): Array<T>
}