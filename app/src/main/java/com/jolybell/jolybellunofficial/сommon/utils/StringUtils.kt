package com.jolybell.jolybellunofficial.сommon.utils

class StringUtils {
    companion object {
        fun String.unicodeToUtf8(): String {
            var aChar: Char
            val len = length
            val outBuffer = StringBuffer(len)
            var x = 0
            while (x < len) {
                aChar = get(x++)
                if (aChar == '\\') {
                    aChar = get(x++)
                    if (aChar == 'u') {
                        var value = 0
                        for (i in 0..3) {
                            aChar = get(x++)
                            value = when (aChar) {
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.code - '0'.code
                                'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.code - 'a'.code
                                'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.code - 'A'.code
                                else -> throw IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding."
                                )
                            }
                        }
                        outBuffer.append(value.toChar())
                    } else {
                        if (aChar == 't') aChar = '\t' else if (aChar == 'r') aChar =
                            '\r' else if (aChar == 'n') aChar = '\n' else if (aChar == 'f') aChar =
                            '\u000c'
                        outBuffer.append(aChar)
                    }
                } else outBuffer.append(aChar)
            }
            return outBuffer.toString()
        }
    }
}