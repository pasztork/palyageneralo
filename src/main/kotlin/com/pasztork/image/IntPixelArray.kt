package com.pasztork.image

class IntPixelArray(private val pixelArray: Array<Int>) : PixelArray<Int> {
    override fun matches(pixelArray: PixelArray<Int>) =
        this.pixelArray.contentEquals(pixelArray.toArray())

    override fun toArray() = pixelArray
}