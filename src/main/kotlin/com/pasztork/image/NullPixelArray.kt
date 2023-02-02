package com.pasztork.image

/**
 * Wrapper for Array<Int>.
 */
class NullPixelArray : PixelArray<Int> {
    /**
     * Returns true.
     */
    override fun matches(pixelArray: PixelArray<Int>) = true

    /**
     * Returns empty array of integers.
     */
    override fun toArray() = arrayOf<Int>()
}