package com.pasztork.util

class IntPixelArray(private val pixelArray: Array<Int>) : PixelArray {
    override fun matches(pixelArray: PixelArray): Boolean =
        this.pixelArray.contentEquals(pixelArray.toArray())

    override fun toArray(): Array<Int> = pixelArray
}