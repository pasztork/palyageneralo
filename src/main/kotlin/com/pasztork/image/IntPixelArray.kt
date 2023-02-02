package com.pasztork.image

/**
 * Wrapper class for Array<Int>
 */
class IntPixelArray(private val pixelArray: Array<Int>) : PixelArray<Int> {
    /**
     * Checks if two PixelArrays contain the same values.
     */
    override fun matches(pixelArray: PixelArray<Int>) =
        this.pixelArray.contentEquals(pixelArray.toArray())

    /**
     * Converts PixelArray to Array<Int>
     */
    override fun toArray() = pixelArray
}