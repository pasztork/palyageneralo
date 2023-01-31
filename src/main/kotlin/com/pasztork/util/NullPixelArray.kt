package com.pasztork.util

class NullPixelArray : PixelArray {
    override fun matches(pixelArray: PixelArray) = true
    override fun toArray() = arrayOf<Int>()
}