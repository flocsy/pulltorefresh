package com.fletech.android.pulltorefresh

import android.animation.Animator
import com.fletech.android.pulltorefresh.pullRatio.PullRatio

/**
 * Created by flocsy on 13/01/2018.
 */
interface PullDownAnimation {
    val MAX_PULL_HEIGHT_PX: Int
    val REFRESH_TRIGGER_HEIGHT_PX: Int
    val ANIMATION_ASSET_NAME: String
    var pullRatio : PullRatio
    var onRefreshListener: (() -> Unit?)?
    fun onRefreshStarted()
    fun onRefreshContinued()
    fun onRefreshFinished()
    fun addAnimatorListener(listener: Animator.AnimatorListener)
    fun removeAnimatorListener(listener: Animator.AnimatorListener)
}
