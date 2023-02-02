package com.pasztork.image

import com.pasztork.data.Size
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Cuts out pieces of the input pattern.
 */
class ImageParser(
    private val inputImage: BufferedImage, private val kernelSize: Size<Int>
) {
    val subImages = mutableListOf<BufferedImage>()

    init {
        parseInputImage()
        rotateSubImages()
        removeDuplicates()
        // uncomment if you want to see what sub-images the program works with
        // saveAll()
    }

    /**
     * Cuts pieces out of input pattern.
     */
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

    /**
     * Rotates every image that was cut out of the pattern.
     */
    private fun rotateSubImages() {
        val rotatedImages = mutableListOf<BufferedImage>()
        subImages.forEach {
            val rotated90 = it.rotateRight()
            val rotated180 = rotated90.rotateRight()
            val rotated270 = rotated180.rotateRight()
            rotatedImages.addAll(
                mutableListOf(
                    rotated90, rotated180, rotated270
                )
            )
        }
        subImages.addAll(rotatedImages)
    }

    /**
     * Removes any duplicate images. (i.e. one colored images)
     */
    private fun removeDuplicates() {
        val uniqueImages = mutableListOf<BufferedImage>()
        subImages.forEach { bufferedImage ->
            if (uniqueImages.filter {
                    areDifferent(bufferedImage, it)
                }.size == uniqueImages.size) {
                uniqueImages.add(bufferedImage)
            }
        }
        subImages.clear()
        subImages.addAll(uniqueImages)
    }

    /**
     * Checks if two images are different.
     */
    private fun areDifferent(bi1: BufferedImage, bi2: BufferedImage) =
        !bi1.getPixelValuesAsArray().contentEquals(bi2.getPixelValuesAsArray())

    /**
     * Saves all sub-images to ./images directory.
     */
    private fun saveAll() = subImages.forEachIndexed { index, bufferedImage ->
        ImageIO.write(bufferedImage, "PNG", File("./images/$index.png"))
    }
}