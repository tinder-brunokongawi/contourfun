package com.tinder.contourfun.quadpanel

import com.squareup.contour.solvers.XAxisSolver
import com.squareup.contour.solvers.YAxisSolver

data class AxesSolver(
    val x: XAxisSolver,
    val y: YAxisSolver
)