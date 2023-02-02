package com.pasztork.image

class IntPixelArray(private val pixelArray: Array<Int>) : PixelArray {
    override fun matches(pixelArray: PixelArray) =
        this.pixelArray.contentEquals(pixelArray.toArray())

    override fun toArray() = pixelArray
}