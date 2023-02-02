package com.pasztork.image

/**
 * Wrapper class for Array<Int>
 */
class IntPixelArray(private val pixelArray: Array<Int>) : PixelArray<Int> {
    /**
     * Checks if two PixelArrays contain the same values.
     */
    override fun equals(pixelArray: Any?) =
        this.pixelArray.contentEquals((pixelArray as PixelArray<Int>).toArray())

    /**
     * Converts PixelArray to Array<Int>
     */
    override fun toArray() = pixelArray
}