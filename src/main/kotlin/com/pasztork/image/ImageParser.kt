package com.pasztork.image

import com.pasztork.data.Size
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageParser(
    private val inputImage: BufferedImage, private val kernelSize: Size<Int>
) {
    val subImages = mutableListOf<BufferedImage>()

    init {
        parseInputImage()
        rotateSubImages()
        removeDuplicates()
        saveAll()
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
        val rotatedImages = mutableListOf<BufferedImage>()
        subImages.forEach {
            val rotated90 = rotateRight(it)
            val rotated180 = rotateRight(rotated90)
            val rotated270 = rotateRight(rotated180)
            rotatedImages.addAll(
                mutableListOf(
                    rotated90, rotated180, rotated270
                )
            )
        }
        subImages.addAll(rotatedImages)
    }

    private fun rotateRight(source: BufferedImage): BufferedImage {
        val width = source.height
        val height = source.width
        val destination =
            BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val graphics = destination.createGraphics()
        graphics.rotate(
            Math.toRadians(90.0), width.toDouble() / 2, height.toDouble() / 2
        )
        graphics.drawImage(source, null, 0, 0)
        return destination
    }

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

    private fun areDifferent(bi1: BufferedImage, bi2: BufferedImage) =
        !getPixelValues(bi1).contentEquals(getPixelValues(bi2))

    private fun getPixelValues(bufferedImage: BufferedImage): IntArray {
        val pixels = IntArray(bufferedImage.width * bufferedImage.height)
        pixels.forEachIndexed { index, _ ->
            val x = index / bufferedImage.width
            val y = index % bufferedImage.height
            pixels[index] = bufferedImage.getRGB(x, y)
        }
        return pixels
    }

    private fun saveAll() = subImages.forEachIndexed { index, bufferedImage ->
        ImageIO.write(bufferedImage, "PNG", File("./images/$index.png"))
    }
}