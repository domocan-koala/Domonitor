package inside.koala.kotlinpowaaaa.Pager

//region Imports
import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller
//endregion

class FixedSpeedScroller : Scroller {

    private var _duration = 500

    constructor(context: Context) : super(context) {}

    constructor(context: Context, interpolator: Interpolator) : super(context, interpolator) {}

    constructor(context: Context, interpolator: Interpolator, flywheel: Boolean) : super(context, interpolator, flywheel) {}

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, _duration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, _duration)
    }

    fun setScrollDuration(duration: Int) {
        _duration = duration
    }
}