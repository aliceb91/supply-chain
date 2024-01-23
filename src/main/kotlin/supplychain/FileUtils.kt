package supplychain

import java.io.File

class FileUtils {

    open fun readFromPath(filePath: String): String {
        return this::class.java.getResource(filePath)?.readText() ?: "[]"
    }
}