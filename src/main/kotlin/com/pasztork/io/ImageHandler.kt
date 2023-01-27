package com.pasztork.io

import com.pasztork.util.ImageParser
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageHandler(private val inputHandler: InputHandler) {
    val outputImage: BufferedImage
    val imageParser: ImageParser

    init {
        val inputFile = File(inputHandler.inputPath)
        require(inputFile.exists()) {
            "The specified input image does not exist!"
        }

        imageParser =
            ImageParser(ImageIO.read(inputFile), inputHandler.kernelSize)

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