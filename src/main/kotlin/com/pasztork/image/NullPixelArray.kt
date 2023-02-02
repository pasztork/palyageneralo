package com.pasztork.image

class NullPixelArray : PixelArray<Int> {
    override fun matches(pixelArray: PixelArray<Int>) = true
    override fun toArray() = arrayOf<Int>()
}