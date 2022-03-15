package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createFragment(
    packageName: String,
    className: String,
    fragmentLayoutName: String,
    viewModelName: String,
    viewModelItemName: String,
    recyclerLayoutName: String,
    adapterName: String,
    originPackageName: String
) = """
package $packageName
	
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import $originPackageName.R
import $originPackageName.view.model.$viewModelName
import $originPackageName.view.adapter.$adapterName
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

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
""".trimIndent()
