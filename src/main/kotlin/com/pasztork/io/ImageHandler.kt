package com.pasztork.io

import com.pasztork.generator.GeneratorBase
import com.pasztork.image.ImageParser
import java.io.File
import javax.imageio.ImageIO

class ImageHandler(inputHandler: InputHandler, generator: GeneratorBase) {
    init {
        val inputFile = File(inputHandler.inputPath)
        require(inputFile.exists()) {
            "The specified input image does not exist!"
        }

        val imageParser =
            ImageParser(ImageIO.read(inputFile), inputHandler.kernelSize)

        generator.init(imageParser.subImages, inputHandler.outputSize)
        generator.generate()
        ImageIO.write(
            generator.outputImage, "PNG", File(inputHandler.outputPath)
        )
    }
}