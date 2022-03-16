package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createListFragment(
    packageName: String,
    path: String,
    className: String,
    fragmentLayoutName: String,
    viewModelName: String,
    viewModelItemName: String,
    recyclerLayoutName: String,
    adapterName: String,
    originPackageName: String
) = """
package $packageName$path
	
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import $originPackageName.R
import $packageName.view.model.$viewModelName
import $packageName.view.adapter.$adapterName
import kotlinx.android.synthetic.main.$fragmentLayoutName.*

class ${className}Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.$fragmentLayoutName, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        $viewModelName.getInstance(requireContext())?.let { vm ->
            vm.$viewModelItemName.observe(viewLifecycleOwner) { item ->
                $recyclerLayoutName.adapter = $adapterName(requireContext(), item)
            }
        }
    }
}
""".trimIndent()
