package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createAdapter(
    packageName: String,
    path: String,
    className: String,
    dataName: String,
    adapterLayoutName: String,
    originPackageName: String
) = """
package $packageName$path

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList
import $originPackageName.R
import $packageName.data.$dataName

class ${className}Adapter(
    private val context: Context,
    var items: ArrayList<$dataName> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ${className}ViewHolder(view: View) : BindHolder(view) {
        override fun bind(data: $dataName) {
            with(itemView) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ${className}ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.$adapterLayoutName, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].let {
            (holder as? BindHolder)?.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    abstract class BindHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: $dataName)
    }
}
""".trimIndent()