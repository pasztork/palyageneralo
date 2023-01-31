package com.pasztork.util

import java.awt.image.BufferedImage

fun BufferedImage.getRowAsPixelArray(rowIndex: Int): PixelArray {
    return IntPixelArray(Array(this.width) { index ->
        getRGB(index % this.width, rowIndex)
    })
}

fun BufferedImage.getColumnAsPixelArray(columnIndex: Int): PixelArray {
    return IntPixelArray(Array(this.height) { index ->
        getRGB(columnIndex, index % this.height)
    })
}