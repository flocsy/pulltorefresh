package com.fletech.android.pulltorefresh

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
* Created by flocsy on 2018-01-17.
*/
class PullDownRefreshAnimationView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LottieRefreshAnimationView(context, attrs, defStyleAttr) {
    private var maxPullHeight = 0
    private var refreshTriggerHeight = 0

    override fun setup(parent: PullDownAnimation) {
        maxPullHeight = parent.MAX_PULL_HEIGHT_PX
        refreshTriggerHeight = parent.REFRESH_TRIGGER_HEIGHT_PX
//        layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setAnimation(parent.ANIMATION_ASSET_NAME)
    }

    override fun addViewToParent(parent : ViewGroup) {
        parent.addView(this, 0)
    }

    override fun onPullDownLayout(parent: View, target: View, changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        target.let {
            val height = parent.measuredHeight
            val width = parent.measuredWidth
            val left = parent.paddingLeft
            val top = parent.paddingTop
            val right = parent.paddingRight
            val bottom = parent.paddingBottom

            it.layout(left, top + it.top, left + width - right, top + height - bottom + it.top)

            //Our refresh animation is above our first child
            layout(left, -refreshTriggerHeight, width, top)
        }
    }

    override fun animateToStartPosition() : Animator? {
        return ObjectAnimator.ofInt(this, "top", -refreshTriggerHeight)
    }

    override fun animateToRefreshPosition(): Animator? {
        return ObjectAnimator.ofInt(this, "top", 0)
    }
}