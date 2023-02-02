package com.pasztork.image

interface PixelArray {
    fun matches(pixelArray: PixelArray): Boolean
    fun toArray(): Array<Int>
}