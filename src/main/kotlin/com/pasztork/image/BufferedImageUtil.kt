package com.pasztork.image

import java.awt.image.BufferedImage

fun BufferedImage.getRowAsPixelArray(rowIndex: Int): PixelArray =
    IntPixelArray(Array(this.width) { getRGB(it % this.width, rowIndex) })

fun BufferedImage.getColumnAsPixelArray(columnIndex: Int): PixelArray =
    IntPixelArray(Array(this.height) { getRGB(columnIndex, it % this.height) })