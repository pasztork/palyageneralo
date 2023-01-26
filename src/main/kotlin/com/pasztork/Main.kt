package com.pasztork

import com.pasztork.io.InputHandler

fun main(args: Array<String>) {
    InputHandler(args).also {
        println(it)
    }
}