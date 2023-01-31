package com.pasztork.util

class NullPixelArray : PixelArray {
    override fun matches(pixelArray: PixelArray): Boolean = true
    override fun toArray(): Array<Int> = arrayOf()

}