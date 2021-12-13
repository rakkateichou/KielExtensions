package com.spevsand.kielextensions

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import me.ibrahimyilmaz.kiel.core.AdapterBuilder
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

inline fun <reified T : Any, reified B : ViewBinding> AdapterBuilder<in T>.reg(
    @LayoutRes layoutRes: Int,
    crossinline provBind: (view: View) -> B,
    noinline onBind: (bg: B, i: Int, data: T) -> Unit,
) =
    register(
        layoutResource = layoutRes,
        viewHolder = { object : RecyclerViewHolder<T>(it) {} },
        onBindViewHolder = { param: RecyclerViewHolder<T>, i: Int, t: T ->
            onBind(provBind(param.itemView), i, t)
        }
    )