package com.pasztork.generator

import com.pasztork.data.Size
import java.awt.image.BufferedImage

abstract class GeneratorBase() {
    lateinit var outputImage: BufferedImage
    protected lateinit var subImages: List<BufferedImage>
    private lateinit var outputSize: Size<Int>

    fun init(subImages: List<BufferedImage>, outputSize: Size<Int>) {
        this.subImages = subImages
        this.outputSize = outputSize
    }

    fun generate() {
        val gridSystem = GridSystem(
            Size(
                outputSize.x / subImages[0].width,
                outputSize.y / subImages[0].height
            )
        )
        generateImage(gridSystem)
        outputImage = gridSystem.bufferedImage
    }

    protected abstract fun generateImage(gridSystem: GridSystem)
}