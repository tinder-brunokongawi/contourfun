package com.tinder.contourfun.quadpanel

import android.content.Context
import com.squareup.contour.ContourLayout
import com.squareup.contour.LayoutContainer
import com.squareup.contour.XInt
import com.squareup.contour.YInt
import java.util.Collections
import kotlin.math.pow

abstract class BaseQuadPanel<T>(context: Context) : ContourLayout(context) {

    companion object {
        // For calculating size based on the golden ratio
        internal val PHI_POW_5 = PHI.pow(5)

        internal const val COLUMN = 2
        internal const val ROW = 2
        internal const val MAX_NUM_CARD = 5

        internal const val TRANSITION_ANIMATION_DURATION = 500L
        internal const val OVERSHOOT_TENSION = 1F
    }

    protected val cards = mutableListOf<BaseCard<T>>()
    protected val focusedAxesSolver = AxesSolver(
        x = centerHorizontallyTo { parent.centerX() }.widthOf { parent.width() },
        y = topTo { parent.top() }.heightOf { getBottomOfFocused() }
    )
    private val leftTopAxesSolver = AxesSolver(
        x = leftTo { parent.left() }.widthOf { getMinimizedCardWidth() },
        y = topTo { getBottomOfFocused() }.heightOf { getMinimizedCardHeight() }
    )
    private val rightTopAxeSolver = AxesSolver(
        x = rightTo { parent.right() }.widthOf { getMinimizedCardWidth() },
        y = topTo { getBottomOfFocused() }.heightOf { getMinimizedCardHeight() }
    )
    private val leftBottomAxeSolver = AxesSolver(
        x = leftTo { parent.left() }.widthOf { getMinimizedCardWidth() },
        y = bottomTo { parent.bottom() }.heightOf { getMinimizedCardHeight() }
    )
    private val rightBottomAxeSolver = AxesSolver(
        x = rightTo { parent.right() }.widthOf { getMinimizedCardWidth() },
        y = bottomTo { parent.bottom() }.heightOf { getMinimizedCardHeight() }
    )
    /*
         ______________________
        |     focused card     |
         ______________________
        | left top | right top |
         ______________________
        | left bot | right bot |
         ______________________
     */
    protected val axesSolvers = mutableListOf(
        focusedAxesSolver, // top/focused card
        leftTopAxesSolver, rightTopAxeSolver,
        leftBottomAxeSolver, rightBottomAxeSolver
    )
    protected var selected = 0

    init {
        contourHeightMatchParent()
        contourWidthMatchParent()
        layoutCards()
    }

    // responsible for creating card
    abstract fun createCard(index: Int): BaseCard<T>

    // initial layout of cards
    private fun layoutCards() {
        for (x in 0 until MAX_NUM_CARD) {
            cards.add(createCard(x))
        }
        cards.forEachIndexed { index, card ->
            card.layoutBy(
                x = axesSolvers[index].x,
                y = axesSolvers[index].y
            )
        }
    }

    abstract fun focusOn(index: Int)

    private fun LayoutContainer.getBottomOfFocused(): YInt {
        return (parent.height().value * PHI - parent.height().value * PHI_POW_5).toInt()
            .toYInt()
    }

    private fun LayoutContainer.getMinimizedCardWidth(): XInt {
        return parent.width() / COLUMN
    }

    private fun LayoutContainer.getMinimizedCardHeight(): YInt {
        return (parent.bottom() - getBottomOfFocused()) / ROW
    }

    protected fun <T> MutableList<T>.swap(indexOne: Int, indexTwo: Int) {
        Collections.swap(this, indexOne, indexTwo)
    }
}