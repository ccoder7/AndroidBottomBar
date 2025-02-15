/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.skydoves.androidbottombar.forms

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import com.skydoves.androidbottombar.annotations.Dp
import com.skydoves.androidbottombar.extensions.contextColor
import com.skydoves.androidbottombar.extensions.dp2Px
import com.skydoves.androidbottombar.extensions.resourceDrawable

@DslMarker
internal annotation class IconFormDsl

/** creates an instance of [IconForm] from [IconForm.Builder] using kotlin dsl. */
@IconFormDsl
@JvmSynthetic
inline fun iconForm(context: Context, block: IconForm.Builder.() -> Unit): IconForm =
  IconForm.Builder(context).apply(block).build()

/**
 * IconForm is an attribute class that has icon attributes
 * for customizing menu item icons easily.
 */
data class IconForm(

  var icon: Drawable? = null,
  @Px var iconSize: Int = 28,
  @ColorInt var iconColor: Int = Color.WHITE,
  @ColorInt var iconActiveColor: Int = Color.WHITE

) {

  /** Builder class for [IconForm]. */
  @IconFormDsl
  data class Builder(private val context: Context) {

    @JvmField
    var icon: Drawable? = null

    @Px
    @JvmField
    var iconSize: Int = context.dp2Px(28)

    @ColorInt
    @JvmField
    var iconColor: Int = Color.WHITE

    @ColorInt
    @JvmField
    var iconActiveColor: Int = Color.WHITE

    /** sets the [Drawable] of the icon. */
    fun setIcon(value: Drawable?) = apply { this.icon = value }

    /** sets the [Drawable] of the icon using resource. */
    fun setIcon(@DrawableRes value: Int) = apply {
      this.icon = context.resourceDrawable(value)
    }

    /** sets the size of the icon. */
    fun setIconSize(@Dp value: Int) = apply { this.iconSize = context.dp2Px(value) }

    /** sets the color of the icon. */
    fun setIconColor(@ColorInt value: Int) = apply { this.iconColor = value }

    /** sets the color of the icon using resource */
    fun setIconColorRes(@ColorRes value: Int) = apply {
      this.iconColor = context.contextColor(value)
    }

    /** sets the active color of the icon. */
    fun setIconActiveColor(@ColorInt value: Int) = apply { this.iconActiveColor = value }

    /** sets the active color of the icon using resource */
    fun setIconActiveColorRes(@ColorRes value: Int) = apply {
      this.iconActiveColor = context.contextColor(value)
    }

    fun build() = IconForm(icon, iconSize, iconColor, iconActiveColor)
  }
}
