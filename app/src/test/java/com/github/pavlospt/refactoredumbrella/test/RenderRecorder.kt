package com.github.pavlospt.refactoredumbrella.test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.util.concurrent.CopyOnWriteArrayList

class RenderRecorder<T : Any?> {

    val renders: CopyOnWriteArrayList<T> = CopyOnWriteArrayList()

    fun add(render: T) {
        renders.add(render)
    }

    fun assertRenders(vararg expectedList: T) = apply {
        assertEquals(expectedList.size, renders.size)

        expectedList.forEachIndexed { index, expectedItem ->
            val actualItem = renders[index]
            assertEquals(expectedItem, actualItem)
        }
    }

    fun assertRenderedOnce() {
        assertEquals(1, renders.size)
    }

    fun assertRenderedTimes(times: Int) {
        assertEquals(times, renders.size)
    }

    fun assertRenderedAtLeastOnce() {
        assertTrue(renders.size >= 1)
    }

    fun assertNoRenders() = apply {
        assertEquals(0, renders.size)
    }

    fun reset() = apply {
        renders.clear()
    }
}
