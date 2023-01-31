package com.pasztork.io

import com.pasztork.generator.ImageGenerator
import com.pasztork.util.ImageParser
import java.io.File
import javax.imageio.ImageIO

class ImageHandler(inputHandler: InputHandler) {
    init {
        val inputFile = File(inputHandler.inputPath)
        require(inputFile.exists()) {
            "The specified input image does not exist!"
        }

        val imageParser =
            ImageParser(ImageIO.read(inputFile), inputHandler.kernelSize)

        val outputImage = ImageGenerator(
            imageParser.subImages, inputHandler.outputSize
        ).outputImage
        ImageIO.write(outputImage, "PNG", File(inputHandler.outputPath))
    }
}