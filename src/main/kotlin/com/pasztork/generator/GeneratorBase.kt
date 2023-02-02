package com.pasztork.generator

import com.pasztork.data.Size
import java.awt.image.BufferedImage

/**
 * Generator class should implement this class.
 */
abstract class GeneratorBase() {
    lateinit var outputImage: BufferedImage
    protected lateinit var subImages: List<BufferedImage>
    private lateinit var outputSize: Size<Int>

    /**
     * Sets fields that are used for generating images.
     */
    fun init(subImages: List<BufferedImage>, outputSize: Size<Int>) {
        this.subImages = subImages
        this.outputSize = outputSize
    }

    /**
     * Creates GridSystem and runs generator function.
     */
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

    /**
     * This function must be implemented by descendants.
     * This function is used to actually generate images.
     */
    protected abstract fun generateImage(gridSystem: GridSystem)
}