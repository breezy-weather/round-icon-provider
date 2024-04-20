/**
 * This file is part of Breezy Weather Round Icon Provider.
 *
 * Breezy Weather Round Icon Provider is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, version 3 of the License.
 *
 * Breezy Weather Round Icon Provider is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Breezy Weather Round Icon Provider. If not, see <https://www.gnu.org/licenses/>.
 */

package org.breezyweather.roundiconprovider

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import kotlin.math.min

class SunDrawable : Drawable() {
    private val mPaint = Paint().apply {
        isAntiAlias = true
    }

    private val mCoreColor: Int = Color.rgb(255, 205, 76)
    private var mCoreRadius = 0f
    private val mHaloColors = intArrayOf(
        Color.rgb(255, 201, 62),
        Color.rgb(255, 210, 91),
        Color.rgb(255, 202, 65),
        Color.rgb(255, 209, 88),
        Color.rgb(255, 205, 76),
        Color.rgb(255, 205, 77),
        Color.rgb(255, 209, 88),
        Color.rgb(255, 202, 65)
    )
    private var mHaloMargin = 0f
    private var mHaloRadius = 0f
    private var mAlpha: Float = 1f
    private var mBounds: Rect
    private var mCX = 0f
    private var mCY = 0f

    init {
        mBounds = bounds
        ensurePosition(mBounds)
    }

    private fun ensurePosition(bounds: Rect) {
        val boundSize = min(bounds.width(), bounds.height())
        mCoreRadius = (0.6601f * boundSize) / 2.0f
        mCX = (1.0 * bounds.width() / 2 + bounds.left).toFloat()
        mCY = (1.0 * bounds.height() / 2 + bounds.top).toFloat()
        mHaloRadius = (0.0703f * boundSize) / 2.0f;
        mHaloMargin = boundSize * 0.039f;
    }

    override fun onBoundsChange(bounds: Rect) {
        mBounds = bounds
        ensurePosition(bounds)
    }

    override fun draw(canvas: Canvas) {
        mPaint.alpha = (mAlpha * 255).toInt()
        for (i in 0..3) {
            val save = canvas.save()
            canvas.rotate((i * 45).toFloat(), mCX, mCY)
            val j = i * 2
            mPaint.setColor(mHaloColors[j])
            canvas.drawCircle(
                mCX,
                mCY - mCoreRadius - mHaloMargin - mHaloRadius,
                mHaloRadius,
                mPaint
            )
            mPaint.setColor(mHaloColors[j + 1])
            canvas.drawCircle(
                mCX,
                mCY + mCoreRadius + mHaloMargin + mHaloRadius,
                mHaloRadius,
                mPaint
            )
            canvas.restoreToCount(save)
        }
        mPaint.color = mCoreColor
        canvas.drawCircle(mCX, mCY, mCoreRadius, mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mAlpha = alpha.toFloat()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.setColorFilter(colorFilter)
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun getIntrinsicWidth(): Int {
        return mBounds.width()
    }

    override fun getIntrinsicHeight(): Int {
        return mBounds.height()
    }
}
