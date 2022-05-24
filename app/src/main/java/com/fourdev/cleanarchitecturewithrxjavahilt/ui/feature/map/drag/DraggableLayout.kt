package com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.drag

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class DraggableLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
):FrameLayout(context, attrs, defStyleAttr) {

    var lastTouch : Long = 0
    private val scrollTime = 200L

    private var iDragCallback: IDragCallback? = null

    fun setDragCallback(iDragCallback: IDragCallback) {
        this.iDragCallback = iDragCallback
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouch = SystemClock.uptimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                if(SystemClock.uptimeMillis() - lastTouch > scrollTime) {
                    iDragCallback?.onDrag()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}