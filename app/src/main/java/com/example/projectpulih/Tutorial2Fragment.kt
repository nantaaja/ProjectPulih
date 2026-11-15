package com.example.projectpulih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class Tutorial2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tutorial2, container, false)
        view.findViewById<MaterialButton>(R.id.btnSkip)?.setOnClickListener {
            requireActivity().finish()
        }
        return view
    }
}
