package com.pasztork.util

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
        cleanUp()
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
            val rotate90 = rotateRight(it)
            val rotate180 = rotateRight(rotate90)
            val rotate270 = rotateRight(rotate180)
            rotatedImages.addAll(
                mutableListOf(
                    rotate90, rotate180, rotate270
                )
            )
        }
        subImages.addAll(rotatedImages)
    }

    private fun rotateRight(source: BufferedImage): BufferedImage {
        // image will be rotated, so width and height will be changed up
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

    private fun cleanUp() {
        val uniqueImages = mutableListOf<BufferedImage>()
        subImages.forEach { bufferedImage ->
            if (uniqueImages.filter {
                    areDifferent(
                        bufferedImage,
                        it
                    )
                }.size == uniqueImages.size) {
                uniqueImages.add(bufferedImage)
            }
        }
        subImages.clear()
        subImages.addAll(uniqueImages)
    }

    private fun areDifferent(bi1: BufferedImage, bi2: BufferedImage): Boolean {
        return !getPixelValues(bi1).contentEquals(getPixelValues(bi2))
    }

    private fun getPixelValues(bi: BufferedImage): IntArray {
        val pixels = IntArray(bi.width * bi.height)
        pixels.forEachIndexed { index, _ ->
            val x = index / bi.width
            val y = index % bi.height
            pixels[index] = bi.getRGB(x, y)
        }
        return pixels
    }

    private fun saveAll() {
        subImages.forEachIndexed { index, bufferedImage ->
            ImageIO.write(bufferedImage, "PNG", File("./images/$index.png"))
        }
    }
}