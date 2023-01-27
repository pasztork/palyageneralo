package com.pasztork.util

import com.pasztork.data.Size
import java.awt.image.BufferedImage

class ImageParser(
    private val inputImage: BufferedImage, private val kernelSize: Size<Int>
) {
    val subImages = mutableListOf<BufferedImage>()

    init {
        parseInputImage()
        rotateSubImages()
    }

    private fun parseInputImage() {
        val width = inputImage.width
        val height = inputImage.height
        require(width % kernelSize.x == 0 && height % kernelSize.y == 0) {
            "The kernel size you provided is invalid"
        }
        for (x in 0 until width / kernelSize.x) {
            for (y in 0 until height / kernelSize.y) {
                subImages.add(
                    inputImage.getSubimage(
                        x * kernelSize.x,
                        y * kernelSize.y,
                        kernelSize.x,
                        kernelSize.y
                    )
                )
            }
        }
    }

    private fun rotateSubImages() {
    }
}