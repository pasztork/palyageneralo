package com.pasztork

import com.pasztork.generator.NaiveGenerator
import com.pasztork.io.ImageHandler
import com.pasztork.io.InputHandler

/**
 * Entrance point of the program.
 */
fun main(args: Array<String>) {
    ImageHandler(InputHandler(args), NaiveGenerator())
}