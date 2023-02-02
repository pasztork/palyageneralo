package com.pasztork.image

/**
 * Wrapper for Array<Int>.
 */
class NullPixelArray : PixelArray<Int> {
    /**
     * Returns true.
     */
    override fun equals(pixelArray: Any?) = pixelArray is PixelArray<*>

    /**
     * Returns empty array of integers.
     */
    override

    fun toArray() = arrayOf<Int>()
}