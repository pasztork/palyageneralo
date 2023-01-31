package com.pasztork.util

interface PixelArray {
    fun matches(pixelArray: PixelArray): Boolean
    fun toArray(): Array<Int>
}