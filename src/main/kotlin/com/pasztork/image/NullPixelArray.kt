package com.pasztork.image

class NullPixelArray : PixelArray {
    override fun matches(pixelArray: PixelArray) = true
    override fun toArray() = arrayOf<Int>()
}