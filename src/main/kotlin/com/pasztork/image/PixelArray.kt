package com.pasztork.image

/**
 * Generic wrapper for Array class.
 */
interface PixelArray<T> {
    /**
     * Checks if two instances are the same.
     */
    override operator fun equals(pixelArray: Any?): Boolean

    /**
     * Creates array from this class.
     */
    fun toArray(): Array<T>
}