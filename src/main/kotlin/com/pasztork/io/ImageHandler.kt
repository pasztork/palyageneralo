package com.pasztork.io

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageHandler(private val inputHandler: InputHandler) {
    val inputImage: BufferedImage
    val outputImage: BufferedImage

    init {
        val inputFile = File(inputHandler.inputPath)
        require(inputFile.exists()) {
            "The specified input image does not exist!"
        }
        println("Loading image...")
        inputImage = ImageIO.read(inputFile)
        println("Image loaded!")

        outputImage = BufferedImage(
            inputHandler.outputSize.x,
            inputHandler.outputSize.y,
            BufferedImage.TYPE_INT_ARGB
        )
    }

    fun saveOutput() {
        ImageIO.write(outputImage, "PNG", File(inputHandler.outputPath))
    }
}