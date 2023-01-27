package com.pasztork.io

import com.pasztork.data.Size

class InputHandler(args: Array<String>) {
    val inputPath: String
    val outputPath: String
    val kernelSize: Size<Int>
    val outputSize: Size<Int>

    init {
        require(args.filter {
            it.contains("in=") || it.contains("out=") || it.contains(
                "kernel-size="
            ) || it.contains("output-size=")
        }.size == 4) {
            """Something is missing! Expected format:
                in=source-image-path out=target-image-path kernel-size=64x64 
                output-size=1024x1024""".trim()
        }

        inputPath = args.first { it.contains("in=") }.substringAfter("in=")
        require(inputPath.isNotEmpty())

        outputPath = args.first { it.contains("out=") }.substringAfter("out=")
        require(outputPath.isNotEmpty())

        val kernelSizeList = args.first { it.contains("kernel-size=") }
            .substringAfter("kernel-size=").split("x").map(String::toInt)
        require(kernelSizeList.size >= 2)
        kernelSize = Size(kernelSizeList[0], kernelSizeList[1])

        val outputSizeList = args.first { it.contains("output-size=") }
            .substringAfter("output-size=").split("x").map(String::toInt)
        require(outputSizeList.size >= 2)
        outputSize = Size(outputSizeList[0], outputSizeList[1])

        require(
            outputSize.x % kernelSize.x == 0 && outputSize.y % kernelSize.y == 0
        ) {
            "The kernel and output size you provided do not work together!"
        }
    }

    override fun toString(): String {
        return """
            |in=$inputPath
            |out=$outputPath
            |kernel-size=${kernelSize.x}x${kernelSize.y}
            |output-size=${outputSize.x}x${outputSize.y}
        """.trimMargin()
    }
}