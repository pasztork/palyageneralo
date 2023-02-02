package com.pasztork.image

interface PixelArray<T> {
    fun matches(pixelArray: PixelArray<T>): Boolean
    fun toArray(): Array<T>
}